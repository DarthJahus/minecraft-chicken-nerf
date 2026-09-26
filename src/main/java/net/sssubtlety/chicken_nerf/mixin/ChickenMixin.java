package net.sssubtlety.chicken_nerf.mixin;

import net.minecraft.world.entity.animal.chicken.Chicken;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(value = Chicken.class, priority = 1500)
abstract class ChickenMixin {
	@ModifyExpressionValue(
		method = "aiStep",
		require = 1, allow = 1,
		at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/animal/chicken/Chicken;isAlive()Z")
	)
	private boolean deadChickensLayNoEggs(boolean original) {
		return false;
	}
}
