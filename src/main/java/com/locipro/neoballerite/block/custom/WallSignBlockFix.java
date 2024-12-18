package com.locipro.neoballerite.block.custom;

import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.WallSignBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.phys.HitResult;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

public class WallSignBlockFix extends WallSignBlock {

    Supplier<Item> pickStack;
    public WallSignBlockFix(WoodType type, Properties properties, Supplier<Item> pickStack) {
        super(type, properties);
        this.pickStack = pickStack;
    }

    @Override
    public @NotNull String getDescriptionId() {
        return Util.makeDescriptionId("block", BuiltInRegistries.BLOCK.getKey(this)).replace("wall_", "");
    }

    @Override
    public ItemStack getCloneItemStack(BlockState state, HitResult target, LevelReader level, BlockPos pos, Player player) {
        return new ItemStack(pickStack.get());
    }
}
