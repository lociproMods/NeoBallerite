package net.locipro.balleritemod.world.gen;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.locipro.balleritemod.world.ModPlacedFeatures;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.levelgen.GenerationStep;
/// After you make the configured features and placed features, you add them to the world like this.
// pick da biome, da generation step, then add ur placed feature
public class ModTreeGeneration {
    public static void generateTrees() {

        BiomeModifications.addFeature(BiomeSelectors.includeByKey(Biomes.SWAMP, Biomes.FOREST, Biomes.RIVER),
                GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacedFeatures.WITHERED_TREE_PLACED_KEY);
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(Biomes.MUSHROOM_FIELDS, Biomes.SAVANNA, Biomes.SAVANNA_PLATEAU, Biomes.WINDSWEPT_SAVANNA),
                GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacedFeatures.STAR_TREE_PLACED_KEY);
    }
}
