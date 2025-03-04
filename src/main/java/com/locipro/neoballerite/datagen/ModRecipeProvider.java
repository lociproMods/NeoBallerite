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
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.registries.DeferredItem;
import org.jetbrains.annotations.NotNull;

import static com.locipro.neoballerite.NeoBallerite.MODID;
import static com.locipro.neoballerite.block.ModBlocks.*;
import static com.locipro.neoballerite.item.ModItems.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends RecipeProvider {


    public static final ImmutableList<ItemLike> LEAD_ORES = ImmutableList.of(LEAD_ORE, DEEPSLATE_LEAD_ORE, RAW_LEAD);

    protected ModRecipeProvider(HolderLookup.Provider registries, RecipeOutput output) {
        super(registries, output);
    }

    public static class Runner extends RecipeProvider.Runner {
        public Runner(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> registries) {
            super(packOutput, registries);
        }

        @Override
        protected @NotNull RecipeProvider createRecipeProvider(HolderLookup.Provider registries, RecipeOutput output) {
            return new ModRecipeProvider(registries, output);
        }

        @Override
        public @NotNull String getName() {
            return "NeoRecipes";
        }
    }


    @Override
    protected void buildRecipes() {
        // VERY IMPORTANT : When two recipes result in the same item result, even if the count differs, they are saved under the same json.
        // You will have to do this : .save(recipeOutput, "neoballeite:result_from_different_crafting"); for it to be saved under a different json

        // If you have deepslate ores, you'd do `List.of(X_ORE, DEESPLATE_X_ORE)` and use that as your ingredient in the smelting recipes
        // dont do this wth List<ItemLike> BALLERITE_SMELTABLES = List.of(RAW_BALLERITE, RAW_BALLERITE_BLOCK, COOKED_BALLERITE_BLOCK, BURNT_BALLERITE_BLOCK, CHARRED_BALLERITE_BLOCK);



        HolderGetter<Item> itemRegistry =  registries.lookupOrThrow(Registries.ITEM);
        threeByThreePacker(RecipeCategory.MISC, RAW_BALLERITE_BLOCK, RAW_BALLERITE);
        threeByThreePacker(RecipeCategory.MISC, COOKED_BALLERITE_BLOCK, COOKED_BALLERITE);
        twoByTwoPacker(RecipeCategory.MISC, CHARRED_BALLERITE_BLOCK, CHARRED_BALLERITE);
        ShapelessRecipeBuilder.shapeless(itemRegistry, RecipeCategory.MISC, CHARRED_BALLERITE, 4)
                        .requires(CHARRED_BALLERITE_BLOCK)
                        .group("ballerite")
                        .unlockedBy("has_charred_ballerite_block", has(CHARRED_BALLERITE_BLOCK))
                        .save(output);


        ShapedRecipeBuilder.shaped(itemRegistry, RecipeCategory.TOOLS, KNIFE)
                .define('#', Tags.Items.INGOTS_IRON)
                .define('X', Items.STICK)
                .pattern(" #")
                .pattern("X ")
                .unlockedBy("has_iron_ingot_tag", has(Tags.Items.INGOTS_IRON))
                .save(output);
        ShapedRecipeBuilder.shaped(itemRegistry, RecipeCategory.TOOLS, DIAMOND_KNIFE)
                .define('#', Tags.Items.GEMS_DIAMOND)
                .define('X', Items.STICK)
                .pattern(" #")
                .pattern("X ")
                .unlockedBy("has_gems_diamond", has(Tags.Items.GEMS_DIAMOND))
                .save(output);


        offerTools(itemRegistry, output,
                BALLERITE_SWORD,
                BALLERITE_PICKAXE,
                BALLERITE_AXE,
                BALLERITE_SHOVEL,
                BALLERITE_HOE,
                COMPRESSED_BALLERITE_INGOT);
        offerTools(itemRegistry, output,
                LEAD_SWORD,
                LEAD_PICKAXE,
                LEAD_AXE,
                LEAD_SHOVEL,
                LEAD_HOE,
                LEAD_INGOT);

        offerClaymore(itemRegistry, output, WOODEN_CLAYMORE, ItemTags.PLANKS, "planks");
        offerClaymore(itemRegistry, output, STONE_CLAYMORE, ItemTags.STONE_CRAFTING_MATERIALS, "stone");
        offerClaymore(itemRegistry, output, IRON_CLAYMORE, Items.IRON_INGOT);
        offerClaymore(itemRegistry, output, LEAD_CLAYMORE, LEAD_INGOT);
        offerClaymore(itemRegistry, output, GOLD_CLAYMORE, Items.GOLD_INGOT);
        offerClaymore(itemRegistry, output, DIAMOND_CLAYMORE, Items.DIAMOND);
        offerClaymore(itemRegistry, output, BALLERITE_CLAYMORE, COMPRESSED_BALLERITE_INGOT);
        netheriteSmithing(DIAMOND_CLAYMORE.get(), RecipeCategory.COMBAT, NETHERITE_CLAYMORE.get());


        offerHelmet(itemRegistry, output, BALLERITE_HELMET, COMPRESSED_BALLERITE_INGOT);
        offerChestplate(itemRegistry, output, BALLERITE_CHESTPLATE, COMPRESSED_BALLERITE_INGOT);
        offerLeggings(itemRegistry, output, BALLERITE_LEGGINGS, COMPRESSED_BALLERITE_INGOT);
        offerBoots(itemRegistry, output, BALLERITE_BOOTS, COMPRESSED_BALLERITE_INGOT);

        offerHelmet(itemRegistry, output, LEAD_HELMET, LEAD_INGOT);
        offerChestplate(itemRegistry, output, LEAD_CHESTPLATE, LEAD_INGOT);
        offerLeggings(itemRegistry, output, LEAD_LEGGINGS, LEAD_INGOT);
        offerBoots(itemRegistry, output, LEAD_BOOTS, LEAD_INGOT);

        offerBoots(itemRegistry, output, LEAVES_BOOTS, STAR_LEAVES);






        planksFromLogs(WITHERED_PLANKS, ModTags.Items.WITHERED_LOGS, 4);
        woodFromLogs(WITHERED_WOOD, WITHERED_LOG);
        // Not actually sure if it's supposed to be like this.
        woodFromLogs(STRIPPED_WITHERED_WOOD, STRIPPED_WITHERED_LOG);


        stairBuilder(WITHERED_STAIRS, Ingredient.of(WITHERED_PLANKS))
                .group("withered")
                .unlockedBy("has_withered_planks", has(WITHERED_PLANKS)).save(output);
        slab(RecipeCategory.BUILDING_BLOCKS, WITHERED_SLAB, WITHERED_PLANKS);

        fenceBuilder(WITHERED_FENCE, Ingredient.of(WITHERED_PLANKS))
                .group("withered")
                .unlockedBy("has_withered_planks", has(WITHERED_PLANKS)).save(output);
        fenceGateBuilder(WITHERED_FENCE_GATE, Ingredient.of(WITHERED_PLANKS))
                .group("withered")
                .unlockedBy("has_withered_planks", has(WITHERED_PLANKS)).save(output);

        buttonBuilder(WITHERED_BUTTON, Ingredient.of(WITHERED_PLANKS))
                .group("withered")
                .unlockedBy("has_withered_planks", has(WITHERED_PLANKS)).save(output);
        pressurePlate(WITHERED_PRESSURE_PLATE, WITHERED_PLANKS);

        doorBuilder(WITHERED_DOOR, Ingredient.of(WITHERED_PLANKS))
                .group("withered")
                .unlockedBy("has_withered_planks", has(WITHERED_PLANKS)).save(output);
        trapdoorBuilder(WITHERED_TRAPDOOR, Ingredient.of(WITHERED_PLANKS))
                .group("withered")
                .unlockedBy("has_withered_planks", has(WITHERED_PLANKS)).save(output);
        signBuilder(ModItems.WITHERED_SIGN, Ingredient.of(WITHERED_PLANKS))
                .group("withered")
                .unlockedBy("has_withered_planks", has(WITHERED_PLANKS)).save(output);
        hangingSign(ModItems.WITHERED_HANGING_SIGN, STRIPPED_WITHERED_LOG);


        planksFromLogs(STAR_PLANKS, ModTags.Items.STAR_LOGS, 4);
        woodFromLogs(STAR_WOOD, STAR_LOG);

        woodFromLogs(STRIPPED_STAR_WOOD, STRIPPED_STAR_LOG);


        stairBuilder(STAR_STAIRS, Ingredient.of(STAR_PLANKS))
                .group("star")
                .unlockedBy("has_star_planks", has(STAR_PLANKS)).save(output);
        slab(RecipeCategory.BUILDING_BLOCKS, STAR_SLAB, STAR_PLANKS);

        fenceBuilder(STAR_FENCE, Ingredient.of(STAR_PLANKS))
                .group("star")
                .unlockedBy("has_star_planks", has(STAR_PLANKS)).save(output);
        fenceGateBuilder(STAR_FENCE_GATE, Ingredient.of(STAR_PLANKS))
                .group("star")
                .unlockedBy("has_star_planks", has(STAR_PLANKS)).save(output);

        buttonBuilder(STAR_BUTTON, Ingredient.of(STAR_PLANKS))
                .group("star")
                .unlockedBy("has_star_planks", has(STAR_PLANKS)).save(output);
        pressurePlate(STAR_PRESSURE_PLATE, STAR_PLANKS);

        doorBuilder(STAR_DOOR, Ingredient.of(STAR_PLANKS))
                .group("star")
                .unlockedBy("has_star_planks", has(STAR_PLANKS)).save(output);
        trapdoorBuilder(STAR_TRAPDOOR, Ingredient.of(STAR_PLANKS))
                .group("star")
                .unlockedBy("has_star_planks", has(STAR_PLANKS)).save(output);

        signBuilder(ModItems.STAR_SIGN, Ingredient.of(STAR_PLANKS))
                .group("star")
                .unlockedBy("has_star_planks", has(STAR_PLANKS)).save(output);
        hangingSign(ModItems.STAR_HANGING_SIGN, STRIPPED_STAR_LOG);




        threeByThreePacker(RecipeCategory.BUILDING_BLOCKS, COMPRESSED_BALLERITE_BLOCK, COMPRESSED_BALLERITE_INGOT);
        threeByThreePacker(RecipeCategory.BUILDING_BLOCKS, LEAD_BLOCK, LEAD_INGOT);
        threeByThreePacker(RecipeCategory.BUILDING_BLOCKS, RAW_LEAD_BLOCK, RAW_LEAD);

        ShapelessRecipeBuilder.shapeless(itemRegistry, RecipeCategory.MISC, COMPRESSED_BALLERITE_INGOT, 9)
                .requires(COMPRESSED_BALLERITE_BLOCK)
                .unlockedBy("has_compressed_ballerite_block", has(COMPRESSED_BALLERITE_BLOCK))
                .save(output);
        ShapelessRecipeBuilder.shapeless(itemRegistry, RecipeCategory.MISC, RAW_LEAD, 9)
                .requires(RAW_LEAD_BLOCK)
                .unlockedBy("has_raw_lead_block", has(RAW_LEAD_BLOCK))
                .save(output);


        ShapedRecipeBuilder.shaped(itemRegistry, RecipeCategory.BUILDING_BLOCKS, LEAD_BLOCK.get())
                .pattern("BBB")
                .pattern("BBB")
                .pattern("BBB")
                .define('B', LEAD_INGOT)
                .unlockedBy("has_lead_ingot", has(LEAD_INGOT))
                .save(output, MODID + ":lead_block_from_ingot");

        ShapelessRecipeBuilder.shapeless(itemRegistry, RecipeCategory.MISC, LEAD_NUGGET, 9)
                .requires(LEAD_INGOT)
                .unlockedBy("has_lead_ingot", has(LEAD_INGOT))
                .save(output, MODID + ":lead_nugget_from_ingot");

        ShapedRecipeBuilder.shaped(itemRegistry, RecipeCategory.BUILDING_BLOCKS, LEAD_INGOT)
                .pattern("BBB")
                .pattern("BBB")
                .pattern("BBB")
                .define('B', LEAD_NUGGET)
                .unlockedBy("has_lead_nugget", has(LEAD_NUGGET))
                .save(output, MODID + ":lead_ingot_from_nuggets");

        ShapelessRecipeBuilder.shapeless(itemRegistry, RecipeCategory.MISC, LEAD_INGOT, 9)
                .requires(LEAD_BLOCK)
                .unlockedBy("has_lead_block", has(LEAD_BLOCK))
                .save(output, MODID + ":lead_ingot_from_block");



        ShapelessRecipeBuilder.shapeless(itemRegistry, RecipeCategory.FOOD, STRAWBERRY_SEEDS, 2)
                .requires(STRAWBERRY)
                .unlockedBy(getHasName(STRAWBERRY), has(STRAWBERRY))
                .save(output);
        ShapelessRecipeBuilder.shapeless(itemRegistry, RecipeCategory.FOOD, TOMATO_SEEDS, 3)
                .requires(TOMATO)
                .unlockedBy(getHasName(TOMATO), has(TOMATO))
                .save(output);
        offerSeeds(itemRegistry, output, CORN_KERNELS, CORN_COB, 2);

        twoByTwoPacker( RecipeCategory.BUILDING_BLOCKS, SWEET_POTATO_BLOCK, SWEET_POTATO);

        ShapelessRecipeBuilder.shapeless(itemRegistry, RecipeCategory.FOOD, MILK_VILE, 3)
                .requires(Items.MILK_BUCKET)
                .requires(Items.GLASS_BOTTLE, 3)
                .unlockedBy(getHasName(Items.MILK_BUCKET), has(Items.MILK_BUCKET))
                .save(output);

        offerCheese(itemRegistry, output, MILK_CHEESE);
        offerCheese(itemRegistry, output, WARPED_CHEESE);






        offerSmeltingAndBlasting(output, List.of(BALLERITE_ORE), RecipeCategory.MISC, COOKED_BALLERITE_BLOCK,
                0.7f, 260, "ballerite");
        offerSmeltingAndBlasting(output, List.of(COOKED_BALLERITE_BLOCK), RecipeCategory.MISC, BURNT_BALLERITE_BLOCK,
                0.6f, 260, "ballerite");
        offerSmoking(output, List.of(COOKED_BALLERITE_BLOCK), RecipeCategory.MISC, BURNT_BALLERITE_BLOCK,
                0.7f, 180, "ballerite");

        offerSmeltingAndBlasting(output, List.of(BURNT_BALLERITE_BLOCK), RecipeCategory.MISC, CHARRED_BALLERITE_BLOCK,
                0.7f, 260, "ballerite");


        offerBlasting(output, List.of(CHARRED_BALLERITE), RecipeCategory.MISC, COMPRESSED_BALLERITE_INGOT,
                0.9f, 180, "ballerite");

        offerSmeltingAndSmoking(output, List.of(RAW_BALLERITE), RecipeCategory.FOOD, COOKED_BALLERITE,
                0.6f, 200, "ballerite");
        offerSmeltingAndSmoking(output, List.of(RAW_BALLERITE_BLOCK), RecipeCategory.FOOD, COOKED_BALLERITE_BLOCK,
                0.7f, 200, "ballerite");

        offerSmeltingAndBlasting(output, LEAD_ORES, RecipeCategory.MISC, LEAD_INGOT,
                0.7f, 200, "lead");


        offerFoodCooking(output, List.of(TOMATO), RecipeCategory.FOOD, GRILLED_TOMATO,
                0.3f, 200, "tomato");
        offerFoodCooking(output, List.of(EGGPLANT), RecipeCategory.FOOD, GRILLED_EGGPLANT,
                0.36f, 200, "eggplant");
        offerFoodCooking(output, List.of(SWEET_POTATO), RecipeCategory.FOOD, BAKED_SWEET_POTATO,
                0.36f, 200, "eggplant");
        offerFoodCooking(output, List.of(CORN_COB), RecipeCategory.FOOD, GRILLED_CORN_COB,
                0.45f, 200, "corn");


        offerSmelting(output, List.of(Items.EGG), RecipeCategory.FOOD, EGGS_SCRAMBLED,
                0.4f, 200, "egg");
        offerSmoking(output, List.of(Items.EGG), RecipeCategory.FOOD, EGGS_SUNNY,
                0.5f, 100, "egg");
        // Campfires magically imbue eggs with milk and tomatoes. Fascinating.
        offerCampfireCooking(output, List.of(Items.EGG), RecipeCategory.FOOD, EGGS_OMLETTE,
                0.3f, 600, "egg");

        ShapelessRecipeBuilder.shapeless(itemRegistry, RecipeCategory.FOOD, EGGS_OMLETTE)
                        .requires(Items.EGG)
                        .requires(MILK_VILE)
                        .requires(TOMATO)
                        .unlockedBy(getHasName(Items.EGG), has(Items.EGG))
                        .save(output);

        ShapelessRecipeBuilder.shapeless(itemRegistry, RecipeCategory.FOOD, CORN_BREAD_SLICE, 3)
                .requires(CORN_BREAD)
                .requires(CORN_BREAD)
                .requires(ModTags.Items.KNIVES)
                .unlockedBy(getHasName(CORN_BREAD), has(CORN_BREAD))
                .save(output);

        ShapelessRecipeBuilder.shapeless(itemRegistry, RecipeCategory.FOOD, CORN_BREAD, 1)
                .requires(ModTags.Items.KNIVES)
                .requires(CORN_COB)
                .requires(CORN_COB)
                .unlockedBy(getHasName(CORN_COB), has(CORN_COB))
                .save(output);

        offerEightCoveredRecipe(itemRegistry, output, RecipeCategory.FOOD, IRON_CARROT, Items.CARROT, Items.IRON_INGOT);
        offerEightCoveredRecipe(itemRegistry, output, RecipeCategory.FOOD, IRON_CARROT, Items.CARROT, ItemTags.create(ResourceLocation.fromNamespaceAndPath("c", "ingots/iron")), "common_iron_ingots");

        offerEightCoveredRecipe(itemRegistry, output, RecipeCategory.FOOD, DIAMOND_CARROT, Items.CARROT, Items.DIAMOND);
        offerEightCoveredRecipe(itemRegistry, output, RecipeCategory.FOOD, ENCHANTED_DIAMOND_CARROT, Items.CARROT, Items.DIAMOND_BLOCK);


        /*offerCheesyItemRecipe(recipeOutput, CHEESE_STEAK.get(), MILK_CHEESE.get(), Items.COOKED_BEEF);
        offerCheesyItemRecipe(output, CHEESE_PORK.get(), MILK_CHEESE.get(), Items.COOKED_PORKCHOP);
        offerCheesyItemRecipe(output, CHEESE_MUTTON.get(), MILK_CHEESE.get(), Items.COOKED_MUTTON);
        offerCheesyItemRecipe(output, CHEESE_CHICKEN.get(), MILK_CHEESE.get(), Items.COOKED_CHICKEN);
        offerCheesyItemRecipe(output, CHEESE_FRIES.get(), MILK_CHEESE.get(), ModTags.Items.POTATOES, "potatoes");
        */

        offerLantern(itemRegistry, output, LEAD_LANTERN, LEAD_NUGGET, LEAD_INGOT);
        offerUnlitLantern(itemRegistry, output, UNLIT_LANTERN, Items.IRON_NUGGET, Items.IRON_INGOT);

        NeoJams.JAMS.iterator().forEachRemaining(item ->
                offerJamRecipe(itemRegistry, output, item));


        SpecialRecipeBuilder.special(ShapelessRepairRecipe::new)
                .save(output, getId("leaves_boots_repair"));
        SpecialRecipeBuilder.special(SandwichRecipe::new)
                .save(output, getId("sandwich_recipe"));
    }


    private ResourceKey<Recipe<?>> getId(String path) {
        return ResourceKey.create(Registries.RECIPE, ResourceLocation.fromNamespaceAndPath(MODID, path));
    }

    protected void offerSeeds(HolderGetter<Item> registry, RecipeOutput recipeOutput, ItemLike seed, ItemLike ingredient, int count) {
        ShapelessRecipeBuilder.shapeless(registry, RecipeCategory.FOOD, seed, count)
                .requires(ingredient)
                .unlockedBy(getHasName(ingredient), has(ingredient))
                .save(recipeOutput);
    }
    protected void offerCheese(HolderGetter<Item> registry, RecipeOutput recipeOutput, DeferredItem<CheeseItem> cheeseItem) {
        ShapelessRecipeBuilder.shapeless(registry, RecipeCategory.FOOD, cheeseItem, 3)
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
    protected void offerSmelting(RecipeOutput recipeOutput, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult,
                                 float pExperience, int pCookingTIme, String pGroup) {
        cookingRecipe(recipeOutput, RecipeSerializer.SMELTING_RECIPE, SmeltingRecipe::new, pIngredients, pCategory, pResult,
                pExperience, pCookingTIme, pGroup, "_from_smelting");
    }
    protected void offerBlasting(RecipeOutput recipeOutput, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult,
                                 float pExperience, int pCookingTime, String pGroup) {
        cookingRecipe(recipeOutput, RecipeSerializer.BLASTING_RECIPE, BlastingRecipe::new, pIngredients, pCategory, pResult,
                pExperience, pCookingTime, pGroup, "_from_blasting");
    }
    protected void offerSmoking(RecipeOutput recipeOutput, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult,
                                float pExperience, int pCookingTime, String pGroup) {
        cookingRecipe(recipeOutput, RecipeSerializer.SMOKING_RECIPE, SmokingRecipe::new, pIngredients, pCategory, pResult,
                pExperience, pCookingTime, pGroup, "_from_smoking");
    }
    /** default is 600 cooking time.**/
    protected void offerCampfireCooking(RecipeOutput recipeOutput, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult,
                                        float pExperience, int pCookingTime, String pGroup) {
        cookingRecipe(recipeOutput, RecipeSerializer.CAMPFIRE_COOKING_RECIPE, CampfireCookingRecipe::new, pIngredients, pCategory, pResult,
                pExperience, pCookingTime, pGroup, "_from_campfire_cooking");
    }


    // Custom generic cooking recipe to be saved under OUR mod's namespace.
    // SimpleCookingRecipeBuilder has these methods but they get saved under minecraft's namespace. Which is why we make em.
    protected <T extends AbstractCookingRecipe> void cookingRecipe(RecipeOutput recipeOutput, RecipeSerializer<T> pCookingSerializer, AbstractCookingRecipe.Factory<T> factory,
                                                                   Iterable<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup, String pRecipeName) {
        for(ItemLike itemlike : pIngredients) {
            SimpleCookingRecipeBuilder.generic(Ingredient.of(itemlike), pCategory, pResult, pExperience, pCookingTime, pCookingSerializer, factory).group(pGroup).unlockedBy(getHasName(itemlike), has(itemlike))
                    .save(recipeOutput, MODID + ":" + getItemName(pResult) + pRecipeName + "_" + getItemName(itemlike));
        }
    }

    protected void offerClaymore(HolderGetter<Item> registry, RecipeOutput recipeOutput, DeferredItem<NeoClaymoreItem> claymore, ItemLike ingredient) {
        ShapedRecipeBuilder.shaped(registry, RecipeCategory.COMBAT, claymore)
                .define('X', Items.STICK)
                .define('#', ingredient)
                .pattern("  #")
                .pattern("## ")
                .pattern("X# ")
                .unlockedBy(getHasName(ingredient), has(ingredient))
                .save(recipeOutput, getItemName(claymore));
    }

    protected void offerClaymore(HolderGetter<Item> registry, RecipeOutput recipeOutput, DeferredItem<NeoClaymoreItem> claymore, TagKey<Item> tagKey, String tagName) {
        ShapedRecipeBuilder.shaped(registry, RecipeCategory.COMBAT, claymore)
                .define('X', Items.STICK)
                .define('#', tagKey)
                .pattern("  #")
                .pattern("## ")
                .pattern("X# ")
                .unlockedBy("has" + tagName, has(tagKey))
                .save(recipeOutput, getItemName(claymore) + "_from_tag_" + tagName);;
    }

    protected void offerHelmet(HolderGetter<Item> registry, RecipeOutput recipeOutput, DeferredItem<?> helmet, ItemLike ingredient) {
        ShapedRecipeBuilder.shaped(registry, RecipeCategory.COMBAT, helmet)
                .define('X', ingredient)
                .pattern("XXX")
                .pattern("X X")
                .unlockedBy(getHasName(ingredient), has(ingredient))
                .save(recipeOutput);
    }
    protected void offerChestplate(HolderGetter<Item> registry, RecipeOutput recipeOutput, DeferredItem<?> chestplate, ItemLike ingredient) {
        ShapedRecipeBuilder.shaped(registry, RecipeCategory.COMBAT, chestplate)
                .define('X', ingredient)
                .pattern("X X")
                .pattern("XXX")
                .pattern("XXX")
                .unlockedBy(getHasName(ingredient), has(ingredient))
                .save(recipeOutput);
    }
    protected void offerLeggings(HolderGetter<Item> registry, RecipeOutput recipeOutput, DeferredItem<?> leggings, ItemLike ingredient) {
        ShapedRecipeBuilder.shaped(registry, RecipeCategory.COMBAT, leggings)
                .define('X', ingredient)
                .pattern("XXX")
                .pattern("X X")
                .pattern("X X")
                .unlockedBy(getHasName(ingredient), has(ingredient))
                .save(recipeOutput);
    }
    protected void offerBoots(HolderGetter<Item> registry, RecipeOutput recipeOutput, DeferredItem<?> boots, ItemLike ingredient) {
        ShapedRecipeBuilder.shaped(registry, RecipeCategory.COMBAT, boots)
                .define('X', ingredient)
                .pattern("X X")
                .pattern("X X")
                .unlockedBy(getHasName(ingredient), has(ingredient))
                .save(recipeOutput);
    }
    protected void offerTools(HolderGetter<Item> registry,
                              RecipeOutput recipeOutput,
                              DeferredItem<SwordItem> swordItem,
                              DeferredItem<PickaxeItem> pickaxeItem,
                              DeferredItem<AxeItem> axeItem,
                              DeferredItem<ShovelItem> shovelItem,
                              DeferredItem<HoeItem> hoeItem,
                              ItemLike repairItem) {
        String condition = getHasName(repairItem);
        ShapedRecipeBuilder.shaped(registry, RecipeCategory.TOOLS, hoeItem)
                .define('#', Items.STICK)
                .define('X', repairItem)
                .pattern("XX")
                .pattern(" #")
                .pattern(" #")
                .unlockedBy(condition, has(repairItem))
                .save(recipeOutput);
        ShapedRecipeBuilder.shaped(registry, RecipeCategory.TOOLS, pickaxeItem)
                .define('#', Items.STICK)
                .define('X', repairItem)
                .pattern("XXX")
                .pattern(" # ")
                .pattern(" # ")
                .unlockedBy(condition, has(repairItem))
                .save(recipeOutput);
        ShapedRecipeBuilder.shaped(registry, RecipeCategory.TOOLS, shovelItem)
                .define('#', Items.STICK)
                .define('X', repairItem)
                .pattern("X")
                .pattern("#")
                .pattern("#")
                .unlockedBy(condition, has(repairItem))
                .save(recipeOutput);
        ShapedRecipeBuilder.shaped(registry, RecipeCategory.COMBAT, swordItem)
                .define('#', Items.STICK)
                .define('X', repairItem)
                .pattern("X")
                .pattern("X")
                .pattern("#")
                .unlockedBy(condition, has(repairItem))
                .save(recipeOutput);
        ShapedRecipeBuilder.shaped(registry, RecipeCategory.TOOLS, axeItem)
                .define('#', Items.STICK)
                .define('X', repairItem)
                .pattern("XX")
                .pattern("X#")
                .pattern(" #")
                .unlockedBy(condition, has(repairItem))
                .save(recipeOutput);
    }


    protected void offerEightCoveredRecipe(HolderGetter<Item> registry, RecipeOutput recipeOutput, RecipeCategory recipeCategory, ItemLike result, ItemLike covered, ItemLike covering) {
        ShapedRecipeBuilder.shaped(registry, recipeCategory, result)
                .define('#', covering)
                .define('O', covered)
                .pattern("###")
                .pattern("#O#")
                .pattern("###")
                .unlockedBy(getHasName(covered), has(covered))
                .save(recipeOutput);
    }
    protected void offerEightCoveredRecipe(HolderGetter<Item> registry, RecipeOutput recipeOutput, RecipeCategory recipeCategory, ItemLike result, ItemLike covered, TagKey<Item> covering, String tagName) {
        ShapedRecipeBuilder.shaped(registry, recipeCategory, result)
                .define('#', covering)
                .define('O', covered)
                .pattern("###")
                .pattern("#O#")
                .pattern("###")
                .unlockedBy(getHasName(covered), has(covered))
                .save(recipeOutput, MODID + ":" + getItemName(result) + "from_tag_" + tagName);
    }

    protected void offerLantern(HolderGetter<Item> registry, RecipeOutput recipeOutput, ItemLike lantern, ItemLike nugget, ItemLike ingot) {
        ShapedRecipeBuilder.shaped(registry, RecipeCategory.DECORATIONS, lantern)
                .define('#', Items.TORCH)
                .define('X', nugget)
                .pattern("XXX")
                .pattern("X#X")
                .pattern("XXX")
                .unlockedBy(getHasName(nugget), has(nugget))
                .unlockedBy(getHasName(ingot), has(ingot))
                .save(recipeOutput);
    }
    protected void offerUnlitLantern(HolderGetter<Item> registry, RecipeOutput recipeOutput, ItemLike lantern, ItemLike nugget, ItemLike ingot) {
        ShapedRecipeBuilder.shaped(registry, RecipeCategory.DECORATIONS, lantern)
                .define('#', Items.GLOWSTONE_DUST)
                .define('X', nugget)
                .pattern("XXX")
                .pattern("X#X")
                .pattern("XXX")
                .unlockedBy(getHasName(nugget), has(nugget))
                .unlockedBy(getHasName(ingot), has(ingot))
                .save(recipeOutput);
    }
    protected void offerJamRecipe(HolderGetter<Item> registry, RecipeOutput recipeOutput, DeferredItem<JamItem> jamItem) {
        ShapelessRecipeBuilder.shapeless(registry, RecipeCategory.FOOD, jamItem)
                .requires(jamItem.get().getBaseItem(), 2)
                .requires(ModTags.Items.KNIVES)
                .unlockedBy(getHasName(jamItem.get().getBaseItem()), has(jamItem.get().getBaseItem()))
                .save(recipeOutput);
    }
}