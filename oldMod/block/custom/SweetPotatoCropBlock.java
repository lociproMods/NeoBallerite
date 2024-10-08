package net.locipro.balleritemod.block.custom;

import net.locipro.balleritemod.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.PotatoBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class SweetPotatoCropBlock extends PotatoBlock {
    public SweetPotatoCropBlock(Properties settings) {
        super(settings);
    }
    public static final IntegerProperty AGE = IntegerProperty.create("age", 0, 3);

    private static final VoxelShape[] AGE_TO_SHAPE = new VoxelShape[]{
            Block.box(0.0, 0.0, 0.0, 16.0, 5.0, 16.0),
            Block.box(0.0, 0.0, 0.0, 16.0, 8.0, 16.0),
            Block.box(0.0, 0.0, 0.0, 16.0, 11.0, 16.0),
            Block.box(0.0, 0.0, 0.0, 16.0, 16.0, 16.0)};
    @Override
    public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        return AGE_TO_SHAPE[state.getValue(this.getAgeProperty())];
    }
    @Override
    public IntegerProperty getAgeProperty() {
        return AGE;
    }
    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(AGE);
    }
    @Override
    protected ItemLike getBaseSeedId() {
        return ModItems.SWEET_POTATO;
    }

    @Override
    public int getMaxAge() {
        return 3;
    }
}
