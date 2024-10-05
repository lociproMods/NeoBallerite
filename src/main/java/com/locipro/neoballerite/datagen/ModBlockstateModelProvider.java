package com.locipro.neoballerite.datagen;

import net.minecraft.commands.arguments.TemplateRotationArgument;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ModelFile;
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



        logWithItem(WITHERED_LOG);
        logWithItem(STRIPPED_WITHERED_LOG);

        woodBlockWithItem(WITHERED_WOOD);
        woodBlockWithItem(STRIPPED_WITHERED_WOOD);

        blockWithItem(WITHERED_PLANKS);

        stairsBlock(WITHERED_STAIRS.get(), blockTexture(WITHERED_PLANKS.get()));
        blockItem(WITHERED_STAIRS);

        slabBlock(WITHERED_SLAB.get(), blockTexture(WITHERED_PLANKS.get()), blockTexture(WITHERED_PLANKS.get()));
        buttonBlock(WITHERED_BUTTON.get(), blockTexture(WITHERED_PLANKS.get()));
        pressurePlateBlock(WITHERED_PRESSURE_PLATE.get(), blockTexture(WITHERED_PLANKS.get()));

        fenceBlock(WITHERED_FENCE.get(), blockTexture(WITHERED_PLANKS.get()));
        fenceGateBlock(WITHERED_FENCE_GATE.get(), blockTexture(WITHERED_PLANKS.get()));

        doorBlockWithRenderType(WITHERED_DOOR.get(), modLoc("block/withered_door_bottom"), modLoc("block/withered_door_top"), "cutout");
        trapdoorBlockWithRenderType(WITHERED_TRAPDOOR.get(), modLoc("block/withered_trapdoor"), true, "cutout");

        blockItem(WITHERED_SLAB);
        blockItem(WITHERED_PRESSURE_PLATE);
        blockItem(WITHERED_FENCE_GATE);
        blockItem(WITHERED_TRAPDOOR, "_bottom");
    }

    private void blockWithItem(DeferredBlock<?> block) {
        simpleBlockWithItem(block.get(), cubeAll(block.get()));
    }

    /** I am a god and my ego remains unbroken **/
    private void woodBlockWithItem(DeferredBlock<?> block) {
        // Gets the path to the normal default texture (the side texture)
        String woodTexturePath = block.getId().getPath().replace("wood", "log");
        ResourceLocation woodTexture = modLoc("block/" + woodTexturePath);

        // Makes a cubeAll block model with that texture, attached to our new block
        simpleBlock(block.get(), models().cubeAll(block.getId().getPath(), woodTexture));

        blockItem(block);
    }
    private void blockItem(DeferredBlock<?> deferredBlock) {
        simpleBlockItem(deferredBlock.get(), new ModelFile.UncheckedModelFile("neoballerite:block/" + deferredBlock.getId().getPath()));
    }
    private void logWithItem(DeferredBlock<?> block) {
        logBlock((RotatedPillarBlock) block.get());
        blockItem(block);
    }

    private void blockItem(DeferredBlock<?> deferredBlock, String appendix) {
        simpleBlockItem(deferredBlock.get(), new ModelFile.UncheckedModelFile("neoballerite:block/" + deferredBlock.getId().getPath() + appendix));
    }
}
