package com.locipro.neoballerite.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.DataMapProvider;
import net.neoforged.neoforge.registries.datamaps.builtin.Compostable;
import net.neoforged.neoforge.registries.datamaps.builtin.FurnaceFuel;
import net.neoforged.neoforge.registries.datamaps.builtin.NeoForgeDataMaps;
import static com.locipro.neoballerite.block.ModBlocks.*;
import static com.locipro.neoballerite.item.ModItems.*;
import java.util.concurrent.CompletableFuture;

public class ModDataMapProvider extends DataMapProvider {


    protected ModDataMapProvider(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(packOutput, lookupProvider);
    }

    @Override
    protected void gather(HolderLookup.Provider provider) {
        builder(NeoForgeDataMaps.FURNACE_FUELS)
                .add(CHARRED_BALLERITE.getId(), new FurnaceFuel(888), false)
                .add(CHARRED_BALLERITE_BLOCK.getId(), new FurnaceFuel(8000), false);
        builder(NeoForgeDataMaps.COMPOSTABLES)
                .add(EGGPLANT_SEEDS, new Compostable(0.3f, true), false)
                .add(TOMATO_SEEDS, new Compostable(0.3f, true), false)
                .add(STRAWBERRY_SEEDS, new Compostable(0.3f, true), false)
                .add(CORN_KERNELS, new Compostable(0.4f, true), false)
                .add(EGGPLANT, new Compostable(0.5f, true), false)
                .add(TOMATO, new Compostable(0.5f, true), false)
                .add(SWEET_POTATO, new Compostable(0.66f, true), false)
                .add(CORN_COB, new Compostable(0.8f, true), false)
                .add(BLUEBERRIES, new Compostable(0.5f, true), false)
                .add(BLACKBERRIES, new Compostable(0.5f, true), false)
                .add(STRAWBERRY, new Compostable(0.6f, true), false)
                .add(UNRIPE_STRAWBERRY, new Compostable(0.4f, true), false)
                .add(WITHERED_SAPLING.getId(), new Compostable(0.3f, true), false)
                .add(WITHERED_LEAVES.getId(), new Compostable(0.3f, true), false)
                .add(STAR_SAPLING.getId(), new Compostable(0.3f, true), false)
                .add(STAR_LEAVES.getId(), new Compostable(0.3f, true), false);
    }
}
