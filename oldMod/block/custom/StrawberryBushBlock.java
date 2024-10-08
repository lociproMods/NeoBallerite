package net.locipro.balleritemod.block.custom;

import com.mojang.serialization.MapCodec;
import net.locipro.balleritemod.item.ModArmorMaterials;
import net.locipro.balleritemod.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import java.util.function.Supplier;

public class StrawberryBushBlock
        extends BushBlock
        implements BonemealableBlock {
//    public static final int MAX_AGE = 5;
    public static final IntegerProperty AGE = BlockStateProperties.AGE_5;

    private static final VoxelShape[] AGE_TO_SHAPE = new VoxelShape[] {
            Block.box(3.0, 0.0, 3.0, 10, 8, 10),
            Block.box(3.0, 0.0, 3.0, 14, 11, 14),
            Block.box(2.0, 0.0, 2.0, 14, 11, 14),
            Block.box(2.0, 0.0, 2.0, 14, 15, 14),
            Block.box(1.0, 0.0, 1.0, 14, 16, 14),
            Block.box(1.0, 0.0, 1.0, 16, 16, 16)
    };
    public static final Supplier<Item> STRAWBERRY_SUPPLIER = () -> ModItems.STRAWBERRY;
    public static final Supplier<Item> UNRIPE_STRAWBERRY_SUPPLIER = () -> ModItems.UNRIPE_STRAWBERRY;
    public static final Supplier<Item> STRAWBERRY_SEEDS_SUPPLIER = () -> ModItems.STRAWBERRY_SEEDS;
    public StrawberryBushBlock(Properties settings) {
        super(settings);
        this.registerDefaultState((this.stateDefinition.any()).setValue(AGE, 0));
    }
    public static final MapCodec<StrawberryBushBlock> CODEC = StrawberryBushBlock.simpleCodec(StrawberryBushBlock::new);



    @Override
    public ItemStack getCloneItemStack(LevelReader world, BlockPos pos, BlockState state) {
        return new ItemStack(STRAWBERRY_SEEDS_SUPPLIER.get());
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
         return AGE_TO_SHAPE[state.getValue(AGE)];
    }

    @Override
    public boolean isRandomlyTicking(BlockState state) {
        return state.getValue(AGE) < 5;
    }

    @Override
    public void randomTick(BlockState state, ServerLevel world, BlockPos pos, RandomSource random) {
        if (!world.isClientSide()) {
            int i = state.getValue(AGE);
            if (i < 5 && random.nextInt(7) == 0 && world.getRawBrightness(pos.above(), 0) >= 9) {
                BlockState blockState = state.setValue(AGE, i + 1);
                world.setBlock(pos, blockState, Block.UPDATE_CLIENTS);
                world.gameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Context.of(blockState));
            }
        }
    }

    @Override
    public void entityInside(BlockState state, Level world, BlockPos pos, Entity entity) {
        if (!(entity instanceof LivingEntity) || entity.getType() == EntityType.FOX || entity.getType() == EntityType.BEE) {
            return;
        }
        if (state.getValue(AGE) < 2) {
            return;
        }
        if (entity instanceof Player player) {
            if (player.getInventory().getArmor(0).getItem() instanceof ArmorItem armorItem) {
                if (armorItem.getMaterial() == ModArmorMaterials.LEAVES) {
                    return;
                }
            }
        }
        entity.makeStuckInBlock(state, new Vec3(0.8f, 0.75, 0.8f));
        if (!(world.isClientSide || state.getValue(AGE) <= 0 || entity.xOld == entity.getX() && entity.zOld == entity.getZ())) {
            double d = Math.abs(entity.getX() - entity.xOld);
            double e = Math.abs(entity.getZ() - entity.zOld);
            if (d >= (double)0.003f || e >= (double)0.003f) {
                entity.hurt(world.damageSources().sweetBerryBush(), 1.0f);
            }
        }
    }

    @Override
    public InteractionResult use(BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        if (!world.isClientSide()) {
            boolean bl;
            int i = state.getValue(AGE);
            bl = i == 5;
            if (!bl && player.getItemInHand(hand).is(Items.BONE_MEAL)) {
                return InteractionResult.PASS;
            }
            if (i == 5) {
                int j = 1 + world.random.nextInt(1);
                StrawberryBushBlock.popResource(world, pos, new ItemStack(STRAWBERRY_SUPPLIER.get(), j + 1));
                world.playSound(null, pos, SoundEvents.SWEET_BERRY_BUSH_PICK_BERRIES, SoundSource.BLOCKS, 1.0f, 0.8f + world.random.nextFloat() * 0.4f);
                BlockState blockState = state.setValue(AGE, 1);
                world.setBlock(pos, blockState, Block.UPDATE_CLIENTS);
                world.gameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Context.of(player, blockState));
                return InteractionResult.sidedSuccess(true);
            }
            if (i == 4) {
                int j = 1 + world.random.nextInt(1);
                StrawberryBushBlock.popResource(world, pos, new ItemStack(UNRIPE_STRAWBERRY_SUPPLIER.get(), j));
                world.playSound(null, pos, SoundEvents.SWEET_BERRY_BUSH_PICK_BERRIES, SoundSource.BLOCKS, 1.0f, 0.8f + world.random.nextFloat() * 0.4f);
                BlockState blockState = state.setValue(AGE, 1);
                world.setBlock(pos, blockState, Block.UPDATE_CLIENTS);
                world.gameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Context.of(player, blockState));
                return InteractionResult.sidedSuccess(true);
            }
        }
        return super.use(state, world, pos, player, hand, hit);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(AGE);
    }



    @Override
    public boolean isValidBonemealTarget(LevelReader world, BlockPos pos, BlockState state) {
        return state.getValue(AGE) < 5;
    }

    @Override
    public boolean isBonemealSuccess(Level world, RandomSource random, BlockPos pos, BlockState state) {
        return true;
    }

    @Override
    public void performBonemeal(ServerLevel world, RandomSource random, BlockPos pos, BlockState state) {
        int i = Math.min(5, state.getValue(AGE) + 1);
        world.setBlock(pos, state.setValue(AGE, i), Block.UPDATE_CLIENTS);
    }

    @Override
    protected MapCodec<? extends BushBlock> codec() {
        return CODEC;
    }
}