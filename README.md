# Chicken Nerf

> This fork is for 1.21.4. It changes the way egg throw chances are calculated. For any other version, download the mod from the original source.

[![Minecraft versions](https://cf.way2muchnoise.eu/versions/Minecraft_396238_all.svg)](https://modrinth.com/mod/chicken-nerf/versions)
![environment: server](https://img.shields.io/badge/environment-server-orangered)  
[![loader: Fabric](https://img.shields.io/badge/loader-Fabric-cdc4ae?logo=data:image/svg+xml;base64,PHN2ZyB2aWV3Qm94PSIwIDAgMjYgMjgiIHhtbG5zPSJodHRwOi8vd3d3LnczLm9yZy8yMDAwL3N2ZyIgeG1sOnNwYWNlPSJwcmVzZXJ2ZSIgc3R5bGU9ImZpbGwtcnVsZTpldmVub2RkO2NsaXAtcnVsZTpldmVub2RkO3N0cm9rZS1saW5lam9pbjpyb3VuZDtzdHJva2UtbWl0ZXJsaW1pdDoyIj48cGF0aCBkPSJtMTAgMjQtOC04TDE0IDRWMmgybDYgNnY0TDEwIDI0eiIgc3R5bGU9ImZpbGw6I2RiZDBiNCIvPjxwYXRoIGQ9Ik0xMiA2djJoLTJ2Mkg4djJINnYySDR2MkgydjRoMnYyaDJ2MmgydjJoMnYtMmgydi0yaDJ2LTJoMnYtMmgydi0yaDJ2LTRoLTJ2LTJoLTJWOGgtMlY2aDJ2MmgydjJoMnYyaDJ2Mmgydi0yaC0yVjhoLTJWNmgtMlY0aC0yVjJoMnYyaDJ2MmgydjJoMnYyaDJ2NGgtMnYyaC00djJoLTJ2MmgtMnYyaC0ydjRoLTJ2Mkg4di0ySDZ2LTJINHYtMkgydi0ySDB2LTRoMnYtMmgydi0yaDJ2LTJoMlY4aDJWNmgyVjJoMlYwaDJ2MmgtMnY0aC0yIiBzdHlsZT0iZmlsbDojMzgzNDJhIi8+PHBhdGggc3R5bGU9ImZpbGw6IzgwN2E2ZCIgZD0iTTIyIDEyaDJ2MmgtMnoiLz48cGF0aCBkPSJNMiAxOGgydjJoMnYyaDJ2MmgydjJIOHYtMkg2di0ySDR2LTJIMnYtMiIgc3R5bGU9ImZpbGw6IzlhOTI3ZSIvPjxwYXRoIGQ9Ik0yIDE2aDJ2MmgydjJoMnYyaDJ2Mkg4di0ySDZ2LTJINHYtMkgydi0yeiIgc3R5bGU9ImZpbGw6I2FlYTY5NCIvPjxwYXRoIGQ9Ik0yMiAxMnYtMmgtMlY4aC0yVjZsLTIuMDIzLjAyM0wxNiA4aDJ2MmgydjJoMnpNMTAgMjR2LTJoMnYtNGg0di00aDJ2LTJoMnY0aC0ydjJoLTJ2MmgtMnYyaC0ydjJoLTIiIHN0eWxlPSJmaWxsOiNiY2IyOWMiLz48cGF0aCBkPSJNMTQgMThoLTR2LTJIOHYtMmgydjJoNHYyem00LTRoLTR2LTJoLTJ2LTJoLTJWOGgydjJoMnYyaDR2MnpNMTQgNGgydjJoLTJWNHoiIHN0eWxlPSJmaWxsOiNjNmJjYTUiLz48L3N2Zz4=)](https://fabricmc.net/)
<a href="https://quiltmc.org/"><img alt="available for: Quilt Loader" width=73 src="https://raw.githubusercontent.com/QuiltMC/art/master/brand/1024png/quilt_available_dark.png"></a>  
<a href="https://modrinth.com/mod/fabric-api/versions"><img alt="Requires: Fabric API" width="60" src="https://i.imgur.com/Ol1Tcf8.png"></a>
[![supports: Cloth Config](https://img.shields.io/badge/supports-Cloth_Config-89dd43?logo=data:image/svg+xml;base64,PD94bWwgdmVyc2lvbj0iMS4wIiBlbmNvZGluZz0iVVRGLTgiIHN0YW5kYWxvbmU9Im5vIj8+PCFET0NUWVBFIHN2ZyBQVUJMSUMgIi0vL1czQy8vRFREIFNWRyAxLjEvL0VOIiAiaHR0cDovL3d3dy53My5vcmcvR3JhcGhpY3MvU1ZHLzEuMS9EVEQvc3ZnMTEuZHRkIj48c3ZnIHdpZHRoPSIxMDAlIiBoZWlnaHQ9IjEwMCUiIHZpZXdCb3g9IjAgMCAyNjQgMjY0IiB2ZXJzaW9uPSIxLjEiIHhtbG5zPSJodHRwOi8vd3d3LnczLm9yZy8yMDAwL3N2ZyIgeG1sbnM6eGxpbms9Imh0dHA6Ly93d3cudzMub3JnLzE5OTkveGxpbmsiIHhtbDpzcGFjZT0icHJlc2VydmUiIHhtbG5zOnNlcmlmPSJodHRwOi8vd3d3LnNlcmlmLmNvbS8iIHN0eWxlPSJmaWxsLXJ1bGU6ZXZlbm9kZDtjbGlwLXJ1bGU6ZXZlbm9kZDtzdHJva2UtbGluZWpvaW46cm91bmQ7c3Ryb2tlLW1pdGVybGltaXQ6MjsiPjxwYXRoIGlkPSJiYWNrZ3JvdW5kIiBkPSJNODQsMjA3bC0zNiwtMzZsMCwtMzlsMTgsLTE4bDE4LC0yMWwyMSwtMThsMzYsLTM2bDAsLTIxbDIxLDBsMTgsMjFsMzYsMzZsMCwxOGwtNTQsNzhsLTM5LDM2bC0zOSwwWiIgc3R5bGU9ImZpbGw6IzljZmY1NTsiLz48cGF0aCBpZD0ib3V0bGluZSIgZD0iTTE2MiwxOGwwLC0xOGwtMjEsMGwwLDE4bC0xOCwwbDAsMzlsLTE4LDBsMCwxOGwtMjEsMGwwLDE4bC0xOCwwbDAsMjFsLTE4LDBsMCwxOGwtMTgsMGwwLDE4bC0yMSwwbDAsMzlsMjEsMGwwLDE4bDE4LDBsMCwxOGwxOCwwbDAsMjFsMTgsMGwwLDE4bDIxLDBsMCwtMThsMTgsMGwwLC0yMWwxOCwwbDAsLTE4bDIxLDBsMCwtMThsMTgsMGwwLC0xOGwxOCwwbDAsLTIxbDM5LDBsMCwtMThsMTgsMGwwLC0zOWwtMTgsMGwwLC0xOGwtMjEsMGwwLC0xOGwtMTgsMGwwLC0xOGwtMTgsMGwwLC0yMWwtMTgsMGwwLDIxbDE4LDBsMCwxOGwxOCwwbDAsMThsMTgsMGwwLDM5bDIxLDBsMCwxOGwtMjEsMGwwLC0xOGwtMTgsMGwwLC0yMWwtMTgsMGwwLC0xOGwtMTgsMGwwLC0xOGwtMjEsMGwwLDE4bDIxLDBsMCwxOGwxOCwwbDAsMjFsMTgsMGwwLDM2bC0xOCwwbDAsMjFsLTE4LDBsMCwxOGwtMjEsMGwwLDE4bC0xOCwwbDAsMThsLTE4LDBsMCwyMWwtMjEsMGwwLC0yMWwtMTgsMGwwLC0xOGwtMTgsMGwwLC0xOGwtMTgsMGwwLC0zOWwxOCwwbDAsLTE4bDE4LDBsMCwtMThsMTgsMGwwLC0yMWwyMSwwbDAsLTE4bDE4LDBsMCwtMThsMTgsMGwwLC0zOWwyMSwwIiBzdHlsZT0iZmlsbDojMTE1MTEwOyIvPjxwYXRoIGlkPSJzaGFkZS0xIiBzZXJpZjppZD0ic2hhZGUgMSIgZD0iTTQ4LDE3MWwtMTgsMGwwLDE4bDE4LDBsMCwxOGwxOCwwbDAsMThsMTgsMGwwLDIxbDIxLDBsMCwtMjFsLTIxLDBsMCwtMThsLTE4LDBsMCwtMThsLTE4LDBsMCwtMThabTE2OCwtNTdsMjEsMGwwLDE4bC0yMSwwbDAsLTE4WiIgc3R5bGU9ImZpbGw6IzZlYWEzMzsiLz48cGF0aCBpZD0ic2hhZGUtMiIgc2VyaWY6aWQ9InNoYWRlIDIiIGQ9Ik00OCwxNTBsLTE4LDBsMCwyMWwxOCwwbDAsMThsMTgsMGwwLDE4bDE4LDBsMCwxOGwyMSwwbDAsLTE4bC0yMSwwbDAsLTE4bC0xOCwwbDAsLTE4bC0xOCwwbDAsLTIxWiIgc3R5bGU9ImZpbGw6IzgwY2MzZDsiLz48cGF0aCBpZD0ic2hhZGUtMyIgc2VyaWY6aWQ9InNoYWRlIDMiIGQ9Ik0xMDUsMjI1bDAsLTE4bDE4LDBsMCwtMzZsMzksMGwwLC0zOWwxOCwwbDAsLTE4bDE4LDBsMCwzNmwtMTgsMGwwLDIxbC0xOCwwbDAsMThsLTIxLDBsMCwxOGwtMTgsMGwwLDE4bC0xOCwwWm0xMTEsLTExMWwtMTgsMGwwLC0yMWwtMTgsMGwwLC0xOGwtMTgsMGwwLC0xOGwxOCwwbDAsMThsMTgsMGwwLDE4bDE4LDBsMCwyMVoiIHN0eWxlPSJmaWxsOiM4OWRjNDI7Ii8+PHBhdGggaWQ9InNoYWRlLTQiIHNlcmlmOmlkPSJzaGFkZSA0IiBkPSJNMTQxLDE3MWwtMzYsMGwwLC0yMWwtMjEsMGwwLC0xOGwyMSwwbDAsMThsMzYsMGwwLDIxWm0zOSwtMzlsLTM5LDBsMCwtMThsLTE4LDBsMCwtMjFsLTE4LDBsMCwtMThsMTgsMGwwLDE4bDE4LDBsMCwyMWwzOSwwbDAsMThabS0xOCwtNzVsLTIxLDBsMCwtMThsMjEsMGwwLDE4WiIgc3R5bGU9ImZpbGw6IzkwZWI0YjsiLz48L3N2Zz4=)](https://modrinth.com/mod/cloth-config/versions)
[![license: MIT](https://img.shields.io/badge/license-MIT-white)](https://gitlab.com/supersaiyansubtlety/chicken_nerf/-/blob/master/LICENSE)
[![source on: GitLab](https://img.shields.io/badge/source_on-GitLab-fc6e26?logo=gitlab)](https://gitlab.com/supersaiyansubtlety/chicken_nerf)
[![Modrinth: Downloads](https://img.shields.io/modrinth/dt/chicken-nerf?logo=modrinth&label=Modrinth&color=00ae5d)](https://modrinth.com/mod/chicken-nerf/versions)
[![CurseForge: Downloads](https://img.shields.io/curseforge/dt/396238?logo=curseforge&label=CurseForge&color=f16437)](https://www.curseforge.com/minecraft/mc-mods/chicken-nerf/files)  
[![chat: Discord](https://img.shields.io/discord/1006391289006280746?logo=discord&color=5964f3)](https://discord.gg/xABmPngXAH)
<a href="https://coindrop.to/supersaiyansubtlety"><img alt="coindrop.to me" width="82" style="border-radius:3px" src="https://coindrop.to/embed-button.png"></a>
<a href="https://ko-fi.com/supersaiyansubtlety"><img alt="Buy me a coffee" width="106" src="https://i.ibb.co/4gwRR8L/p.png"></a>

**Prevents random chicken egg laying. Instead, breeding chickens produces eggs.**

Works **server-side** and in **single player**.

[Original mod](https://modrinth.com/mod/chicken-nerf) + [Forge port](https://modrinth.com/mod/chicken-nerf-reforged).

Ever think "Chicken farms are too easy...", or "Getting food in Minecraft isn't a challenge anymore..."?  
Well, this mod can help you with that.

Chickens will no longer randomly lay eggs.  
Instead, when you breed two chicken (with seeds like in vanilla), they will produce a random number of eggs.
This has several advantages:
- it makes more sense
- it makes chicken farms less trivial
- it can reduce lag because wild chickens won't randomly create items all over the place
- it more closely matches the other animals of minecraft

You can see the mod showcased at [2:00 of this YouTube video](https://youtu.be/x0R2ubLZEVE?t=120).

## Mod Integrations
Some entities from these mods will have their breeding behavior changed similarly to how Chicken Nerf affects chickens.

- [Better Animals Plus](https://www.curseforge.com/minecraft/mc-mods/betteranimalsplus); affected entities:
  - Geese
  - Pheasants
  - Turkeys

## Configuration

Supports [Cloth Config](https://modrinth.com/mod/cloth-config/versions) for configuration, but neither is required.

Options will use their default values if [Cloth Config](https://modrinth.com/mod/cloth-config/versions) is absent.

If Cloth Config is present, options can be configured by editing `config/chicken_nerf.json` in your
instance folder (`.minecraft/` by default for the vanilla launcher).

```
{
  "minLaidEggs": 1,
  "maxLaidEggs": 3,
  "eggSuccessChance": 0.5
}
```

- `minLaidEggs`: Minimum eggs chickens lay; default `1`;
- `maxLaidEggs`: Maximum eggs chickens lay; default `3`;
- `eggSuccessChance`: Success chance when throwing an egg; default `0.5`; vanilla is `1/8 = 0.125`.
