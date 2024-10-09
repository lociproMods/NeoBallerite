package com.locipro.neoballerite.worldgen.vegetation;

import com.locipro.neoballerite.worldgen.ore.NeoOrePlacement;
import com.locipro.neoballerite.worldgen.vegetation.vegetation.NeoVegetationPlacements;
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
        /*BiomeModifications.addFeature(BiomeSelectors.includeByKey(Biomes.SWAMP, Biomes.FOREST, Biomes.RIVER),
                GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacedFeatures.WITHERED_TREE_PLACED_KEY);
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(Biomes.MUSHROOM_FIELDS, Biomes.SAVANNA, Biomes.SAVANNA_PLATEAU, Biomes.WINDSWEPT_SAVANNA),
                GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacedFeatures.STAR_TREE_PLACED_KEY);*/


    }
}
