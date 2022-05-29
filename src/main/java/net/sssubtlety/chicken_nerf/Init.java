package net.sssubtlety.chicken_nerf;

import net.fabricmc.api.ModInitializer;

public class Init implements ModInitializer {
    @Override
    public void onInitialize() {
        // reference FeatureControl class so it consistently loads at this point
        FeatureControl.init();
    }
}
