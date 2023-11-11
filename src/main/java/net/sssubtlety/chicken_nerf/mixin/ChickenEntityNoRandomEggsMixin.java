package net.sssubtlety.chicken_nerf.mixin;

import net.minecraft.entity.passive.ChickenEntity;
import net.minecraft.world.World;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(ChickenEntity.class)
class ChickenEntityNoRandomEggsMixin {
	@Redirect(method = "tickMovement", at = @At(value = "FIELD", opcode = Opcodes.GETFIELD, target = "Lnet/minecraft/world/World;isClient:Z"))
	private boolean fakeIsClient(World owner) {
		return true;
	}
}
