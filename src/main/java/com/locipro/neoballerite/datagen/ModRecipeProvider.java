package com.locipro.neoballerite.datagen;

import com.google.common.collect.ImmutableList;
import com.locipro.neoballerite.block.ModBlocks;
import com.locipro.neoballerite.item.ModItems;
import com.locipro.neoballerite.item.NeoJams;
import com.locipro.neoballerite.item.custom.CheeseItem;
import com.locipro.neoballerite.item.custom.JamItem;
import com.locipro.neoballerite.item.tool.NeoClaymoreItem;
import com.locipro.neoballerite.recipe.SandwichRecipe;
import com.locipro.neoballerite.recipe.ShapelessRepairRecipe;
import com.locipro.neoballerite.util.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.conditions.IConditionBuilder;
import net.neoforged.neoforge.registries.DeferredItem;

import static com.locipro.neoballerite.NeoBallerite.MODID;
import static com.locipro.neoballerite.block.ModBlocks.*;
import static com.locipro.neoballerite.item.ModItems.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public ModRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    public static final ImmutableList<ItemLike> LEAD_ORES = ImmutableList.of(LEAD_ORE, DEEPSLATE_LEAD_ORE, RAW_LEAD);


    @Override
    protected void buildRecipes(RecipeOutput recipeOutput) {
        // VERY IMPORTANT : When two recipes result in the same item result, even if the count differs, they are saved under the same json.
        // You will have to do this : .save(recipeOutput, "neoballeite:result_from_different_crafting"); for it to be saved under a different json

        // If you have deepslate ores, you'd do `List.of(X_ORE, DEESPLATE_X_ORE)` and use that as your ingredient in the smelting recipes
        // dont do this wth List<ItemLike> BALLERITE_SMELTABLES = List.of(RAW_BALLERITE, RAW_BALLERITE_BLOCK, COOKED_BALLERITE_BLOCK, BURNT_BALLERITE_BLOCK, CHARRED_BALLERITE_BLOCK);


        threeByThreePacker(recipeOutput, RecipeCategory.MISC, RAW_BALLERITE_BLOCK, RAW_BALLERITE);
        threeByThreePacker(recipeOutput, RecipeCategory.MISC, COOKED_BALLERITE_BLOCK, COOKED_BALLERITE);
        twoByTwoPacker(recipeOutput, RecipeCategory.MISC, CHARRED_BALLERITE_BLOCK, CHARRED_BALLERITE);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, CHARRED_BALLERITE, 4)
                        .requires(CHARRED_BALLERITE_BLOCK)
                        .group("ballerite")
                        .unlockedBy("has_charred_ballerite_block", has(CHARRED_BALLERITE_BLOCK))
                        .save(recipeOutput);


        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, KNIFE)
                .define('#', Tags.Items.INGOTS_IRON)
                .define('X', Items.STICK)
                .pattern(" #")
                .pattern("X ")
                .unlockedBy("has_iron_ingot_tag", has(Tags.Items.INGOTS_IRON))
                .save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, DIAMOND_KNIFE)
                .define('#', Tags.Items.GEMS_DIAMOND)
                .define('X', Items.STICK)
                .pattern(" #")
                .pattern("X ")
                .unlockedBy("has_gems_diamond", has(Tags.Items.GEMS_DIAMOND))
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

        offerClaymore(recipeOutput, WOODEN_CLAYMORE, ItemTags.PLANKS, "planks");
        offerClaymore(recipeOutput, STONE_CLAYMORE, ItemTags.STONE_CRAFTING_MATERIALS, "stone");
        offerClaymore(recipeOutput, IRON_CLAYMORE, Items.IRON_INGOT);
        offerClaymore(recipeOutput, LEAD_CLAYMORE, LEAD_INGOT);
        offerClaymore(recipeOutput, GOLD_CLAYMORE, Items.GOLD_INGOT);
        offerClaymore(recipeOutput, DIAMOND_CLAYMORE, Items.DIAMOND);
        offerClaymore(recipeOutput, BALLERITE_CLAYMORE, COMPRESSED_BALLERITE_INGOT);
        netheriteSmithing(recipeOutput, DIAMOND_CLAYMORE.get(), RecipeCategory.COMBAT, NETHERITE_CLAYMORE.get());


        offerHelmet(recipeOutput, BALLERITE_HELMET, COMPRESSED_BALLERITE_INGOT);
        offerChestplate(recipeOutput, BALLERITE_CHESTPLATE, COMPRESSED_BALLERITE_INGOT);
        offerLeggings(recipeOutput, BALLERITE_LEGGINGS, COMPRESSED_BALLERITE_INGOT);
        offerBoots(recipeOutput, BALLERITE_BOOTS, COMPRESSED_BALLERITE_INGOT);

        offerHelmet(recipeOutput, LEAD_HELMET, LEAD_INGOT);
        offerChestplate(recipeOutput, LEAD_CHESTPLATE, LEAD_INGOT);
        offerLeggings(recipeOutput, LEAD_LEGGINGS, LEAD_INGOT);
        offerBoots(recipeOutput, LEAD_BOOTS, LEAD_INGOT);

        offerBoots(recipeOutput, LEAVES_BOOTS, STAR_LEAVES);

        
        
        
        
        
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
        signBuilder(ModItems.WITHERED_SIGN, Ingredient.of(WITHERED_PLANKS))
                .group("withered")
                .unlockedBy("has_withered_planks", has(WITHERED_PLANKS)).save(recipeOutput);
        hangingSign(recipeOutput, ModItems.WITHERED_HANGING_SIGN, STRIPPED_WITHERED_LOG);


        planksFromLogs(recipeOutput, STAR_PLANKS, ModTags.Items.STAR_LOGS, 4);
        woodFromLogs(recipeOutput, STAR_WOOD, STAR_LOG);

        woodFromLogs(recipeOutput, STRIPPED_STAR_WOOD, STRIPPED_STAR_LOG);


        stairBuilder(STAR_STAIRS, Ingredient.of(STAR_PLANKS))
                .group("star")
                .unlockedBy("has_star_planks", has(STAR_PLANKS)).save(recipeOutput);
        slab(recipeOutput, RecipeCategory.BUILDING_BLOCKS, STAR_SLAB, STAR_PLANKS);

        fenceBuilder(STAR_FENCE, Ingredient.of(STAR_PLANKS))
                .group("star")
                .unlockedBy("has_star_planks", has(STAR_PLANKS)).save(recipeOutput);
        fenceGateBuilder(STAR_FENCE_GATE, Ingredient.of(STAR_PLANKS))
                .group("star")
                .unlockedBy("has_star_planks", has(STAR_PLANKS)).save(recipeOutput);

        buttonBuilder(STAR_BUTTON, Ingredient.of(STAR_PLANKS))
                .group("star")
                .unlockedBy("has_star_planks", has(STAR_PLANKS)).save(recipeOutput);
        pressurePlate(recipeOutput, STAR_PRESSURE_PLATE, STAR_PLANKS);

        doorBuilder(STAR_DOOR, Ingredient.of(STAR_PLANKS))
                .group("star")
                .unlockedBy("has_star_planks", has(STAR_PLANKS)).save(recipeOutput);
        trapdoorBuilder(STAR_TRAPDOOR, Ingredient.of(STAR_PLANKS))
                .group("star")
                .unlockedBy("has_star_planks", has(STAR_PLANKS)).save(recipeOutput);

        signBuilder(ModItems.STAR_SIGN, Ingredient.of(STAR_PLANKS))
                .group("star")
                .unlockedBy("has_star_planks", has(STAR_PLANKS)).save(recipeOutput);
        hangingSign(recipeOutput, ModItems.STAR_HANGING_SIGN, STRIPPED_STAR_LOG);




        threeByThreePacker(recipeOutput, RecipeCategory.BUILDING_BLOCKS, COMPRESSED_BALLERITE_BLOCK, COMPRESSED_BALLERITE_INGOT);
        threeByThreePacker(recipeOutput, RecipeCategory.BUILDING_BLOCKS, LEAD_BLOCK, LEAD_INGOT);
        threeByThreePacker(recipeOutput, RecipeCategory.BUILDING_BLOCKS, RAW_LEAD_BLOCK, RAW_LEAD);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, COMPRESSED_BALLERITE_INGOT, 9)
                .requires(COMPRESSED_BALLERITE_BLOCK)
                .unlockedBy("has_compressed_ballerite_block", has(COMPRESSED_BALLERITE_BLOCK))
                .save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, RAW_LEAD, 9)
                .requires(RAW_LEAD_BLOCK)
                .unlockedBy("has_raw_lead_block", has(RAW_LEAD_BLOCK))
                .save(recipeOutput);


        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, LEAD_BLOCK.get())
                .pattern("BBB")
                .pattern("BBB")
                .pattern("BBB")
                .define('B', LEAD_INGOT)
                .unlockedBy("has_lead_ingot", has(LEAD_INGOT))
                .save(recipeOutput, MODID + ":lead_block_from_ingot");

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, LEAD_NUGGET, 9)
                .requires(LEAD_INGOT)
                .unlockedBy("has_lead_ingot", has(LEAD_INGOT))
                .save(recipeOutput, MODID + ":lead_nugget_from_ingot");

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, LEAD_INGOT)
                .pattern("BBB")
                .pattern("BBB")
                .pattern("BBB")
                .define('B', LEAD_NUGGET)
                .unlockedBy("has_lead_nugget", has(LEAD_NUGGET))
                .save(recipeOutput, MODID + ":lead_ingot_from_nuggets");

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, LEAD_INGOT, 9)
                .requires(LEAD_BLOCK)
                .unlockedBy("has_lead_block", has(LEAD_BLOCK))
                .save(recipeOutput, MODID + ":lead_ingot_from_block");



        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, STRAWBERRY_SEEDS, 2)
                .requires(STRAWBERRY)
                .unlockedBy(getHasName(STRAWBERRY), has(STRAWBERRY))
                .save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, TOMATO_SEEDS, 3)
                .requires(TOMATO)
                .unlockedBy(getHasName(TOMATO), has(TOMATO))
                .save(recipeOutput);
        offerSeeds(recipeOutput, CORN_KERNELS, CORN_COB, 2);

        twoByTwoPacker(recipeOutput, RecipeCategory.BUILDING_BLOCKS, SWEET_POTATO_BLOCK, SWEET_POTATO);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, MILK_VILE, 3)
                .requires(Items.MILK_BUCKET)
                .requires(Items.GLASS_BOTTLE, 3)
                .unlockedBy(getHasName(Items.MILK_BUCKET), has(Items.MILK_BUCKET))
                .save(recipeOutput);

        offerCheese(recipeOutput, MILK_CHEESE);
        offerCheese(recipeOutput, WARPED_CHEESE);






        offerSmeltingAndBlasting(recipeOutput, List.of(BALLERITE_ORE), RecipeCategory.MISC, COOKED_BALLERITE_BLOCK,
                0.7f, 260, "ballerite");
        offerSmeltingAndBlasting(recipeOutput, List.of(COOKED_BALLERITE_BLOCK), RecipeCategory.MISC, BURNT_BALLERITE_BLOCK,
                0.6f, 260, "ballerite");
        offerSmoking(recipeOutput, List.of(COOKED_BALLERITE_BLOCK), RecipeCategory.MISC, BURNT_BALLERITE_BLOCK,
                0.7f, 180, "ballerite");

        offerSmeltingAndBlasting(recipeOutput, List.of(BURNT_BALLERITE_BLOCK), RecipeCategory.MISC, CHARRED_BALLERITE_BLOCK,
                0.7f, 260, "ballerite");


        offerBlasting(recipeOutput, List.of(CHARRED_BALLERITE), RecipeCategory.MISC, COMPRESSED_BALLERITE_INGOT,
                0.9f, 180, "ballerite");

        offerSmeltingAndSmoking(recipeOutput, List.of(RAW_BALLERITE), RecipeCategory.FOOD, COOKED_BALLERITE,
                0.6f, 200, "ballerite");
        offerSmeltingAndSmoking(recipeOutput, List.of(RAW_BALLERITE_BLOCK), RecipeCategory.FOOD, COOKED_BALLERITE_BLOCK,
                0.7f, 200, "ballerite");

        offerSmeltingAndBlasting(recipeOutput, LEAD_ORES, RecipeCategory.MISC, LEAD_INGOT,
                0.7f, 200, "lead");


        offerFoodCooking(recipeOutput, List.of(TOMATO), RecipeCategory.FOOD, GRILLED_TOMATO,
                0.3f, 200, "tomato");
        offerFoodCooking(recipeOutput, List.of(EGGPLANT), RecipeCategory.FOOD, GRILLED_EGGPLANT,
                0.36f, 200, "eggplant");
        offerFoodCooking(recipeOutput, List.of(SWEET_POTATO), RecipeCategory.FOOD, BAKED_SWEET_POTATO,
                0.36f, 200, "eggplant");
        offerFoodCooking(recipeOutput, List.of(CORN_COB), RecipeCategory.FOOD, GRILLED_CORN_COB,
                0.45f, 200, "corn");


        offerSmelting(recipeOutput, List.of(Items.EGG), RecipeCategory.FOOD, EGGS_SCRAMBLED,
                0.4f, 200, "egg");
        offerSmoking(recipeOutput, List.of(Items.EGG), RecipeCategory.FOOD, EGGS_SUNNY,
                0.5f, 100, "egg");
        // Campfires magically imbue eggs with milk and tomatoes. Fascinating.
        offerCampfireCooking(recipeOutput, List.of(Items.EGG), RecipeCategory.FOOD, EGGS_OMLETTE,
                0.3f, 600, "egg");

        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, EGGS_OMLETTE)
                        .requires(Items.EGG)
                        .requires(MILK_VILE)
                        .requires(TOMATO)
                        .unlockedBy(getHasName(Items.EGG), has(Items.EGG))
                        .save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, CORN_BREAD_SLICE, 3)
                .requires(CORN_BREAD)
                .requires(CORN_BREAD)
                .requires(ModTags.Items.KNIVES)
                .unlockedBy(getHasName(CORN_BREAD), has(CORN_BREAD))
                .save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, CORN_BREAD, 1)
                .requires(ModTags.Items.KNIVES)
                .requires(CORN_COB)
                .requires(CORN_COB)
                .unlockedBy(getHasName(CORN_COB), has(CORN_COB))
                .save(recipeOutput);

        offerEightCoveredRecipe(recipeOutput, RecipeCategory.FOOD, IRON_CARROT, Items.CARROT, Items.IRON_INGOT);
        offerEightCoveredRecipe(recipeOutput, RecipeCategory.FOOD, IRON_CARROT, Items.CARROT, ItemTags.create(ResourceLocation.fromNamespaceAndPath("c", "ingots/iron")), "common_iron_ingots");

        offerEightCoveredRecipe(recipeOutput, RecipeCategory.FOOD, DIAMOND_CARROT, Items.CARROT, Items.DIAMOND);
        offerEightCoveredRecipe(recipeOutput, RecipeCategory.FOOD, ENCHANTED_DIAMOND_CARROT, Items.CARROT, Items.DIAMOND_BLOCK);


        /*offerCheesyItemRecipe(recipeOutput, CHEESE_STEAK.get(), MILK_CHEESE.get(), Items.COOKED_BEEF);
        offerCheesyItemRecipe(recipeOutput, CHEESE_PORK.get(), MILK_CHEESE.get(), Items.COOKED_PORKCHOP);
        offerCheesyItemRecipe(recipeOutput, CHEESE_MUTTON.get(), MILK_CHEESE.get(), Items.COOKED_MUTTON);
        offerCheesyItemRecipe(recipeOutput, CHEESE_CHICKEN.get(), MILK_CHEESE.get(), Items.COOKED_CHICKEN);
        offerCheesyItemRecipe(recipeOutput, CHEESE_FRIES.get(), MILK_CHEESE.get(), ModTags.Items.POTATOES, "potatoes");
        */

        offerLantern(recipeOutput, LEAD_LANTERN, LEAD_NUGGET, LEAD_INGOT);
        offerUnlitLantern(recipeOutput, UNLIT_LANTERN, Items.IRON_NUGGET, Items.IRON_INGOT);

        NeoJams.JAMS.iterator().forEachRemaining(item ->
                offerJamRecipe(recipeOutput, item));


        SpecialRecipeBuilder.special(ShapelessRepairRecipe::new)
                .save(recipeOutput, getId("leaves_boots_repair"));
        SpecialRecipeBuilder.special(SandwichRecipe::new)
                .save(recipeOutput, getId("sandwich_recipe"));
    }


    private ResourceLocation getId(String path) {
        return ResourceLocation.fromNamespaceAndPath(MODID, path);
    }

    protected void offerSeeds(RecipeOutput recipeOutput, ItemLike seed, ItemLike ingredient, int count) {
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, seed, count)
                .requires(ingredient)
                .unlockedBy(getHasName(ingredient), has(ingredient))
                .save(recipeOutput);
    }
    protected void offerCheese(RecipeOutput recipeOutput, DeferredItem<CheeseItem> cheeseItem) {
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, cheeseItem, 3)
                .requires(ModTags.getFungalTagForCheese(cheeseItem.get()))
                .requires(MILK_VILE, 3)
                .unlockedBy(getHasName(MILK_VILE), has(MILK_VILE))
                .save(recipeOutput);
    }

    protected void offerSmeltingAndBlasting(RecipeOutput recipeOutput, List<ItemLike> ingredients, RecipeCategory category, ItemLike result, float experience, int smeltingCookingTime, String group) {
        offerSmelting(recipeOutput, ingredients, category, result, experience, smeltingCookingTime, group);
        offerBlasting(recipeOutput, ingredients, category, result, experience, Math.round(((float) smeltingCookingTime /2)), group);
    }
    protected void offerSmeltingAndSmoking(RecipeOutput recipeOutput, List<ItemLike> ingredients, RecipeCategory category, ItemLike result, float experience, int smeltingCookingTime, String group) {
        offerSmelting(recipeOutput, ingredients, category, result, experience, smeltingCookingTime, group);
        offerSmoking(recipeOutput, ingredients, category, result, experience, Math.round(((float) smeltingCookingTime /2)), group);
    }
    /** Smelting, smoking, and campfire cooking.**/
    protected void offerFoodCooking(RecipeOutput recipeOutput, List<ItemLike> ingredients, RecipeCategory category, ItemLike result, float experience, int smeltingCookingTime, String group) {
        offerSmelting(recipeOutput, ingredients, category, result, experience, smeltingCookingTime, group);
        offerSmoking(recipeOutput, ingredients, category, result, experience, Math.round(((float) smeltingCookingTime /2)), group);
        offerCampfireCooking(recipeOutput, ingredients, category, result, experience, smeltingCookingTime * 3, group);
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
    /** default is 600 cooking time.**/
    protected static void offerCampfireCooking(RecipeOutput recipeOutput, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult,
                                       float pExperience, int pCookingTime, String pGroup) {
        cookingRecipe(recipeOutput, RecipeSerializer.CAMPFIRE_COOKING_RECIPE, CampfireCookingRecipe::new, pIngredients, pCategory, pResult,
                pExperience, pCookingTime, pGroup, "_from_campfire_cooking");
    }


    // Custom generic cooking recipe to be saved under OUR mod's namespace.
    // SimpleCookingRecipeBuilder has these methods but they get saved under minecraft's namespace. Which is why we make em.
    protected static <T extends AbstractCookingRecipe> void cookingRecipe(RecipeOutput recipeOutput, RecipeSerializer<T> pCookingSerializer, AbstractCookingRecipe.Factory<T> factory,
                                                                          Iterable<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup, String pRecipeName) {
        for(ItemLike itemlike : pIngredients) {
            SimpleCookingRecipeBuilder.generic(Ingredient.of(itemlike), pCategory, pResult, pExperience, pCookingTime, pCookingSerializer, factory).group(pGroup).unlockedBy(getHasName(itemlike), has(itemlike))
                    .save(recipeOutput, MODID + ":" + getItemName(pResult) + pRecipeName + "_" + getItemName(itemlike));
        }
    }

    protected static void offerClaymore(RecipeOutput recipeOutput, DeferredItem<NeoClaymoreItem> claymore, ItemLike ingredient) {
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, claymore)
                .define('X', Items.STICK)
                .define('#', ingredient)
                .pattern("  #")
                .pattern("## ")
                .pattern("X# ")
                .unlockedBy(getHasName(ingredient), has(ingredient))
                .save(recipeOutput, getItemName(claymore));
    }

    protected static void offerClaymore(RecipeOutput recipeOutput, DeferredItem<NeoClaymoreItem> claymore, TagKey<Item> tagKey, String tagName) {
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, claymore)
                .define('X', Items.STICK)
                .define('#', tagKey)
                .pattern("  #")
                .pattern("## ")
                .pattern("X# ")
                .unlockedBy("has" + tagName, has(tagKey))
                .save(recipeOutput, getItemName(claymore) + "_from_tag_" + tagName);;
    }

    protected static void offerHelmet(RecipeOutput recipeOutput, DeferredItem<?> helmet, ItemLike ingredient) {
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, helmet)
                .define('X', ingredient)
                .pattern("XXX")
                .pattern("X X")
                .unlockedBy(getHasName(ingredient), has(ingredient))
                .save(recipeOutput);
    }
    protected static void offerChestplate(RecipeOutput recipeOutput, DeferredItem<?> chestplate, ItemLike ingredient) {
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, chestplate)
                .define('X', ingredient)
                .pattern("X X")
                .pattern("XXX")
                .pattern("XXX")
                .unlockedBy(getHasName(ingredient), has(ingredient))
                .save(recipeOutput);
    }
    protected static void offerLeggings(RecipeOutput recipeOutput, DeferredItem<?> leggings, ItemLike ingredient) {
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, leggings)
                .define('X', ingredient)
                .pattern("XXX")
                .pattern("X X")
                .pattern("X X")
                .unlockedBy(getHasName(ingredient), has(ingredient))
                .save(recipeOutput);
    }
    protected static void offerBoots(RecipeOutput recipeOutput, DeferredItem<?> boots, ItemLike ingredient) {
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, boots)
                .define('X', ingredient)
                .pattern("X X")
                .pattern("X X")
                .unlockedBy(getHasName(ingredient), has(ingredient))
                .save(recipeOutput);
    }
    protected static void offerTools(RecipeOutput recipeOutput,
                                     DeferredItem<SwordItem> swordItem,
                                     DeferredItem<PickaxeItem> pickaxeItem,
                                     DeferredItem<AxeItem> axeItem,
                                     DeferredItem<ShovelItem> shovelItem,
                                     DeferredItem<HoeItem> hoeItem,
                                     ItemLike repairItem) {
        String condition = getHasName(repairItem);
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


    protected static void offerEightCoveredRecipe(RecipeOutput recipeOutput, RecipeCategory recipeCategory, ItemLike result, ItemLike covered, ItemLike covering) {
        ShapedRecipeBuilder.shaped(recipeCategory, result)
                .define('#', covering)
                .define('O', covered)
                .pattern("###")
                .pattern("#O#")
                .pattern("###")
                .unlockedBy(getHasName(covered), has(covered))
                .save(recipeOutput);
    }
    protected static void offerEightCoveredRecipe(RecipeOutput recipeOutput, RecipeCategory recipeCategory, ItemLike result, ItemLike covered, TagKey<Item> covering, String tagName) {
        ShapedRecipeBuilder.shaped(recipeCategory, result)
                .define('#', covering)
                .define('O', covered)
                .pattern("###")
                .pattern("#O#")
                .pattern("###")
                .unlockedBy(getHasName(covered), has(covered))
                .save(recipeOutput, MODID + ":" + getItemName(result) + "from_tag_" + tagName);
    }

    protected void offerLantern(RecipeOutput recipeOutput, ItemLike lantern, ItemLike nugget, ItemLike ingot) {
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, lantern)
                .define('#', Items.TORCH)
                .define('X', nugget)
                .pattern("XXX")
                .pattern("X#X")
                .pattern("XXX")
                .unlockedBy(getHasName(nugget), has(nugget))
                .unlockedBy(getHasName(ingot), has(ingot))
                .save(recipeOutput);
    }
    protected void offerUnlitLantern(RecipeOutput recipeOutput, ItemLike lantern, ItemLike nugget, ItemLike ingot) {
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, lantern)
                .define('#', Items.GLOWSTONE_DUST)
                .define('X', nugget)
                .pattern("XXX")
                .pattern("X#X")
                .pattern("XXX")
                .unlockedBy(getHasName(nugget), has(nugget))
                .unlockedBy(getHasName(ingot), has(ingot))
                .save(recipeOutput);
    }
    protected void offerJamRecipe(RecipeOutput recipeOutput, DeferredItem<JamItem> jamItem) {
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, jamItem)
                .requires(jamItem.get().getBaseItem(), 2)
                .requires(ModTags.Items.KNIVES)
                .unlockedBy(getHasName(jamItem.get().getBaseItem()), has(jamItem.get().getBaseItem()))
                .save(recipeOutput);
    }
}