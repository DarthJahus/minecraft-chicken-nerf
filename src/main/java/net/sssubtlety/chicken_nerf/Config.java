package net.sssubtlety.chicken_nerf;

import net.sssubtlety.chicken_nerf.FeatureControl.Defaults;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.ConfigEntry;

import static net.sssubtlety.chicken_nerf.ChickenNerf.LOGGER;
import static net.sssubtlety.chicken_nerf.ChickenNerf.NAMESPACE;

@me.shedaniel.autoconfig.annotation.Config(name = NAMESPACE)
public class Config implements ConfigData {
    @ConfigEntry.BoundedDiscrete(max = 10)
    public int minLaidEggs = Defaults.MIN_LAID_EGGS;

    @ConfigEntry.BoundedDiscrete(max = 10)
    public int maxLaidEggs = Defaults.MAX_LAID_EGGS;

    public double averageChickensFromEgg = Defaults.AVERAGE_CHICKENS_FROM_EGG;

    @Override
    public void validatePostLoad() {
        if(this.minLaidEggs < 0) {
            LOGGER.warn("Config: found negative minLaidEggs; defaulting to 0");
            this.minLaidEggs = 0;
        } else if (this.minLaidEggs > 10) {
            LOGGER.warn("Config: found minLaidEggs > 10; defaulting to 10");
            this.minLaidEggs = 10;
        }

        if (this.maxLaidEggs < 0) {
            LOGGER.warn("Config: found negative maxLaidEggs; defaulting to 0");
            this.maxLaidEggs = 0;
        } else if (this.maxLaidEggs > 10) {
            LOGGER.warn("Config: found maxLaidEggs > 10; defaulting to 10");
            this.maxLaidEggs = 10;
        }

        if (this.minLaidEggs > this.maxLaidEggs) {
            LOGGER.warn("Config: found minLaidEggs > maxLaidEggs; swapping");
            final int swap = this.minLaidEggs;
            this.minLaidEggs = this.maxLaidEggs;
            this.maxLaidEggs = swap;
        }

        if (this.averageChickensFromEgg <= 0) {
            LOGGER.warn("Config: found averageChickensFromEgg <= 0; defaulting to 0.01");
            this.averageChickensFromEgg = 0.01;
        }

        if (this.averageChickensFromEgg > 100) {
            LOGGER.warn("Config: found averageChickensFromEgg > 100; defaulting to 100");
            this.averageChickensFromEgg = 100;
        }
    }
}
