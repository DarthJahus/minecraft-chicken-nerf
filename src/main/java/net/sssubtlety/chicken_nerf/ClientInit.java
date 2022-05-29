package net.sssubtlety.chicken_nerf;

import de.guntram.mcmod.crowdintranslate.CrowdinTranslate;
import net.fabricmc.api.ClientModInitializer;

import static net.sssubtlety.chicken_nerf.FeatureControl.isTranslationFetchingEnabled;

public class ClientInit implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        if (isTranslationFetchingEnabled())
            CrowdinTranslate.downloadTranslations("chicken-nerf", ChickenNerf.NAMESPACE);
    }
}
