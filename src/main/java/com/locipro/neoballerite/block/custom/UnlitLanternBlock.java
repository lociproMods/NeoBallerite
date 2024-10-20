package com.locipro.neoballerite.block.custom;

import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LanternBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.neoforged.neoforge.common.ItemAbilities;
import net.neoforged.neoforge.common.ItemAbility;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

public class UnlitLanternBlock extends LanternBlock {
    private Supplier<? extends Block> litLantern;

    public UnlitLanternBlock(Properties properties, Supplier<? extends Block> litLantern) {
        super(properties.lightLevel(state -> 0));
        this.litLantern = litLantern;
    }
    ItemPredicate itemCanLight = ItemPredicate.Builder.item().of(
            Items.TORCH,
            Items.SOUL_TORCH,
            Items.REDSTONE_TORCH)
            .build(); // Flint and steel and fire charge are covered under ItemAbilities.FIRESTARTER_LIGHT

    @Override
    protected @NotNull ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        if (itemCanLight.test(stack) || stack.canPerformAction(ItemAbilities.FIRESTARTER_LIGHT)) {
            if (stack.isDamageableItem()) {
                stack.hurtAndBreak(1, player, LivingEntity.getSlotForHand(hand));
            }
            level.setBlock(pos, litLantern.get().defaultBlockState(), 1);
            return ItemInteractionResult.sidedSuccess(level.isClientSide);
        }
        return super.useItemOn(stack, state, level, pos, player, hand, hitResult);
    }
}
