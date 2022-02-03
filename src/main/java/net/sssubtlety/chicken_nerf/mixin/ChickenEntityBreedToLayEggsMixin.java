package net.sssubtlety.chicken_nerf.mixin;

import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ExperienceOrbEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.ChickenEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;

import static net.sssubtlety.chicken_nerf.ChickenNerf.getLayedEggStack;

@Mixin(ChickenEntity.class)
public abstract class ChickenEntityBreedToLayEggsMixin extends AnimalEntity {
	protected ChickenEntityBreedToLayEggsMixin(EntityType<? extends AnimalEntity> entityType, World world) {
		super(entityType, world);
		throw new IllegalStateException("ChickenEntityBreedToLayEggsMixin's dummy constructor called!");
	}

	@Override
	public void breed(ServerWorld serverWorld, AnimalEntity other) {
		ServerPlayerEntity serverPlayerEntity = this.getLovingPlayer();
		if (serverPlayerEntity == null && other.getLovingPlayer() != null) {
			serverPlayerEntity = other.getLovingPlayer();
		}

		if (serverPlayerEntity != null) {
			PassiveEntity passiveEntity = this.createChild(serverWorld, other);
			if (passiveEntity != null) {
				serverPlayerEntity.incrementStat(Stats.ANIMALS_BRED);
				Criteria.BRED_ANIMALS.trigger(serverPlayerEntity, this, other, passiveEntity);
			}
		}

		this.playSound(SoundEvents.ENTITY_CHICKEN_EGG, 1.0F, (this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F);
		ItemStack eggStack = getLayedEggStack(Items.EGG, random);
		this.dropStack(eggStack);

		this.setBreedingAge(6000);
		other.setBreedingAge(6000);
		this.resetLoveTicks();
		other.resetLoveTicks();
		world.sendEntityStatus(this, (byte)18);
		if (world.getGameRules().getBoolean(GameRules.DO_MOB_LOOT)) {
			world.spawnEntity(new ExperienceOrbEntity(world, this.getX(), this.getY(), this.getZ(), this.getRandom().nextInt(7) + 1));
		}
	}
}
