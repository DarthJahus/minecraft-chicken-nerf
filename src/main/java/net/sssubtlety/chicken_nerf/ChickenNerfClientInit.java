package net.sssubtlety.chicken_nerf;

import de.guntram.mcmod.crowdintranslate.CrowdinTranslate;
import net.fabricmc.api.ClientModInitializer;

import static net.sssubtlety.chicken_nerf.ChickenNerfInit.MOD_ID;

public class ChickenNerfClientInit implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        CrowdinTranslate.downloadTranslations("chicken-nerf", MOD_ID);
    }
}
