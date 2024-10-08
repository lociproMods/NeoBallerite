package com.locipro.neoballerite.worldgen;

import com.locipro.neoballerite.worldgen.ore.NeoOrePlacement;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

import static com.locipro.neoballerite.NeoBallerite.MODID;

public class NeoPlacedFeatures {
    public static ResourceKey<PlacedFeature> createKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, ResourceLocation.fromNamespaceAndPath(MODID, name));
    }
    public static void bootstrap(BootstrapContext<PlacedFeature> context) {
        NeoOrePlacement.bootstrap(context);
    }
}
