package net.sssubtlety.chicken_nerf;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import static net.sssubtlety.chicken_nerf.ChickenNerf.LOGGER;
import static net.sssubtlety.chicken_nerf.ChickenNerf.NAMESPACE;

@me.shedaniel.autoconfig.annotation.Config(name = NAMESPACE)
public class Config implements ConfigData {
    @ConfigEntry.BoundedDiscrete(max = 3)
    public int minLaidEggs = 1;

    @ConfigEntry.BoundedDiscrete(max = 3)
    public int maxLaidEggs = 3;

    public double eggSuccessChance = 0.5;

    @Override
    public void validatePostLoad() {
        if(this.minLaidEggs < 0) this.minLaidEggs = 0;
        if(this.maxLaidEggs < 0) this.maxLaidEggs = 0;
        if(this.minLaidEggs > this.maxLaidEggs) {
            int temp = this.minLaidEggs;
            this.minLaidEggs = this.maxLaidEggs;
            this.maxLaidEggs = temp;
        }
        if (this.eggSuccessChance < 0 || this.eggSuccessChance > 1) {
            this.eggSuccessChance = 0.5;
        }
    }
}
