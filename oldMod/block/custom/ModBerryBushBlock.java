package net.locipro.balleritemod.block.custom;


import net.locipro.balleritemod.item.ModArmorMaterials;
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
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SweetBerryBushBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import java.util.function.Supplier;

public class ModBerryBushBlock extends SweetBerryBushBlock {
    final int baseDropCount;
    final Supplier<Item> itemSupplier;

    public ModBerryBushBlock(Properties settings, int baseDropCount, Supplier<Item> itemSupplier) {
        super(settings);
        this.baseDropCount = baseDropCount;
        this.itemSupplier = itemSupplier;
    }
    public ItemStack getPickStack(BlockGetter world, BlockPos pos, BlockState state) {
        return new ItemStack(itemSupplier.get());
    }
    @Override
    public InteractionResult use(BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        if (!world.isClientSide()) {
            boolean bl;
            int i = state.getValue(AGE);
            bl = i == 3;
            if (!bl && player.getItemInHand(hand).is(Items.BONE_MEAL)) {
                return InteractionResult.PASS;
            }
            if (i > 1) {
                int j = baseDropCount + world.random.nextInt(2);
                SweetBerryBushBlock.popResource(world, pos, new ItemStack(itemSupplier.get(), j + (bl ? 1 : 0)));
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
    public void randomTick(BlockState state, ServerLevel world, BlockPos pos, RandomSource random) {
        int i = state.getValue(AGE);
        if (i < 3 && random.nextInt(9) == 0 && world.getRawBrightness(pos.above(), 0) >= 9) {
            BlockState blockState = state.setValue(AGE, i + 1);
            world.setBlock(pos, blockState, Block.UPDATE_CLIENTS);
            world.gameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Context.of(blockState));
        }
    }

    @Override
    public void entityInside(BlockState state, Level world, BlockPos pos, Entity entity) {
        if (!(entity instanceof LivingEntity) || entity.getType() == EntityType.FOX || entity.getType() == EntityType.BEE) {
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
}
