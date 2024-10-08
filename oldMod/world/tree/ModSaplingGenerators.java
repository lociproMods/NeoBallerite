package net.locipro.balleritemod.world.tree;

import net.locipro.balleritemod.world.ModConfiguredFeatures;
import net.minecraft.world.level.block.grower.TreeGrower;
import java.util.Optional;

public class ModSaplingGenerators {
    public static final TreeGrower STAR = new TreeGrower("star", 0f, Optional.empty(), Optional.empty(), Optional.of(ModConfiguredFeatures.STAR_TREE_KEY), Optional.empty(), Optional.empty(), Optional.empty());
    public static final TreeGrower WITHERED = new TreeGrower("withered", 0f, Optional.empty(), Optional.empty(), Optional.of(ModConfiguredFeatures.STAR_TREE_KEY), Optional.empty(), Optional.empty(), Optional.empty());
}
