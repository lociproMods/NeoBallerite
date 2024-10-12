package com.locipro.neoballerite.datagen;


import com.locipro.neoballerite.block.custom.*;
import net.minecraft.data.PackOutput;
import net.minecraft.data.models.BlockModelGenerators;
import net.minecraft.data.models.model.ModelTemplates;
import net.minecraft.data.models.model.TextureMapping;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.SaplingBlock;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ConfiguredModel;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;
import oshi.util.tuples.Pair;

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
        blockWithItem(RAW_LEAD_BLOCK);




        //simpleBlock(WITHERED_SAPLING.get(), models().cross(modelHelper(WITHERED_SAPLING).getA(), modelHelper(WITHERED_SAPLING).getB()).renderType("cutout"));
        saplingWithItem(WITHERED_SAPLING);

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



        saplingWithItem(STAR_SAPLING);

        logWithItem(STAR_LOG);
        logWithItem(STRIPPED_STAR_LOG);
        // Leaves aren't cubeAll... Hmmm
        leavesWithItem(STAR_LEAVES);

        nWoodBlockWithItem(STAR_WOOD);
        nWoodBlockWithItem(STRIPPED_STAR_WOOD);

        blockWithItem(STAR_PLANKS);

        stairsBlock(STAR_STAIRS.get(), blockTexture(STAR_PLANKS.get()));
        blockItem(STAR_STAIRS);

        slabBlock(STAR_SLAB.get(), blockTexture(STAR_PLANKS.get()), blockTexture(STAR_PLANKS.get()));
        buttonBlock(STAR_BUTTON.get(), blockTexture(STAR_PLANKS.get()));
        pressurePlateBlock(STAR_PRESSURE_PLATE.get(), blockTexture(STAR_PLANKS.get()));

        fenceBlock(STAR_FENCE.get(), blockTexture(STAR_PLANKS.get()));
        fenceGateBlock(STAR_FENCE_GATE.get(), blockTexture(STAR_PLANKS.get()));

        doorBlockWithRenderType(STAR_DOOR.get(), modLoc("block/star_door_bottom"), modLoc("block/star_door_top"), "cutout");
        trapdoorBlockWithRenderType(STAR_TRAPDOOR.get(), modLoc("block/star_trapdoor"), true, "cutout");

        blockItem(STAR_SLAB);
        blockItem(STAR_PRESSURE_PLATE);
        blockItem(STAR_FENCE_GATE);
        blockItem(STAR_TRAPDOOR, "_bottom");



        customBerryBushBlock(BLUEBERRY_BUSH, NeoBerryBushBlock.AGE);
        customBerryBushBlock(BLACKBERRY_BUSH, NeoBerryBushBlock.AGE);
        customBerryBushBlock(STRAWBERRY_BUSH, StrawBerryBushBlock.AGE);

        crossBlock(TOMATO_BUSH);

        cropBlock(TOMATO_CROP, TomatoCropBlock.AGE);
        cropBlock(EGGPLANT_CROP, EggplantCropBlock.AGE);
        cropBlock(SWEET_POTATO_CROP, SweetPotatoCropBlock.AGE);

        pumpkinBlock(SWEET_POTATO_BLOCK);

    }

    private void blockWithItem(DeferredBlock<?> block) {
        simpleBlockWithItem(block.get(), cubeAll(block.get()));
    }

    /** I am a god and my ego remains unbroken
     * update 1 day later : Actual wood blocks in the game are axis-rotated blocks... This isn't.**/
//    private void woodBlockWithItem(DeferredBlock<?> block) {
//        // Gets the path to the normal default texture (the side texture)
//        String woodTexturePath = block.getId().getPath().replace("wood", "log");
//        ResourceLocation woodTexture = modLoc("block/" + woodTexturePath);
//        // Makes a cubeAll block model with that texture, attached to our new block
//        simpleBlock(block.get(), models().cubeAll(block.getId().getPath(), woodTexture));
//        blockItem(block);
//    }
    private void leavesWithItem(DeferredBlock<?> leavesBlock) {
        ResourceLocation leavesTexture = modLoc("block/" + leavesBlock.getId().getPath());
        simpleBlock(leavesBlock.get(), models().leaves(leavesBlock.getId().toString(), leavesTexture).renderType("cutout"));
        blockItem(leavesBlock);
    }
    private void saplingWithItem(DeferredBlock<SaplingBlock> sapling) {

        ResourceLocation saplingCrossTexture = modLoc("block/" + sapling.getId().getPath());
        simpleBlock(sapling.get(), models().cross(sapling.getId().toString(), saplingCrossTexture).renderType("cutout"));

        itemModels().getBuilder(sapling.getId().getPath())
                .parent(new ModelFile.UncheckedModelFile("item/generated"))
                .texture("layer0", saplingCrossTexture);
    }
    private Pair<String, ResourceLocation> modelHelper(DeferredBlock<?> block) {
        return new Pair<>(block.getId().toString(), modLoc("block/" + block.getId().getPath()));
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

    private void customBerryBushBlock(DeferredBlock<?> bush, IntegerProperty bushAgeProperty) {
        String bushName = bush.getId().getPath();
        getVariantBuilder(bush.get()).forAllStates( state -> {
            String stateAndModelName = bushName + "_stage" + state.getValue(bushAgeProperty);
            return new ConfiguredModel[]{new ConfiguredModel(models().cross(stateAndModelName,
                    ResourceLocation.fromNamespaceAndPath(MODID, "block/" + stateAndModelName)).renderType("cutout"))};
        });
    }
    private void cropBlock(DeferredBlock<?> crop, IntegerProperty cropAgeProperty) {
        String cropName = crop.getId().getPath();
        getVariantBuilder(crop.get()).forAllStates( state -> {
            String stateAndModelName = cropName + "_stage" + state.getValue(cropAgeProperty);
            return new ConfiguredModel[]{new ConfiguredModel(models().crop(stateAndModelName,
                    ResourceLocation.fromNamespaceAndPath(MODID, "block/" + stateAndModelName)).renderType("cutout"))};
        });
    }
    private void crossBlock(DeferredBlock<?> block) {
        ResourceLocation crossTexture = modLoc("block/" + block.getId().getPath());
        simpleBlock(block.get(), models().cross(block.getId().toString(), crossTexture).renderType("cutout"));
    }

    /** Kind of misleading since the pumpkin block rotates the item model **/
    private void pumpkinBlock(DeferredBlock<?> block) {
        ResourceLocation sideTexture = modLoc("block/" + block.getId().getPath() + "_side");
        ResourceLocation endTexture = modLoc("block/" + block.getId().getPath() + "_end");
        simpleBlockWithItem(block.get(), models().cubeColumn(block.getId().toString(), sideTexture, endTexture));
    }
}
