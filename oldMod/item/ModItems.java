package net.locipro.balleritemod.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.locipro.balleritemod.BalleriteMod;
import net.locipro.balleritemod.block.ModBlocks;
import net.locipro.balleritemod.item.custom.*;
import net.locipro.balleritemod.util.foodcomponents.*;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.EnchantedGoldenAppleItem;
import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.HorseArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemNameBlockItem;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.ShovelItem;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tiers;

import static net.locipro.balleritemod.item.ModItemGroup.addToItemGroup;

public class ModItems {
    public static void registerModItems() {
        BalleriteMod.LOGGER.info("Registering items for " + BalleriteMod.MOD_ID);
        addItemsToItemGroups();
    }
    //region BALLERITE
    public static final Item RAW_BALLERITE = registerItem("raw_ballerite",
            new RawBallerite(new FabricItemSettings().food(BalleriteFoodComponents.RAW_BALLERITE)), ModItemGroup.BALLERITE_MATERIALS);
    public static final Item BALLERITE = registerItem("ballerite",
            new Ballerite(new FabricItemSettings().food(BalleriteFoodComponents.BALLERITE)), ModItemGroup.BALLERITE_MATERIALS);
    public static final Item CHARRED_BALLERITE = registerItem("charred_ballerite",
            new CharredBallerite(), ModItemGroup.BALLERITE_MATERIALS);
    public static final Item COMPRESSED_BALLERITE = registerItem("compressed_ballerite",
            new CompressedBallerite(), ModItemGroup.BALLERITE_MATERIALS);
    public static final Item BALL_DOWSER = registerItem("ball_dowser",
            new BallDowser(new FabricItemSettings().stacksTo(1).durability(50)), ModItemGroup.BALLERITE_TOOLS);
    //endregion

    public static final Item PURPLE_CARROT = registerItem("purple_carrot",
            new EnchantedGoldenAppleItem(new FabricItemSettings().food(PurpleCarrotFoodComponents.PURPLE_CARROT).rarity(Rarity.EPIC)), ModItemGroup.BALLERITE_FOOD);

    //region BALLERITE_TOOLS
    public static final Item BALLERITE_SWORD = registerItem("ballerite_sword",
            new SwordItem(ModToolMaterials.COMPRESSED_BALLERITE, 5, -2.5f,
                    new FabricItemSettings()), ModItemGroup.BALLERITE_TOOLS);
    public static final Item BALLERITE_PICKAXE = registerItem("ballerite_pickaxe",
            new PickaxeItem(ModToolMaterials.COMPRESSED_BALLERITE, 1, -2.8f,
                    new FabricItemSettings()), ModItemGroup.BALLERITE_TOOLS);
    public static final Item BALLERITE_AXE = registerItem("ballerite_axe",
            new AxeItem(ModToolMaterials.COMPRESSED_BALLERITE, 6.0f, -3.1f,
                    new FabricItemSettings()), ModItemGroup.BALLERITE_TOOLS);
    public static final Item BALLERITE_SHOVEL = registerItem("ballerite_shovel",
            new ShovelItem(ModToolMaterials.COMPRESSED_BALLERITE, 1.4f, -3.0f,
                    new FabricItemSettings()), ModItemGroup.BALLERITE_TOOLS);
    public static final Item BALLERITE_HOE = registerItem("ballerite_hoe",
            new HoeItem(ModToolMaterials.COMPRESSED_BALLERITE, -2, -1.0f,
                    new FabricItemSettings()), ModItemGroup.BALLERITE_TOOLS);


    public static final Item BALLERITE_HELMET = registerItem("ballerite_helmet",
            new BalleriteArmorEffectTickItem(ArmorItem.Type.HELMET), ModItemGroup.BALLERITE_TOOLS);
    public static final Item BALLERITE_CHESTPLATE = registerItem("ballerite_chestplate",
                new BalleriteArmorItem(ArmorItem.Type.CHESTPLATE), ModItemGroup.BALLERITE_TOOLS);
    public static final Item BALLERITE_LEGGINGS = registerItem("ballerite_leggings",
                new BalleriteArmorItem(ArmorItem.Type.LEGGINGS), ModItemGroup.BALLERITE_TOOLS);
    public static final Item BALLERITE_BOOTS = registerItem("ballerite_boots",
                new BalleriteArmorItem(ArmorItem.Type.BOOTS), ModItemGroup.BALLERITE_TOOLS);
    public static final Item BALLERITE_HORSE_ARMOR = registerItem("ballerite_horse_armor",
                new HorseArmorItem(9, "ballerite", new FabricItemSettings().stacksTo(1).rarity(Rarity.RARE)), ModItemGroup.BALLERITE_TOOLS);


    //endregion
    //region LEAD_TOOLS
    public static final Item LEAD_INGOT = registerItem("lead_ingot", new Item(new FabricItemSettings()), ModItemGroup.BALLERITE_MATERIALS);
    public static final Item LEAD_NUGGET =  registerItem("lead_nugget", new Item(new FabricItemSettings()), ModItemGroup.BALLERITE_MATERIALS);
    public static final Item LEAD_SWORD = registerItem("lead_sword",
            new LeadSword(ModToolMaterials.LEAD, 3, -2.4f), ModItemGroup.BALLERITE_TOOLS);
    public static final Item LEAD_PICKAXE = registerItem("lead_pickaxe",
            new PickaxeItem(ModToolMaterials.LEAD, 1, -2.8f,
                    new FabricItemSettings()), ModItemGroup.BALLERITE_TOOLS);
    public static final Item LEAD_AXE = registerItem("lead_axe",
            new AxeItem(ModToolMaterials.LEAD, 6.0f, -3.1f,
                    new FabricItemSettings()), ModItemGroup.BALLERITE_TOOLS);
    public static final Item LEAD_SHOVEL = registerItem("lead_shovel",
            new ShovelItem(ModToolMaterials.LEAD, 1.5f, -3.0f,
                    new FabricItemSettings()), ModItemGroup.BALLERITE_TOOLS);
    public static final Item LEAD_HOE = registerItem("lead_hoe",
            new HoeItem(ModToolMaterials.LEAD, -2, -1.0f,
                    new FabricItemSettings()), ModItemGroup.BALLERITE_TOOLS);

    public static final Item LEAD_HELMET = registerItem("lead_helmet",
            new LeadArmorEffectTickItem(ArmorItem.Type.HELMET), ModItemGroup.BALLERITE_TOOLS);
    public static final Item LEAD_CHESTPLATE = registerItem("lead_chestplate",
            new LeadArmorItem(ArmorItem.Type.CHESTPLATE), ModItemGroup.BALLERITE_TOOLS);
    public static final Item LEAD_LEGGINGS = registerItem("lead_leggings",
            new LeadArmorItem(ArmorItem.Type.LEGGINGS), ModItemGroup.BALLERITE_TOOLS);
    public static final Item LEAD_BOOTS = registerItem("lead_boots",
            new LeadArmorItem(ArmorItem.Type.BOOTS), ModItemGroup.BALLERITE_TOOLS);

    public static final Item FARMERS_BOOTS = registerItem("farmers_boots",
            new FarmersBootsItem(new FabricItemSettings()), ModItemGroup.BALLERITE_TOOLS);
    //endregion
    //region CLAYMORES
    public static final Item WOODEN_CLAYMORE = registerItem("wooden_claymore", new SwordItem(Tiers.WOOD, 4, -2.8F, new FabricItemSettings()), ModItemGroup.BALLERITE_TOOLS);
    public static final Item STONE_CLAYMORE = registerItem("stone_claymore", new SwordItem(Tiers.STONE, 4, -2.8F, new FabricItemSettings()), ModItemGroup.BALLERITE_TOOLS);
    public static final Item IRON_CLAYMORE = registerItem("iron_claymore", new SwordItem(ModToolMaterials.LEAD, 4, -2.8F, new FabricItemSettings()), ModItemGroup.BALLERITE_TOOLS);
    public static final Item LEAD_CLAYMORE = registerItem("lead_claymore", new LeadSword(ModToolMaterials.LEAD, 4, -2.8f), ModItemGroup.BALLERITE_TOOLS);
    public static final Item GOLD_CLAYMORE = registerItem("gold_claymore", new SwordItem(Tiers.GOLD, 4, -2.8F, new FabricItemSettings()), ModItemGroup.BALLERITE_TOOLS);
    public static final Item DIAMOND_CLAYMORE = registerItem("diamond_claymore", new SwordItem(Tiers.DIAMOND, 5, -2.8F, new FabricItemSettings()), ModItemGroup.BALLERITE_TOOLS);
    public static final Item NETHERITE_CLAYMORE = registerItem("netherite_claymore", new SwordItem(Tiers.NETHERITE, 4, -2.8F, new FabricItemSettings()), ModItemGroup.BALLERITE_TOOLS);
    public static final Item BALLERITE_CLAYMORE = registerItem("ballerite_claymore", new SwordItem(ModToolMaterials.COMPRESSED_BALLERITE, 5, -2.8F, new FabricItemSettings()), ModItemGroup.BALLERITE_TOOLS);


    public static final Item KNIFE = registerItem("knife",
        new KnifeItem(), ModItemGroup.BALLERITE_TOOLS);



    //endregion

    //region FARMING
    public static final Item EGGPLANT_SEEDS = registerItem("eggplant_seeds",
            new ItemNameBlockItem(ModBlocks.EGGPLANT_CROP, new FabricItemSettings()), ModItemGroup.BALLERITE_PLANTS);

    public static final Item CORN_SEEDS = registerItem("corn_seeds",
            new ItemNameBlockItem(ModBlocks.CORN_CROP, new FabricItemSettings()), ModItemGroup.BALLERITE_PLANTS);
    public static final Item TOMATO_SEEDS = registerItem("tomato_seeds",
            new ItemNameBlockItem(ModBlocks.TOMATO_CROP, new FabricItemSettings()), ModItemGroup.BALLERITE_PLANTS);
    public static final Item STRAWBERRY_SEEDS = registerItem("strawberry_seeds",
            new BlockItem(ModBlocks.STRAWBERRY_BUSH, new FabricItemSettings()), ModItemGroup.BALLERITE_PLANTS);

    public static final Item EGGPLANT = registerItem("eggplant",
            new Item(new FabricItemSettings().food(EggplantFoodComponents.EGGPLANT)), ModItemGroup.BALLERITE_FOOD);
    public static final Item CORN_COB = registerItem("corn_cob",
            new Item(new FabricItemSettings().food(CornFoodComponents.COB)), ModItemGroup.BALLERITE_FOOD);
    public static final Item GRILLED_CORN_COB = registerItem("corn_cob_grilled",
            new Item(new FabricItemSettings().food(CornFoodComponents.GRILLED)), ModItemGroup.BALLERITE_FOOD);
    public static final Item CORN_KERNEL = registerItem("corn_kernel",
            new Item(new FabricItemSettings().food(CornFoodComponents.KERNEL)), ModItemGroup.BALLERITE_FOOD);
    public static final Item GRILLED_EGGPLANT = registerItem("grilled_eggplant",
            new Item(new FabricItemSettings().food(EggplantFoodComponents.GRILLED_EGGPLANT)), ModItemGroup.BALLERITE_FOOD);


    public static final Item TOMATO = registerItem("tomato",
            new Item(new FabricItemSettings().food(TomatoFoodComponents.TOMATO)), ModItemGroup.BALLERITE_FOOD);
    public static final Item GRILLED_TOMATO = registerItem("grilled_tomato",
            new Item(new FabricItemSettings().food(TomatoFoodComponents.GRILLED_TOMATO)), ModItemGroup.BALLERITE_FOOD);



    public static final Item SWEET_POTATO = registerItem("sweet_potato",
            new ItemNameBlockItem(ModBlocks.SWEET_POTATO_CROP, new FabricItemSettings().food(PotatoFoodComponents.SWEET_POTATO)), ModItemGroup.BALLERITE_FOOD);
    public static final Item BAKED_SWEET_POTATO = registerItem("baked_sweet_potato",
            new Item(new FabricItemSettings().food(PotatoFoodComponents.BAKED_SWEET_POTATO)), ModItemGroup.BALLERITE_FOOD);


    public static final Item BLUE_BERRIES = registerItem("blue_berries",
            new ItemNameBlockItem(ModBlocks.BLUE_BERRY_BUSH, new FabricItemSettings().food(BerryFoodComponents.BLUE_BERRY)), ModItemGroup.BALLERITE_FOOD);
    public static final Item BLACK_BERRIES = registerItem("black_berries",
            new ItemNameBlockItem(ModBlocks.BLACK_BERRY_BUSH, new FabricItemSettings().food(BerryFoodComponents.BLACK_BERRY)), ModItemGroup.BALLERITE_FOOD);




    public static final Item UNRIPE_STRAWBERRY = registerItem("unripe_strawberry",
        new Item(new FabricItemSettings().food(BerryFoodComponents.UNRIPE_STRAWBERRIES)), ModItemGroup.BALLERITE_FOOD);
    public static final Item STRAWBERRY = registerItem("strawberry",
        new Item(new FabricItemSettings().food(BerryFoodComponents.STRAWBERRIES)), ModItemGroup.BALLERITE_FOOD);


    public static final Item SWEET_BERRY_JAM = registerItem("sweet_berry_jam",
        new JamItem(Items.SWEET_BERRIES), ModItemGroup.BALLERITE_FOOD);
    public static final Item TOMATO_JAM = registerItem("tomato_jam",
            new JamItem(TOMATO), ModItemGroup.BALLERITE_FOOD);
    public static final Item BLUEBERRY_JAM = registerItem("blueberry_jam",
            new JamItem(BLUE_BERRIES), ModItemGroup.BALLERITE_FOOD);
    public static final Item BLACKBERRY_JAM = registerItem("blackberry_jam",
            new JamItem(BLACK_BERRIES), ModItemGroup.BALLERITE_FOOD);
    public static final Item STRAWBERRY_JAM = registerItem("strawberry_jam",
        new JamItem(STRAWBERRY), ModItemGroup.BALLERITE_FOOD);

//endregion
    //region FOOD
    public static final Item MILK_VILE = registerItem("milk_vile",
        new MilkVileItem(new FabricItemSettings().craftRemainder(Items.GLASS_BOTTLE)), ModItemGroup.BALLERITE_FOOD);
    public static final Item CHEESE = registerItem("cheese",
        new Item(new FabricItemSettings().food(CheeseFoodComponents.CHEESE)), ModItemGroup.BALLERITE_FOOD);


    public static final Item EGGS_SUNNY = registerItem("eggs_sunny",
        new Item(new FabricItemSettings().food(EggFoodComponents.EGG_SUNNY)), ModItemGroup.BALLERITE_FOOD);
    public static final Item EGGS_SCRAMBLED = registerItem("eggs_scrambled",
        new Item(new FabricItemSettings().food(EggFoodComponents.EGG_SCRAMBLED)), ModItemGroup.BALLERITE_FOOD);
    public static final Item EGGS_OMLETTE = registerItem("eggs_omlette",
        new Item(new FabricItemSettings().food(EggFoodComponents.EGG_OMLETTE)), ModItemGroup.BALLERITE_FOOD);



    public static final Item CHEESE_STEAK = registerItem("cheese_steak",
        new Item(new FabricItemSettings().food(CheeseFoodComponents.CHEESE_STEAK)), ModItemGroup.BALLERITE_FOOD);
    public static final Item CHEESE_PORK = registerItem("cheese_pork",
        new Item(new FabricItemSettings().food(CheeseFoodComponents.CHEESE_PORK)), ModItemGroup.BALLERITE_FOOD);
    public static final Item CHEESE_MUTTON = registerItem("cheese_mutton",
        new Item(new FabricItemSettings().food(CheeseFoodComponents.CHEESE_MUTTON)), ModItemGroup.BALLERITE_FOOD);
    public static final Item CHEESE_CHICKEN = registerItem("cheese_chicken",
        new Item(new FabricItemSettings().food(CheeseFoodComponents.CHEESE_CHICKEN)), ModItemGroup.BALLERITE_FOOD);
    public static final Item CHEESE_FRIES = registerItem("cheese_fries",
        new Item(new FabricItemSettings().food(CheeseFoodComponents.CHEESE_FRIES)), ModItemGroup.BALLERITE_FOOD);

    public static final Item STEAK_SANDWICH = registerItem("steak_sandwich",
        new Item(new FabricItemSettings().food(ModSandwiches.STEAK_SANDWICH)), ModItemGroup.BALLERITE_FOOD);
    public static final Item PORK_SANDWICH = registerItem("pork_sandwich",
        new Item(new FabricItemSettings().food(ModSandwiches.PORK_SANDWICH)), ModItemGroup.BALLERITE_FOOD);
    public static final Item MUTTON_SANDWICH = registerItem("mutton_sandwich",
        new Item(new FabricItemSettings().food(ModSandwiches.MUTTON_SANDWICH)), ModItemGroup.BALLERITE_FOOD);
    public static final Item CHICKEN_SANDWICH = registerItem("chicken_sandwich",
        new Item(new FabricItemSettings().food(ModSandwiches.CHICKEN_SANDWICH)), ModItemGroup.BALLERITE_FOOD);
    public static final Item FRIES_SANDWICH = registerItem("fries_sandwich",
        new Item(new FabricItemSettings().food(ModSandwiches.FRIES_SANDWICH)), ModItemGroup.BALLERITE_FOOD);
    public static final Item CHEESE_SANDWICH = registerItem("cheese_sandwich",
        new Item(new FabricItemSettings().food(ModSandwiches.CHEESE_SANDWICH)), ModItemGroup.BALLERITE_FOOD);

    public static final Item STEAK_SANDWICH_CHEESE = registerItem("steak_sandwich_cheese",
        new Item(new FabricItemSettings().food(ModSandwiches.STEAK_SANDWICH_CHEESE)), ModItemGroup.BALLERITE_FOOD);
    public static final Item PORK_SANDWICH_CHEESE = registerItem("pork_sandwich_cheese",
        new Item(new FabricItemSettings().food(ModSandwiches.PORK_SANDWICH_CHEESE)), ModItemGroup.BALLERITE_FOOD);
    public static final Item MUTTON_SANDWICH_CHEESE = registerItem("mutton_sandwich_cheese",
        new Item(new FabricItemSettings().food(ModSandwiches.MUTTON_SANDWICH_CHEESE)), ModItemGroup.BALLERITE_FOOD);
    public static final Item CHICKEN_SANDWICH_CHEESE = registerItem("chicken_sandwich_cheese",
        new Item(new FabricItemSettings().food(ModSandwiches.CHICKEN_SANDWICH_CHEESE)), ModItemGroup.BALLERITE_FOOD);
    public static final Item FRIES_SANDWICH_CHEESE = registerItem("fries_sandwich_cheese",
        new Item(new FabricItemSettings().food(ModSandwiches.FRIES_SANDWICH_CHEESE)), ModItemGroup.BALLERITE_FOOD);


    public static final Item COCAINE = registerItem("cocaine",
            new CocaineItem(new FabricItemSettings()), ModItemGroup.BALLERITE_FOOD);
    
    //endregion
    private static void addItemsToItemGroups() {
        //region Balls
        addToItemGroup(COMPRESSED_BALLERITE, CreativeModeTabs.INGREDIENTS);
        //endregion


        addToItemGroup(EGGPLANT, CreativeModeTabs.FOOD_AND_DRINKS);
        addToItemGroup(GRILLED_EGGPLANT, CreativeModeTabs.FOOD_AND_DRINKS); 
        addToItemGroup(TOMATO, CreativeModeTabs.FOOD_AND_DRINKS);
        addToItemGroup(GRILLED_TOMATO, CreativeModeTabs.FOOD_AND_DRINKS);
        addToItemGroup(SWEET_POTATO, CreativeModeTabs.FOOD_AND_DRINKS);
        addToItemGroup(BAKED_SWEET_POTATO, CreativeModeTabs.FOOD_AND_DRINKS);
        addToItemGroup(BLACK_BERRIES, CreativeModeTabs.FOOD_AND_DRINKS);
        addToItemGroup(BLUE_BERRIES, CreativeModeTabs.FOOD_AND_DRINKS);
        addToItemGroup(STRAWBERRY, CreativeModeTabs.FOOD_AND_DRINKS);
        addToItemGroup(MILK_VILE, CreativeModeTabs.FOOD_AND_DRINKS);
        addToItemGroup(CHEESE, CreativeModeTabs.FOOD_AND_DRINKS);
        addToItemGroup(EGGS_SUNNY, CreativeModeTabs.FOOD_AND_DRINKS);
        addToItemGroup(EGGS_OMLETTE, CreativeModeTabs.FOOD_AND_DRINKS);
        addToItemGroup(EGGS_SCRAMBLED, CreativeModeTabs.FOOD_AND_DRINKS);

        addToItemGroup(CHEESE_CHICKEN, CreativeModeTabs.FOOD_AND_DRINKS);
        addToItemGroup(CHEESE_STEAK, CreativeModeTabs.FOOD_AND_DRINKS);
        addToItemGroup(CHEESE_MUTTON, CreativeModeTabs.FOOD_AND_DRINKS);
        addToItemGroup(CHEESE_PORK, CreativeModeTabs.FOOD_AND_DRINKS);
        addToItemGroup(CHEESE_FRIES, CreativeModeTabs.FOOD_AND_DRINKS);
        
        addToItemGroup(CHICKEN_SANDWICH, CreativeModeTabs.FOOD_AND_DRINKS);
        addToItemGroup(STEAK_SANDWICH, CreativeModeTabs.FOOD_AND_DRINKS);
        addToItemGroup(MUTTON_SANDWICH, CreativeModeTabs.FOOD_AND_DRINKS);
        addToItemGroup(PORK_SANDWICH, CreativeModeTabs.FOOD_AND_DRINKS);
        addToItemGroup(FRIES_SANDWICH, CreativeModeTabs.FOOD_AND_DRINKS);
        addToItemGroup(CHEESE_SANDWICH, CreativeModeTabs.FOOD_AND_DRINKS);
        
        addToItemGroup(CHICKEN_SANDWICH_CHEESE, CreativeModeTabs.FOOD_AND_DRINKS);
        addToItemGroup(STEAK_SANDWICH_CHEESE, CreativeModeTabs.FOOD_AND_DRINKS);
        addToItemGroup(MUTTON_SANDWICH_CHEESE, CreativeModeTabs.FOOD_AND_DRINKS);
        addToItemGroup(PORK_SANDWICH_CHEESE, CreativeModeTabs.FOOD_AND_DRINKS);
        addToItemGroup(FRIES_SANDWICH_CHEESE, CreativeModeTabs.FOOD_AND_DRINKS);

        addToItemGroup(EGGPLANT, ModItemGroup.BALLERITE_PLANTS);
        addToItemGroup(CORN_COB, ModItemGroup.BALLERITE_PLANTS);
        addToItemGroup(TOMATO, ModItemGroup.BALLERITE_PLANTS);
        addToItemGroup(SWEET_POTATO, ModItemGroup.BALLERITE_PLANTS);
        addToItemGroup(BLUE_BERRIES, ModItemGroup.BALLERITE_PLANTS);
        addToItemGroup(BLACK_BERRIES, ModItemGroup.BALLERITE_PLANTS);
        addToItemGroup(STRAWBERRY, ModItemGroup.BALLERITE_PLANTS);
        addToItemGroup(UNRIPE_STRAWBERRY, ModItemGroup.BALLERITE_PLANTS);

        addToItemGroup(PURPLE_CARROT, CreativeModeTabs.FOOD_AND_DRINKS);

        
        addToItemGroup(BALLERITE_SWORD, CreativeModeTabs.COMBAT);
        addToItemGroup(BALLERITE_PICKAXE, CreativeModeTabs.TOOLS_AND_UTILITIES);
        addToItemGroup(BALLERITE_AXE, CreativeModeTabs.TOOLS_AND_UTILITIES);
        addToItemGroup(BALLERITE_SHOVEL, CreativeModeTabs.TOOLS_AND_UTILITIES);
        addToItemGroup(BALLERITE_HOE, CreativeModeTabs.TOOLS_AND_UTILITIES);


        addToItemGroup(LEAD_INGOT, CreativeModeTabs.INGREDIENTS);
        addToItemGroup(LEAD_NUGGET, CreativeModeTabs.INGREDIENTS);
        addToItemGroup(LEAD_SWORD, CreativeModeTabs.COMBAT);
        addToItemGroup(LEAD_PICKAXE, CreativeModeTabs.TOOLS_AND_UTILITIES);
        addToItemGroup(LEAD_AXE, CreativeModeTabs.TOOLS_AND_UTILITIES);
        addToItemGroup(LEAD_SHOVEL, CreativeModeTabs.TOOLS_AND_UTILITIES);
        addToItemGroup(LEAD_HOE, CreativeModeTabs.TOOLS_AND_UTILITIES);

        addToItemGroup(WOODEN_CLAYMORE, CreativeModeTabs.COMBAT);
        addToItemGroup(STONE_CLAYMORE, CreativeModeTabs.COMBAT);
        addToItemGroup(IRON_CLAYMORE, CreativeModeTabs.COMBAT);
        addToItemGroup(LEAD_CLAYMORE, CreativeModeTabs.COMBAT);
        addToItemGroup(GOLD_CLAYMORE, CreativeModeTabs.COMBAT);
        addToItemGroup(DIAMOND_CLAYMORE, CreativeModeTabs.COMBAT);
        addToItemGroup(NETHERITE_CLAYMORE, CreativeModeTabs.COMBAT);
        addToItemGroup(BALLERITE_CLAYMORE, CreativeModeTabs.COMBAT);


    }
    //region Registry commands

    private static Item registerItem(String name, Item item, CreativeModeTab itemGroup) {
        Item regItem = Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(BalleriteMod.MOD_ID, name), item);
        addToItemGroup(regItem, itemGroup);
        return regItem;
    }
    private static Item registerItem(String name, Item item) {
        return Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(BalleriteMod.MOD_ID, name), item);
    }
    //endregion


}