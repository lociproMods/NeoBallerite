package com.locipro.neoballerite.block.custom;

import net.minecraft.Util;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.WallSignBlock;
import net.minecraft.world.level.block.state.properties.WoodType;
import org.jetbrains.annotations.NotNull;

public class WallSignBlockFix extends WallSignBlock {

    public WallSignBlockFix(WoodType type, Properties properties) {
        super(type, properties);
    }

    @Override
    public @NotNull String getDescriptionId() {
        return Util.makeDescriptionId("block", BuiltInRegistries.BLOCK.getKey(this)).replace("wall_", "");
    }
}
