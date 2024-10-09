package com.locipro.neoballerite.worldgen.vegetation.vegetation;

import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

import static com.locipro.neoballerite.worldgen.NeoPlacedFeatures.*;
import static net.minecraft.data.worldgen.placement.VegetationPlacements.treePlacement;

// The placed features of our BUNDLED UP configured features in NeoVegetationFeatures
public class NeoVegetationPlacements {
    public static final ResourceKey<PlacedFeature> TREES_WITHERED = createKey("trees_withered");
    public static final ResourceKey<PlacedFeature> TREES_STAR = createKey("trees_star");
//    public static final ResourceKey<PlacedFeature> WITHERED_TREE = createKey("withered_tree");
//    public static final ResourceKey<PlacedFeature> FANCY_WITHERED_TREE = createKey("fancy_withered_tree");

    public static void bootstrap(BootstrapContext<PlacedFeature> context) {
        HolderGetter<ConfiguredFeature<?, ?>> holderGetter = context.lookup(Registries.CONFIGURED_FEATURE);

          Holder<ConfiguredFeature<?, ?>> trees_withered = holderGetter.getOrThrow(NeoVegetationFeatures.TREES_WITHERED);
          Holder<ConfiguredFeature<?, ?>> trees_star = holderGetter.getOrThrow(NeoVegetationFeatures.TREES_STAR);
//        Holder<ConfiguredFeature<?, ?>> withered_tree = holderGetter.getOrThrow(NeoTreeFeatures.WITHERED_TREE);
//        Holder<ConfiguredFeature<?, ?>> fancy_withered_tree = holderGetter.getOrThrow(NeoTreeFeatures.FANCY_WITHERED_TREE);

        PlacementUtils.register(
                context, TREES_WITHERED, trees_withered,
                treePlacement(PlacementUtils.countExtra(1, 0.05f, 1))
        );

        PlacementUtils.register(
                context, TREES_STAR, trees_star,
                treePlacement(PlacementUtils.countExtra(2, 0.05f, 1))
        );
    }
}
