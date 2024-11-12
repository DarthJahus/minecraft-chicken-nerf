package net.sssubtlety.chicken_nerf.mixin;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.projectile.thrown.EggEntity;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.world.World;
import net.sssubtlety.chicken_nerf.ChickenNerf;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(EggEntity.class)
abstract class EggEntityChickenChanceMixin extends ThrownItemEntity {
    private EggEntityChickenChanceMixin() {
        //noinspection DataFlowIssue
        super(null, null);
        throw new IllegalStateException("EggEntityChickenChanceMixin's dummy constructor called!");
    }

    @Redirect(
        method = "onCollision",
        at = @At(value = "FIELD", opcode = Opcodes.GETFIELD, target = "Lnet/minecraft/world/World;isClient:Z")
    )
    private boolean spawnChickensAndFakeClient(World world) {
        ChickenNerf.spawnEntities(
            EntityType.CHICKEN,
            this.getX(), this.getY(), this.getZ(), this.getYaw(),
            world, chicken -> chicken.setBreedingAge(-24000)
        );

        this.getWorld().sendEntityStatus(this, (byte)3);
        this.discard();

        return true;
    }
}
