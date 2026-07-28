[![](https://img.shields.io/endpoint?url=https%3A%2F%2Fcurseforge-badge-shields-io-caaw7pcenm0t.runkit.sh%2Fdownloads%3FprojectId%3D630841%26mode%3Dfull)](https://minecraft.curseforge.com/projects/friendly-griefing)
[![](https://img.shields.io/endpoint?url=https%3A%2F%2Fcurseforge-badge-shields-io-caaw7pcenm0t.runkit.sh%2Fversions%3FprojectId%3D630841)](https://minecraft.curseforge.com/projects/friendly-griefing)  
[![](https://img.shields.io/endpoint?url=https%3A%2F%2Fmodrinth-badge-shields-io-s1co4c2czdpy.runkit.sh/%2Fdownloads%3FprojectId%3D71ytzNvi%26mode%3Dfull)](https://modrinth.com/mod/friendly-griefing)
[![](https://img.shields.io/endpoint?url=https%3A%2F%2Fmodrinth-badge-shields-io-s1co4c2czdpy.runkit.sh%2Fversions%3FprojectId%3D71ytzNvi)](https://modrinth.com/mod/friendly-griefing)

# Friendly Griefing

[Friendly Griefing](https://www.curseforge.com/minecraft/mc-mods/friendly-griefing) lets you keep Minecraft's `mobGriefing` gamerule off for almost everything, while still allowing specific entities (like villagers) to perform their useful "griefing" actions.

## Requirements

- Minecraft **1.21.1**
- [NeoForge](https://neoforged.net/) for 1.21.1 (`21.1.x`)

## Installation

1. Install NeoForge for Minecraft 1.21.1.
2. Place the mod jar in your instance's `mods` folder.
3. Launch the game once and open/create a world so the config file is generated.

## How it works

Vanilla Minecraft has one switch for all mob block-changing behavior:

```
/gamerule mobGriefing false
```

When that gamerule is `false`, creepers, endermen, withers, and most other entities cannot destroy or move blocks. The downside is that helpful entities (especially villagers farming crops) are blocked too.

Friendly Griefing runs **only when `mobGriefing` is false**. It then checks the entity against a whitelist in the server config:

- Entity ID is on the list → griefing is allowed for that entity
- Entity ID is not on the list → griefing stays denied

If `mobGriefing` is `true`, the mod does nothing and vanilla behavior applies.

## Config

Per-world server config (created on first world load):

```
<world>/serverconfig/friendlygriefing-server.toml
```

Example on a singleplayer world:

```
.minecraft/saves/<YourWorld>/serverconfig/friendlygriefing-server.toml
```

Default contents:

```toml
[general]
	# Mob ids to allow griefing
	# Ids have the pattern "minecraft/mod:entity"
	# Default is "minecraft:villager"
	friendlyGriefing = ["minecraft:villager"]
```

Entity IDs use the `namespace:path` form (for example `minecraft:villager`, `minecraft:sheep`).

After editing the config, reload the world or restart the game for changes to apply.

## Example: disable all mob griefing, whitelist villagers only

This is the intended setup for most players: protect builds from hostile griefing, but still let villagers farm.

1. Open your world (singleplayer or server).
2. Turn off global mob griefing:

   ```
   /gamerule mobGriefing false
   ```

3. Open `friendlygriefing-server.toml` for that world and keep (or set) villagers on the whitelist:

   ```toml
   [general]
   	friendlyGriefing = ["minecraft:villager"]
   ```

4. Save the file and reload the world.

Result:

- Creepers, endermen, ghasts, withers, and other unlisted entities remain unable to grief.
- All villager entities (`minecraft:villager`) can still farm and perform other griefing checks the game applies to them.

### Adding more friendly entities

You can whitelist additional entity IDs in the same list:

```toml
[general]
	friendlyGriefing = [
		"minecraft:villager",
		"minecraft:sheep",
		"minecraft:snow_golem"
	]
```

Only entities listed there are allowed to grief while `mobGriefing` is false.
