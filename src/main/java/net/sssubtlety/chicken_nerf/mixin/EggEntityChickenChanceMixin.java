package net.sssubtlety.chicken_nerf.mixin;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.ChickenEntity;
import net.minecraft.entity.projectile.thrown.EggEntity;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;
import net.sssubtlety.chicken_nerf.ChickenNerf;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(EggEntity.class)
public abstract class EggEntityChickenChanceMixin extends ThrownItemEntity {
    public EggEntityChickenChanceMixin(EntityType<? extends ThrownItemEntity> entityType, World world) {
        super(entityType, world);
        throw new IllegalStateException("chicken_nerf: EggEntityChickenChanceMixin's dummy constructor called. ");
    }

    @Redirect(method = "onCollision", at = @At(value = "FIELD", opcode = Opcodes.GETFIELD, target = "Lnet/minecraft/world/World;isClient:Z"))
    boolean chicken_nerf$spawnChickensAndFakeClient(World world) {
        ChickenNerf.spawnEntities(EntityType.CHICKEN, getX(), getY(), getZ(), getYaw(), world, chicken -> chicken.setBreedingAge(-24000));

        this.world.sendEntityStatus(this, (byte)3);
        this.discard();
        return true;
    }
}
