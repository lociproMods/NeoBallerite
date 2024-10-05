package com.locipro.neoballerite.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.DataMapProvider;
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
    protected void gather() {
        // TODO Here you will add the compostables when the crops exist.
        builder(NeoForgeDataMaps.FURNACE_FUELS)
                .add(CHARRED_BALLERITE.getId(), new FurnaceFuel(888), false)
                .add(CHARRED_BALLERITE_BLOCK.getId(), new FurnaceFuel(8000), false);
    }
}
