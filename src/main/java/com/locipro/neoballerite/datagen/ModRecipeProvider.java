package com.locipro.neoballerite.datagen;

import com.google.common.collect.ImmutableList;
import com.locipro.neoballerite.item.tool.NeoClaymoreItem;
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

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, KNIFE)
                .define('#', Items.IRON_INGOT)
                .define('X', Items.STICK)
                .pattern(" #")
                .pattern("X ")
                .unlockedBy("has_iron_ingot", has(Items.IRON_INGOT))
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

        offerClaymore(recipeOutput, WOODEN_CLAYMORE, ItemTags.PLANKS);
        offerClaymore(recipeOutput, STONE_CLAYMORE, ItemTags.STONE_CRAFTING_MATERIALS);
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




//        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, COMPRESSED_BALLERITE_BLOCK.get())
//                .pattern("BBB")
//                .pattern("BBB")
//                .pattern("BBB")
//                .define('B', COMPRESSED_BALLERITE_INGOT)
//                .unlockedBy("has_compressed_ballerite", has(COMPRESSED_BALLERITE_INGOT))
//                .save(recipeOutput);
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



        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, STRAWBERRY_SEEDS, 3)
                .requires(STRAWBERRY)
                .unlockedBy("has_strawberry", has(STRAWBERRY))
                .save(recipeOutput);




        offerSmeltingAndBlasting(recipeOutput, List.of(BALLERITE_ORE), RecipeCategory.MISC, COOKED_BALLERITE_BLOCK,
                0.7f, 260, "ballerite");
        offerSmeltingAndBlasting(recipeOutput, List.of(COOKED_BALLERITE_BLOCK), RecipeCategory.MISC, BURNT_BALLERITE_BLOCK,
                0.6f, 260, "ballerite");
        offerSmoking(recipeOutput, List.of(COOKED_BALLERITE_BLOCK), RecipeCategory.MISC, BURNT_BALLERITE_BLOCK,
                0.7f, 180, "ballerite");

        offerSmeltingAndBlasting(recipeOutput, List.of(BURNT_BALLERITE_BLOCK), RecipeCategory.MISC, CHARRED_BALLERITE_BLOCK,
                0.7f, 260, "ballerite");
        offerBlasting(recipeOutput, List.of(CHARRED_BALLERITE_BLOCK), RecipeCategory.MISC, COMPRESSED_BALLERITE_BLOCK,
                0.9f, 180, "ballerite");

        offerSmeltingAndSmoking(recipeOutput, List.of(RAW_BALLERITE), RecipeCategory.FOOD, COOKED_BALLERITE,
                0.6f, 200, "ballerite");
        offerSmeltingAndSmoking(recipeOutput, List.of(RAW_BALLERITE_BLOCK), RecipeCategory.FOOD, COOKED_BALLERITE_BLOCK,
                0.7f, 200, "ballerite");

        offerSmeltingAndBlasting(recipeOutput, LEAD_ORES, RecipeCategory.MISC, LEAD_INGOT,
                0.7f, 200, "lead");


        SpecialRecipeBuilder.special(ShapelessRepairRecipe::new)
                        .save(recipeOutput, getId("leaves_boots_repair"));
    }

//    protected static String getHasName(DeferredItem<?> item) {
//        return item.getId().getPath();
//    }
    private ResourceLocation getId(String path) {
        return ResourceLocation.fromNamespaceAndPath(MODID, path);
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
                .unlockedBy(getHasName(ingredient), has(ingredient));
    }

    protected static void offerClaymore(RecipeOutput recipeOutput, DeferredItem<NeoClaymoreItem> claymore, TagKey<Item> tagKey) {
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, claymore)
                .define('X', Items.STICK)
                .define('#', tagKey)
                .pattern("  #")
                .pattern("## ")
                .pattern("X# ")
                .unlockedBy("has" + tagKey.toString(), has(tagKey));
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
                                     DeferredItem<?> repairItem) {
        String condition = getHasName(repairItem);
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
