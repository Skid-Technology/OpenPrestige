# Handoff: client source refactor (packages + class names)

## Current state

The catastrophic part is done. All 400 obfuscated helper classes have been moved out of the root package into
18 meaningful sub-packages, and the source tree compiles and builds.

- Source tree: `project/client/src/main/java/dev/zprestige/prestige`
- 543 Java classes total:
  - 21 already-named API classes intentionally left at the root (`Module`, `Setting`, `SettingHolder`, the 9
    setting types, `EventBus`, `Category`, `ModuleManager`, `MenuModule`, `Gamemode`, `Native`, `Constants`,
    `Subscribe`, `ListenerEntry`)
  - `client/Prestige`
  - 121 modules under `module/<category>/`
  - 400 renamed helpers under `event/ hud/ render/ data/ type/ mixin/ world/ math/ combat/ inventory/ network/
    reflect/ gui/ config/ util/ social/ annotation/ input/`
- Zero obfuscated class names remain at the root. Verified by listing the renamed jar: the root contains only the
  21 API classes.
- `./gradlew.bat build -PwithClient=true` succeeds (`BUILD SUCCESSFUL`, one deprecation warning about
  `ThreadDeath`). Artifacts: `project/build/libs/prestige-0.1.0.jar`,
  `prestige-0.1.0-sources.jar`.
- **Boot has not been tested yet.** That is the first remaining step.

## What was changed

### New files (`project/client/tools/`)

- `gen_class_mapping.py` — generates `docs/classmap.tsv` (400 `C old new` rows) from the decompiled source by
  classifying superclass/interface/mixin target/HUD description/shader path/record components/enum constants/
  module references.
- `merge_mapping.py` — merges `docs/mapping.tsv` + `docs/classmap.tsv` into `work/rename.map.tsv`, refusing
  duplicate owners.
- `clsmapping.py` — shared lookup used by the fixers:
  - `Mapping(srcdir).path(old_source_name)` -> real path after renames
  - `Mapping.source_to_new` -> class-token map for source snippets
  - `rewrite_tokens()` — token rewrite that skips string literals and fully-qualified package paths

### Changed files

- `client/tools/rename.sh`
  - now merges `docs/classmap.tsv` into the mapping before running tiny-remapper and `rename_manifest.py`.
  - The class map therefore applies to both routes (source regeneration and runtime jar).
- `client/tools/fix_reflect.py`
  - resolves `cU`/`cW`/`EventBus` through `clsmapping` instead of hardcoded root paths
  - uses the renamed `Event.class` instead of `aH.class`
- `client/tools/fix_ctor.py`
  - constructor-noise regex accepts both `aH.b();` and `Event.b();`
- `client/tools/manual_edits.py`
  - uses `clsmapping.Mapping`; snippets are token-rewritten outside string literals
  - removed one obsolete `PostShader` edit (the `i_cF` bridge no longer exists)
  - the restored `TextRenderer` bridge now implements `a_WorldContract` (the method name generated after the
    class rename)
- `client/tools/notry.txt`
  - `ConfigManager.a(...)Lcom/google/gson/JsonObject;` updated to the new FQN. Without this the config loader
    fails to decompile again.
- `docs/classmap.tsv` — 400 generated class rows (frozen; treat as source of truth).
- `docs/classmap_src.tsv` — source-tree name -> new internal name, used by `clsmapping.py` to survive the
  `_`-suffixed case-collision twins (`cg_` is jar `cg`, `d_` is jar `d`). Also frozen.

### Naming decisions

- Events: `event/Event` (base `aH`), 70 `*Event` subclasses. Fieldless events are `TickEvent`, `TickEvent2`, ...
- HUD: `hud/HudElement` (base `b4`), names derived from the `super("Name", ...)` string, e.g.
  `ModuleListElement`, `WatermarkElement`.
- Mixins: `mixin/<Target>Mixin` from `@CTransformer`, e.g. `MinecraftClientMixin`, `EntityMixin`,
  `InGameHudMixin`. Duplicate targets get `2`, `3`, `4`.
- Shaders: `render/<Name>Shader` from the `.frag` path (`AuraOutlineShader`, `ChamsOutlineShader`, ...).
- Enums: `type/*` with semantic overrides (`BowMode`, `AnchorState`, `CartState`, `GlPrimitive`, `Easing`, ...).
- Records/data: `data/*` (`IntData`, `LongData`, `VecIntData`, ...).
- API classes stay at root on purpose. Moving them would require editing hardcoded FQNs in
  `src/main/java/dev/prestige/ui/bridge/Bootstrap.java` and `Bridge.java`, and gains nothing for this task.

## Important caveats

- **Do not blindly re-run `gen_class_mapping.py` on the new tree.** It was written for the pre-refactor tree and
  reads `client/work/renames.tsv`, which is overwritten by every `build_source.sh` run. The new live tree already
  has the final names. `docs/classmap.tsv` + `docs/classmap_src.tsv` are the frozen mapping.
- `work/renames.tsv` now reflects the *renamed* jar, so its `old -> new` pairs are for collision handling of the
  post-classmap jar, not the original obfuscated names.
- `manual_edits.py` token rewriting deliberately ignores single-letter class tokens (`C`, `I`, `M`, `q`, ...)
  because method names are also single letters. Do not weaken that filter.
- `docs/mapping.tsv` still maps old jar names (`dV`, `d_`, ...) to API/module names. `d_` there is the module
  `AutoCart`; the enum that the tree calls `d_` is jar class `d` (`type/BowMode`). This collision is handled by
  `classmap_src.tsv`; do not "fix" it by hand.
- Some generated names are functionally consistent but semantically imperfect: `RenderHelperN`, `WorldContract`
  (this is the text-renderer interface implemented by `TextRenderer`), `RenderContract*`. Renaming them is
  cosmetic and requires regenerating the decompiled source (method names in these contracts are derived from the
  class name).
- 5 method bodies are still TODO no-ops from the stripping stage. Search for `body lost` /
  `unresolved ZKM reference` in the tree.
- Strings are not restored, and helper **member** names are still obfuscated. Only class/package names changed.

## Next steps (in order)

1. **Boot the source route.**
   ```powershell
   cd project
   .\gradlew.bat runClient -PwithClient=true --console=plain
   ```
   or, for a timed logged run:
   ```bash
   cd project/client
   "C:/Program Files/Git/bin/bash.exe" -lc "bash tools/boot_test.sh 200 -PwithClient=true"
   ```
   Use Git Bash, not the WSL `bash` in PATH (WSL has no `cygpath`/Windows Python).
   Check `project/run/logs/latest.log` for `[prestige-ui] bridge bound: N modules` (expect 121) and for
   `ClassNotFoundException` / `NoClassDefFoundError`. The manifest was rewritten (903 setting prop keys), so this
   primarily validates module + setting binding after the package moves.

2. **Test the runtime jar route.** The class map is applied by `rename.sh`, so the boot jar should contain the same
   renamed classes:
   ```bash
   cd project/client
   "C:/Program Files/Git/bin/bash.exe" -lc "bash tools/build_runtime.sh"
   ```
   Then boot through the normal launcher path and compare the bridge line with step 1.

3. **Real input test.** Open the ClickGUI, HUD editor, toggle modules, change settings, move HUD elements. This was
   never tested in this refactor; package moves can expose package-private access that `Widen` masked at bytecode
   level but that no longer matches after decompilation.

4. **Restore the 5 lost bodies** (currently comments with `TODO(compile)`): `dE.run`, `av.run`, `ca.q`, `cb.q`,
   `b4.q` in the old names. After renaming, locate with:
   ```powershell
   Get-ChildItem client/src/main/java -Recurse -Filter *.java |
     Select-String -Pattern 'body lost|unresolved ZKM reference'
   ```
   `client/TODO_COMPILE.md` has the original phase notes.

5. **Restore strings / member names** if desired. `gen_mapping.py` is provenance only; add `F`/`M` rows to
   `docs/mapping.tsv` and regenerate. Do not touch `docs/classmap.tsv` for member work.

6. **Update docs.** `client/TODO_COMPILE.md` and `liberate/HANDOFF_2.md` still describe the pre-refactor tree.
   Record the new package layout, the classmap files, and the boot result.

## Useful commands / paths

- Root: `C:\Users\Tyler\Documents\Shared\prestige\liberate\project`
- Regenerate source into `client/work/src_gen` (does not touch live tree):
  ```bash
  cd client
  "C:/Program Files/Git/bin/bash.exe" -lc "bash tools/build_source.sh"
  ```
- Compile generated tree only:
  ```bash
  "C:/Program Files/Git/bin/bash.exe" -lc "cd project/client && bash tools/compile.sh work/src_gen work/javac_final.log"
  ```
- Rename a jar manually:
  ```bash
  "C:/Program Files/Git/bin/bash.exe" -lc "cd project/client && bash tools/rename.sh work/strip1.jar work/test.renamed.jar"
  ```
- Build from source: `.\gradlew.bat build -PwithClient=true`
- Manifest: `project/src/main/resources/prestige_ui/manifest_live.json`
- Bridge FQNs (unchanged): `project/src/main/java/dev/prestige/ui/bridge/{Bridge,Bootstrap}.java`
- Class map: `project/docs/classmap.tsv`, `project/docs/classmap_src.tsv`
- Merge check: `python client/tools/merge_mapping.py docs/mapping.tsv docs/classmap.tsv <out>`
