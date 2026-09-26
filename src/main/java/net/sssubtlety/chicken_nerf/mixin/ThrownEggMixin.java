package net.sssubtlety.chicken_nerf.mixin;

import net.sssubtlety.chicken_nerf.ChickenNerf;

import net.minecraft.core.component.DataComponents;
import net.minecraft.world.entity.EntityEvent;
import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.entity.projectile.throwableitemprojectile.ThrowableItemProjectile;
import net.minecraft.world.entity.projectile.throwableitemprojectile.ThrownEgg;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import java.util.Optional;

@Mixin(value = ThrownEgg.class, priority = 1500)
abstract class ThrownEggMixin extends ThrowableItemProjectile {
	@SuppressWarnings("DataFlowIssue")
	private ThrownEggMixin() {
		super(null, null);
		throw new IllegalStateException("dummy constructor called!");
	}

	@ModifyExpressionValue(
		method = "onHit",
		at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/Level;isClientSide()Z")
	)
	private boolean spawnChickensAndFakeClient(boolean original) {
		ChickenNerf.spawnEntities(
			EntityTypes.CHICKEN,
			this.getX(), this.getY(), this.getZ(), this.getYRot(),
			this.level(),
			chicken -> {
				chicken.setAge(-24000);
				Optional.ofNullable(this.getItem().get(DataComponents.CHICKEN_VARIANT))
					.ifPresent(chicken::setVariant);
			}
		);

		this.level().broadcastEntityEvent(this, EntityEvent.DEATH);
		this.discard();

		return true;
	}
}
