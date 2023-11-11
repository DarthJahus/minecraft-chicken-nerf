package net.sssubtlety.chicken_nerf.mixin;

import com.llamalad7.mixinextras.injector.WrapWithCondition;
import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ExperienceOrbEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.ChickenEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.world.GameRules;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static net.sssubtlety.chicken_nerf.ChickenNerf.getLayedEggStack;
import static net.sssubtlety.chicken_nerf.FeatureControl.getEggForAnimal;

@Mixin(AnimalEntity.class)
abstract class AnimalEntityBreedMixin extends PassiveEntity {
	private AnimalEntityBreedMixin(EntityType<? extends AnimalEntity> entityType, World world) {
		super(entityType, world);
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
