# BOOT_NOTES (agent "boot", 2026-10-19)

Goal: the deobfuscated Prestige client boots under Fabric (dev client, `./gradlew runClient`), `Prestige.init()` completes
and the UI bridge binds to the live modules.

## Findings (root causes, in order of discovery)
1. **Two copies of the client were loaded.** `build.gradle` has `implementation files("client/work/client-runtime.jar")`
   (a Yarn-named jar on the dev classpath). A jar in `run/mods` is a *second* copy (loaded as a mod and remapped
   intermediary->named by Fabric Loader at runtime). Whichever won the race was used, so every earlier "fix" that only
   went into `run/mods` seemed flaky (the log showed classes loaded from `client/work/client-runtime.jar`, static keys = 0).
   Decision: **the classpath jar `client/work/client-runtime.jar` is the one used by the dev client; `run/mods` stays empty.**
   (If you want to use `run/mods` instead, the jar must be INTERMEDIARY-named incl. intermediary AW: `work/client-boot.jar`,
   and `build.gradle`'s `implementation files(...)` line must be removed. Loom's loader remaps mods-folder jars itself.)
2. **ZKM-like key generator is stateful.** Every class (358 of them) computes its decryption key in `<clinit>` through
   `hc.a(seed1, seed2, lookupClass).a(const)` (`hb`/`hc` = an interpreter with shared, mutable cached objects:
   `this.a = a ^ arg ^ d`, instance cache sorted by `identityHashCode`...). The result depends on **how often / in which order the
   generator was called before**, so the values obtained under Fabric (different class-init order) differ from the values
   the isolated child JVMs of `IndyResolve` see (e.g. `cJ.g` = 0 / 182876734801923 instead of 22310984220293).
   Wrong key -> wrong table index -> `Index 184919 out of bounds for length 81` in `bO.a`.
3. The old `IndyResolve` "clinit cut" (a `goto` over the table initialisation) was *not* the real problem for these classes
   (only for the tables' decoded content); but it also removed the table init, so it is now disabled for the runtime jar.
4. `FixFlow` recomputed frames with `getCommonSuperClass -> Object` fallback => `VerifyError` (`Object` not assignable to `PlayerEntity`).
   Now it resolves common super classes through a URLClassLoader over MC + libs + the jar.

## Fix (pipeline `client/tools/build_runtime.sh`, replaces the previous ad-hoc jars)
* `IndyResolve -Dno.clinitcut=true`: no clinit cut. Instead `keyFix()` replaces, in every `<clinit>`,
  `ldc s1; ldc s2; MethodHandles.lookup().lookupClass(); hc.a(JJObject); ldc c; ha.a(J)J`  by  `ldc <value recorded by the isolated child run>`
  (`IndyRec.recH` -> `info.rec["H"+ordinal]`). Result: 357 of 358 classes get the constant; the whole table init (`h`/`i` arrays,
  `a()V`, DES decrypt, `ldc` tables) stays intact and runs at run time. The 1 remaining class is `gf` (no child value; see below).
* Module constructors are hooked: `PatchBootstrap` inserts `dev.prestige.ui.bridge.Bootstrap.pre(this)` after `super()` in all 122
  subclasses of `dV` (the native module normally injects the settings before the ctor body runs). 
* `FixFlow` (frames, `getCommonSuperClass` via real classes) -> `work/client-boot.jar` (intermediary-named, usable in a mods folder
  for Prism, with the Bootstrap hook; the hook class lives in `prestige-ui`).
* tiny-remapper intermediary->named + `fix_aw.py` -> `work/client-runtime.jar` (Yarn-named; this is what Gradle uses).
* `-Xverify:all` check of all 708 classes standalone: `client/work/t2/V.java` (ok=708 bad=0).

## Bridge
* `Bridge.bind()` is called from the first client tick (`Entry`). The manager (`Prestige.d`, class `cJ`) list is empty after
  `Prestige.init()` (modules are registered by the native module), so `Bridge.buildModules` constructs every class of
  `src/main/resources/prestige_ui/manifest_live.json` (copied from `liberate/work/deobf/manifest_live.json`), calls `Bootstrap.post(m)`
  and registers the list with the manager's `a(List)` setter. Bootstrap = port of `work/deobf/gui/zkminit/Bootstrap.java`
  (package `dev.prestige.ui.bridge`).

## Run
```
bash client/tools/build_runtime.sh          # ~2 min; writes work/client-boot.jar (intermediary) + work/client-runtime.jar (yarn)
bash client/tools/boot_test.sh 200          # kills stale dev clients, runs ./gradlew runClient (timeout), copies the log to run/logs/boot_*.log
```
Log lines to look for: `[prestige-ui] bridge bound: 121 modules`.

## Result (verified 15:20)
* `Prestige.init()` completes (no STDERR in the log), `Prestige.d` manager exists; its module list is empty (native registers it).
* `[prestige-ui] bridge: built 121 modules from manifest, failed 0` / `[prestige-ui] bridge bound: 121 modules`
  (log: `client/work/boot_success_latest.log`).
* UI click test (`run/cmd/ui.txt` command file, `-PuiCmd=`; new dev commands `pointmod <module>` / `state <module>`, `ui/DevCmd.java`):
  `Aim Assist ui=false live=false` -> click on its toggle -> `ui=true live.a()=true enabledSetting.value=true` -> click -> false -> click -> true.
  Screenshot `client/work/boot_toggle_on.png` (header "1 enabled", Aim Assist switch on). Note: the very first click after opening
  is sometimes ignored (earlier run); a 40-frame wait after `open` makes it reliable.

## Not done / caveats
* Class `gf` has no recorded key value (child run produced no value) -> its `<clinit>` still calls the stateful `hc` generator
  (`KEYFIX-NOVALUE dev/zprestige/prestige/gf 0` in `work/boot_indy.report.txt`). Not hit during boot; check when it is used.
* Module *behaviour* (onEnable hooks, event bus dispatch, rendering) is not verified; only construction + enable flag round trip.
* The enable flag round trip goes through the module's `dM` setting (`Bridge.LiveModule.setEnabled`); `onEnable`/`onDisable` side
  effects depend on how the original dM listener fires (not checked).
* `run/mods` is empty on purpose (see Finding 1). `work/client-runtime.pre-boot.jar` is the previous classpath jar (backup).
* Source tree compile (client/src) was not touched.

## Update (agent "rename", 2026-10-19 16:45)
* API surface renamed by bytecode: `docs/mapping.tsv` (applied by `tools/rename.sh` = tiny-remapper + `Widen`, called from `build_runtime.sh` and
  `build_source.sh`). dV->Module, i->Category, dK->Setting, dU->SettingHolder, dM/dN/dO/dP/dQ/dR/dS/dL/dT -> Bool/Color/Float/Int/Range/Enum/Multi/
  Keybind/TextSetting, bO->EventBus, cJ->ModuleManager, e_->MenuModule, q->Gamemode, bP->Subscribe, Prestige.b/d -> eventBus/moduleManager,
  plus the fields/methods the UI uses (name/description/value/min/max/low/high/options/selected/toggleMode, getModules/setModules, isEnabled ...).
  The 121 module classes are mapped to `dev.zprestige.prestige.module.<category>.<PascalCaseName>` (section generated by `tools/gen_module_mapping.py`).
* Bridge/Bootstrap use the new names; manifest class/prop names are rewritten from `docs/manifest_live.obf.json` by `tools/rename_manifest.py`.
* Evidence: `work/boot_rename_success.log` (API rename, jar route), `work/boot_jar_modules_renamed.log` (+ module classes renamed),
  `work/boot_source_modules_renamed.log` (source route, `-PwithClient=true`); all show `bridge bound: 121 modules` and the Aim Assist toggle round trip.
* Source route: see `TODO_COMPILE.md`.
