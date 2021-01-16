Minecraft 1.16.1 mod for the Fabric [mod loader](https://www.fabricmc.net/). It requires the [Fabric API](https://www.curseforge.com/minecraft/mc-mods/fabric-api/files/all?filter-game-version=1738749986%3a70886). 

Ever think "Chicken farms are too easy...", or "Getting food in Minecraft isn't a challenge anymore..."?

Well, this mod can help you with that. 

Chickens will no longer randomly lay eggs. 
Instead, when you breed two chicken (with seed like in vanilla), they will produce a random number of eggs. 

This has several advantages:
- it makes more sense
- it makes chicken farms non-trivial
- it can reduce lag because wild chickens are randomly creating items all over the place
- it more closely matches the other animals of minecraft

There are three settings that can be changed with Mod Menu if you have it installed, or by editting `.minecraft/config/chicken_nerf.json`

These are the settings with their default values:
```json
  "minLayedEggs": 1,
  "maxLayedEggs": 3,
  "eggSuccessChance": 0.3333
```
The default settings will result in about one baby chicken each time you breed two chickens. 

If you'd like to translate Chicken Nerf (there are only a few lines of text), follow [this link](https://crowdin.com/project/chicken-nerf/invite). New translations will be added once approved without the mod needing an update thanks to [CrowdinTranslate](https://github.com/gbl/CrowdinTranslate).

![Requires the Fabric API](https://i.imgur.com/Ol1Tcf8.png)

This mod is only for Fabric and I won't be porting it to Forge. The license is [MIT](https://will-lucic.mit-license.org/), however, so anyone else is free to port it. 

I'd appreciate links back to this page if you port or otherwise modify this project, but links aren't required. 
