package com.smokecraft.smokecraft.init;

import com.smokecraft.smokecraft.Smokecraft;
import com.smokecraft.smokecraft.blocks.TobaccoBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.minecraft.core.registries.BuiltInRegistries;
import net.neoforged.neoforge.registries.DeferredHolder;

public class BlockInit {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(BuiltInRegistries.BLOCK, Smokecraft.MOD_ID);

    // Tobacco plant block (grows like sugarcane)
    public static final DeferredHolder<Block, Block> TOBACCO_PLANT = BLOCKS.register("tobacco_plant",
            () -> new TobaccoBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.PLANT)
                    .noCollission()
                    .randomTicks()
                    .strength(0.0F)
                    .sound(net.minecraft.world.level.block.SoundType.GRASS)
                    .noOcclusion()));
}