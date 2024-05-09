package net.sssubtlety.chicken_nerf.mixin;

import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.item.Item;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvents;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import static net.sssubtlety.chicken_nerf.ChickenNerf.getLayedEggStack;
import static net.sssubtlety.chicken_nerf.FeatureControl.getEggForAnimal;

@Mixin(AnimalEntity.class)
abstract class AnimalEntityBreedMixin extends PassiveEntity {
	private AnimalEntityBreedMixin() {
        //noinspection DataFlowIssue
        super(null, null);
		throw new IllegalStateException("AnimalEntityBreedMixin's dummy constructor called!");
	}

	@WrapWithCondition(method = "breed(Lnet/minecraft/server/world/ServerWorld;Lnet/minecraft/entity/passive/AnimalEntity;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/world/ServerWorld;spawnEntityAndPassengers(Lnet/minecraft/entity/Entity;)V"))
	private boolean spawnEggsInsteadOfBabies(ServerWorld world, Entity entity) {
		Item eggItem = getEggForAnimal(this.getClass(), this.random);
		if (eggItem == null) return true;
		else {
			this.playSound(SoundEvents.ENTITY_CHICKEN_EGG, 1.0F, (this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F);
			this.dropStack(getLayedEggStack(eggItem, random));
			return false;
		}
	}
}
