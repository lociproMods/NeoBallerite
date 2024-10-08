package com.locipro.neoballerite.datagen;

import com.locipro.neoballerite.NeoBallerite;
import com.locipro.neoballerite.util.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.common.conditions.IConditionBuilder;
import net.neoforged.neoforge.registries.DeferredItem;

import static com.locipro.neoballerite.block.ModBlocks.*;
import static com.locipro.neoballerite.item.ModItems.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public ModRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    @Override
    protected void buildRecipes(RecipeOutput recipeOutput) {
        // VERY IMPORTANT : When two recipes result in the same item result, even if the count differs, they are saved under the same json.
        // You will have to do this : .save(recipeOutput, "neoballeite:result_from_different_crafting"); for it to be saved under a different json

        // If you have deepslate ores, you'd do `List.of(X_ORE, DEESPLATE_X_ORE)` and use that as your ingredient in the smelting recipes
        // dont do this wth List<ItemLike> BALLERITE_SMELTABLES = List.of(RAW_BALLERITE, RAW_BALLERITE_BLOCK, COOKED_BALLERITE_BLOCK, BURNT_BALLERITE_BLOCK, CHARRED_BALLERITE_BLOCK);


        twoByTwoPacker(recipeOutput, RecipeCategory.MISC, RAW_BALLERITE_BLOCK, RAW_BALLERITE);
        twoByTwoPacker(recipeOutput, RecipeCategory.MISC, COOKED_BALLERITE_BLOCK, COOKED_BALLERITE);
        twoByTwoPacker(recipeOutput, RecipeCategory.MISC, CHARRED_BALLERITE_BLOCK, CHARRED_BALLERITE);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, CHARRED_BALLERITE, 4)
                        .requires(CHARRED_BALLERITE_BLOCK)
                        .group("ballerite")
                        .unlockedBy("has_charred_ballerite_block", has(CHARRED_BALLERITE_BLOCK))
                        .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, BALL_DOWSER)
                .define('#', COOKED_BALLERITE_BLOCK)
                .define('X', Items.STICK)
                .pattern("###")
                .pattern("#X#")
                .pattern(" X ")
                .unlockedBy("has_cooked_ballerite", has(COOKED_BALLERITE))
                .save(recipeOutput);

        
        offerTools(recipeOutput,
                BALLERITE_SWORD,
                BALLERITE_PICKAXE,
                BALLERITE_AXE,
                BALLERITE_SHOVEL,
                BALLERITE_HOE,
                COMPRESSED_BALLERITE_INGOT);
        offerTools(recipeOutput,
                LEAD_SWORD,
                LEAD_PICKAXE,
                LEAD_AXE,
                LEAD_SHOVEL,
                LEAD_HOE,
                LEAD_INGOT);
        
        
        
        
        
        
        planksFromLogs(recipeOutput, WITHERED_PLANKS, ModTags.Items.WITHERED_LOGS, 4);
        woodFromLogs(recipeOutput, WITHERED_WOOD, WITHERED_LOG);
        // Not actually sure if it's supposed to be like this.
        woodFromLogs(recipeOutput, STRIPPED_WITHERED_WOOD, STRIPPED_WITHERED_LOG);


        stairBuilder(WITHERED_STAIRS, Ingredient.of(WITHERED_PLANKS))
                .group("withered")
                .unlockedBy("has_withered_planks", has(WITHERED_PLANKS)).save(recipeOutput);
        slab(recipeOutput, RecipeCategory.BUILDING_BLOCKS, WITHERED_SLAB, WITHERED_PLANKS);

        fenceBuilder(WITHERED_FENCE, Ingredient.of(WITHERED_PLANKS))
                .group("withered")
                .unlockedBy("has_withered_planks", has(WITHERED_PLANKS)).save(recipeOutput);
        fenceGateBuilder(WITHERED_FENCE_GATE, Ingredient.of(WITHERED_PLANKS))
                .group("withered")
                .unlockedBy("has_withered_planks", has(WITHERED_PLANKS)).save(recipeOutput);

        buttonBuilder(WITHERED_BUTTON, Ingredient.of(WITHERED_PLANKS))
                .group("withered")
                .unlockedBy("has_withered_planks", has(WITHERED_PLANKS)).save(recipeOutput);
        pressurePlate(recipeOutput, WITHERED_PRESSURE_PLATE, WITHERED_PLANKS);

        doorBuilder(WITHERED_DOOR, Ingredient.of(WITHERED_PLANKS))
                .group("withered")
                .unlockedBy("has_withered_planks", has(WITHERED_PLANKS)).save(recipeOutput);
        trapdoorBuilder(WITHERED_TRAPDOOR, Ingredient.of(WITHERED_PLANKS))
                .group("withered")
                .unlockedBy("has_withered_planks", has(WITHERED_PLANKS)).save(recipeOutput);







//        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, COMPRESSED_BALLERITE_BLOCK.get())
//                .pattern("BBB")
//                .pattern("BBB")
//                .pattern("BBB")
//                .define('B', COMPRESSED_BALLERITE_INGOT)
//                .unlockedBy("has_compressed_ballerite", has(COMPRESSED_BALLERITE_INGOT))
//                .save(recipeOutput);
        threeByThreePacker(recipeOutput, RecipeCategory.BUILDING_BLOCKS, COMPRESSED_BALLERITE_BLOCK, COMPRESSED_BALLERITE_INGOT);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, COMPRESSED_BALLERITE_INGOT, 9)
                .requires(COMPRESSED_BALLERITE_BLOCK)
                .unlockedBy("has_compressed_ballerite_block", has(COMPRESSED_BALLERITE_BLOCK))
                .save(recipeOutput);


        offerSmeltingAndBlasting(recipeOutput, List.of(BALLERITE_ORE), RecipeCategory.MISC, COOKED_BALLERITE_BLOCK,
                0.25f, 260, "ballerite");
        offerSmeltingAndBlasting(recipeOutput, List.of(COOKED_BALLERITE_BLOCK), RecipeCategory.MISC, BURNT_BALLERITE_BLOCK,
                0.25f, 260, "ballerite");
        offerSmoking(recipeOutput, List.of(COOKED_BALLERITE_BLOCK), RecipeCategory.MISC, BURNT_BALLERITE_BLOCK,
                0.25f, 180, "ballerite");

        offerSmeltingAndBlasting(recipeOutput, List.of(BURNT_BALLERITE_BLOCK), RecipeCategory.MISC, CHARRED_BALLERITE_BLOCK,
                0.25f, 260, "ballerite");
        offerBlasting(recipeOutput, List.of(CHARRED_BALLERITE_BLOCK), RecipeCategory.MISC, COMPRESSED_BALLERITE_BLOCK,
                0.25f, 180, "ballerite");

        offerSmeltingAndSmoking(recipeOutput, List.of(RAW_BALLERITE), RecipeCategory.FOOD, COOKED_BALLERITE,
                0.25f, 200, "ballerite");
        offerSmeltingAndSmoking(recipeOutput, List.of(RAW_BALLERITE_BLOCK), RecipeCategory.FOOD, COOKED_BALLERITE_BLOCK,
                0.25f, 200, "ballerite");


    }



    protected void offerSmeltingAndBlasting(RecipeOutput recipeOutput, List<ItemLike> ingredients, RecipeCategory category, ItemLike result, float experience, int smeltingCookingTime, String group) {
        offerSmelting(recipeOutput, ingredients, category, result, experience, smeltingCookingTime, group);
        offerBlasting(recipeOutput, ingredients, category, result, experience, Math.round(((float) smeltingCookingTime /2)), group);
    }
    protected void offerSmeltingAndSmoking(RecipeOutput recipeOutput, List<ItemLike> ingredients, RecipeCategory category, ItemLike result, float experience, int smeltingCookingTime, String group) {
        offerSmelting(recipeOutput, ingredients, category, result, experience, smeltingCookingTime, group);
        offerSmoking(recipeOutput, ingredients, category, result, experience, Math.round(((float) smeltingCookingTime /2)), group);
    }


    // Custom helper methods because silly game methods would dump our jsons into the MINECRAFT recipes folder.
    protected static void offerSmelting(RecipeOutput recipeOutput, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult,
                                        float pExperience, int pCookingTIme, String pGroup) {
        cookingRecipe(recipeOutput, RecipeSerializer.SMELTING_RECIPE, SmeltingRecipe::new, pIngredients, pCategory, pResult,
                pExperience, pCookingTIme, pGroup, "_from_smelting");
    }
    protected static void offerBlasting(RecipeOutput recipeOutput, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult,
                                        float pExperience, int pCookingTime, String pGroup) {
        cookingRecipe(recipeOutput, RecipeSerializer.BLASTING_RECIPE, BlastingRecipe::new, pIngredients, pCategory, pResult,
                pExperience, pCookingTime, pGroup, "_from_blasting");
    }
    protected static void offerSmoking(RecipeOutput recipeOutput, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult,
                                       float pExperience, int pCookingTime, String pGroup) {
        cookingRecipe(recipeOutput, RecipeSerializer.SMOKING_RECIPE, SmokingRecipe::new, pIngredients, pCategory, pResult,
                pExperience, pCookingTime, pGroup, "_from_smoking");
    }

    // Custom generic cooking recipe to be saved under OUR mod's namespace.
    protected static <T extends AbstractCookingRecipe> void cookingRecipe(RecipeOutput recipeOutput, RecipeSerializer<T> pCookingSerializer, AbstractCookingRecipe.Factory<T> factory,
                                                                       List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup, String pRecipeName) {
        for(ItemLike itemlike : pIngredients) {
            SimpleCookingRecipeBuilder.generic(Ingredient.of(itemlike), pCategory, pResult, pExperience, pCookingTime, pCookingSerializer, factory).group(pGroup).unlockedBy(getHasName(itemlike), has(itemlike))
                    .save(recipeOutput, NeoBallerite.MODID + ":" + getItemName(pResult) + pRecipeName + "_" + getItemName(itemlike));
        }
    }

    protected static void offerTools(RecipeOutput recipeOutput,
                                     DeferredItem<SwordItem> swordItem,
                                     DeferredItem<PickaxeItem> pickaxeItem,
                                     DeferredItem<AxeItem> axeItem,
                                     DeferredItem<ShovelItem> shovelItem,
                                     DeferredItem<HoeItem> hoeItem,
                                     DeferredItem<?> repairItem) {
//        Ingredient repairIngredient = hoeItem.get().getTier().getRepairIngredient();
//        Item repairItem = repairIngredient.getItems()[0].getItem();
//        ResourceLocation repairIngLoc = ResourceLocation.fromNamespaceAndPath(repairItem.getNa)
//        String repairItemName = repairItem.getDescription()
        String condition = "has_" + repairItem.getRegisteredName().replace(repairItem.getId().getNamespace() + ":", "");
        System.out.println(condition);
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, hoeItem)
                .define('#', Items.STICK)
                .define('X', repairItem)
                .pattern("XX")
                .pattern(" #")
                .pattern(" #")
                .unlockedBy(condition, has(repairItem))
                .save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, pickaxeItem)
                .define('#', Items.STICK)
                .define('X', repairItem)
                .pattern("XXX")
                .pattern(" # ")
                .pattern(" # ")
                .unlockedBy(condition, has(repairItem))
                .save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, shovelItem)
                .define('#', Items.STICK)
                .define('X', repairItem)
                .pattern("X")
                .pattern("#")
                .pattern("#")
                .unlockedBy(condition, has(repairItem))
                .save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, swordItem)
                .define('#', Items.STICK)
                .define('X', repairItem)
                .pattern("X")
                .pattern("X")
                .pattern("#")
                .unlockedBy(condition, has(repairItem))
                .save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, axeItem)
                .define('#', Items.STICK)
                .define('X', repairItem)
                .pattern("XX")
                .pattern("X#")
                .pattern(" #")
                .unlockedBy(condition, has(repairItem))
                .save(recipeOutput);
    }

}
