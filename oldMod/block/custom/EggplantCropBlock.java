package net.locipro.balleritemod.block.custom;

import net.locipro.balleritemod.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class EggplantCropBlock extends CropBlock {

    //The minecraft default is 7, this is just for the sake of understanding how you can make crops
    //with varying max ages.
    public static final IntegerProperty AGE = IntegerProperty.create("age", 0, 7);
    private static final VoxelShape[] AGE_TO_SHAPE = new VoxelShape[]{
            Block.box(0.0D, 0.0D, 0.0D, 16.0D, 5.0D, 16.0D),
            Block.box(0.0D, 0.0D, 0.0D, 16.0D, 5.0D, 16.0D),
            Block.box(0.0D, 0.0D, 0.0D, 16.0D, 8.0D, 16.0D),
            Block.box(0.0D, 0.0D, 0.0D, 16.0D, 12.0D, 16.0D),
            Block.box(0.0D, 0.0D, 0.0D, 16.0D, 15.0D, 16.0D),
            Block.box(0.0D, 0.0D, 0.0D, 16.0D, 15.0D, 16.0D),
            Block.box(0.0D, 0.0D, 0.0D, 16.0D, 15.0D, 16.0D),
            Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D)
        };

    public EggplantCropBlock(Properties settings) {
        super(settings);
    }


    //Default is 7 so this is useless.
    @Override
    public int getMaxAge() {
        return 7;
    }
    //Always gotta have this, don't forgor.
    @Override
    public IntegerProperty getAgeProperty() {
        return AGE;
    }
    //Adding the "AGE" property to the blockstates of the block, so that we can give it different textures based on its age.
    //Again, due to the minecraft default being 7, this is all redundant, it's just for the sake of understanding.
    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(AGE);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        return AGE_TO_SHAPE[state.getValue(this.getAgeProperty())];
    }

    @Override
    protected ItemLike getBaseSeedId() {
        return ModItems.EGGPLANT_SEEDS;
    }
}