package com.smokecraft.smokecraft.items;

import com.smokecraft.smokecraft.config.SmokecraftConfig;
import com.smokecraft.smokecraft.init.ItemInit;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;

public class CigaretteItem extends Item {

    public CigaretteItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack cigaretteStack = player.getItemInHand(hand);
        
        // Check if player has a lighter
        if (!hasLighter(player)) {
            if (!level.isClientSide) {
                player.sendSystemMessage(net.minecraft.network.chat.Component.literal(
                    "You need a lighter to smoke this cigarette!"
                ));
            }
            return InteractionResultHolder.fail(cigaretteStack);
        }

        // Start smoking
        player.startUsingItem(hand);
        return InteractionResultHolder.consume(cigaretteStack);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity entity) {
        if (entity instanceof Player player) {
            // Consume cigarette
            if (!player.getAbilities().instabuild) {
                stack.shrink(1);
            }

            // Damage lighter
            damageLighter(player);

            // Apply effects based on config
            if (!level.isClientSide) {
                applySmokingEffects(player);
                
                // Drop cigarette butt if enabled
                if (SmokecraftConfig.CIGARETTE_BUTT_ENABLED.get()) {
                    ItemStack butt = new ItemStack(ItemInit.CIGARETTE_BUTT.get());
                    if (!player.getInventory().add(butt)) {
                        player.drop(butt, false);
                    }
                }
            }

            // Sound effect
            level.playSound(null, player.getX(), player.getY(), player.getZ(),
                SoundEvents.FIRE_EXTINGUISH, SoundSource.PLAYERS, 0.5f, 1.0f);
        }

        return stack;
    }

    @Override
    public int getUseDuration(ItemStack stack) {
        return SmokecraftConfig.CIGARETTE_USE_DURATION.get();
    }

    @Override
    public UseAnim getUseAnimation(ItemStack stack) {
        return UseAnim.BOW; // Similar to drinking animation
    }

    @Override
    public void onUseTick(Level level, LivingEntity entity, ItemStack stack, int remainingUseTicks) {
        if (level.isClientSide && entity instanceof Player player) {
            // Spawn smoke particles
            double density = SmokecraftConfig.SMOKE_PARTICLE_DENSITY.get();
            if (level.random.nextDouble() < density) {
                double x = player.getX() + (level.random.nextDouble() - 0.5) * 0.3;
                double y = player.getY() + player.getEyeHeight() - 0.1;
                double z = player.getZ() + (level.random.nextDouble() - 0.5) * 0.3;
                
                level.addParticle(ParticleTypes.CAMPFIRE_COSY_SMOKE, x, y, z, 
                    (level.random.nextDouble() - 0.5) * 0.02, 
                    0.05 + level.random.nextDouble() * 0.02, 
                    (level.random.nextDouble() - 0.5) * 0.02);
            }
        }
    }

    private boolean hasLighter(Player player) {
        return player.getInventory().contains(new ItemStack(ItemInit.LIGHTER.get()));
    }

    private void damageLighter(Player player) {
        for (int i = 0; i < player.getInventory().getContainerSize(); i++) {
            ItemStack stack = player.getInventory().getItem(i);
            if (stack.getItem() instanceof LighterItem) {
                stack.hurtAndBreak(1, player, (p) -> {
                    p.sendSystemMessage(net.minecraft.network.chat.Component.literal(
                        "Your lighter broke!"
                    ));
                });
                break;
            }
        }
    }

    private void applySmokingEffects(Player player) {
        // Only apply effects if not cosmetic only
        if (SmokecraftConfig.COSMETIC_ONLY.get()) {
            return;
        }

        ServerLevel serverLevel = (ServerLevel) player.level();

        // Hunger effect
        if (SmokecraftConfig.ENABLE_HUNGER_EFFECT.get()) {
            int hungerDrain = SmokecraftConfig.CIGARETTE_HUNGER_DRAIN.get();
            player.getFoodData().addExhaustion(hungerDrain);
        }

        // Nicotine rush (speed effect)
        if (SmokecraftConfig.ENABLE_NICOTINE_RUSH.get()) {
            int duration = SmokecraftConfig.NICOTINE_RUSH_DURATION.get();
            int amplifier = SmokecraftConfig.NICOTINE_RUSH_AMPLIFIER.get();
            player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, duration, amplifier));
        }

        // Nausea for new smokers
        if (SmokecraftConfig.ENABLE_NAUSEA_EFFECT.get()) {
            // Simple check - if player has no speed effect, they might be a new smoker
            if (!player.hasEffect(MobEffects.MOVEMENT_SPEED)) {
                int duration = SmokecraftConfig.NAUSEA_DURATION.get();
                int amplifier = SmokecraftConfig.NAUSEA_AMPLIFIER.get();
                player.addEffect(new MobEffectInstance(MobEffects.CONFUSION, duration, amplifier));
            }
        }
    }
}