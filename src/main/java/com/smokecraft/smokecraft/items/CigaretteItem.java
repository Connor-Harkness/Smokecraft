package com.smokecraft.smokecraft.items;

import com.smokecraft.smokecraft.config.SmokecraftConfig;
import com.smokecraft.smokecraft.init.ItemInit;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUseAnimation;
import net.minecraft.world.level.Level;

public class CigaretteItem extends Item {

    public CigaretteItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult use(Level level, Player player, InteractionHand hand) {
        ItemStack cigaretteStack = player.getItemInHand(hand);
        
        // Check if player has a lighter
        ItemStack otherHandStack = player.getItemInHand(hand == InteractionHand.MAIN_HAND ? InteractionHand.OFF_HAND : InteractionHand.MAIN_HAND);
        boolean hasLighter = otherHandStack.getItem() == ItemInit.LIGHTER.get();
        
        if (!hasLighter && !player.getInventory().contains(new ItemStack(ItemInit.LIGHTER.get()))) {
            // No lighter available
            return InteractionResult.FAIL;
        }

        player.startUsingItem(hand);
        return InteractionResult.CONSUME;
    }

    @Override
    public int getUseDuration(ItemStack stack, LivingEntity entity) {
        return 72000; // 60 seconds at 20 ticks per second
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity entity) {
        if (entity instanceof Player player) {
            // Apply effects if enabled
            if (!SmokecraftConfig.COSMETIC_ONLY.get()) {
                // Small hunger restoration (like eating a small snack)
                player.getFoodData().eat(1, 0.1f);
                
                // TODO: Re-enable effects once we figure out the correct MobEffects constants
                // Small temporary speed boost (like a caffeine effect)
                // player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 200, 0));
                
                // Very minor poison effect if nausea effects are enabled
                // if (SmokecraftConfig.ENABLE_NAUSEA_EFFECT.get()) {
                //     player.addEffect(new MobEffectInstance(MobEffects.NAUSEA, 100, 0));
                // }
            }
            
            // Play finish sound
            level.playSound(null, player.getX(), player.getY(), player.getZ(),
                SoundEvents.FIRE_EXTINGUISH, SoundSource.PLAYERS, 0.5f, 1.0f);
                
            // Consume the cigarette and give back cigarette butt
            stack.shrink(1);
            if (!player.getInventory().add(new ItemStack(ItemInit.CIGARETTE_BUTT.get()))) {
                player.drop(new ItemStack(ItemInit.CIGARETTE_BUTT.get()), false);
            }
        }
        
        return stack;
    }

    @Override
    public ItemUseAnimation getUseAnimation(ItemStack stack) {
        return ItemUseAnimation.DRINK; // Similar to drinking animation
    }

    @Override
    public void onUseTick(Level level, LivingEntity entity, ItemStack stack, int remainingUseTicks) {
        if (level.isClientSide && entity instanceof Player player) {
            // Spawn smoke particles
            if (remainingUseTicks % 10 == 0) { // Every half second
                double x = player.getX() + level.random.nextGaussian() * 0.1;
                double y = player.getY() + player.getEyeHeight() + 0.1;
                double z = player.getZ() + level.random.nextGaussian() * 0.1;
                
                level.addParticle(ParticleTypes.CAMPFIRE_COSY_SMOKE, x, y, z, 0.0, 0.05, 0.0);
            }
        }
        
        if (!level.isClientSide && entity instanceof Player player) {
            // Server-side particle spawning for other players to see
            if (remainingUseTicks % 20 == 0) { // Every second
                ((ServerLevel) level).sendParticles(ParticleTypes.CAMPFIRE_COSY_SMOKE, 
                    player.getX(), player.getY() + player.getEyeHeight() + 0.1, player.getZ(),
                    3, 0.1, 0.1, 0.1, 0.02);
            }
        }
    }

    public boolean releaseUsing(ItemStack stack, Level level, LivingEntity entity, int timeLeft) {
        // If player stops using early, still spawn some smoke and play sound
        if (entity instanceof Player player) {
            level.playSound(null, player.getX(), player.getY(), player.getZ(),
                SoundEvents.FIRE_EXTINGUISH, SoundSource.PLAYERS, 0.3f, 1.2f);
        }
        return true;
    }
}