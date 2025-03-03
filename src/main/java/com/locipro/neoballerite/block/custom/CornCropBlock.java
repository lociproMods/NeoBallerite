package com.locipro.neoballerite.block.custom;

import com.locipro.neoballerite.block.ModBlocks;
import com.locipro.neoballerite.item.ModItems;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Ravager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.neoforged.neoforge.common.util.TriState;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class CornCropBlock extends DoublePlantBlock implements BonemealableBlock {
    public static final MapCodec<CornCropBlock> CODEC = simpleCodec(CornCropBlock::new);
    public static final IntegerProperty AGE = IntegerProperty.create("age", 0, 9);
    public static final int MAX_AGE = 9;
    public static final int BECOMES_DOUBLE_BLOCK = 5;
    // 0, 1, 2, 3, 4 -> Lower
    // 5, 6, 7, 8, 9 -> Upper

    private static final VoxelShape LOWER_SHAPE_FULL = Block.box(0.0, 0.0, 0.0, 16.0, 16.0, 16.0);
    private static final VoxelShape[] LOWER_SHAPE_BY_AGE = new VoxelShape[]{
            Block.box(0.0, 0.0, 0.0, 16.0, 2.0, 16.0),
            Block.box(0.0, 0.0, 0.0, 16.0, 5.0, 16.0),
            Block.box(0.0, 0.0, 0.0, 16.0, 8.0, 16.0),
            Block.box(0.0, 0.0, 0.0, 16.0, 12.0, 16.0),
            LOWER_SHAPE_FULL,
            LOWER_SHAPE_FULL,
            LOWER_SHAPE_FULL,
            LOWER_SHAPE_FULL,
            LOWER_SHAPE_FULL,
            LOWER_SHAPE_FULL};
    private static final VoxelShape[] UPPER_SHAPE_BY_AGE = new VoxelShape[]{
            Block.box(0.0, 0.0, 0.0, 16.0, 6.0, 16.0),
            Block.box(0.0, 0.0, 0.0, 16.0, 14.0, 16.0),
            Block.box(0.0, 0.0, 0.0, 16.0, 16.0, 16.0),
            Block.box(0.0, 0.0, 0.0, 16.0, 16.0, 16.0),
            Block.box(0.0, 0.0, 0.0, 16.0, 16.0, 16.0)};

    private static final int BONEMEAL_INCREASE = 1;

    @Override
    public MapCodec<? extends DoublePlantBlock> codec() {
        return CODEC;
    }

    public CornCropBlock(Properties properties) {
        super(properties);
    }
    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(AGE);
        super.createBlockStateDefinition(builder);
    }


    @Override
    public ItemStack getCloneItemStack(LevelReader level, BlockPos pos, BlockState state, boolean includeData, Player player) {
        return new ItemStack(ModItems.CORN_KERNELS.get());
    }

    @Override
    public @Nullable BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState();
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        if (state.getValue(HALF) == DoubleBlockHalf.LOWER) {
            return LOWER_SHAPE_BY_AGE[state.getValue(AGE)];
        }else {
            // Math.abs(STAGE2 - AGE) gets the difference between ages to get the index for the shape array
            // Math.min(diff, array.length - 1) makes sure you don't get a value bigger than the array's length. array[array.length-1] returns the LAST element.
            return UPPER_SHAPE_BY_AGE[Math.min(Math.abs(state.getValue(AGE) - BECOMES_DOUBLE_BLOCK), UPPER_SHAPE_BY_AGE.length - 1)];
        }
    }


    @Override
    protected @NotNull BlockState updateShape(BlockState state, LevelReader level, ScheduledTickAccess scheduledTickAccess, BlockPos currentPos, Direction facing, BlockPos facingPos, BlockState facingState, RandomSource p_374454_) {
        if (isDoubleBlock(state)) {
            return super.updateShape(state, level, scheduledTickAccess, currentPos, facing, facingPos, facingState, p_374454_);
        } else {
            return state.canSurvive(level, currentPos) ? state : Blocks.AIR.defaultBlockState();
        }
    }

    @Override
    public boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        TriState soilDecision = level.getBlockState(pos.below()).canSustainPlant(level, pos.below(), Direction.UP, state);
        return isLower(state) && !sufficientLight(level, pos) ? soilDecision.isTrue() : super.canSurvive(state, level, pos);
    }
    @Override
    protected boolean mayPlaceOn(BlockState state, BlockGetter level, BlockPos pos) {
        return state.getBlock() instanceof FarmBlock;
    }
    private static boolean sufficientLight(LevelReader level, BlockPos pos) {
        return CropBlock.hasSufficientLight(level, pos);
    }
    @Override
    public void entityInside(BlockState state, Level level, BlockPos pos, Entity entity) {
        if (level instanceof ServerLevel serverLevel) {
            if (entity instanceof Ravager && serverLevel.getGameRules().getBoolean(GameRules.RULE_MOBGRIEFING)) {
                level.destroyBlock(pos, true, entity);
            }
        }
        super.entityInside(state, level, pos, entity);
    }
    @Override
    public boolean canBeReplaced(BlockState state, BlockPlaceContext useContext) {
        return false;
    }

    /**
     * Called by BlockItem after this block has been placed.
     */
    @Override
    public void setPlacedBy(Level level, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack) {

    }



    private static boolean isDoubleBlock(BlockState state) {
        return state.getValue(AGE) >= BECOMES_DOUBLE_BLOCK;
    }

    private static boolean isLower(BlockState state) {
        return state.is(ModBlocks.CORN_CROP) && state.getValue(HALF) == DoubleBlockHalf.LOWER;
    }


    // This implies that only the bottom block ticks? Hmm. I guess only the bottom block
    // is responsible for growth, which kind of makes sense I guess.
    @Override
    public boolean isRandomlyTicking(BlockState state) {
        return state.getValue(HALF) == DoubleBlockHalf.LOWER && !this.isMaxAge(state);
    }


    private boolean isMaxAge(BlockState state) {
        return state.getValue(AGE) >= MAX_AGE;
    }

    @Override
    public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        float f = getGrowthSpeed(state, level, pos);
        boolean flag = random.nextInt((int)(25.0F / f) + 1) == 0;
        if (flag) {
            this.grow(level, state, pos, 1);
        }
    }
    private void grow(ServerLevel level, BlockState state, BlockPos pos, int ageIncrement) {
        int ageAfter = Math.min(state.getValue(AGE) + ageIncrement, MAX_AGE);
        if (this.canGrow(level, pos, state, ageAfter)) {
            BlockState blockstate = state.setValue(AGE, ageAfter);
            level.setBlock(pos, blockstate, 2);
            if (isDoubleBlock(state)) {
                level.setBlock(pos.above(), blockstate.setValue(HALF, DoubleBlockHalf.UPPER), 3);
            }
        }
    }

    private boolean canGrow(LevelReader reader, BlockPos pos, BlockState state, int age) {
        return !this.isMaxAge(state) && sufficientLight(reader, pos) && (!isDoubleBlock(age) || canGrowInto(reader, pos.above()));
    }

    private boolean isDoubleBlock(int age) {
        return age >= BECOMES_DOUBLE_BLOCK;
    }

    private static boolean canGrowInto(LevelReader level, BlockPos pos) {
        BlockState blockstate = level.getBlockState(pos);
        return blockstate.isAir() || blockstate.is(ModBlocks.CORN_CROP);
    }










    @javax.annotation.Nullable
    private PosAndState getLowerHalf(LevelReader level, BlockPos pos, BlockState state) {
        if (isLower(state)) {
            return new PosAndState(pos, state);
        } else {
            BlockPos blockpos = pos.below();
            BlockState blockstate = level.getBlockState(blockpos);
            return isLower(blockstate) ? new PosAndState(blockpos, blockstate) : null;
        }
    }
    @Override
    public boolean isValidBonemealTarget(LevelReader level, BlockPos pos, BlockState state) {
        PosAndState posAndState = getLowerHalf(level, pos, state);
        return posAndState != null
                && canGrow(level, posAndState.pos, posAndState.state, posAndState.state.getValue(AGE) + 1);
    }

    @Override
    public boolean isBonemealSuccess(Level level, RandomSource random, BlockPos pos, BlockState state) {
        return true;
    }

    @Override
    public void performBonemeal(ServerLevel level, RandomSource random, BlockPos pos, BlockState state) {
        PosAndState posAndState = getLowerHalf(level, pos, state);
        if (posAndState != null) {
            grow(level, posAndState.state, posAndState.pos, 1);
        }
    }

    record PosAndState(BlockPos pos, BlockState state) {
    }

    // Copy paste from CropBlock because it's FUCKING PROTECTEX ACCESS
    private static float getGrowthSpeed(BlockState blockState, BlockGetter p_52274_, BlockPos p_52275_) {
        Block p_52273_ = blockState.getBlock();
        float f = 1.0F;
        BlockPos blockpos = p_52275_.below();

        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                float f1 = 0.0F;
                BlockState blockstate = p_52274_.getBlockState(blockpos.offset(i, 0, j));
                net.neoforged.neoforge.common.util.TriState soilDecision = blockstate.canSustainPlant(p_52274_, blockpos.offset(i, 0, j), net.minecraft.core.Direction.UP, blockState);
                if (soilDecision.isDefault() ? blockstate.getBlock() instanceof net.minecraft.world.level.block.FarmBlock : soilDecision.isTrue()) {
                    f1 = 1.0F;
                    if (blockstate.isFertile(p_52274_, p_52275_.offset(i, 0, j))) {
                        f1 = 3.0F;
                    }
                }

                if (i != 0 || j != 0) {
                    f1 /= 4.0F;
                }

                f += f1;
            }
        }

        BlockPos blockpos1 = p_52275_.north();
        BlockPos blockpos2 = p_52275_.south();
        BlockPos blockpos3 = p_52275_.west();
        BlockPos blockpos4 = p_52275_.east();
        boolean flag = p_52274_.getBlockState(blockpos3).is(p_52273_) || p_52274_.getBlockState(blockpos4).is(p_52273_);
        boolean flag1 = p_52274_.getBlockState(blockpos1).is(p_52273_) || p_52274_.getBlockState(blockpos2).is(p_52273_);
        if (flag && flag1) {
            f /= 2.0F;
        } else {
            boolean flag2 = p_52274_.getBlockState(blockpos3.north()).is(p_52273_)
                    || p_52274_.getBlockState(blockpos4.north()).is(p_52273_)
                    || p_52274_.getBlockState(blockpos4.south()).is(p_52273_)
                    || p_52274_.getBlockState(blockpos3.south()).is(p_52273_);
            if (flag2) {
                f /= 2.0F;
            }
        }

        return f;
    }
}
