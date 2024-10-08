package net.locipro.balleritemod.block.custom;

import net.locipro.balleritemod.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class CornCropBlock extends CropBlock {
    public CornCropBlock(Properties settings) {
        super(settings);
    }
    public static final IntegerProperty AGE = IntegerProperty.create("age", 0, 9);

    public static final int FIRST_STAGE_MAX_AGE = 4;
    public static final int SECOND_STAGE_MAX_AGE = 5;

    private static final VoxelShape[] AGE_TO_SHAPE = new VoxelShape[]{
            Block.box(0.0, 0.0, 0.0, 16.0, 2.0, 16.0),
            Block.box(0.0, 0.0, 0.0, 16.0, 5.0, 16.0),
            Block.box(0.0, 0.0, 0.0, 16.0, 8.0, 16.0),
            Block.box(0.0, 0.0, 0.0, 16.0, 12.0, 16.0),
            Block.box(0.0, 0.0, 0.0, 16.0, 16.0, 16.0),
            Block.box(0.0, 0.0, 0.0, 16.0, 22.0, 16.0),
            Block.box(0.0, 0.0, 0.0, 16.0, 30.0, 16.0),
            Block.box(0.0, 0.0, 0.0, 16.0, 32.0, 16.0),
            Block.box(0.0, 0.0, 0.0, 16.0, 32.0, 16.0),
            Block.box(0.0, 0.0, 0.0, 16.0, 32.0, 16.0)};



    @Override
    public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        return AGE_TO_SHAPE[this.getAge(state)];
    }
    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(AGE);
    }
    @Override
    protected IntegerProperty getAgeProperty() {
        return AGE;
    }
    @Override
    protected ItemLike getBaseSeedId() {
        return ModItems.CORN_SEEDS.asItem();
    }
    @Override
    public boolean canSurvive(BlockState state, LevelReader world, BlockPos pos) {
        return super.canSurvive(state, world, pos) ||
            /*if*/    (world.getBlockState(pos.below(1)).is(this) &&
                        !world.getBlockState(pos).is(this) &&
                        world.getBlockState(pos.below(1)).getValue(AGE) >= FIRST_STAGE_MAX_AGE);  /*{place 'state' at 'pos'}*/
    }
    @Override
    public int getMaxAge() {
        return FIRST_STAGE_MAX_AGE + SECOND_STAGE_MAX_AGE;
    }

    @Override
    public void randomTick(BlockState state, ServerLevel world, BlockPos pos, RandomSource random) {
        if (world.getRawBrightness(pos, 0) >= 9) {
            int age = this.getAge(state);
            if (age < this.getMaxAge()) {
                float f = getGrowthSpeed(this, world, pos);
                if (random.nextInt((int)(25.0F / f) + 1) == 0) {
                    // Growth tick
                    if (age < FIRST_STAGE_MAX_AGE) {
                        world.setBlock(pos, this.getStateForAge(age + 1), 2);
                    }else if (age == FIRST_STAGE_MAX_AGE && world.getBlockState(pos.above(1)).is(Blocks.AIR)) {
                        world.setBlock(pos.above(1), this.getStateForAge(age + 1), 2);
                    }else {
                        world.setBlock(pos, this.getStateForAge(age + 1), 2);
                    }
                }
            }
        }
    }/* the super method
    if (world.getBaseLightLevel(pos, 0) >= 9) {
            int i = this.getAge(state);
            if (i < this.getMaxAge()) {
                float f = getAvailableMoisture(this, world, pos);
                if (random.nextInt((int)(25.0F / f) + 1) == 0) {
                    world.setBlockState(pos, this.withAge(i + 1), 2);
                }
            }
        }*/

    @Override
    public void growCrops(Level world, BlockPos pos, BlockState state) {
        int currentAge = getAge(state);
        int nextAge = this.getAge(state) + this.getBonemealAgeIncrease(world);
        int maxAge = this.getMaxAge();
        if (nextAge > maxAge) {
            nextAge = maxAge;
        }

        if (currentAge == FIRST_STAGE_MAX_AGE && world.getBlockState(pos.above(1)).is(Blocks.AIR)) {
            world.setBlock(pos.above(1), this.getStateForAge(nextAge), 2);
        }else {
            world.setBlock(pos, this.getStateForAge(nextAge), 2);
        }
    }/* the super method
    int i = this.getAge(state) + this.getGrowthAmount(world);
        int j = this.getMaxAge();
        if (i > j) {
            i = j;
        }

        world.setBlockState(pos, this.withAge(i), 2);*/

    /*@Override
    public void applyGrowth(World world, BlockPos pos, BlockState state) {
        int nextAge = this.getAge(state) + this.getGrowthAmount(world);

        int maxAge = this.getMaxAge();
        if (nextAge > maxAge) {
            nextAge = maxAge;
        }

        if (getAge(state) >= FIRST_STAGE_MAX_AGE && world.getBlockState(pos.up(1)).isOf(Blocks.AIR)) {
            world.setBlockState(pos.up(1), this.withAge(nextAge), 2);
        }else {
            world.setBlockState(pos, this.withAge(nextAge - 1), 2);
        }
    }

    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (world.getBaseLightLevel(pos, 0) >= 9) {
            int currentAge = this.getAge(state);
            if (currentAge < this.getMaxAge()) {
                float f = getAvailableMoisture(this, world, pos);
                if (random.nextInt((int)(25.0F / f) + 1) == 0) {
                    if(currentAge >= FIRST_STAGE_MAX_AGE) {
                        if(world.getBlockState(pos.up(1)).isOf(Blocks.AIR)) {
                            world.setBlockState(pos.up(1), this.withAge(currentAge + 1), 2);
                        }
                    } else {
                        world.setBlockState(pos, this.withAge(currentAge + 1), 2);
                    }
                }
            }
        }
    }*/
}
