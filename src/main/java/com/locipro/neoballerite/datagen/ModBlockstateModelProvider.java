package com.locipro.neoballerite.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.LeavesBlock;
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
        blockWithItem(LEAD_ORE);
        blockWithItem(DEEPSLATE_LEAD_ORE);
        blockWithItem(RAW_BALLERITE_BLOCK);
        blockWithItem(COOKED_BALLERITE_BLOCK);
        blockWithItem(BURNT_BALLERITE_BLOCK);
        blockWithItem(CHARRED_BALLERITE_BLOCK);
        blockWithItem(COMPRESSED_BALLERITE_BLOCK);
        blockWithItem(LEAD_BLOCK);


        logWithItem(WITHERED_LOG);
        logWithItem(STRIPPED_WITHERED_LOG);
        // Leaves aren't cubeAll... Hmmm
        leavesWithItem(WITHERED_LEAVES);

        nWoodBlockWithItem(WITHERED_WOOD);
        nWoodBlockWithItem(STRIPPED_WITHERED_WOOD);

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

    /** I am a god and my ego remains unbroken
     * update 1 day later : Actual wood blocks in the game are still axis-rotated blocks... This isn't.**/
    private void woodBlockWithItem(DeferredBlock<?> block) {
        // Gets the path to the normal default texture (the side texture)
        String woodTexturePath = block.getId().getPath().replace("wood", "log");
        ResourceLocation woodTexture = modLoc("block/" + woodTexturePath);
        // Makes a cubeAll block model with that texture, attached to our new block
        simpleBlock(block.get(), models().cubeAll(block.getId().getPath(), woodTexture));
        blockItem(block);
    }
    private void leavesWithItem(DeferredBlock<LeavesBlock> leavesBlock) {
        ResourceLocation leavesTexture = modLoc("block/" + leavesBlock.getId().getPath());
        simpleBlock(leavesBlock.get(), models().leaves(leavesBlock.getId().toString(), leavesTexture));
        blockItem(leavesBlock);
    }

    /** NOW, I am a god and my ego remains unbroken. **/
    private void nWoodBlockWithItem(DeferredBlock<RotatedPillarBlock> woodBlock) {
        // Gets the path to the normal default texture (the side texture)
        String woodTexturePath = woodBlock.getId().getPath().replace("wood", "log");
        ResourceLocation woodTexture = modLoc("block/" + woodTexturePath);
        // This treats the second parameter as "baseName" and goes and looks for "baseName_side" and "baseName_end".
        //axisBlock(woodBlock.get(), woodTexture);
        axisBlock(woodBlock.get(), woodTexture, woodTexture);
        blockItem(woodBlock);
        // This makes a cubeAll block... Which actual wood isn't!
        //simpleBlock(woodBlock.get(), models().cubeAll(woodBlock.getId().getPath(), woodTexture));
    }/*public BlockModelGenerators.WoodProvider wood(Block woodBlock) {
            TextureMapping texturemapping = this.logMapping.copyAndUpdate(TextureSlot.END, this.logMapping.get(TextureSlot.SIDE));
            ResourceLocation resourcelocation = ModelTemplates.CUBE_COLUMN.create(woodBlock, texturemapping, BlockModelGenerators.this.modelOutput);
            BlockModelGenerators.this.blockStateOutput.accept(BlockModelGenerators.createAxisAlignedPillarBlock(woodBlock, resourcelocation));
            return this;
        }*/


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
