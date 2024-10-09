package com.locipro.neoballerite.worldgen.vegetation.tree;

import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

import static com.locipro.neoballerite.block.ModBlocks.*;
import static com.locipro.neoballerite.worldgen.NeoPlacedFeatures.createKey;

// We could add more placement modifiers to say how many there should be per chunk, but we're only checking the block below
// because we will add these as a VegetationFeature later on, and there we shall add our count, etc.

// You could hypothetically add that here and be done (with the PlacedFeature)
public class NeoTreePlacements {
    public static final ResourceKey<PlacedFeature> WITHERED_TREE_CHECKED = createKey("withered_tree_checked");
    public static final ResourceKey<PlacedFeature> FANCY_WITHERED_TREE_CHECKED = createKey("fancy_withered_tree_checked");
    public static final ResourceKey<PlacedFeature> STAR_TREE_CHECKED = createKey("star_tree_checked");
    public static final ResourceKey<PlacedFeature> STRIPPED_STAR_TREE_CHECKED = createKey("stripped_star_tree_checked");


    public static void bootstrap(BootstrapContext<PlacedFeature> context) {
        HolderGetter<ConfiguredFeature<?, ?>> holderGetter = context.lookup(Registries.CONFIGURED_FEATURE);

        Holder<ConfiguredFeature<?, ?>> withered_tree_feature = holderGetter.getOrThrow(NeoTreeFeatures.WITHERED_TREE);
        Holder<ConfiguredFeature<?, ?>> fancy_withered_tree_feature = holderGetter.getOrThrow(NeoTreeFeatures.FANCY_WITHERED_TREE);
        Holder<ConfiguredFeature<?, ?>> star_tree_feature = holderGetter.getOrThrow(NeoTreeFeatures.STAR_TREE);
        Holder<ConfiguredFeature<?, ?>> stripped_star_tree_feature = holderGetter.getOrThrow(NeoTreeFeatures.STRIPPED_STAR_TREE);

        PlacementUtils.register(context, WITHERED_TREE_CHECKED, withered_tree_feature, PlacementUtils.filteredByBlockSurvival(WITHERED_SAPLING.get()));
        PlacementUtils.register(context, FANCY_WITHERED_TREE_CHECKED, fancy_withered_tree_feature, PlacementUtils.filteredByBlockSurvival(WITHERED_SAPLING.get()));
        PlacementUtils.register(context, STAR_TREE_CHECKED, star_tree_feature, PlacementUtils.filteredByBlockSurvival(STAR_SAPLING.get()));
        PlacementUtils.register(context, STRIPPED_STAR_TREE_CHECKED, stripped_star_tree_feature, PlacementUtils.filteredByBlockSurvival(STAR_SAPLING.get()));
    }
}
