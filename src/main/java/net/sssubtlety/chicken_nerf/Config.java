package net.sssubtlety.chicken_nerf;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;

import static net.sssubtlety.chicken_nerf.ChickenNerf.NAMESPACE;

@me.shedaniel.autoconfig.annotation.Config(name = NAMESPACE)
public class Config implements ConfigData {
    @ConfigEntry.BoundedDiscrete(max = 10)
    public int minLaidEggs = 1;

    @ConfigEntry.BoundedDiscrete(max = 10)
    public int maxLaidEggs = 3;

    public double averageChickensFromEgg = 0.6;

    @ConfigEntry.Gui.Tooltip
    public boolean enableTranslationFetching = true;

    @Override
    public void validatePostLoad() {
        if(minLaidEggs < 0) {
            ChickenNerf.LOGGER.warn("ChickenNerfConfig found negative minLaidEggs. Defaulting to 0. ");
            minLaidEggs = 0;
        } else if (minLaidEggs > 10) {
            ChickenNerf.LOGGER.warn("ChickenNerfConfig found minLaidEggs > 10. Defaulting to 10. ");
            minLaidEggs = 10;
        }

        if (maxLaidEggs < 0) {
            ChickenNerf.LOGGER.warn("ChickenNerfConfig found negative maxLaidEggs. Defaulting to 0. ");
            maxLaidEggs = 0;
        } else if (maxLaidEggs > 10) {
            ChickenNerf.LOGGER.warn("ChickenNerfConfig found maxLaidEggs > 10. Defaulting to 10. ");
            maxLaidEggs = 10;
        }

        if (minLaidEggs > maxLaidEggs) {
            ChickenNerf.LOGGER.warn("ChickenNerfConfig found minLaidEggs > maxLaidEggs. Swapping. ");
            int temp = minLaidEggs;
            minLaidEggs = maxLaidEggs;
            maxLaidEggs = temp;
        }

        if (averageChickensFromEgg <= 0) {
            ChickenNerf.LOGGER.warn("ChickenNerfConfig found averageChickensFromEgg <= 0. Defaulting to 0.01. ");
            averageChickensFromEgg = 0.01;
        }

        if (averageChickensFromEgg > 100) {
            ChickenNerf.LOGGER.warn("ChickenNerfConfig found averageChickensFromEgg > 100. Defaulting to 100. ");
            averageChickensFromEgg = 100;
        }
    }
}
