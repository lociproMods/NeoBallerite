package net.locipro.balleritemod.data;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.locipro.balleritemod.block.ModBlocks;
import net.locipro.balleritemod.block.custom.*;
import net.locipro.balleritemod.item.ModItems;
import net.locipro.balleritemod.util.ModMathUtil;
import net.minecraft.data.models.BlockModelGenerators;
import net.minecraft.data.models.ItemModelGenerators;
import net.minecraft.data.models.blockstates.MultiVariantGenerator;
import net.minecraft.data.models.blockstates.PropertyDispatch;
import net.minecraft.data.models.blockstates.Variant;
import net.minecraft.data.models.blockstates.VariantProperties;
import net.minecraft.data.models.model.ModelLocationUtils;
import net.minecraft.data.models.model.ModelTemplates;
import net.minecraft.data.models.model.TextureMapping;
import net.minecraft.data.models.model.TexturedModel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;


/*
    Sapling models and blockstates are handmade, and so are the sapling Items.
*/

/**
 * <b>HEAD OVER TO </b><i>BlockStateModelGenerator</i> <b>AND</b> <i>ItemModelGenerator</i> <b>TO SEE HOW THE VANILLA BLOCKSTATES
 * AND MODELS ARE GENERATED</b>
 */
public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    private static final int[] eight_indices_model = ModMathUtil.generateArrayFromZeroToX(7);
    @Override
    public void generateBlockStateModels(BlockModelGenerators blockStateModelGenerator) {
        blockStateModelGenerator.family(ModBlocks.RAW_BALLERITE_BLOCK);
        blockStateModelGenerator.family(ModBlocks.BALLERITE_BLOCK);
        blockStateModelGenerator.family(ModBlocks.BURNT_BALLERITE_BLOCK);
        blockStateModelGenerator.family(ModBlocks.CHARRED_BALLERITE_BLOCK);
        blockStateModelGenerator.family(ModBlocks.COMPRESSED_BALLERITE_BLOCK);
        blockStateModelGenerator.family(ModBlocks.BALLERITE_ORE);
        blockStateModelGenerator.family(ModBlocks.LEAD_ORE);
        blockStateModelGenerator.family(ModBlocks.DEEPSLATE_LEAD_ORE);
        blockStateModelGenerator.family(ModBlocks.LEAD_BLOCK);


        blockStateModelGenerator.woodProvider(ModBlocks.WITHERED_LOG).logWithHorizontal(ModBlocks.WITHERED_LOG).wood(ModBlocks.WITHERED_WOOD);
        blockStateModelGenerator.woodProvider(ModBlocks.STRIPPED_WITHERED_LOG).logWithHorizontal(ModBlocks.STRIPPED_WITHERED_LOG).wood(ModBlocks.STRIPPED_WITHERED_WOOD);
        blockStateModelGenerator.woodProvider(ModBlocks.STAR_LOG).logWithHorizontal(ModBlocks.STAR_LOG).wood(ModBlocks.STAR_WOOD);
        blockStateModelGenerator.woodProvider(ModBlocks.STRIPPED_STAR_LOG).logWithHorizontal(ModBlocks.STRIPPED_STAR_LOG).wood(ModBlocks.STRIPPED_STAR_WOOD);


        blockStateModelGenerator.family(ModBlocks.WITHERED_PLANKS);
        blockStateModelGenerator.family(ModBlocks.WITHERED_LEAVES);
        blockStateModelGenerator.family(ModBlocks.STAR_PLANKS);
        blockStateModelGenerator.family(ModBlocks.STAR_LEAVES);
        blockStateModelGenerator.createCrossBlockWithDefaultItem(ModBlocks.WITHERED_SAPLING, BlockModelGenerators.TintState.NOT_TINTED);
        blockStateModelGenerator.createCrossBlockWithDefaultItem(ModBlocks.STAR_SAPLING, BlockModelGenerators.TintState.NOT_TINTED);

        blockStateModelGenerator.createDoor(ModBlocks.WITHERED_DOOR);
        blockStateModelGenerator.createDoor(ModBlocks.STAR_DOOR);
        blockStateModelGenerator.createDoor(ModBlocks.COBBLESTONE_DOOR);
        blockStateModelGenerator.createDoor(ModBlocks.COPPER_DOOR);


        blockStateModelGenerator.createTrapdoor(ModBlocks.WITHERED_TRAPDOOR);
        blockStateModelGenerator.createTrapdoor(ModBlocks.STAR_TRAPDOOR);
        blockStateModelGenerator.createTrapdoor(ModBlocks.COBBLESTONE_TRAPDOOR);
        blockStateModelGenerator.createTrapdoor(ModBlocks.COPPER_TRAPDOOR);

        registerFenceAndFenceGate(ModBlocks.WITHERED_FENCE, ModBlocks.WITHERED_FENCE_GATE, ModBlocks.WITHERED_PLANKS, blockStateModelGenerator, true);
        registerFenceAndFenceGate(ModBlocks.STAR_FENCE, ModBlocks.STAR_FENCE_GATE, ModBlocks.STAR_PLANKS, blockStateModelGenerator, true);

        registerPressurePlate(ModBlocks.WITHERED_PRESSURE_PLATE, ModBlocks.WITHERED_PLANKS, blockStateModelGenerator);
        registerPressurePlate(ModBlocks.STAR_PRESSURE_PLATE, ModBlocks.STAR_PLANKS, blockStateModelGenerator);
        registerPressurePlate(ModBlocks.COPPER_PRESSURE_PLATE, Blocks.CUT_COPPER, blockStateModelGenerator);


        registerButton(ModBlocks.WITHERED_BUTTON, ModBlocks.WITHERED_PLANKS, blockStateModelGenerator);
        registerButton(ModBlocks.STAR_BUTTON, ModBlocks.STAR_PLANKS, blockStateModelGenerator);
        registerButton(ModBlocks.COPPER_BUTTON, Blocks.CUT_COPPER, blockStateModelGenerator);
        
        registerSlab(ModBlocks.WITHERED_SLAB, ModBlocks.WITHERED_PLANKS, blockStateModelGenerator);
        registerSlab(ModBlocks.STAR_SLAB, ModBlocks.STAR_PLANKS, blockStateModelGenerator);
        
        registerStair(ModBlocks.WITHERED_STAIRS, ModBlocks.WITHERED_PLANKS, blockStateModelGenerator);
        registerStair(ModBlocks.STAR_STAIRS, ModBlocks.STAR_PLANKS, blockStateModelGenerator);

        //registerCrop() makes the item model for the seeds.
        blockStateModelGenerator.createCropBlock(ModBlocks.EGGPLANT_CROP, EggplantCropBlock.AGE, eight_indices_model);
        blockStateModelGenerator.createCropBlock(ModBlocks.TOMATO_CROP, TomatoCropBlock.AGE, eight_indices_model); // 8 stages
        blockStateModelGenerator.createCropBlock(ModBlocks.SWEET_POTATO_CROP, SweetPotatoCropBlock.AGE, ModMathUtil.generateArrayFromZeroToX(3)); // 4 stages
        blockStateModelGenerator.createCropBlock(ModBlocks.CORN_CROP, CornCropBlock.AGE, ModMathUtil.generateArrayFromZeroToX(9)); // 10 stages

        registerSweetBerryBush(ModBlocks.BLACK_BERRY_BUSH, BlockStateProperties.AGE_3, blockStateModelGenerator);
        registerSweetBerryBush(ModBlocks.BLUE_BERRY_BUSH, BlockStateProperties.AGE_3, blockStateModelGenerator);
        registerSweetBerryBush(ModBlocks.STRAWBERRY_BUSH, StrawberryBushBlock.AGE, blockStateModelGenerator);


        blockStateModelGenerator.createCrossBlockWithDefaultItem(ModBlocks.TOMATO_FLOWER, BlockModelGenerators.TintState.NOT_TINTED);

        registerPumpkin(ModBlocks.SWEET_POTATO_BLOCK, blockStateModelGenerator);

    }
    @Override
    public void generateItemModels(ItemModelGenerators itemModelGenerator) {
        //IMPORTANT NOTICE FOR REGISTERING CROPS : YOU DO NOT NEED TO REGISTER THE SEED'S ITEM MODEL. IT ALREADY DOES THAT ITSELF.
        itemModelGenerator.generateFlatItem(ModItems.RAW_BALLERITE, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.BALLERITE, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.CHARRED_BALLERITE, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.COMPRESSED_BALLERITE, ModelTemplates.FLAT_ITEM);

        itemModelGenerator.generateFlatItem(ModItems.BALL_DOWSER, ModelTemplates.FLAT_ITEM);

        itemModelGenerator.generateFlatItem(ModItems.EGGPLANT, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.GRILLED_EGGPLANT, ModelTemplates.FLAT_ITEM);

        itemModelGenerator.generateFlatItem(ModItems.CORN_COB, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.GRILLED_CORN_COB, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.CORN_KERNEL, ModelTemplates.FLAT_ITEM);

        itemModelGenerator.generateFlatItem(ModItems.TOMATO, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.GRILLED_TOMATO, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.TOMATO_JAM, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.SWEET_BERRY_JAM, ModelTemplates.FLAT_ITEM);

        itemModelGenerator.generateFlatItem(ModItems.BLUEBERRY_JAM, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.BLACKBERRY_JAM, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.BLUE_BERRIES, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.BLACK_BERRIES, ModelTemplates.FLAT_ITEM);

        itemModelGenerator.generateFlatItem(ModItems.STRAWBERRY_JAM, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.STRAWBERRY, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.STRAWBERRY_SEEDS, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.UNRIPE_STRAWBERRY, ModelTemplates.FLAT_ITEM);

        itemModelGenerator.generateFlatItem(ModItems.MILK_VILE, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.CHEESE, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.EGGS_SCRAMBLED, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.EGGS_OMLETTE, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.EGGS_SUNNY, ModelTemplates.FLAT_ITEM);

        itemModelGenerator.generateFlatItem(ModItems.CHEESE_PORK, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.CHEESE_MUTTON, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.CHEESE_CHICKEN, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.CHEESE_STEAK, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.CHEESE_FRIES, ModelTemplates.FLAT_ITEM);
        
        itemModelGenerator.generateFlatItem(ModItems.PORK_SANDWICH, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.MUTTON_SANDWICH, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.CHICKEN_SANDWICH, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.STEAK_SANDWICH, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.FRIES_SANDWICH, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.CHEESE_SANDWICH, ModelTemplates.FLAT_ITEM);

        itemModelGenerator.generateFlatItem(ModItems.PORK_SANDWICH_CHEESE, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.MUTTON_SANDWICH_CHEESE, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.CHICKEN_SANDWICH_CHEESE, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.STEAK_SANDWICH_CHEESE, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.FRIES_SANDWICH_CHEESE, ModelTemplates.FLAT_ITEM);

        itemModelGenerator.generateFlatItem(ModItems.BAKED_SWEET_POTATO, ModelTemplates.FLAT_ITEM);



        itemModelGenerator.generateFlatItem(ModItems.PURPLE_CARROT, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.COCAINE, ModelTemplates.FLAT_ITEM);

        itemModelGenerator.generateFlatItem(ModItems.LEAD_INGOT, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.LEAD_NUGGET, ModelTemplates.FLAT_ITEM);

        itemModelGenerator.generateFlatItem(ModItems.BALLERITE_SWORD, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.BALLERITE_PICKAXE, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.BALLERITE_AXE, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.BALLERITE_SHOVEL, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.BALLERITE_HOE, ModelTemplates.FLAT_HANDHELD_ITEM);

        itemModelGenerator.generateFlatItem(ModItems.BALLERITE_HORSE_ARMOR, ModelTemplates.FLAT_ITEM);

        itemModelGenerator.generateFlatItem(ModItems.LEAD_SWORD, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.LEAD_PICKAXE, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.LEAD_AXE, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.LEAD_SHOVEL, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.LEAD_HOE, ModelTemplates.FLAT_HANDHELD_ITEM);

        itemModelGenerator.generateFlatItem(ModItems.WOODEN_CLAYMORE, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.STONE_CLAYMORE, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.IRON_CLAYMORE, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.LEAD_CLAYMORE, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.GOLD_CLAYMORE, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.DIAMOND_CLAYMORE, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.NETHERITE_CLAYMORE, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.BALLERITE_CLAYMORE, ModelTemplates.FLAT_HANDHELD_ITEM);


        itemModelGenerator.generateFlatItem(ModItems.KNIFE, ModelTemplates.FLAT_HANDHELD_ITEM);

        itemModelGenerator.generateFlatItem(ModItems.BALLERITE_HELMET, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.BALLERITE_CHESTPLATE, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.BALLERITE_LEGGINGS, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.BALLERITE_BOOTS, ModelTemplates.FLAT_ITEM);
        
        itemModelGenerator.generateFlatItem(ModItems.LEAD_HELMET, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.LEAD_CHESTPLATE, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.LEAD_LEGGINGS, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.LEAD_BOOTS, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.FARMERS_BOOTS, ModelTemplates.FLAT_ITEM);
    }




    static void registerButton(Block buttonBlock, Block texureBlock, BlockModelGenerators blockStateModelGenerator) {
        TextureMapping textureMap = TextureMapping.defaultTexture(texureBlock);
        ResourceLocation identifier = ModelTemplates.BUTTON.create(buttonBlock, textureMap, blockStateModelGenerator.modelOutput);
        ResourceLocation identifier2 = ModelTemplates.BUTTON_PRESSED.create(buttonBlock, textureMap, blockStateModelGenerator.modelOutput);
        ResourceLocation identifier3 = ModelTemplates.BUTTON_INVENTORY.create(buttonBlock, textureMap, blockStateModelGenerator.modelOutput);
        blockStateModelGenerator.delegateItemModel(buttonBlock, identifier3);
        blockStateModelGenerator.blockStateOutput.accept(BlockModelGenerators.createButton(buttonBlock, identifier, identifier2));

    }
    static void registerPressurePlate(Block pressurePlate, Block textureBlock, BlockModelGenerators blockStateModelGenerator) {
        TextureMapping textureMap = TextureMapping.defaultTexture(textureBlock);
        ResourceLocation identifier = ModelTemplates.PRESSURE_PLATE_UP.create(pressurePlate, textureMap, blockStateModelGenerator.modelOutput);
        ResourceLocation identifier2 = ModelTemplates.PRESSURE_PLATE_DOWN.create(pressurePlate, textureMap, blockStateModelGenerator.modelOutput);
        blockStateModelGenerator.delegateItemModel(pressurePlate, identifier);
        blockStateModelGenerator.blockStateOutput.accept(BlockModelGenerators.createPressurePlate(pressurePlate, identifier, identifier2));
    }
    static void registerFence(Block fenceBlock, Block textureBlock, BlockModelGenerators blockStateModelGenerator) {
        TextureMapping textureMap = TextureMapping.defaultTexture(textureBlock);
        ResourceLocation identifier = ModelTemplates.FENCE_POST.create(fenceBlock, textureMap, blockStateModelGenerator.modelOutput);
        ResourceLocation identifier2 = ModelTemplates.FENCE_SIDE.create(fenceBlock, textureMap, blockStateModelGenerator.modelOutput);
        ResourceLocation identifier3 = ModelTemplates.FENCE_INVENTORY.create(fenceBlock, textureMap, blockStateModelGenerator.modelOutput);
        blockStateModelGenerator.delegateItemModel(fenceBlock, identifier3);
        blockStateModelGenerator.blockStateOutput.accept(BlockModelGenerators.createFence(fenceBlock, identifier, identifier2));
    }
    static void registerFenceGate(Block fenceGateBlock, Block textureBlock, BlockModelGenerators blockStateModelGenerator, boolean uvlock) {
        TextureMapping textureMap = TextureMapping.defaultTexture(textureBlock);
        ResourceLocation identifier = ModelTemplates.FENCE_GATE_OPEN.create(fenceGateBlock, textureMap, blockStateModelGenerator.modelOutput);
        ResourceLocation identifier2 = ModelTemplates.FENCE_GATE_CLOSED.create(fenceGateBlock, textureMap, blockStateModelGenerator.modelOutput);
        ResourceLocation identifier3 = ModelTemplates.FENCE_GATE_WALL_OPEN.create(fenceGateBlock, textureMap, blockStateModelGenerator.modelOutput);
        ResourceLocation identifier4 = ModelTemplates.FENCE_GATE_WALL_CLOSED.create(fenceGateBlock, textureMap, blockStateModelGenerator.modelOutput);
        blockStateModelGenerator.delegateItemModel(fenceGateBlock, identifier2);
        blockStateModelGenerator.blockStateOutput.accept(BlockModelGenerators.createFenceGate(fenceGateBlock, identifier, identifier2, identifier3, identifier4, uvlock));
    }
    static void registerFenceAndFenceGate(Block fenceBlock, Block fenceGateBlock, Block textureBlock, BlockModelGenerators blockStateModelGenerator, boolean uvlock) {
        registerFence(fenceBlock, textureBlock, blockStateModelGenerator);
        registerFenceGate(fenceGateBlock, textureBlock, blockStateModelGenerator, uvlock);
        //
    }
    static void registerSlab(Block slabBlock, Block textureBlock, BlockModelGenerators blockStateModelGenerator) {
        ResourceLocation textureId = ModelLocationUtils.getModelLocation(textureBlock);
        TexturedModel texturedModel = TexturedModel.CUBE.get(textureBlock);
        ResourceLocation identifier1 = ModelTemplates.SLAB_BOTTOM.create(slabBlock, texturedModel.getMapping(), blockStateModelGenerator.modelOutput);
        ResourceLocation identifier2 = ModelTemplates.SLAB_TOP.create(slabBlock, texturedModel.getMapping(), blockStateModelGenerator.modelOutput);
        blockStateModelGenerator.delegateItemModel(slabBlock, identifier1);
        blockStateModelGenerator.blockStateOutput.accept(BlockModelGenerators.createSlab(slabBlock, identifier1, identifier2, textureId));
    }
    static void registerStair(Block stairBlock, Block textureBlock, BlockModelGenerators blockStateModelGenerator) {
        TexturedModel texturedModel = TexturedModel.CUBE.get(textureBlock);
        ResourceLocation identifier1 = ModelTemplates.STAIRS_INNER.create(stairBlock, texturedModel.getMapping(), blockStateModelGenerator.modelOutput);
        ResourceLocation identifier2 = ModelTemplates.STAIRS_STRAIGHT.create(stairBlock, texturedModel.getMapping(), blockStateModelGenerator.modelOutput);
        ResourceLocation identifier3 = ModelTemplates.STAIRS_OUTER.create(stairBlock, texturedModel.getMapping(), blockStateModelGenerator.modelOutput);
        blockStateModelGenerator.delegateItemModel(stairBlock, identifier2);
        blockStateModelGenerator.blockStateOutput.accept(BlockModelGenerators.createStairs(stairBlock, identifier1, identifier2, identifier3));
    }
    static void registerPumpkin(Block block, BlockModelGenerators blockStateModelGenerator) {
        TextureMapping textureMap = TextureMapping.column(block);
        ResourceLocation identifier = ModelTemplates.CUBE_COLUMN.create(block, textureMap, blockStateModelGenerator.modelOutput);
        blockStateModelGenerator.blockStateOutput.accept(BlockModelGenerators.createSimpleBlock(block, identifier));
    }
    static void registerSweetBerryBush(Block bush, IntegerProperty bushAgeProperty, BlockModelGenerators blockStateModelGenerator) {
        blockStateModelGenerator.blockStateOutput.accept(MultiVariantGenerator.multiVariant(bush).with(PropertyDispatch.property(bushAgeProperty).generate(stage -> Variant.variant().with(VariantProperties.MODEL, blockStateModelGenerator.createSuffixedVariant(bush, "_stage" + stage, ModelTemplates.CROSS, TextureMapping::cross)))));
    }
}
