package com.locipro.neoballerite.datagen;

import com.locipro.neoballerite.NeoBallerite;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.common.conditions.IConditionBuilder;
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
//        threeByThreePacker(recipeOutput, RecipeCategory.MISC, COMPRESSED_BALLERITE_BLOCK, COMPRESSED_BALLERITE_INGOT);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, COMPRESSED_BALLERITE_BLOCK.get())
                .pattern("BBB")
                .pattern("BBB")
                .pattern("BBB")
                .define('B', COMPRESSED_BALLERITE_INGOT)
                .unlockedBy("has_compressed_ballerite", has(COMPRESSED_BALLERITE_INGOT))
                .save(recipeOutput);
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
        offerSmeltingAndSmoking(recipeOutput, List.of(RAW_BALLERITE_BLOCK), RecipeCategory.FOOD, COOKED_BALLERITE,
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

}