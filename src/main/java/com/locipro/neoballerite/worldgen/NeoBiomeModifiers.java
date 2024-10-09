package com.locipro.neoballerite.worldgen;

import com.locipro.neoballerite.worldgen.ore.NeoOreBiomes;
import com.locipro.neoballerite.worldgen.ore.NeoOreFeatures;
import com.locipro.neoballerite.worldgen.vegetation.NeoVegetationBiomes;
import com.locipro.neoballerite.worldgen.vegetation.tree.NeoTreeFeatures;
import com.locipro.neoballerite.worldgen.vegetation.vegetation.NeoVegetationFeatures;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.neoforged.neoforge.common.world.BiomeModifier;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import static com.locipro.neoballerite.NeoBallerite.MODID;

public class NeoBiomeModifiers {
    public static ResourceKey<BiomeModifier> createKey(String name) {
        return ResourceKey.create(NeoForgeRegistries.Keys.BIOME_MODIFIERS, ResourceLocation.fromNamespaceAndPath(MODID, name));
    }

    public static void bootstrap(BootstrapContext<BiomeModifier> context) {
        NeoVegetationBiomes.bootstrap(context);
        NeoOreBiomes.bootstrap(context);
    }
}