package net.sssubtlety.chicken_nerf;

import net.fabricmc.api.ModInitializer;

public class Init implements ModInitializer {
    @Override
    public void onInitialize() {
        FeatureControl.init();
    }
}
