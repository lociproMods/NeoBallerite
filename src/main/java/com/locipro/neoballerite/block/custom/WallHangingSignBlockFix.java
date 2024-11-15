package com.locipro.neoballerite.block.custom;

import net.minecraft.Util;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.WallHangingSignBlock;
import net.minecraft.world.level.block.state.properties.WoodType;
import org.jetbrains.annotations.NotNull;

public class WallHangingSignBlockFix extends WallHangingSignBlock {
    public WallHangingSignBlockFix(WoodType type, Properties properties) {
        super(type, properties);
    }

    @Override
    public @NotNull String getDescriptionId() {
        return Util.makeDescriptionId("block", BuiltInRegistries.BLOCK.getKey(this)).replace("wall_", "");
    }
}
