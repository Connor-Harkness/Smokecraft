package com.smokecraft.smokecraft.datagen;

import com.smokecraft.smokecraft.Smokecraft;
import com.smokecraft.smokecraft.init.ItemInit;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import java.util.concurrent.CompletableFuture;

public class SmokecraftItemTagProvider extends ItemTagsProvider {

    public SmokecraftItemTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider,
                                     ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, CompletableFuture.completedFuture(TagsProvider.TagLookup.empty()), Smokecraft.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        // Add cigarette butt to compostables if enabled in config
        this.tag(ItemTags.VILLAGER_PLANTABLE_SEEDS)
            .add(ItemInit.CIGARETTE_BUTT.get()); // Placeholder - would be conditional based on config
    }
}