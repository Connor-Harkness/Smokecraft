package com.smokecraft.smokecraft.init;

import com.smokecraft.smokecraft.Smokecraft;
import com.smokecraft.smokecraft.items.CigaretteItem;
import com.smokecraft.smokecraft.items.LighterItem;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.minecraft.core.registries.BuiltInRegistries;
import net.neoforged.neoforge.registries.DeferredHolder;

public class ItemInit {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(BuiltInRegistries.ITEM, Smokecraft.MOD_ID);

    // Cigarette item
    public static final DeferredHolder<Item, Item> CIGARETTE = ITEMS.register("cigarette",
            () -> new CigaretteItem(new Item.Properties().stacksTo(64)));

    // Lighter item  
    public static final DeferredHolder<Item, Item> LIGHTER = ITEMS.register("lighter",
            () -> new LighterItem(new Item.Properties().stacksTo(1).durability(100)));

    // Cigarette butt (optional)
    public static final DeferredHolder<Item, Item> CIGARETTE_BUTT = ITEMS.register("cigarette_butt",
            () -> new Item(new Item.Properties().stacksTo(64)));

    // Raw tobacco (harvested from tobacco plant)
    public static final DeferredHolder<Item, Item> RAW_TOBACCO = ITEMS.register("raw_tobacco",
            () -> new Item(new Item.Properties().stacksTo(64)));

    // Cured tobacco (smelted from raw tobacco)
    public static final DeferredHolder<Item, Item> CURED_TOBACCO = ITEMS.register("cured_tobacco",
            () -> new Item(new Item.Properties().stacksTo(64)));

    // Filter (crafted with sugarcane and paper)
    public static final DeferredHolder<Item, Item> FILTER = ITEMS.register("filter",
            () -> new Item(new Item.Properties().stacksTo(64)));

    // Tobacco seeds/plant item (for planting tobacco)
    public static final DeferredHolder<Item, Item> TOBACCO_SEEDS = ITEMS.register("tobacco_seeds",
            () -> new BlockItem(BlockInit.TOBACCO_PLANT.get(), new Item.Properties().stacksTo(64)));
}