package com.locipro.neoballerite.worldgen;

import com.locipro.neoballerite.worldgen.ore.NeoOreFeatures;
import com.locipro.neoballerite.worldgen.vegetation.tree.NeoTreeFeatures;
import com.locipro.neoballerite.worldgen.vegetation.vegetation.NeoVegetationFeatures;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;

import static com.locipro.neoballerite.NeoBallerite.MODID;

public class NeoConfiguredFeatures {
    public static ResourceKey<ConfiguredFeature<?, ?>> createKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, ResourceLocation.fromNamespaceAndPath(MODID, name));
    }
    public static void boostrap(BootstrapContext<ConfiguredFeature<?, ?>> context) {
        NeoOreFeatures.bootstrap(context);
        NeoTreeFeatures.bootstrap(context);
        NeoVegetationFeatures.bootstrap(context);
    }
}
