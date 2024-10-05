package com.locipro.neoballerite.datagen;

import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;

import static com.locipro.neoballerite.block.ModBlocks.*;
import static com.locipro.neoballerite.NeoBallerite.MODID;

public class ModBlockstateModelProvider extends BlockStateProvider {

    public ModBlockstateModelProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, MODID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        blockWithItem(BALLERITE_ORE);
        blockWithItem(RAW_BALLERITE_BLOCK);
        blockWithItem(COOKED_BALLERITE_BLOCK);
        blockWithItem(BURNT_BALLERITE_BLOCK);
        blockWithItem(CHARRED_BALLERITE_BLOCK);
        blockWithItem(COMPRESSED_BALLERITE_BLOCK);
    }

    protected void blockWithItem(DeferredBlock<?> block) {
        simpleBlockWithItem(block.get(), cubeAll(block.get()));
    }
}
