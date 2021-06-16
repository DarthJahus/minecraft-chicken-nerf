package net.sssubtlety.chicken_nerf.mixin;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.ChickenEntity;
import net.minecraft.entity.projectile.thrown.EggEntity;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.world.World;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import static net.sssubtlety.chicken_nerf.ChickenNerfInit.getCONFIG;

@Mixin(EggEntity.class)
public abstract class EggEntityChickenChanceMixin extends ThrownItemEntity {
    public EggEntityChickenChanceMixin(EntityType<? extends ThrownItemEntity> entityType, World world) {
        super(entityType, world);
        throw new IllegalStateException("chicken_nerf: EggEntityChickenChanceMixin's dummy constructor called. ");
    }

    @Redirect(method = "onCollision", at = @At(value = "FIELD", opcode = Opcodes.GETFIELD, target = "Lnet/minecraft/world/World;isClient:Z"))
    boolean spawnChickensAndFakeClient(World world) {

        while(random.nextFloat() < getCONFIG().getEggSuccessChance()) {
            ChickenEntity chickenEntity = EntityType.CHICKEN.create(this.world);
            if(chickenEntity != null) {
                chickenEntity.setBreedingAge(-24000);
                chickenEntity.refreshPositionAndAngles(this.getX(), this.getY(), this.getZ(), this.getYaw(), 0.0F);
                this.world.spawnEntity(chickenEntity);
            }
        }

        this.world.sendEntityStatus(this, (byte)3);
        this.discard();
        return true;
    }
}
