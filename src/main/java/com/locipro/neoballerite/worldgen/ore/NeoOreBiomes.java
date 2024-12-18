package com.locipro.neoballerite.worldgen.ore;

import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.neoforged.neoforge.common.world.BiomeModifier;
import net.neoforged.neoforge.common.world.BiomeModifiers;

import static com.locipro.neoballerite.worldgen.NeoBiomeModifiers.createKey;

public class NeoOreBiomes {
    public static final ResourceKey<BiomeModifier> ADD_ORE_BALLERITE = createKey("add_ore_ballerite");
    public static final ResourceKey<BiomeModifier> ADD_ORE_LEAD = createKey("add_ore_lead");
    public static final ResourceKey<BiomeModifier> ADD_ORE_LEAD_BURIED = createKey("add_ore_lead_buried");

    public static void bootstrap(BootstrapContext<BiomeModifier> context) {
        HolderGetter<PlacedFeature> placedFeatures = context.lookup(Registries.PLACED_FEATURE);
        HolderGetter<Biome> biomes = context.lookup(Registries.BIOME);


        context.register(ADD_ORE_BALLERITE, new BiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_OVERWORLD),
                HolderSet.direct(placedFeatures.getOrThrow(NeoOrePlacement.ORE_BALLERITE)),
                GenerationStep.Decoration.UNDERGROUND_ORES
        ));

        context.register(ADD_ORE_LEAD, new BiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_OVERWORLD),
                HolderSet.direct(placedFeatures.getOrThrow(NeoOrePlacement.ORE_LEAD)),
                GenerationStep.Decoration.UNDERGROUND_ORES
        ));

        context.register(ADD_ORE_LEAD_BURIED, new BiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_OVERWORLD),
                HolderSet.direct(placedFeatures.getOrThrow(NeoOrePlacement.ORE_LEAD_BURIED)),
                GenerationStep.Decoration.UNDERGROUND_ORES
        ));

    }
}
