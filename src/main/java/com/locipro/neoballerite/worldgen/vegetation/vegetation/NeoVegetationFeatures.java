package com.locipro.neoballerite.worldgen.vegetation.vegetation;

import com.locipro.neoballerite.worldgen.vegetation.tree.NeoTreePlacements;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.WeightedPlacedFeature;
import net.minecraft.world.level.levelgen.feature.configurations.RandomFeatureConfiguration;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

import java.util.List;

import static com.locipro.neoballerite.worldgen.NeoConfiguredFeatures.createKey;


// We're just bundling up our *configured* features, into one vegetation feature.
public class NeoVegetationFeatures {
    public static final ResourceKey<ConfiguredFeature<?, ?>> TREES_WITHERED = createKey("trees_withered");

    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context) {
        HolderGetter<PlacedFeature> holderGetter = context.lookup(Registries.PLACED_FEATURE);

        Holder<PlacedFeature> withered_tree = holderGetter.getOrThrow(NeoTreePlacements.WITHERED_TREE_CHECKED);
        Holder<PlacedFeature> fancy_withered_tree = holderGetter.getOrThrow(NeoTreePlacements.FANCY_WITHERED_TREE_CHECKED);

        FeatureUtils.register(
                context, TREES_WITHERED, Feature.RANDOM_SELECTOR,
                new RandomFeatureConfiguration(List.of(new WeightedPlacedFeature(withered_tree, 0.15f), new WeightedPlacedFeature(fancy_withered_tree, 0.08f)),
                        withered_tree)
        );
    }
}
