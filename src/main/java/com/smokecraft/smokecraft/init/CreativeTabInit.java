package com.smokecraft.smokecraft.init;

import com.smokecraft.smokecraft.Smokecraft;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;

public class CreativeTabInit {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Smokecraft.MOD_ID);

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> SMOKECRAFT_TAB = CREATIVE_MODE_TABS.register("smokecraft_tab", () -> CreativeModeTab.builder()
            .icon(() -> new ItemStack(ItemInit.CIGARETTE.get()))
            .title(Component.translatable("itemGroup.smokecraft"))
            .displayItems((parameters, output) -> {
                output.accept(ItemInit.CIGARETTE.get());
                output.accept(ItemInit.LIGHTER.get());
                output.accept(ItemInit.CIGARETTE_BUTT.get());
            }).build());
}