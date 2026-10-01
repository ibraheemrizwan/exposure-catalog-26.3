<p align="center"><img src="docs/icon.png" width="112" alt="Exposure Catalog"></p>
<h1 align="center">Exposure Catalog</h1>
<p align="center">Browse, export and manage the photographs stored in your world.</p>
<p align="center"><strong>Minecraft 26.3 · Fabric · Unofficial port</strong></p>
<p align="center"><a href="https://github.com/ibraheemrizwan/exposure-catalog-26.3/releases/latest">Download JAR</a> · <a href="https://github.com/ibraheemrizwan/exposure-catalog-26.3">Source</a> · <a href="https://github.com/ibraheemrizwan/exposure-catalog-26.3/issues">Report an issue</a> · <a href="https://modrinth.com/mod/exposure-catalog">Original on Modrinth</a></p>

## Your world's photo collection

Run `/exposure catalog` to browse the exposures stored in your current world.
Preview photographs, export images, or remove exposures you no longer need.
The textures view also lets you browse loaded game and resource-pack textures.

The command requires permission level 3 by default. Enabling cheats in a
singleplayer world provides access. Install the mod on both client and server.

## Installation

Use **Minecraft 26.3**, **Fabric Loader 0.19.5+** and **Java 25+**.
Download this mod's JAR from [Releases](https://github.com/ibraheemrizwan/exposure-catalog-26.3/releases/latest) and place it in your
instance's `mods` folder. Keep only one version of this mod installed.

Required dependencies:

- [Fabric API](https://modrinth.com/mod/fabric-api) 0.161.0+26.3 or later for 26.3.
- [Exposure for 26.3](https://github.com/ibraheemrizwan/exposure-26.3/releases/latest), port.4 or later.

Fabric Permissions API is included inside the JAR.

Only this mod's JAR is uploaded to its releases. Dependencies are available from
the links above. GitHub also supplies source archives automatically.

## The Exposure collection

[Exposure](https://github.com/ibraheemrizwan/exposure-26.3) · [Exposure Catalog](https://github.com/ibraheemrizwan/exposure-catalog-26.3) · [Exposure: Polaroid](https://github.com/ibraheemrizwan/exposure-polaroid-26.3)

## About this port

Original mod by **[mortuusars](https://github.com/mortuusars/ExposureCatalog)**. See the [official Modrinth page](https://modrinth.com/mod/exposure-catalog)
for the original project. This repository maintains a separate Fabric 26.3 port.

The three ports have been tested together in a Minecraft client, including
capture, printing, albums, search, and saving/reopening a world. Dedicated-server
and remote multiplayer testing is not yet complete. Optional mod integrations
have not all been play-tested. Create integration is not included.

## Building

Install JDK 25, then run `./gradlew build` (Windows: `gradlew.bat build`).
The mod JAR is written to `build/libs/`.

The build downloads the required Exposure JAR from its GitHub release. For a local
build, pass `-PexposureJar=/path/to/exposure.jar`.

## License

[MIT](LICENSE.md). Original attribution is preserved in [NOTICE.md](NOTICE.md).
