package net.locipro.balleritemod.data;

import com.google.common.collect.ImmutableList;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.locipro.balleritemod.block.ModBlocks;
import net.locipro.balleritemod.item.ModItems;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.SmeltingRecipe;
import net.minecraft.world.item.crafting.SmokingRecipe;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;
import java.util.List;

public class ModRecipeGenerator extends FabricRecipeProvider {


    public ModRecipeGenerator(FabricDataOutput output) {
        super(output);
    }



    /**
     * @param inputs The ItemConvertible that you want to get cooked
     * @param output Output after cooking
     *                    * @param time <i>Smelting</i> cooking time in ticks. Default is 200, and gets cut in half when blasting. (100 ticks).
     */

    private static void offerSmeltingSmoking(RecipeOutput exporter, List<ItemLike> inputs, ItemLike output, float experience, int time, String group) {
        //Default time for food is 200
        oreCooking(exporter, RecipeSerializer.SMELTING_RECIPE, SmeltingRecipe::new, inputs, RecipeCategory.FOOD, output,
                experience, time, group, "_from_smelting");
        oreCooking(exporter, RecipeSerializer.SMOKING_RECIPE,SmokingRecipe::new, inputs, RecipeCategory.FOOD, output,
                experience, time/2, group, "_from_smoking");
    }
    private static void offerSmoking(RecipeOutput exporter, List<ItemLike> inputs, AbstractCookingRecipe.Factory recipeFactory, RecipeCategory recipeCategory, ItemLike output, float experience, int time, String group) {
        oreCooking(exporter, RecipeSerializer.SMOKING_RECIPE, recipeFactory, inputs, recipeCategory, output,
                experience, time, group, "_from_smoking");
    }
    /**
     * Stick in the exporter.<br> Stick in the raw item, then the cooked item, then 200.<br> Simple right?
     *
     * @param inputs Raw ore or metallic item
     * @param output Processed item
     * @param time <i>Smelting</i> cooking time in ticks. Default is 200, and gets cut in half when blasting. (100 ticks).
     */
    private static void offerModMetalRecipe(RecipeOutput exporter, List<ItemLike> inputs, RecipeCategory category, ItemLike output, float experience, int time, String group) {
        //Default time for food is 200
        oreSmelting(exporter, inputs, category, output, experience, time, group);
        oreBlasting(exporter, inputs, category, output, experience, time/2, group);
    }
    private static final ImmutableList<ItemLike> LEAD_ORES = ImmutableList.of(ModBlocks.LEAD_ORE, ModBlocks.DEEPSLATE_LEAD_ORE);

    /**
     * Generally, you do "offerX()" and that's it! <br>
     * <b>"How am I supposed to know which method to use, and how to use it!"</b> <br>
     * Well, <i>"VanillaRecipeProvider"</i> has all the vanilla recipes, generated using fabric's api!
     * @param exporter Don't mind this.
     */
    @Override
    public void buildRecipes(RecipeOutput exporter) {

        offerModMetalRecipe(exporter, List.of(ModItems.RAW_BALLERITE), RecipeCategory.MISC, ModItems.BALLERITE,
                0.35f, 100, "ballerite");


        offerSmeltingSmoking(exporter, List.of(ModBlocks.RAW_BALLERITE_BLOCK), ModBlocks.BALLERITE_BLOCK,
                1.4f, 200, "ballerite");



        offerModMetalRecipe(exporter, List.of(ModBlocks.BALLERITE_BLOCK), RecipeCategory.MISC, ModBlocks.BURNT_BALLERITE_BLOCK,
                1.4f, 200, "ballerite");


        offerModMetalRecipe(exporter, List.of(ModBlocks.BURNT_BALLERITE_BLOCK), RecipeCategory.MISC, ModBlocks.CHARRED_BALLERITE_BLOCK,
                3.7f, 400, "ballerite");

        oreBlasting(exporter, List.of(ModBlocks.CHARRED_BALLERITE_BLOCK), RecipeCategory.MISC, ModBlocks.COMPRESSED_BALLERITE_BLOCK,
                2.0f, 400, "ballerite");

        nineBlockStorageRecipes(exporter, RecipeCategory.MISC, ModItems.COMPRESSED_BALLERITE, RecipeCategory.BUILDING_BLOCKS,
                ModBlocks.COMPRESSED_BALLERITE_BLOCK);

        threeByThreePacker(exporter, RecipeCategory.MISC, ModBlocks.RAW_BALLERITE_BLOCK, ModItems.RAW_BALLERITE);

        woodFromLogs(exporter, ModBlocks.WITHERED_WOOD, ModBlocks.WITHERED_LOG);
        woodFromLogs(exporter, ModBlocks.STAR_WOOD, ModBlocks.STAR_LOG);

        planksFromLogs(exporter, ModBlocks.WITHERED_PLANKS, ModItemTagGenerator.WITHERED_LOGS_ITEM, 4);
        planksFromLogs(exporter, ModBlocks.STAR_PLANKS, ModItemTagGenerator.STAR_LOGS_ITEM, 4);

        pressurePlate(exporter, ModBlocks.WITHERED_PRESSURE_PLATE, ModBlocks.WITHERED_PLANKS);
        pressurePlate(exporter, ModBlocks.STAR_PRESSURE_PLATE, ModBlocks.STAR_PLANKS);
        pressurePlate(exporter, ModBlocks.COPPER_PRESSURE_PLATE, Blocks.COPPER_BLOCK);

        offerFenceRecipe(exporter, ModBlocks.WITHERED_FENCE, ModBlocks.WITHERED_PLANKS);
        offerFenceRecipe(exporter, ModBlocks.STAR_FENCE, ModBlocks.STAR_PLANKS);

        offerFenceGateRecipe(exporter, ModBlocks.WITHERED_FENCE_GATE, ModBlocks.WITHERED_PLANKS);
        offerFenceGateRecipe(exporter, ModBlocks.STAR_FENCE_GATE, ModBlocks.STAR_PLANKS);

        oneToOneConversionRecipe(exporter, ModBlocks.WITHERED_BUTTON, ModBlocks.WITHERED_PLANKS, "withered");
        oneToOneConversionRecipe(exporter, ModBlocks.STAR_BUTTON, ModBlocks.STAR_PLANKS, "star");
        oneToOneConversionRecipe(exporter, ModBlocks.COPPER_BUTTON, Items.COPPER_INGOT, "copper");

        slab(exporter, RecipeCategory.DECORATIONS, ModBlocks.WITHERED_SLAB, ModBlocks.WITHERED_PLANKS);
        slab(exporter, RecipeCategory.DECORATIONS, ModBlocks.STAR_SLAB, ModBlocks.STAR_PLANKS);

        offerStairsRecipe(exporter, ModBlocks.WITHERED_STAIRS, ModBlocks.WITHERED_PLANKS);
        offerStairsRecipe(exporter, ModBlocks.STAR_STAIRS, ModBlocks.STAR_PLANKS);

        offerTrapDoorRecipe(exporter, ModBlocks.WITHERED_TRAPDOOR, ModBlocks.WITHERED_PLANKS);
        offerTrapDoorRecipe(exporter, ModBlocks.STAR_TRAPDOOR, ModBlocks.STAR_PLANKS);
        offerTrapDoorRecipe(exporter, ModBlocks.COBBLESTONE_TRAPDOOR, Blocks.COBBLESTONE);
        offerTrapDoorRecipe(exporter, ModBlocks.COPPER_TRAPDOOR, Blocks.COPPER_BLOCK);

        offerDoor(exporter, ModBlocks.WITHERED_DOOR, ModBlocks.WITHERED_PLANKS);
        offerDoor(exporter, ModBlocks.STAR_DOOR, ModBlocks.STAR_PLANKS);
        offerDoor(exporter, ModBlocks.COBBLESTONE_DOOR, Blocks.COBBLESTONE);
        offerDoor(exporter, ModBlocks.COPPER_DOOR, Blocks.COPPER_BLOCK);


        offerToolRecipes(exporter, ModItems.BALLERITE_SWORD, ModItems.BALLERITE_PICKAXE, ModItems.BALLERITE_AXE, ModItems.BALLERITE_SHOVEL, ModItems.BALLERITE_HOE, ModItems.COMPRESSED_BALLERITE);
        offerToolRecipes(exporter, ModItems.LEAD_SWORD, ModItems.LEAD_PICKAXE, ModItems.LEAD_AXE, ModItems.LEAD_SHOVEL, ModItems.LEAD_HOE, ModItems.LEAD_INGOT);


        nineBlockStorageRecipesRecipesWithCustomUnpacking(exporter, RecipeCategory.MISC, ModItems.LEAD_INGOT, RecipeCategory.BUILDING_BLOCKS, ModBlocks.LEAD_BLOCK, "lead_ingot_from_lead_block", "lead_ingot");
        nineBlockStorageRecipesWithCustomPacking(exporter, RecipeCategory.MISC, ModItems.LEAD_NUGGET, RecipeCategory.MISC , ModItems.LEAD_INGOT, "lead_ingot_from_nuggets", "lead_ingot");

        offerModMetalRecipe(exporter, LEAD_ORES, RecipeCategory.MISC, ModItems.LEAD_INGOT, 1f, 200, "lead_ingot");

        offerClaymoreFromTag(exporter, ModItems.WOODEN_CLAYMORE, ItemTags.PLANKS, "has_planks");
        offerClaymore(exporter, ModItems.STONE_CLAYMORE, Blocks.COBBLESTONE);
        offerClaymore(exporter, ModItems.IRON_CLAYMORE, Items.IRON_INGOT);
        offerClaymore(exporter, ModItems.LEAD_CLAYMORE, ModItems.LEAD_INGOT);
        offerClaymore(exporter, ModItems.GOLD_CLAYMORE, Items.GOLD_INGOT);
        offerClaymore(exporter, ModItems.DIAMOND_CLAYMORE, Items.DIAMOND);
        offerClaymore(exporter, ModItems.BALLERITE_CLAYMORE, ModItems.COMPRESSED_BALLERITE);


        // NEW NETHERITE UPGRADES IN 1.20 REQUIRE A "TEMPLATE", AN ITEM WHICH CAN BE FOUND IN BASTIONS.

        //offerLegacyNetheriteUpgradeRecipe(exporter, Items.NETHERITE_SWORD, RecipeCategory.COMBAT, ModItems.NETHERITE_CLAYMORE);

        netheriteSmithing(exporter, Items.NETHERITE_SWORD, RecipeCategory.COMBAT, ModItems.NETHERITE_CLAYMORE);

        offerSmeltingSmoking(exporter, List.of(ModItems.EGGPLANT), ModItems.GRILLED_EGGPLANT,
                0.4f, 200, "eggplant");
        offerSmeltingSmoking(exporter, List.of(ModItems.CORN_COB), ModItems.GRILLED_CORN_COB,
                0.4f, 200, "corn");
        offerSmeltingSmoking(exporter, List.of(ModItems.TOMATO), ModItems.GRILLED_TOMATO,
                0.4f, 200, "tomato");
        offerSmeltingSmoking(exporter, List.of(ModItems.SWEET_POTATO), ModItems.BAKED_SWEET_POTATO,
                0.4f, 200, "sweet_potato");

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModItems.BALL_DOWSER).define('X', Items.STICK).define('#', ModBlocks.CHARRED_BALLERITE_BLOCK)
                .pattern("###")
                .pattern("#X#")
                .pattern(" X ").unlockedBy(getHasName(ModItems.COMPRESSED_BALLERITE), has(ModItems.COMPRESSED_BALLERITE)).save(exporter);

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModItems.KNIFE).define('X', Items.STICK).define('#', Items.IRON_INGOT)
                .pattern("  #")
                .pattern(" # ")
                .pattern("X  ").unlockedBy(getHasName(Items.IRON_INGOT), has(Items.IRON_INGOT)).save(exporter);



        oneToOneConversionRecipe(exporter, ModItems.TOMATO_SEEDS, ModItems.TOMATO, "tomato", 4);
        oneToOneConversionRecipe(exporter, ModItems.STRAWBERRY_SEEDS, ModItems.STRAWBERRY, "strawberry", 2);
        oneToOneConversionRecipe(exporter, ModItems.EGGPLANT_SEEDS, ModItems.EGGPLANT, "eggplant", 2);
        oneToOneConversionRecipe(exporter, ModItems.CORN_SEEDS, ModItems.CORN_KERNEL, "corn", 1);

        oneToOneConversionRecipe(exporter, ModItems.CORN_KERNEL, ModItems.CORN_COB, "corn", 3);

        offerArmorRecipes(exporter, ModItems.BALLERITE_HELMET, ModItems.BALLERITE_CHESTPLATE, ModItems.BALLERITE_LEGGINGS, ModItems.BALLERITE_BOOTS, ModItems.COMPRESSED_BALLERITE);
        offerArmorRecipes(exporter, ModItems.LEAD_HELMET, ModItems.LEAD_CHESTPLATE, ModItems.LEAD_LEGGINGS, ModItems.LEAD_BOOTS, ModItems.LEAD_INGOT);

        nineBlockStorageRecipes(exporter, RecipeCategory.FOOD, ModItems.SWEET_POTATO, RecipeCategory.BUILDING_BLOCKS, ModBlocks.SWEET_POTATO_BLOCK);

        offerJamRecipe(exporter, ModItems.SWEET_BERRY_JAM, Items.SWEET_BERRIES, ModItems.KNIFE, 2);
        offerJamRecipe(exporter, ModItems.TOMATO_JAM, ModItems.TOMATO, ModItems.KNIFE, 2);
        offerJamRecipe(exporter, ModItems.BLUEBERRY_JAM, ModItems.BLUE_BERRIES, ModItems.KNIFE, 2);
        offerJamRecipe(exporter, ModItems.BLACKBERRY_JAM, ModItems.BLACK_BERRIES, ModItems.KNIFE, 2);
        offerJamRecipe(exporter, ModItems.STRAWBERRY_JAM, ModItems.STRAWBERRY, ModItems.KNIFE, 2);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.MILK_VILE, 3).requires(Items.MILK_BUCKET).requires(Items.GLASS_BOTTLE, 3).unlockedBy(getHasName(Items.MILK_BUCKET), has(Items.MILK_BUCKET)).save(exporter);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.CHEESE, 4).requires(ModItems.MILK_VILE).requires(Items.ROTTEN_FLESH).unlockedBy(getHasName(Items.ROTTEN_FLESH), has(Items.ROTTEN_FLESH)).save(exporter);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.EGGS_OMLETTE).requires(ModItems.MILK_VILE).requires(Items.EGG, 2  ).unlockedBy(getHasName(Items.EGG), has(Items.EGG)).save(exporter);
        oreSmelting(exporter, List.of(Items.EGG), RecipeCategory.FOOD, ModItems.EGGS_SCRAMBLED, 0.5f, 200, "egg");
        offerSmoking(exporter, List.of(Items.EGG), SmokingRecipe::new, RecipeCategory.FOOD, ModItems.EGGS_SUNNY, 0.5f, 100, "egg");

        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.CHEESE_STEAK).requires(ModItems.CHEESE).requires(Items.COOKED_BEEF).unlockedBy(getHasName(ModItems.CHEESE), has(ModItems.CHEESE)).save(exporter);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.CHEESE_PORK).requires(ModItems.CHEESE).requires(Items.COOKED_PORKCHOP).unlockedBy(getHasName(ModItems.CHEESE), has(ModItems.CHEESE)).save(exporter);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.CHEESE_MUTTON).requires(ModItems.CHEESE).requires(Items.COOKED_MUTTON).unlockedBy(getHasName(ModItems.CHEESE), has(ModItems.CHEESE)).save(exporter);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.CHEESE_CHICKEN).requires(ModItems.CHEESE).requires(Items.COOKED_CHICKEN).unlockedBy(getHasName(ModItems.CHEESE), has(ModItems.CHEESE)).save(exporter);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.CHEESE_FRIES).requires(ModItems.CHEESE).requires(Items.BAKED_POTATO).unlockedBy(getHasName(ModItems.CHEESE), has(ModItems.CHEESE)).save(exporter, getItemName(ModItems.CHEESE_FRIES) + "_from_potato");
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.CHEESE_FRIES).requires(ModItems.CHEESE).requires(ModItems.BAKED_SWEET_POTATO).unlockedBy(getHasName(ModItems.CHEESE), has(ModItems.CHEESE)).save(exporter, getItemName(ModItems.CHEESE_FRIES) + "_from_sweet_potato");


        offerSandwichRecipe(exporter, ModItems.CHEESE_SANDWICH, ModItems.CHEESE, ModItems.KNIFE);
        offerSandwichRecipe(exporter, ModItems.STEAK_SANDWICH, Items.COOKED_BEEF, ModItems.KNIFE);
        offerSandwichRecipe(exporter, ModItems.CHICKEN_SANDWICH, Items.COOKED_CHICKEN, ModItems.KNIFE);
        offerSandwichRecipe(exporter, ModItems.PORK_SANDWICH, Items.COOKED_PORKCHOP, ModItems.KNIFE);
        offerSandwichRecipe(exporter, ModItems.MUTTON_SANDWICH, Items.COOKED_MUTTON, ModItems.KNIFE);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.FRIES_SANDWICH).requires(ModItems.KNIFE).requires(Items.BAKED_POTATO).requires(Items.BREAD).unlockedBy(getHasName(Items.BAKED_POTATO), has(ModItems.CHEESE)).save(exporter, getItemName(ModItems.FRIES_SANDWICH) + "_from_potato");
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.FRIES_SANDWICH).requires(ModItems.KNIFE).requires(ModItems.BAKED_SWEET_POTATO).requires(Items.BREAD).unlockedBy(getHasName(ModItems.BAKED_SWEET_POTATO), has(ModItems.CHEESE)).save(exporter, getItemName(ModItems.FRIES_SANDWICH) + "_from_sweet_potato");

        offerCheeseSandwichRecipe(exporter, ModItems.STEAK_SANDWICH_CHEESE, Items.COOKED_BEEF, ModItems.KNIFE, ModItems.CHEESE_STEAK);
        offerCheeseSandwichRecipe(exporter, ModItems.CHICKEN_SANDWICH_CHEESE, Items.COOKED_CHICKEN, ModItems.KNIFE, ModItems.CHEESE_CHICKEN);
        offerCheeseSandwichRecipe(exporter, ModItems.PORK_SANDWICH_CHEESE, Items.COOKED_PORKCHOP, ModItems.KNIFE, ModItems.CHEESE_PORK);
        offerCheeseSandwichRecipe(exporter, ModItems.MUTTON_SANDWICH_CHEESE, Items.COOKED_MUTTON, ModItems.KNIFE, ModItems.CHEESE_MUTTON);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.FRIES_SANDWICH_CHEESE).requires(Items.BAKED_POTATO).requires(ModItems.CHEESE).requires(ModItems.KNIFE).requires(Items.BREAD).unlockedBy(getHasName(Items.BAKED_POTATO), has(Items.BAKED_POTATO)).save(exporter, getItemName(ModItems.FRIES_SANDWICH_CHEESE) + "_from_potato_and_cheese");
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.FRIES_SANDWICH_CHEESE).requires(ModItems.BAKED_SWEET_POTATO).requires(ModItems.CHEESE).requires(ModItems.KNIFE).requires(Items.BREAD).unlockedBy(getHasName(ModItems.BAKED_SWEET_POTATO), has(ModItems.BAKED_SWEET_POTATO)).save(exporter, getItemName(ModItems.FRIES_SANDWICH_CHEESE) + "_from_sweet_potato_and_cheese");
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.FRIES_SANDWICH_CHEESE).requires(ModItems.CHEESE_FRIES).requires(ModItems.KNIFE).requires(Items.BREAD).unlockedBy(getHasName(ModItems.CHEESE_FRIES), has(ModItems.CHEESE_FRIES)).save(exporter, getItemName(ModItems.FRIES_SANDWICH_CHEESE) + "_from_cheesy_item");



        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItems.FARMERS_BOOTS).define('X', ModBlocks.STAR_LEAVES)
                .pattern("X X")
                .pattern("X X")
                .unlockedBy(getHasName(ModBlocks.STAR_LEAVES), has(ModBlocks.STAR_LEAVES)).save(exporter);

    }
//region boring methods
    /**
     * @param input This is the PLANK that is used to make the fence.
     */
    private static void offerFenceRecipe(RecipeOutput exporter, ItemLike output, ItemLike input) {
        fenceBuilder(output, Ingredient.of(input)).unlockedBy(RecipeProvider.getHasName(input), RecipeProvider.has(input)).save(exporter);
    }

    /**
     * @param input This is the PLANK that is used to make the fence gate.
     */
    private static void offerFenceGateRecipe(RecipeOutput exporter, ItemLike output, ItemLike input) {
        fenceGateBuilder(output, Ingredient.of(input)).unlockedBy(RecipeProvider.getHasName(input), RecipeProvider.has(input)).save(exporter);
    }

    /**
     * @param input This is the PLANK that is used to make the stairs
     */
    private static void offerStairsRecipe(RecipeOutput exporter, ItemLike output, ItemLike input) {
        stairBuilder(output, Ingredient.of(input)).unlockedBy(getHasName(input), has(input)).save(exporter);
    }

    /**
     * @param input This is the BLOCK that is used to make the trap door
     */
    private static void offerTrapDoorRecipe(RecipeOutput exporter, ItemLike output, ItemLike input) {
        trapdoorBuilder(output, Ingredient.of(input)).unlockedBy(getHasName(input), has(input)).save(exporter);
    }
    /**
     * @param input This is the BLOCK that is used to make the door
     */
    private static void offerDoor(RecipeOutput exporter, ItemLike output, ItemLike input) {
        doorBuilder(output, Ingredient.of(input)).unlockedBy(getHasName(input), has(input)).save(exporter);
    }
    private static void offerSword(RecipeOutput exporter, ItemLike output, ItemLike input) {
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, output).define('#', Items.STICK).define('X', input)
                .pattern("X")
                .pattern("X")
                .pattern("#").unlockedBy(getHasName(input), has(input)).save(exporter);
    }
    private static void offerPickaxe(RecipeOutput exporter, ItemLike output, ItemLike input) {
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, output).define('#', Items.STICK).define('X', input)
                .pattern("XXX")
                .pattern(" # ")
                .pattern(" # ").unlockedBy(getHasName(input), has(input)).save(exporter);
    }
    private static void offerAxe(RecipeOutput exporter, ItemLike output, ItemLike input) {
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, output).define('#', Items.STICK).define('X', input)
                .pattern("XX")
                .pattern("X#")
                .pattern(" #").unlockedBy(getHasName(input), has(input)).save(exporter);
    }
    private static void offerShovel(RecipeOutput exporter, ItemLike output, ItemLike input) {
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, output).define('#', Items.STICK).define('X', input)
                .pattern("X")
                .pattern("#")
                .pattern("#").unlockedBy(getHasName(input), has(input)).save(exporter);
    }
    private static void offerHoe(RecipeOutput exporter, ItemLike output, ItemLike input) {
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, output).define('#', Items.STICK).define('X', input)
                .pattern("XX")
                .pattern(" #")
                .pattern(" #").unlockedBy(getHasName(input), has(input)).save(exporter);
    }
    private static void offerClaymore(RecipeOutput exporter, ItemLike output, ItemLike input) {
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, output).define('#', Items.STICK).define('X', input)
                .pattern("  X")
                .pattern("XX ")
                .pattern("#X ")
                .unlockedBy(getHasName(input), has(input)).save(exporter);
    }
    private static void offerClaymoreFromTag(RecipeOutput exporter, ItemLike output, TagKey<Item> input, String criterion) {
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, output).define('#', Items.STICK).define('X', input)
                .pattern("  X")
                .pattern("XX ")
                .pattern("#X ")
                .unlockedBy(criterion, has(input)).save(exporter);
    }
    private static void offerArmorRecipes(RecipeOutput exporter, Item helmet, Item chestplate, Item leggings, Item boots, Item input) {
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, helmet).define('X', input)
                .pattern("XXX")
                .pattern("X X")
                .unlockedBy(getHasName(input), has(input)).save(exporter);
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, chestplate).define('X', input)
                .pattern("X X")
                .pattern("XXX")
                .pattern("XXX")
                .unlockedBy(getHasName(input), has(input)).save(exporter);
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, leggings).define('X', input)
                .pattern("XXX")
                .pattern("X X")
                .pattern("X X")
                .unlockedBy(getHasName(input), has(input)).save(exporter);
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, boots).define('X', input)
                .pattern("X X")
                .pattern("X X")
                .unlockedBy(getHasName(input), has(input)).save(exporter);
    }
    //endregion
    private static void offerToolRecipes(RecipeOutput exporter, Item sword, Item pickaxe, Item axe, Item shovel, Item hoe, Item input) {
        offerSword(exporter, sword, input);
        offerPickaxe(exporter, pickaxe, input);
        offerAxe(exporter, axe, input);
        offerShovel(exporter, shovel, input);
        offerHoe(exporter, hoe, input);
    }
    private static void offerJamRecipe(RecipeOutput exporter, Item jamItem, Item fruitItem, Item cuttingItem, int count) {
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, jamItem, 2).requires(fruitItem, 4).requires(cuttingItem).unlockedBy(getHasName(fruitItem), has(fruitItem)).save(exporter);
    }
    private static void offerSandwichRecipe(RecipeOutput exporter, Item sandwichItem, Item sandwichFiller, Item cuttingItem) {
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, sandwichItem).requires(sandwichFiller).requires(cuttingItem).requires(Items.BREAD).unlockedBy(getHasName(sandwichFiller), has(sandwichFiller)).save(exporter);
    }
    private static void offerCheeseSandwichRecipe(RecipeOutput exporter, Item sandwichItem, Item sandwichFiller, Item cuttingItem, Item cheesyItem) {
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, sandwichItem).requires(sandwichFiller).requires(ModItems.CHEESE).requires(cuttingItem).requires(Items.BREAD).unlockedBy(getHasName(sandwichFiller), has(sandwichFiller)).save(exporter, getItemName(sandwichItem) + "_from_item_and_cheese");
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, sandwichItem).requires(cheesyItem).requires(cuttingItem).requires(Items.BREAD).unlockedBy(getHasName(cheesyItem), has(cheesyItem)).save(exporter, getItemName(sandwichItem) + "_from_cheesy_item");
    }





}
