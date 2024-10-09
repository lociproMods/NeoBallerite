package com.locipro.neoballerite.block;

import com.locipro.neoballerite.worldgen.vegetation.tree.NeoTreeFeatures;
import net.minecraft.data.worldgen.features.TreeFeatures;
import net.minecraft.world.level.block.grower.TreeGrower;

import java.util.Optional;

public class NeoTreeGrowers {
    public static final TreeGrower WITHERED = new TreeGrower(
            "withered",
            0.1F,
            Optional.empty(),
            Optional.empty(),
            Optional.of(NeoTreeFeatures.WITHERED_TREE),
            Optional.of(NeoTreeFeatures.FANCY_WITHERED_TREE),
            Optional.empty(),
            Optional.empty()
    );
}
