package com.locipro.neoballerite.worldgen.ore;

import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.*;

import java.util.List;

import static com.locipro.neoballerite.worldgen.NeoPlacedFeatures.createKey;

public class NeoOrePlacement {
    public static final ResourceKey<PlacedFeature> ORE_BALLERITE = createKey("ore_ballerite");
    public static final ResourceKey<PlacedFeature> ORE_LEAD = createKey("ore_lead");
    public static final ResourceKey<PlacedFeature> ORE_LEAD_BURIED = createKey("ore_lead_buried");

    public static void bootstrap(BootstrapContext<PlacedFeature> context) {
        // From my understanding, configured features are, the features themselves.
        // They are registered, then they are linked here to their corresponding Placements.
        // Placed features are Configured features with Placement modifiers.
        // Then placed features go into biome modifiers to actually be- well, placed.
        HolderGetter<ConfiguredFeature<?, ?>> holdergetter = context.lookup(Registries.CONFIGURED_FEATURE);

        Holder<ConfiguredFeature<?, ?>> ballerite = holdergetter.getOrThrow(NeoOreFeatures.ORE_BALLERITE);
        Holder<ConfiguredFeature<?, ?>> lead = holdergetter.getOrThrow(NeoOreFeatures.ORE_LEAD);
        Holder<ConfiguredFeature<?, ?>> lead_buried = holdergetter.getOrThrow(NeoOreFeatures.ORE_LEAD_BURIED);



        // Triangle range is bionomial. max+min/2 has the highest stuffs.
        // I think CountPlacement is per chunk?
        PlacementUtils.register(context, ORE_BALLERITE, ballerite,
                orePlacement(CountPlacement.of(10), HeightRangePlacement.triangle(VerticalAnchor.absolute(4), VerticalAnchor.absolute(80))));
        PlacementUtils.register(context, ORE_LEAD, lead,
                orePlacement(CountPlacement.of(12), HeightRangePlacement.triangle(VerticalAnchor.absolute(-16), VerticalAnchor.absolute(66))));
        PlacementUtils.register(context, ORE_LEAD_BURIED, lead_buried,
                orePlacement(CountPlacement.of(12), HeightRangePlacement.triangle(VerticalAnchor.absolute(-50), VerticalAnchor.absolute(4))));
    }



    private static List<PlacementModifier> orePlacement(PlacementModifier countPlacement, PlacementModifier heightRange) {
        return List.of(countPlacement, InSquarePlacement.spread(), heightRange, BiomeFilter.biome());
    }

    private static List<PlacementModifier> commonOrePlacement(int count, PlacementModifier heightRange) {
        return orePlacement(CountPlacement.of(count), heightRange);
    }

    private static List<PlacementModifier> rareOrePlacement(int chance, PlacementModifier heightRange) {
        return orePlacement(RarityFilter.onAverageOnceEvery(chance), heightRange);
    }
}
