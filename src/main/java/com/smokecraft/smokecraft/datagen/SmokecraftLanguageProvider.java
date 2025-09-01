package com.smokecraft.smokecraft.datagen;

import com.smokecraft.smokecraft.Smokecraft;
import com.smokecraft.smokecraft.init.ItemInit;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.LanguageProvider;

public class SmokecraftLanguageProvider extends LanguageProvider {

    public SmokecraftLanguageProvider(PackOutput output, String locale) {
        super(output, Smokecraft.MOD_ID, locale);
    }

    @Override
    protected void addTranslations() {
        // Items
        add(ItemInit.CIGARETTE.get(), "Cigarette");
        add(ItemInit.LIGHTER.get(), "Lighter");
        add(ItemInit.CIGARETTE_BUTT.get(), "Cigarette Butt");
        
        // Messages
        add("smokecraft.health_warning", "§e[Health Warning] §fSmoking is harmful to health. This mod is for entertainment only.");
        add("smokecraft.need_lighter", "You need a lighter to smoke this cigarette!");
        add("smokecraft.lighter_broke", "Your lighter is out of fuel!");
        add("smokecraft.lighter_empty", "Your lighter is out of fuel!");
        
        // Creative tab
        add("itemGroup.smokecraft", "Smokecraft");
    }
}