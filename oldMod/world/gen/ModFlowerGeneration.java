package net.locipro.balleritemod.world.gen;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.locipro.balleritemod.world.ModPlacedFeatures;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.levelgen.GenerationStep;

public class ModFlowerGeneration {
    public static void generateFlowers() {
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(Biomes.PLAINS, Biomes.FOREST, Biomes.BIRCH_FOREST),
                GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacedFeatures.TOMATO_FLOWER_PLACED_KEY);

        BiomeModifications.addFeature(BiomeSelectors.includeByKey(Biomes.PLAINS, Biomes.JUNGLE, Biomes.SAVANNA, Biomes.BADLANDS, Biomes.WOODED_BADLANDS),
                GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacedFeatures.SWEET_POTATO_PATCH_PLACED_KEY);

        BiomeModifications.addFeature(BiomeSelectors.includeByKey(Biomes.TAIGA, Biomes.JUNGLE),
                GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacedFeatures.BLUE_BERRY_PATCH_PLACED_KEY);
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(Biomes.TAIGA, Biomes.JUNGLE),
                GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacedFeatures.BLACK_BERRY_PATCH_PLACED_KEY);

        BiomeModifications.addFeature(BiomeSelectors.includeByKey(Biomes.TAIGA, Biomes.JUNGLE, Biomes.SAVANNA),
                GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacedFeatures.STRAWBERRY_PATCH_PLACED_KEY);


    }
}