package net.sssubtlety.chicken_nerf.mixin;

import org.objectweb.asm.Opcodes;

import net.minecraft.entity.passive.ChickenEntity;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.Slice;

@Mixin(ChickenEntity.class)
abstract class ChickenEntityNoRandomEggsMixin {
	@Redirect(
		method = "tickMovement",
		require = 1, allow = 1,
		// exclude the assignment from random
		slice = @Slice(to = @At(value = "INVOKE", target = "Lnet/minecraft/util/random/RandomGenerator;nextInt(I)I")),
		at = @At(
			value = "FIELD",
			opcode = Opcodes.PUTFIELD,
			target = "Lnet/minecraft/entity/passive/ChickenEntity;eggLayTime:I"
		)
	)
	private void neverDecrementEggLayTime(ChickenEntity instance, int value) { }
}
