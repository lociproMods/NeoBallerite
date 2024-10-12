package com.locipro.neoballerite.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.ButtonBlock;
import net.minecraft.world.level.block.FenceBlock;
import net.minecraft.world.level.block.WallBlock;
import net.neoforged.neoforge.client.model.generators.ItemModelBuilder;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;

import static com.locipro.neoballerite.item.ModItems.*;
import static com.locipro.neoballerite.block.ModBlocks.*;
import static com.locipro.neoballerite.NeoBallerite.MODID;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        basicItem(RAW_BALLERITE.get());
        basicItem(RAW_BALLERITE.get());
        basicItem(COOKED_BALLERITE.get());
        basicItem(CHARRED_BALLERITE.get());
        basicItem(COMPRESSED_BALLERITE_INGOT.get());
        basicItem(BALL_DOWSER.get());


        basicItem(RAW_LEAD.get());
        basicItem(LEAD_INGOT.get());
        basicItem(LEAD_NUGGET.get());

//        basicItem(WITHERED_LOG.asItem());
//        basicItem(STRIPPED_WITHERED_LOG.asItem());
//        basicItem(WITHERED_WOOD.asItem());
//        basicItem(STRIPPED_WITHERED_WOOD.asItem());

        buttonItem(WITHERED_BUTTON, WITHERED_PLANKS);
        fenceItem(WITHERED_FENCE, WITHERED_PLANKS);

        basicItem(WITHERED_DOOR.asItem());
        
        buttonItem(STAR_BUTTON, STAR_PLANKS);
        fenceItem(STAR_FENCE, STAR_PLANKS);

        basicItem(STAR_DOOR.asItem());
//        basicItem(WITHERED_SAPLING.asItem());

        
        handheld(BALLERITE_SWORD);
        handheld(BALLERITE_PICKAXE);
        handheld(BALLERITE_AXE);
        handheld(BALLERITE_SHOVEL);
        handheld(BALLERITE_HOE);
        
        basicItem(BALLERITE_HELMET.asItem());
        basicItem(BALLERITE_CHESTPLATE.asItem());
        basicItem(BALLERITE_LEGGINGS.asItem());
        basicItem(BALLERITE_BOOTS.asItem());

        basicItem(LEAVES_BOOTS.asItem());

        handheld(LEAD_SWORD);
        handheld(LEAD_PICKAXE);
        handheld(LEAD_AXE);
        handheld(LEAD_SHOVEL);
        handheld(LEAD_HOE);

        basicItem(LEAD_HELMET.asItem());
        basicItem(LEAD_CHESTPLATE.asItem());
        basicItem(LEAD_LEGGINGS.asItem());
        basicItem(LEAD_BOOTS.asItem());

        handheld(KNIFE);

        handheld(WOODEN_CLAYMORE);
        handheld(STONE_CLAYMORE);
        handheld(IRON_CLAYMORE);
        handheld(LEAD_CLAYMORE);
        handheld(GOLD_CLAYMORE);
        handheld(BALLERITE_CLAYMORE);
        handheld(DIAMOND_CLAYMORE);
        handheld(NETHERITE_CLAYMORE);

        basicItem(BLUEBERRIES.get());
        basicItem(BLACKBERRIES.get());
        basicItem(STRAWBERRY.get());
        basicItem(UNRIPE_STRAWBERRY.get());
        basicItem(STRAWBERRY_SEEDS.get());

        basicItem(TOMATO.get());
        basicItem(GRILLED_TOMATO.get());
        basicItem(TOMATO_SEEDS.get());
        
        basicItem(EGGPLANT.get());
        basicItem(GRILLED_EGGPLANT.get());
        basicItem(EGGPLANT_SEEDS.get());

        basicItem(SWEET_POTATO.get());
        basicItem(BAKED_SWEET_POTATO.get());


    }
    
    

    public void buttonItem(DeferredBlock<ButtonBlock> block, DeferredBlock<Block> baseBlock) {
        this.withExistingParent(block.getId().getPath(), mcLoc("block/button_inventory"))
                .texture("texture",  ResourceLocation.fromNamespaceAndPath(MODID,
                        "block/" + baseBlock.getId().getPath()));
    }

    public void fenceItem(DeferredBlock<FenceBlock> block, DeferredBlock<Block> baseBlock) {
        this.withExistingParent(block.getId().getPath(), mcLoc("block/fence_inventory"))
                .texture("texture",  ResourceLocation.fromNamespaceAndPath(MODID,
                        "block/" + baseBlock.getId().getPath()));
    }

    public void wallItem(DeferredBlock<WallBlock> block, DeferredBlock<Block> baseBlock) {
        this.withExistingParent(block.getId().getPath(), mcLoc("block/wall_inventory"))
                .texture("wall",  ResourceLocation.fromNamespaceAndPath(MODID,
                        "block/" + baseBlock.getId().getPath()));
    }
    
    /** new ModelFile.UncheckedModelFile("item/generated")**/
//    public ItemModelBuilder basicItemWithModel(DeferredItem<?> item, ModelFile modelFile) {
//        String resourceLocation = Objects.requireNonNull(item.getKey()).toString();
//        System.out.println(resourceLocation);
//        return getBuilder(resourceLocation)
//                .parent(modelFile)
//                .texture("layer0", ResourceLocation.fromNamespaceAndPath(item.getId().getNamespace(), "item/" + item.getId().getPath()));
//    }
//    public ItemModelBuilder handheld(DeferredItem<?> item) {
//        return basicItemWithModel(item, new ModelFile.UncheckedModelFile("item/handheld"));
//    }
    public ItemModelBuilder handheld(DeferredItem<?> item) {
        String itemPath = item.getId().getPath();
        return withExistingParent(
                itemPath,
                mcLoc("item/handheld")).texture("layer0", "item/" + itemPath);
    }
    /*public ItemModelBuilder generateTools(DeferredItem<SwordItem> swordItem,
                                          DeferredItem<PickaxeItem> pickaxeItem,
                                          DeferredItem<AxeItem> axeItem,
                                          DeferredItem<ShovelItem> shovelItem,
                                          DeferredItem<HoeItem> hoeItem) {
        basicItemWithModel(swordItem, new ModelFile.UncheckedModelFile("item/handheld"));
        
    }*/
}
