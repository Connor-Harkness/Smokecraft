package com.smokecraft.smokecraft.items;

import com.smokecraft.smokecraft.config.SmokecraftConfig;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class LighterItem extends Item {

    public LighterItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        
        // Play lighter ignition sound
        level.playSound(player, player.getX(), player.getY(), player.getZ(), 
            SoundEvents.FLINT_AND_STEEL_USE, SoundSource.PLAYERS, 1.0f, 1.0f);
        
        // Damage the lighter slightly on use
        if (!player.getAbilities().instabuild) {
            stack.hurtAndBreak(1, player, (p) -> {
                p.sendSystemMessage(net.minecraft.network.chat.Component.literal(
                    "Your lighter is out of fuel!"
                ));
            });
        }
        
        return InteractionResultHolder.success(stack);
    }

    @Override
    public int getMaxDamage(ItemStack stack) {
        return SmokecraftConfig.LIGHTER_DURABILITY.get();
    }

    @Override
    public boolean isValidRepairItem(ItemStack toRepair, ItemStack repair) {
        // Lighters can be repaired with flint and steel or other lighters
        return repair.getItem() == net.minecraft.world.item.Items.FLINT_AND_STEEL || 
               repair.getItem() instanceof LighterItem;
    }
}