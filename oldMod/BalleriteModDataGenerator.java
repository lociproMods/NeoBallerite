package net.locipro.balleritemod;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.locipro.balleritemod.data.*;
import net.locipro.balleritemod.world.ModConfiguredFeatures;
import net.locipro.balleritemod.world.ModPlacedFeatures;
import net.minecraft.Util;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.registries.VanillaRegistries;
import java.util.concurrent.CompletableFuture;

public class BalleriteModDataGenerator implements DataGeneratorEntrypoint {
    final CompletableFuture<HolderLookup.Provider> completableFuture = CompletableFuture.supplyAsync(VanillaRegistries::createLookup, Util.backgroundExecutor());

    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {

        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

        pack.addProvider(ModLootTableGenerator::new);
        pack.addProvider(ModRecipeGenerator::new);
        pack.addProvider(ModModelProvider::new);
        pack.addProvider(ModWorldGenerator::new);
        FabricTagProvider.BlockTagProvider blockTagProvider = pack.addProvider(ModBlockTagGenerator::new);
        pack.addProvider((FabricDataGenerator.Pack.Factory<ModItemTagGenerator>) output -> new ModItemTagGenerator(output, completableFuture, blockTagProvider));
    }

    @Override
    public void buildRegistry(RegistrySetBuilder registryBuilder) {
        registryBuilder.add(Registries.CONFIGURED_FEATURE, ModConfiguredFeatures::bootstrap);
        registryBuilder.add(Registries.PLACED_FEATURE, ModPlacedFeatures::bootstrap);
    }
}
