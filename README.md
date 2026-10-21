# Open Prestige

A fully deobfuscated, IntelliJ-ready, runnable reconstruction of the Prestige client for Fabric 1.21.11,
with a 1:1 Dear ImGui ClickGUI port.

<p align="center">
  <img src="docs/img/deobf-bird.png" width="260" alt="Deobf?">
  <img src="docs/img/deobf-portrait.webp" width="260" alt="DEOBF?">
</p>

<p align="center">
  <img src="docs/img/clickgui.png" width="720" alt="ClickGUI">
</p>

Cracked by Skid Solutions.

## Contact

Want the latest, have questions, or want to contribute? Email **vikaslagerwij@gmail.com**.

Join the Discord: https://discord.gg/5FB5hqJacr

## Requirements

- JDK 21 (`C:\Program Files\Java\jdk-21`); Gradle 9.6.1 comes with the wrapper
- Minecraft 1.21.11, Fabric Loader 0.19.x, Yarn `1.21.11+build.4`
- Loom plugin `net.fabricmc.fabric-loom-remap` 1.17.21

## Run

```
run.bat
```

`run.bat` starts the dev client (offline) and writes everything to `logs\run_<time>.log`
(plus a copy of `latest.log` and any crash report). Open the ClickGUI with the menu key (default `RIGHT SHIFT`).

Open in IntelliJ: File > Open > this folder (`build.gradle`), "Open as Project", Gradle JVM = JDK 21,
then use the generated **Minecraft Client** run configuration.

Build a self-contained mod jar (imgui-java + natives and ClassTransform are bundled):

```
gradlew build
```

## Layout

| Path | What |
| --- | --- |
| `src/main/java/dev/zprestige/prestige/` | the client: 121 modules under `module/`, helpers in `event/ hud/ render/ world/ ...` |
| `.../prestige/ui/` | the ImGui ClickGUI (`bridge/` binds it to the live modules, `mc/` is the overlay, `mixin/` the input hooks) |
| `.../prestige/engine/` | the hook engine (ClassTransform) that installs the client's Minecraft hooks |
| `src/main/resources/` | fonts, icons, shaders, mixin configs, access widener |
| `docs/` | name mappings and notes |

Configs created in the Configs tab are stored in `<game dir>/prestige/configs/`.

## Credits

- **technix**: sourcing / Frida scripts
- **stutheguyy**: fixing
- **bynbang / nitrogenoliquido**: ZKM transformers
- **dario claude man**: AI models
