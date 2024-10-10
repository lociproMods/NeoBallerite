package com.locipro.neoballerite.worldgen.vegetation.flower;

import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.BiomeFilter;
import net.minecraft.world.level.levelgen.placement.InSquarePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.RarityFilter;

import static com.locipro.neoballerite.worldgen.NeoPlacedFeatures.createKey;

public class NeoBerryPlacements {
    public static ResourceKey<PlacedFeature> BLUEBERRY_BUSH = createKey("blueberry_bush");
    public static ResourceKey<PlacedFeature> BLACKBERRY_BUSH = createKey("blackberry_bush");
    public static ResourceKey<PlacedFeature> STRAWBERRY_BUSH = createKey("strawberry_bush");


    public static void bootstrap(BootstrapContext<PlacedFeature> context) {
        HolderGetter<ConfiguredFeature<?, ?>> holderGetter = context.lookup(Registries.CONFIGURED_FEATURE);
        Holder<ConfiguredFeature<?, ?>> blueberry_bush = holderGetter.getOrThrow(NeoBerryFeatures.BLUEBERRY_BUSH);
        Holder<ConfiguredFeature<?, ?>> blackberry_bush = holderGetter.getOrThrow(NeoBerryFeatures.BLACKBERRY_BUSH);
        Holder<ConfiguredFeature<?, ?>> strawberry_bush = holderGetter.getOrThrow(NeoBerryFeatures.STRAWBERRY_BUSH);


        PlacementUtils.register(context, BLUEBERRY_BUSH, blueberry_bush,
                RarityFilter.onAverageOnceEvery(16),
                InSquarePlacement.spread(),
                PlacementUtils.HEIGHTMAP_WORLD_SURFACE,
                BiomeFilter.biome());
        PlacementUtils.register(context, BLACKBERRY_BUSH, blackberry_bush,
                RarityFilter.onAverageOnceEvery(20),
                InSquarePlacement.spread(),
                PlacementUtils.HEIGHTMAP_WORLD_SURFACE,
                BiomeFilter.biome());
        PlacementUtils.register(context, STRAWBERRY_BUSH, strawberry_bush,
                RarityFilter.onAverageOnceEvery(24),
                InSquarePlacement.spread(),
                PlacementUtils.HEIGHTMAP_WORLD_SURFACE,
                BiomeFilter.biome());
    }


}
