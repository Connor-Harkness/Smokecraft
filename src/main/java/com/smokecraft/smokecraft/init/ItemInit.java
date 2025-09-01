package com.smokecraft.smokecraft.init;

import com.smokecraft.smokecraft.Smokecraft;
import com.smokecraft.smokecraft.items.CigaretteItem;
import com.smokecraft.smokecraft.items.LighterItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ItemInit {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Smokecraft.MOD_ID);

    // Cigarette item
    public static final RegistryObject<Item> CIGARETTE = ITEMS.register("cigarette",
            () -> new CigaretteItem(new Item.Properties().stacksTo(64)));

    // Lighter item  
    public static final RegistryObject<Item> LIGHTER = ITEMS.register("lighter",
            () -> new LighterItem(new Item.Properties().stacksTo(1).durability(100)));

    // Cigarette butt (optional)
    public static final RegistryObject<Item> CIGARETTE_BUTT = ITEMS.register("cigarette_butt",
            () -> new Item(new Item.Properties().stacksTo(64)));
}