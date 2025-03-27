package com.locipro.neoballerite.datagen;

import com.locipro.neoballerite.item.ModItems;
import com.locipro.neoballerite.item.NeoJams;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.ModelProvider;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.data.PackOutput;

import static com.locipro.neoballerite.item.ModItems.*;
import static com.locipro.neoballerite.block.ModBlocks.*;
import static com.locipro.neoballerite.NeoBallerite.MODID;

public class ModItemModelProvider extends ModelProvider {
    public ModItemModelProvider(PackOutput output) {
        super(output, MODID);
    }

    @Override
    protected void registerModels(BlockModelGenerators blockModels, ItemModelGenerators itemModels) {
        itemModels.generateFlatItem(RAW_BALLERITE.get(), ModelTemplates.FLAT_ITEM);

        itemModels.generateFlatItem(RAW_BALLERITE.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(COOKED_BALLERITE.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(CHARRED_BALLERITE.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(COMPRESSED_BALLERITE_INGOT.get(), ModelTemplates.FLAT_ITEM);


        itemModels.generateFlatItem(RAW_LEAD.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(LEAD_INGOT.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(LEAD_NUGGET.get(), ModelTemplates.FLAT_ITEM);

        itemModels.generateFlatItem(WITHERED_DOOR.asItem(), ModelTemplates.FLAT_ITEM);

        itemModels.generateFlatItem(ModItems.WITHERED_SIGN.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.WITHERED_HANGING_SIGN.get(), ModelTemplates.FLAT_ITEM);

        itemModels.generateFlatItem(STAR_DOOR.asItem(), ModelTemplates.FLAT_ITEM);


        itemModels.generateFlatItem(ModItems.STAR_SIGN.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.STAR_HANGING_SIGN.get(), ModelTemplates.FLAT_ITEM);

        itemModels.generateFlatItem(BALLERITE_HELMET.asItem(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(BALLERITE_CHESTPLATE.asItem(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(BALLERITE_LEGGINGS.asItem(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(BALLERITE_BOOTS.asItem(), ModelTemplates.FLAT_ITEM);

        itemModels.generateFlatItem(BALLERITE_HORSE_ARMOR.asItem(), ModelTemplates.FLAT_ITEM);

        itemModels.generateFlatItem(LEAVES_BOOTS.asItem(), ModelTemplates.FLAT_ITEM);

        itemModels.generateFlatItem(LEAD_HELMET.asItem(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(LEAD_CHESTPLATE.asItem(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(LEAD_LEGGINGS.asItem(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(LEAD_BOOTS.asItem(), ModelTemplates.FLAT_ITEM);

        itemModels.generateFlatItem(BLUEBERRIES.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(BLACKBERRIES.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(STRAWBERRY.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(UNRIPE_STRAWBERRY.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(STRAWBERRY_SEEDS.get(), ModelTemplates.FLAT_ITEM);

        itemModels.generateFlatItem(TOMATO.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(GRILLED_TOMATO.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(TOMATO_SEEDS.get(), ModelTemplates.FLAT_ITEM);

        itemModels.generateFlatItem(EGGPLANT.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(GRILLED_EGGPLANT.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(EGGPLANT_SEEDS.get(), ModelTemplates.FLAT_ITEM);

        itemModels.generateFlatItem(SWEET_POTATO.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(BAKED_SWEET_POTATO.get(), ModelTemplates.FLAT_ITEM);

        itemModels.generateFlatItem(CORN_COB.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(GRILLED_CORN_COB.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(CORN_KERNELS.get(), ModelTemplates.FLAT_ITEM);

        itemModels.generateFlatItem(MILK_VILE.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(MILK_CHEESE.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(WARPED_CHEESE.get(), ModelTemplates.FLAT_ITEM);

        itemModels.generateFlatItem(EGGS_SUNNY.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(EGGS_SCRAMBLED.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(EGGS_OMLETTE.get(), ModelTemplates.FLAT_ITEM);

        itemModels.generateFlatItem(IRON_CARROT.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(DIAMOND_CARROT.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ENCHANTED_DIAMOND_CARROT.get(), ModelTemplates.FLAT_ITEM);

        NeoJams.JAMS.iterator().forEachRemaining((item) ->
                itemModels.generateFlatItem(item.get(), ModelTemplates.FLAT_ITEM));
        itemModels.generateFlatItem(CORN_BREAD.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(CORN_BREAD_SLICE.get(), ModelTemplates.FLAT_ITEM);




        itemModels.generateFlatItem(BALLERITE_SWORD.get(), ModelTemplates.FLAT_HANDHELD_ITEM);


        itemModels.generateFlatItem(BALLERITE_SWORD.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(BALLERITE_PICKAXE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(BALLERITE_AXE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(BALLERITE_SHOVEL.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(BALLERITE_HOE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);



        itemModels.generateFlatItem(LEAD_SWORD.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(LEAD_PICKAXE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(LEAD_AXE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(LEAD_SHOVEL.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(LEAD_HOE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);



        itemModels.generateFlatItem(KNIFE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(DIAMOND_KNIFE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);

        itemModels.generateFlatItem(WOODEN_CLAYMORE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(STONE_CLAYMORE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(IRON_CLAYMORE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(LEAD_CLAYMORE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(GOLD_CLAYMORE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(BALLERITE_CLAYMORE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(DIAMOND_CLAYMORE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(NETHERITE_CLAYMORE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);

        itemModels.generateFlatItem(WITHERED_BUTTON.asItem(), ModelTemplates.BUTTON_INVENTORY);
        itemModels.generateFlatItem(STAR_BUTTON.asItem(), ModelTemplates.BUTTON_INVENTORY);


        itemModels.generateFlatItem(WITHERED_FENCE.asItem(), ModelTemplates.FENCE_INVENTORY);
        itemModels.generateFlatItem(STAR_FENCE.asItem(), ModelTemplates.FENCE_INVENTORY);


    }

    /*
    @Override
    protected void registerModels() {

        sandwichVariants(SANDWICH.get());

        
    }*/
    
    



/*
    protected ItemModelBuilder sandwichModel(ItemStack sandwich) {
        return getBuilder(MODID + ":" + SandwichItem.getPath(sandwich))
                .parent(new ModelFile.UncheckedModelFile("item/generated"))
                .texture("layer0", ResourceLocation.fromNamespaceAndPath(MODID, "item/sandwich/" + SandwichItem.getPath(sandwich)));
    }
    protected void sandwichVariants(Item baseItem) {
        ResourceLocation path = BuiltInRegistries.ITEM.getKey(baseItem);

        // Base texture is normally un-reachable without commands.
        ItemModelBuilder base = getBuilder(path.toString())
                .parent(new ModelFile.UncheckedModelFile("item/generated"))
                .texture("layer0", ResourceLocation.fromNamespaceAndPath(MODID, "item/sandwich/" + path.getPath()));

        // Using a Set instead of an ArrayList jumbles shit up. ArrayLists maintain order.
        // POSSIBLE_SANDWICHES is a list of all possible permutations with bread, meat, and cheese, excluding sandwiches with *just* bread.
        for (ItemStack sandwich : NeoSandwiches.POSSIBLE_SANDWICHES) {
            // sandwichModel returns a generated model with a texture path that depends on the sandwich's components
            ItemModelBuilder model = sandwichModel(sandwich);

            // Components are of type Item
            // BREAD/MEAT/CHEESE_MAP maps an Item to a float value
            float bread = sandwich.has(NeoDataComponents.SANDWICH_BREAD) ? NeoSandwiches.getIndex(NeoSandwiches.BREAD_MAP, sandwich.get(NeoDataComponents.SANDWICH_BREAD)) + 1 : 0;
            float meat = sandwich.has(NeoDataComponents.SANDWICH_MEAT) ? NeoSandwiches.getIndex(NeoSandwiches.MEAT_MAP, sandwich.get(NeoDataComponents.SANDWICH_MEAT)) + 1 : 0;
            float cheese = sandwich.has(NeoDataComponents.SANDWICH_CHEESE) ? NeoSandwiches.getIndex(NeoSandwiches.CHEESE_MAP, sandwich.get(NeoDataComponents.SANDWICH_CHEESE)) + 1 : 0;

            // Item properties are all valid
            base.override()
                    .predicate(ResourceLocation.fromNamespaceAndPath(MODID, "bread"), bread)
                    .predicate(ResourceLocation.fromNamespaceAndPath(MODID, "meat"), meat)
                    .predicate(ResourceLocation.fromNamespaceAndPath(MODID, "cheese"), cheese)
                    .model(model);
        }
    }*/

}
