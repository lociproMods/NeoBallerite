package com.locipro.neoballerite.block.custom;

import com.locipro.neoballerite.item.ModItems;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.SweetBerryBushBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.neoforged.neoforge.registries.DeferredItem;

import java.util.function.Supplier;

import static net.neoforged.neoforge.common.CommonHooks.canCropGrow;

public class StrawBerryBushBlock extends BushBlock implements BonemealableBlock {


    public static final MapCodec<SweetBerryBushBlock> CODEC = simpleCodec(SweetBerryBushBlock::new);
    private static final float HURT_SPEED_THRESHOLD = 0.003F;
    public static final int MAX_AGE = 5;
    public static final IntegerProperty AGE = BlockStateProperties.AGE_5;
    //    private static final VoxelShape SAPLING_SHAPE = Block.box(3.0, 0.0, 3.0, 13.0, 8.0, 13.0);
//    private static final VoxelShape MID_GROWTH_SHAPE = Block.box(1.0, 0.0, 1.0, 15.0, 16.0, 15.0);
    private static final VoxelShape[] AGE_TO_SHAPE = new VoxelShape[] {
            Block.box(3.0, 0.0, 3.0, 10, 8, 10),
            Block.box(3.0, 0.0, 3.0, 14, 11, 14),
            Block.box(2.0, 0.0, 2.0, 14, 11, 14),
            Block.box(2.0, 0.0, 2.0, 14, 15, 14),
            Block.box(1.0, 0.0, 1.0, 14, 16, 14),
            Block.box(1.0, 0.0, 1.0, 16, 16, 16)
    };
    public DeferredItem<?> RIPE;
    public DeferredItem<?> UNRIPE;
    public DeferredItem<?> SEED_SUPPLIER;
    
    public StrawBerryBushBlock(Properties properties,
                               DeferredItem<?> RIPE, DeferredItem<?> UNRIPE, DeferredItem<?> SEED) {
        super(properties);
        this.RIPE = RIPE;
        this.UNRIPE = UNRIPE;
        this.SEED_SUPPLIER = SEED;
    }

    @Override
    public MapCodec<SweetBerryBushBlock> codec() {
        return CODEC;
    }
    
    @Override
    public ItemStack getCloneItemStack(LevelReader level, BlockPos pos, BlockState state) {
        return new ItemStack(SEED_SUPPLIER.get());
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return AGE_TO_SHAPE[state.getValue(AGE)];
    }

    @Override
    protected boolean isRandomlyTicking(BlockState state) {
        return state.getValue(AGE) < MAX_AGE;
    }
    
    @Override
    protected void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        int i = state.getValue(AGE);
        if (i < MAX_AGE && level.getRawBrightness(pos.above(), 0) >= 9 && canCropGrow(level, pos, state, random.nextInt(5) == 0)) {
            BlockState blockstate = state.setValue(AGE, Integer.valueOf(i + 1));
            level.setBlock(pos, blockstate, 2);
            net.neoforged.neoforge.common.CommonHooks.fireCropGrowPost(level, pos, state);
            level.gameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Context.of(blockstate));
        }
    }

    // TODO IMPLEMENT FARMERS SHOES
    @Override
    protected void entityInside(BlockState state, Level level, BlockPos pos, Entity entity) {
        if (entity instanceof LivingEntity && entity.getType() != EntityType.FOX && entity.getType() != EntityType.BEE) {
            entity.makeStuckInBlock(state, new Vec3(0.8F, 0.75, 0.8F));
            if (!level.isClientSide && state.getValue(AGE) > 0 && (entity.xOld != entity.getX() || entity.zOld != entity.getZ())) {
                double d0 = Math.abs(entity.getX() - entity.xOld);
                double d1 = Math.abs(entity.getZ() - entity.zOld);
                if (d0 >= 0.003F || d1 >= 0.003F) {
                    entity.hurt(level.damageSources().sweetBerryBush(), 1.0F);
                }
            }
        }
    }

    @Override
    protected ItemInteractionResult useItemOn(
            ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult
    ) {
        int i = state.getValue(AGE);
        boolean fullyGrown = i == MAX_AGE;
        return !fullyGrown && stack.is(Items.BONE_MEAL)
                ? ItemInteractionResult.SKIP_DEFAULT_BLOCK_INTERACTION
                : super.useItemOn(stack, state, level, pos, player, hand, hitResult);
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
        int i = state.getValue(AGE);
        boolean fullyGrown = i == MAX_AGE;
        if (i == 4) {
            int count = 1 + level.random.nextInt(2);
            popResource(level, pos, new ItemStack(UNRIPE.get(), count));
            level.playSound(
                    null, pos, SoundEvents.SWEET_BERRY_BUSH_PICK_BERRIES, SoundSource.BLOCKS, 1.0F, 0.7F + level.random.nextFloat() * 0.4F
            );
            BlockState blockstate = state.setValue(AGE, 1);
            level.setBlock(pos, blockstate, 2);
            level.gameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Context.of(player, blockstate));
            return InteractionResult.sidedSuccess(level.isClientSide);
        }
        else if (i == 5) {
            int j = 1 + level.random.nextInt(2);
            popResource(level, pos, new ItemStack(RIPE.get(), j ));
            level.playSound(null, pos, SoundEvents.SWEET_BERRY_BUSH_PICK_BERRIES, SoundSource.BLOCKS, 1.0f, 1.0f + level.random.nextFloat() * 0.4f);
            BlockState blockState = state.setValue(AGE, 1);
            level.setBlock(pos, blockState, 2);
            level.gameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Context.of(player, blockState));
            return InteractionResult.sidedSuccess(level.isClientSide);
        }
        else {
            return super.useWithoutItem(state, level, pos, player, hitResult);
        }
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(AGE);
    }

    @Override
    public boolean isValidBonemealTarget(LevelReader level, BlockPos pos, BlockState state) {
        return state.getValue(AGE) < MAX_AGE;
    }

    @Override
    public boolean isBonemealSuccess(Level level, RandomSource random, BlockPos pos, BlockState state) {
        return true;
    }

    @Override
    public void performBonemeal(ServerLevel level, RandomSource random, BlockPos pos, BlockState state) {
        int i = Math.min(MAX_AGE, state.getValue(AGE) + 1);
        level.setBlock(pos, state.setValue(AGE, i), 2);
    }
}
