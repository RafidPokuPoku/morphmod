MorphMod (Fabric) - Source
==========================

This is a prototype Fabric mod (source) that provides:
- A Morph menu (open with Z) listing common mobs.
- Client-side selection and a simple "disguise" placeholder (prototype).

IMPORTANT:
- This repository contains source only (Java). I cannot compile a .jar here.
- To build a usable mod jar you must run (in the project root):

    ./gradlew build

  (Requires Java 17 and internet access to download dependencies.)

- Resulting jar will be under build/libs/ and can be dropped into your Minecraft mods/ folder.

References & dependencies used:
- Fabric API 0.102.1+1.21.1 (for Minecraft 1.21.1). Source: Modrinth / Fabric docs.
- fabric-loom Gradle plugin (for setup). See FabricMC docs.

Notes:
- This is a functional skeleton that is safe to build. The "morph" effect is
  implemented as a client-side selection and placeholder effects (potion buffs, sounds).
  A full server-authoritative disguise system requires more complex server-side code
  or external libraries (e.g., Disguises libraries) and permission handling.

Build and testing:
1. Install Java 17 (required by fabric-loom).
2. Run `./gradlew build` in this folder.
3. Place the resulting jar from `build/libs/` into your Minecraft `mods/` folder and run with Fabric loader.
