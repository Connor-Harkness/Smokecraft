package com.smokecraft.smokecraft.blocks;

import com.smokecraft.smokecraft.init.BlockInit;
import com.smokecraft.smokecraft.init.ItemInit;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class TobaccoBlock extends Block {
    public static final IntegerProperty AGE = BlockStateProperties.AGE_15;
    protected static final VoxelShape SHAPE = Block.box(2.0, 0.0, 2.0, 14.0, 16.0, 14.0);

    public TobaccoBlock(BlockBehaviour.Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(AGE, 0));
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }

    @Override
    public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        if (level.isEmptyBlock(pos.above())) {
            int currentAge = state.getValue(AGE);
            
            // Grow upwards like sugarcane
            int height = 1;
            while (level.getBlockState(pos.below(height)).is(this)) {
                height++;
            }

            if (height < 3) { // Max height of 3 blocks like sugarcane
                if (currentAge == 15) {
                    // Create new tobacco plant above
                    level.setBlockAndUpdate(pos.above(), this.defaultBlockState());
                } else if (random.nextInt(3) == 0) {
                    // Grow the current block
                    level.setBlock(pos, state.setValue(AGE, currentAge + 1), 4);
                }
            }
        }
    }

    @Override
    public boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        BlockState below = level.getBlockState(pos.below());
        
        // Can grow on farmland, dirt, grass, or another tobacco plant
        if (below.is(BlockTags.DIRT) || below.is(Blocks.FARMLAND) || below.is(this)) {
            return true;
        }
        
        return false;
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState();
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(AGE);
    }

    @Override
    public boolean isRandomlyTicking(BlockState state) {
        return true;
    }

    // Remove the problematic getCloneItemFromBlockState method for now
}