package com.smokecraft.smokecraft.items;

import com.smokecraft.smokecraft.config.SmokecraftConfig;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class LighterItem extends Item {

    public LighterItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        
        // Play lighter ignition sound
        level.playSound(player, player.getX(), player.getY(), player.getZ(), 
            SoundEvents.FLINTANDSTEEL_USE, SoundSource.PLAYERS, 1.0f, 1.0f);
        
        // Damage the lighter slightly on use
        if (!player.getAbilities().instabuild) {
            stack.hurtAndBreak(1, player, hand);
        }
        
        return InteractionResult.SUCCESS;
    }

    @Override
    public int getMaxDamage(ItemStack stack) {
        return 100; // Lighter has 100 uses
    }

    @Override
    public boolean isDamageable(ItemStack stack) {
        return true;
    }
}