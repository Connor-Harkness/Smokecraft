package com.smokecraft.smokecraft.datagen;

import com.smokecraft.smokecraft.Smokecraft;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;

import java.util.concurrent.CompletableFuture;

@EventBusSubscriber(modid = Smokecraft.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class DataGenerators {

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

        // Add recipe provider
        generator.addProvider(event.includeServer(), new SmokecraftRecipeProvider(packOutput));
        
        // Add item tag provider
        generator.addProvider(event.includeServer(), new SmokecraftItemTagProvider(packOutput, lookupProvider, existingFileHelper));
        
        // Add language provider
        generator.addProvider(event.includeClient(), new SmokecraftLanguageProvider(packOutput, "en_us"));
    }
}