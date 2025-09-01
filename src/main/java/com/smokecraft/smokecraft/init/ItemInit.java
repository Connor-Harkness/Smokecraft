package com.smokecraft.smokecraft.init;

import com.smokecraft.smokecraft.Smokecraft;
// import com.smokecraft.smokecraft.items.CigaretteItem;
// import com.smokecraft.smokecraft.items.LighterItem;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.minecraft.core.registries.BuiltInRegistries;
import net.neoforged.neoforge.registries.DeferredHolder;

public class ItemInit {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(BuiltInRegistries.ITEM, Smokecraft.MOD_ID);

    // Cigarette item - temporarily disabled for build fix
    // public static final DeferredHolder<Item, Item> CIGARETTE = ITEMS.register("cigarette",
    //        () -> new CigaretteItem(new Item.Properties().stacksTo(64)));

    // Lighter item - temporarily disabled for build fix  
    // public static final DeferredHolder<Item, Item> LIGHTER = ITEMS.register("lighter",
    //        () -> new LighterItem(new Item.Properties().stacksTo(1).durability(100)));

    // Cigarette butt (optional)
    public static final DeferredHolder<Item, Item> CIGARETTE_BUTT = ITEMS.register("cigarette_butt",
            () -> new Item(new Item.Properties().stacksTo(64)));
}