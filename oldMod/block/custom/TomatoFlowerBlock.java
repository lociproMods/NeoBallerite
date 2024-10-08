package net.locipro.balleritemod.block.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;


public class TomatoFlowerBlock extends BushBlock {
    public TomatoFlowerBlock(Properties settings) {
        super(settings);
    }
    public static final MapCodec<TomatoFlowerBlock> CODEC = TomatoFlowerBlock.simpleCodec(TomatoFlowerBlock::new);
    @Override
    protected MapCodec<? extends BushBlock> codec() {
        return CODEC;
    }

    private static final VoxelShape BUSH_SHAPE = Block.box(1.0, 0.0, 1.0, 15.0, 16.0, 15.0);
    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter world, BlockPos pPos, CollisionContext context) {
        //return BUSH_SHAPE;
        Vec3 vec3 = pState.getOffset(world, pPos);
        return BUSH_SHAPE.move(vec3.x, vec3.y, vec3.z);
    }
}