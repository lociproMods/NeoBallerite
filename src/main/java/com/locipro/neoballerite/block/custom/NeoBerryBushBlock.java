package com.locipro.neoballerite.block.custom;

import com.locipro.neoballerite.item.armor.BushNegatingArmorItem;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.SweetBerryBushBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

public class NeoBerryBushBlock extends SweetBerryBushBlock {
    Supplier<ItemLike> berryDrops;
    int baseDropCount;
    public NeoBerryBushBlock(Properties properties, Supplier<ItemLike> berryDrops, int baseDropCount) {
        super(properties);
        this.berryDrops = berryDrops;
        this.baseDropCount = baseDropCount;
    }

    @Override
    public @NotNull ItemStack getCloneItemStack(BlockState state, HitResult target, LevelReader level, BlockPos pos, Player player) {
        return new ItemStack(berryDrops.get());
    }



    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
        int i = state.getValue(AGE);
        boolean fullyGrown = i == 3;
        if (i > 1) {
            int j = baseDropCount + level.random.nextInt(2);
            popResource(level, pos, new ItemStack(berryDrops.get(), baseDropCount + (fullyGrown ? 1 : 0)));
            level.playSound(
                    null, pos, SoundEvents.SWEET_BERRY_BUSH_PICK_BERRIES, SoundSource.BLOCKS, 1.0F, 1.0F + level.random.nextFloat() * 0.4F
            );
            BlockState blockstate = state.setValue(AGE, 1);
            level.setBlock(pos, blockstate, 2);
            level.gameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Context.of(player, blockstate));
            return InteractionResult.sidedSuccess(level.isClientSide);
        } else {
            return super.useWithoutItem(state, level, pos, player, hitResult);
        }
    }

    @Override
    protected void entityInside(BlockState state, Level level, BlockPos pos, Entity entity) {
        if (entity instanceof LivingEntity && entity.getType() != EntityType.FOX && entity.getType() != EntityType.BEE) {
            if (entity instanceof Player player) {
                for (ItemStack itemStack : player.getInventory().armor)
                {
                    if (itemStack.getItem() instanceof BushNegatingArmorItem armorItem)
                    {
                        armorItem.onBushEntry(state, level, itemStack, player);
                        return;
                    }
                }
            }

            entity.makeStuckInBlock(state, new Vec3(0.8F, 0.75, 0.8F));

            boolean entityMoved = entity.xOld != entity.getX() || entity.zOld != entity.getZ();
            if (!level.isClientSide
                    && state.getValue(AGE) > 0
                    && entityMoved) {
                double d0 = Math.abs(entity.getX() - entity.xOld);
                double d1 = Math.abs(entity.getZ() - entity.zOld);
                if (d0 >= 0.003F || d1 >= 0.003F) {
                    entity.hurt(level.damageSources().sweetBerryBush(), 1.0F);
                }
            }
        }
    }
}