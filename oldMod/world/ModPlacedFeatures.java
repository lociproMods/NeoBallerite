package net.locipro.balleritemod.world;

import net.locipro.balleritemod.BalleriteMod;
import net.locipro.balleritemod.block.ModBlocks;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.placement.BiomeFilter;
import net.minecraft.world.level.levelgen.placement.HeightRangePlacement;
import net.minecraft.world.level.levelgen.placement.InSquarePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;
import net.minecraft.world.level.levelgen.placement.RarityFilter;
import java.util.List;


/**
 * <pre> From what I understand, World gen in 1.19.4 works like this : Funky business in Json files, then the code is like this:
 *      Configured features : are the feature themselves, for example, the treee, how it looks, the leaves, etc.
 *
 *      Placed features : are how the feature spawns in the world. Aka their placement, their count, etc.
 *          How to register them: register(context, *pf registry key*, .getOrThrow(*cf key*),    type of placed feature.feature, modifiers for that)
 *
 *
 *  bootstrap method gets called in BalleriteModDataGenerator, and it generates all the json files necessary for world gen.
 *
 *      Refer to ConfiguredFeatures's bootstrap method for the different types of vanilla features
 *      Refer to PlacedFeatures's bootstrap method for the different types of vanilla features
 *
 *
 *  After you make the configured features and placed features, go to world.gen.ModXGeneration and follow the code there</pre>
 *   <b>FOR VANILLA CONFIGURED FEATURES, RETURN TO ConfiguredFeatures BOOTSTRAP METHOD.</b>
 */
public class ModPlacedFeatures {
    public static final ResourceKey<PlacedFeature> WITHERED_TREE_PLACED_KEY = registerKey("withered_tree_placed");
    public static final ResourceKey<PlacedFeature> STAR_TREE_PLACED_KEY = registerKey("star_tree_placed");
    public static final ResourceKey<PlacedFeature> BALLERITE_ORE_PLACED_KEY = registerKey("ballerite_ore_placed");
    public static final ResourceKey<PlacedFeature> LEAD_ORE_PLACED_KEY = registerKey("lead_ore_placed");
    public static final ResourceKey<PlacedFeature> BURIED_LEAD_ORE_PLACED_KEY = registerKey("buried_lead_ore_placed");

    public static final ResourceKey<PlacedFeature> TOMATO_FLOWER_PLACED_KEY = registerKey("tomato_flower_placed");
    public static final ResourceKey<PlacedFeature> SWEET_POTATO_PATCH_PLACED_KEY = registerKey("sweet_potato_patch_placed");
    public static final ResourceKey<PlacedFeature> BLUE_BERRY_PATCH_PLACED_KEY = registerKey("blue_berry_patch_placed");
    public static final ResourceKey<PlacedFeature> BLACK_BERRY_PATCH_PLACED_KEY = registerKey("black_berry_patch_placed");
    public static final ResourceKey<PlacedFeature> STRAWBERRY_PATCH_PLACED_KEY = registerKey("strawberry_patch_placed");

    public static void bootstrap(BootstapContext<PlacedFeature> context) {
        var configuredFeatureRegistryEntryLookup = context.lookup(Registries.CONFIGURED_FEATURE);

        register(context, WITHERED_TREE_PLACED_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.WITHERED_TREE_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(1, 0.04f, 1), ModBlocks.WITHERED_SAPLING));
        register(context, STAR_TREE_PLACED_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.STAR_TREE_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(1, 0.05f, 1), ModBlocks.WITHERED_SAPLING));

        register(context, BALLERITE_ORE_PLACED_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.BALLERITE_ORE_KEY),
                ModOrePlacement.modifiersWithCount(16, HeightRangePlacement.triangle(VerticalAnchor.absolute(12), VerticalAnchor.absolute(60))));
        register(context, LEAD_ORE_PLACED_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.LEAD_ORE_KEY),
                ModOrePlacement.modifiersWithCount(24, HeightRangePlacement.triangle(VerticalAnchor.absolute(-36), VerticalAnchor.absolute(80))));
        register(context, BURIED_LEAD_ORE_PLACED_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.BURIED_LEAD_ORE_KEY),
                ModOrePlacement.modifiersWithCount(12, HeightRangePlacement.uniform(VerticalAnchor.absolute(-36), VerticalAnchor.absolute(-64))));


        register(context, TOMATO_FLOWER_PLACED_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.TOMATO_FLOWER_KEY),
                RarityFilter.onAverageOnceEvery(20), InSquarePlacement.spread(),
                PlacementUtils.HEIGHTMAP, BiomeFilter.biome());
        register(context, SWEET_POTATO_PATCH_PLACED_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.SWEET_POTATO_PATCH_KEY),
                RarityFilter.onAverageOnceEvery(255), InSquarePlacement.spread(),
                PlacementUtils.HEIGHTMAP, BiomeFilter.biome());

        register(context, BLUE_BERRY_PATCH_PLACED_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.BLUE_BERRY_PATCH_KEY),
                RarityFilter.onAverageOnceEvery(16), InSquarePlacement.spread(),
                PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome());
        register(context, BLACK_BERRY_PATCH_PLACED_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.BLACK_BERRY_PATCH_KEY),
                RarityFilter.onAverageOnceEvery(16), InSquarePlacement.spread(),
                PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome());
        register(context, STRAWBERRY_PATCH_PLACED_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.STRAWBERRY_PATCH_KEY),
                RarityFilter.onAverageOnceEvery(12), InSquarePlacement.spread(),
                PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome());
    }

    public static ResourceKey<PlacedFeature> registerKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, new ResourceLocation(BalleriteMod.MOD_ID, name));
    }

    private static void register(BootstapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key, Holder<ConfiguredFeature<?, ?>> configuration,
                                 List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key,
                                                                                   Holder<ConfiguredFeature<?, ?>> configuration,
                                                                                   PlacementModifier... modifiers) {
        register(context, key, configuration, List.of(modifiers));
    }
}
