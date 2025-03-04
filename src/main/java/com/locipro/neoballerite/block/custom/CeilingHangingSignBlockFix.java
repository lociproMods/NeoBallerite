package com.locipro.neoballerite.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.CeilingHangingSignBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.phys.HitResult;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

public class CeilingHangingSignBlockFix extends CeilingHangingSignBlock {
    Supplier<Item> pickStack;
    public CeilingHangingSignBlockFix(WoodType type, Properties properties, Supplier<Item> pickStack) {
        super(type, properties);
        this.pickStack = pickStack;
    }


    @Override
    public @NotNull ItemStack getCloneItemStack(LevelReader level, BlockPos pos, BlockState state, boolean includeData, Player player) {
        return new ItemStack(pickStack.get());
    }
}
