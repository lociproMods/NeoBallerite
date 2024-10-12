package com.locipro.neoballerite.worldgen.vegetation;

import com.locipro.neoballerite.worldgen.ore.NeoOrePlacement;
import com.locipro.neoballerite.worldgen.vegetation.flower.NeoBerryFeatures;
import com.locipro.neoballerite.worldgen.vegetation.flower.NeoBerryPlacements;
import com.locipro.neoballerite.worldgen.vegetation.vegetation.NeoVegetationPlacements;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.neoforged.neoforge.common.world.BiomeModifier;
import net.neoforged.neoforge.common.world.BiomeModifiers;

import java.util.List;

import static com.locipro.neoballerite.worldgen.NeoBiomeModifiers.createKey;

public class NeoVegetationBiomes {
    public static final ResourceKey<BiomeModifier> ADD_TREES_WITHERED = createKey("add_trees_withered");
    public static final ResourceKey<BiomeModifier> ADD_TREES_STAR = createKey("add_trees_star");

    public static final ResourceKey<BiomeModifier> ADD_BERRIES = createKey("add_berries");
    public static final ResourceKey<BiomeModifier> ADD_STRAWBERRIES = createKey("add_strawberries");


    public static final ResourceKey<BiomeModifier> ADD_TOMATOES = createKey("add_tomatoes");


    public static final ResourceKey<BiomeModifier> ADD_SWEET_POTATO_PATCHES = createKey("add_sweet_potato_patches");

    public static void bootstrap(BootstrapContext<BiomeModifier> context) {
        HolderGetter<PlacedFeature> placedFeatures = context.lookup(Registries.PLACED_FEATURE);
        HolderGetter<Biome> biomes = context.lookup(Registries.BIOME);

        HolderSet<Biome> withered_biomes = HolderSet.direct(List.of
                (
                biomes.getOrThrow(Biomes.FOREST),
                biomes.getOrThrow(Biomes.SWAMP),
                biomes.getOrThrow(Biomes.RIVER),
                biomes.getOrThrow(Biomes.BIRCH_FOREST),
                biomes.getOrThrow(Biomes.MEADOW)
                )
        );
        HolderSet<Biome> star_biomes = HolderSet.direct(List.of
                (
                biomes.getOrThrow(Biomes.MUSHROOM_FIELDS),
                biomes.getOrThrow(Biomes.SAVANNA),
                biomes.getOrThrow(Biomes.SAVANNA_PLATEAU),
                biomes.getOrThrow(Biomes.WINDSWEPT_SAVANNA)
                )
        );

        HolderSet<Biome> berries = HolderSet.direct(List.of
                (
                biomes.getOrThrow(Biomes.JUNGLE),
                biomes.getOrThrow(Biomes.SPARSE_JUNGLE),
                biomes.getOrThrow(Biomes.TAIGA),
                biomes.getOrThrow(Biomes.SNOWY_TAIGA),
                biomes.getOrThrow(Biomes.OLD_GROWTH_PINE_TAIGA),
                biomes.getOrThrow(Biomes.OLD_GROWTH_SPRUCE_TAIGA),
                biomes.getOrThrow(Biomes.OLD_GROWTH_SPRUCE_TAIGA)
                )
        );
        HolderSet<Biome> strawberries = HolderSet.direct(List.of
                (
                biomes.getOrThrow(Biomes.SAVANNA),
                biomes.getOrThrow(Biomes.SAVANNA_PLATEAU),
                biomes.getOrThrow(Biomes.JUNGLE),
                biomes.getOrThrow(Biomes.SPARSE_JUNGLE),
                biomes.getOrThrow(Biomes.TAIGA),
                biomes.getOrThrow(Biomes.SNOWY_TAIGA),
                biomes.getOrThrow(Biomes.OLD_GROWTH_PINE_TAIGA),
                biomes.getOrThrow(Biomes.OLD_GROWTH_SPRUCE_TAIGA),
                biomes.getOrThrow(Biomes.OLD_GROWTH_SPRUCE_TAIGA)
                )
        );

        HolderSet<Biome> tomatoes = HolderSet.direct(List.of
                (
                biomes.getOrThrow(Biomes.PLAINS),
                biomes.getOrThrow(Biomes.FOREST),
                biomes.getOrThrow(Biomes.FLOWER_FOREST),
                biomes.getOrThrow(Biomes.BIRCH_FOREST)
                )
        );

        HolderSet<Biome> sweet_potatoes = HolderSet.direct(List.of
                (
                biomes.getOrThrow(Biomes.PLAINS),
                biomes.getOrThrow(Biomes.JUNGLE),
                biomes.getOrThrow(Biomes.BADLANDS),
                biomes.getOrThrow(Biomes.WOODED_BADLANDS)
                )
        );



        context.register(ADD_TREES_WITHERED, new BiomeModifiers.AddFeaturesBiomeModifier(
                withered_biomes,
                HolderSet.direct(placedFeatures.getOrThrow(NeoVegetationPlacements.TREES_WITHERED)),
                GenerationStep.Decoration.VEGETAL_DECORATION
        ));

        context.register(ADD_TREES_STAR, new BiomeModifiers.AddFeaturesBiomeModifier(
                star_biomes,
                HolderSet.direct(placedFeatures.getOrThrow(NeoVegetationPlacements.TREES_STAR)),
                GenerationStep.Decoration.VEGETAL_DECORATION
        ));

        context.register(ADD_BERRIES, new BiomeModifiers.AddFeaturesBiomeModifier(
                berries,
                HolderSet.direct(List.of(
                        placedFeatures.getOrThrow(NeoBerryPlacements.BLUEBERRY_BUSH),
                        placedFeatures.getOrThrow(NeoBerryPlacements.BLACKBERRY_BUSH)
                )),
                GenerationStep.Decoration.VEGETAL_DECORATION
        ));
        context.register(ADD_STRAWBERRIES, new BiomeModifiers.AddFeaturesBiomeModifier(
                strawberries,
                HolderSet.direct(placedFeatures.getOrThrow(NeoBerryPlacements.STRAWBERRY_BUSH)),
                GenerationStep.Decoration.VEGETAL_DECORATION
        ));

        context.register(ADD_TOMATOES, new BiomeModifiers.AddFeaturesBiomeModifier(
                tomatoes,
                HolderSet.direct(placedFeatures.getOrThrow(NeoBerryPlacements.TOMATO_BUSH)),
                GenerationStep.Decoration.VEGETAL_DECORATION
        ));

        context.register(ADD_SWEET_POTATO_PATCHES, new BiomeModifiers.AddFeaturesBiomeModifier(
                sweet_potatoes,
                HolderSet.direct(placedFeatures.getOrThrow(NeoBerryPlacements.SWEET_POTATO_PATCH)),
                GenerationStep.Decoration.VEGETAL_DECORATION
        ));

    }
}
