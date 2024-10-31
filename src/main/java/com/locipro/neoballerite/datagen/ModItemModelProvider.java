package com.locipro.neoballerite.datagen;

import com.locipro.neoballerite.component.NeoDataComponents;
import com.locipro.neoballerite.item.ModItems;
import com.locipro.neoballerite.item.NeoJams;
import com.locipro.neoballerite.item.NeoSandwiches;
import com.locipro.neoballerite.item.custom.SandwichItem;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.ButtonBlock;
import net.minecraft.world.level.block.FenceBlock;
import net.minecraft.world.level.block.WallBlock;
import net.neoforged.neoforge.client.model.generators.ItemModelBuilder;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.client.model.generators.ModelBuilder;
import net.neoforged.neoforge.client.model.generators.ModelFile;
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

        basicItem(BALLERITE_HORSE_ARMOR.asItem());

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
        handheld(DIAMOND_KNIFE);

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

        basicItem(CORN_COB.get());
        basicItem(GRILLED_CORN_COB.get());
        basicItem(CORN_KERNELS.get());

        basicItem(MILK_VILE.get());
        basicItem(MILK_CHEESE.get());
        basicItem(WARPED_CHEESE.get());

        basicItem(EGGS_SUNNY.get());
        basicItem(EGGS_SCRAMBLED.get());
        basicItem(EGGS_OMLETTE.get());

        basicItem(IRON_CARROT.get());
        basicItem(DIAMOND_CARROT.get());
        basicItem(ENCHANTED_DIAMOND_CARROT.get());



//        basicItem(CHEESE_CHICKEN.get());
//        basicItem(CHEESE_STEAK.get());
//        basicItem(CHEESE_PORK.get());
//        basicItem(CHEESE_MUTTON.get());
//        basicItem(CHEESE_FRIES.get());
//
//        basicItem(CHEESE_CHICKEN_SANDWICH.get());
//        basicItem(CHEESE_STEAK_SANDWICH.get());
//        basicItem(CHEESE_PORK_SANDWICH.get());
//        basicItem(CHEESE_MUTTON_SANDWICH.get());
//        basicItem(CHEESE_FRIES_SANDWICH.get());
//
//
//        basicItem(CHEESE_SANDWICH.get());
//        basicItem(STEAK_SANDWICH.get());
//        basicItem(CHICKEN_SANDWICH.get());
//        basicItem(PORK_SANDWICH.get());
//        basicItem(MUTTON_SANDWICH.get());
//        basicItem(FRIES_SANDWICH.get());

        NeoJams.JAMS.iterator().forEachRemaining((item) ->
                basicItem(item.get()));
        //NeoSandwiches.POSSIBLE_SANDWICHES.iterator().forEachRemaining(this::sandwichModel);
        baseSandwichModel(SANDWICH.get());
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
    public void sandwichModel(ItemStack sandwich) {
        getBuilder(MODID + ":" + SandwichItem.getPath(sandwich))
                .parent(new ModelFile.UncheckedModelFile("item/generated"))
                .texture("layer0", ResourceLocation.fromNamespaceAndPath(MODID, "item/sandwich/" + SandwichItem.getPath(sandwich)));
    }
    public void baseSandwichModel(Item sandwichItem) {
        ResourceLocation path = BuiltInRegistries.ITEM.getKey(sandwichItem);
        ItemModelBuilder base = getBuilder(path.toString())
                .parent(new ModelFile.UncheckedModelFile("item/generated"))
                .texture("layer0", ResourceLocation.fromNamespaceAndPath(MODID, "item/" + path.getPath()));
        for (ItemStack sandwich : NeoSandwiches.POSSIBLE_SANDWICHES) {
            ItemModelBuilder model = getBuilder(MODID + ":" + SandwichItem.getPath(sandwich))
                    .parent(new ModelFile.UncheckedModelFile("item/generated"))
                    .texture("layer0", ResourceLocation.fromNamespaceAndPath(MODID, "item/sandwich/" + SandwichItem.getPath(sandwich)));

            float bread = sandwich.has(NeoDataComponents.SANDWICH_BREAD) ? NeoSandwiches.BREAD_MAP.get(sandwich.get(NeoDataComponents.SANDWICH_BREAD)) : 0;
            float meat = sandwich.has(NeoDataComponents.SANDWICH_MEAT) ? NeoSandwiches.MEAT_MAP.get(sandwich.get(NeoDataComponents.SANDWICH_MEAT)) : 0;
            float cheese = sandwich.has(NeoDataComponents.SANDWICH_CHEESE) ? NeoSandwiches.CHEESE_MAP.get(sandwich.get(NeoDataComponents.SANDWICH_CHEESE)) : 0;

            base.override()
                    .predicate(ResourceLocation.fromNamespaceAndPath(MODID, "bread"), bread)
                    .predicate(ResourceLocation.fromNamespaceAndPath(MODID, "meat"), meat)
                    .predicate(ResourceLocation.fromNamespaceAndPath(MODID, "cheese"), cheese)
                    .model(model);
        }
    }
    /*public ItemModelBuilder basicItem(ResourceLocation item) {
        return getBuilder(item.toString())
                .parent(new ModelFile.UncheckedModelFile("item/generated"))
                .texture("layer0", ResourceLocation.fromNamespaceAndPath(item.getNamespace(), "item/" + item.getPath()));
    }*/




    public ItemModelBuilder handheld(DeferredItem<?> item) {
        String itemPath = item.getId().getPath();
        return withExistingParent(
                itemPath,
                mcLoc("item/handheld")).texture("layer0", "item/" + itemPath);
    }
}
