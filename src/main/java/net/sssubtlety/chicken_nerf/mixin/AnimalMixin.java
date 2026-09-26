package net.sssubtlety.chicken_nerf.mixin;

import net.sssubtlety.chicken_nerf.FeatureControl;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.gameevent.GameEvent;

import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(Animal.class)
abstract class AnimalMixin extends AgeableMob {
	private AnimalMixin() {
		//noinspection DataFlowIssue
		super(null, null);
		throw new IllegalStateException("dummy constructor called!");
	}

	@WrapWithCondition(
		method = "spawnChildFromBreeding",
		at = @At(
			value = "INVOKE",
			target = "Lnet/minecraft/server/level/ServerLevel;addFreshEntityWithPassengers(Lnet/minecraft/world/entity/Entity;)V"
		)
	)
	private boolean spawnEggsInsteadOfBabies(ServerLevel level, Entity entity) {
		final Item eggItem = FeatureControl.getEggForAnimal(this.getClass());
		if (eggItem == null) {
			return true; // not a mapped egg-layer → allow baby spawn
		}

		final int count = FeatureControl.generateEggCount(this.random);
		if (count > 0) {
			this.spawnAtLocation(level, new ItemStack(eggItem, count));
			this.playSound(
				SoundEvents.CHICKEN_EGG, 1.0F,
				(this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F
			);
			this.gameEvent(GameEvent.ENTITY_PLACE);
		}
		return false; // cancel baby
	}
}
