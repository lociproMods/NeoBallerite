package com.locipro.neoballerite.block.custom;

import com.locipro.neoballerite.item.ModItems;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

public class SweetPotatoCropBlock extends CropBlock {
    public static final IntegerProperty AGE = BlockStateProperties.AGE_3;
    private static final VoxelShape[] AGE_TO_SHAPE = new VoxelShape[]{
            Block.box(0.0, 0.0, 0.0, 16.0, 5.0, 16.0),
            Block.box(0.0, 0.0, 0.0, 16.0, 8.0, 16.0),
            Block.box(0.0, 0.0, 0.0, 16.0, 11.0, 16.0),
            Block.box(0.0, 0.0, 0.0, 16.0, 16.0, 16.0)};
    public static final MapCodec<SweetPotatoCropBlock> CODEC = simpleCodec(SweetPotatoCropBlock::new);

    @Override
    public @NotNull MapCodec<? extends CropBlock> codec() {
        return CODEC;
    }


    public SweetPotatoCropBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(AGE);
    }

    @Override
    protected @NotNull VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return AGE_TO_SHAPE[getAge(state)];
    }

    public static VoxelShape[] getAgeToShape() {
        return AGE_TO_SHAPE;
    }

    @Override
    public int getMaxAge() {
        return 3;
    }

    @Override
    protected @NotNull ItemLike getBaseSeedId() {
        return ModItems.SWEET_POTATO;
    }

    @Override
    protected @NotNull IntegerProperty getAgeProperty() {
        return AGE;
    }
}
