package com.locipro.neoballerite.datagen;

import com.locipro.neoballerite.worldgen.NeoBiomeModifiers;
import com.locipro.neoballerite.worldgen.NeoConfiguredFeatures;
import com.locipro.neoballerite.worldgen.NeoPlacedFeatures;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.Set;
import java.util.concurrent.CompletableFuture;
import static com.locipro.neoballerite.NeoBallerite.MODID;

public class NeoWorldGenProvider extends DatapackBuiltinEntriesProvider {

    public static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
            .add(Registries.CONFIGURED_FEATURE, NeoConfiguredFeatures::boostrap)
            .add(Registries.PLACED_FEATURE, NeoPlacedFeatures::bootstrap)
            .add(NeoForgeRegistries.Keys.BIOME_MODIFIERS, NeoBiomeModifiers::bootstrap);


    public NeoWorldGenProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, BUILDER, Set.of(MODID));
    }
}
