package com.locipro.neoballerite.block.custom;

import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.WallHangingSignBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.phys.HitResult;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

public class WallHangingSignBlockFix extends WallHangingSignBlock {
    Supplier<Item> pickStack;

    public WallHangingSignBlockFix(WoodType type, Properties properties, Supplier<Item> pickStack) {
        super(type, properties);
        this.pickStack = pickStack;
    }

    // this used to be a fix for some silly behaviour, now what do I do... This is what happens when you bodge shit.
    @Override
    public @NotNull String getDescriptionId() {
        return Util.makeDescriptionId("block", BuiltInRegistries.BLOCK.getKey(this)).replace("wall_", "");
    }



    @Override
    public @NotNull ItemStack getCloneItemStack(LevelReader level, BlockPos pos, BlockState state, boolean includeData, Player player) {
        return new ItemStack(pickStack.get());
    }
}
