package com.locipro.neoballerite.menu.tabs;

import static com.locipro.neoballerite.NeoBallerite.MODID;

import com.locipro.neoballerite.NeoBallerite;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.List;

import static com.locipro.neoballerite.item.ModItems.*;
import static com.locipro.neoballerite.block.ModBlocks.*;


public class ModCreativeTabs {

    // Create a Deferred Register to hold CreativeModeTabs which will all be registered under the "neoballerite" namespace
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);

    public static List<ItemLike> TOOL_LIST = List.of(
            BALLERITE_SWORD,
            BALLERITE_PICKAXE,
            BALLERITE_AXE,
            BALLERITE_SHOVEL,
            BALLERITE_HOE,
            BALLERITE_HELMET,
            BALLERITE_CHESTPLATE,
            BALLERITE_LEGGINGS,
            BALLERITE_BOOTS,

            LEAD_SWORD,
            LEAD_PICKAXE,
            LEAD_AXE,
            LEAD_SHOVEL,
            LEAD_HOE,
            LEAD_HELMET,
            LEAD_CHESTPLATE,
            LEAD_LEGGINGS,
            LEAD_BOOTS,

            BALLERITE_HORSE_ARMOR,

            LEAVES_BOOTS,


            WOODEN_CLAYMORE,
            STONE_CLAYMORE,
            IRON_CLAYMORE,
            LEAD_CLAYMORE,
            GOLD_CLAYMORE,
            BALLERITE_CLAYMORE,
            DIAMOND_CLAYMORE,
            NETHERITE_CLAYMORE,
            KNIFE
    );


    public static List<ItemLike> WOOD_LIST = List.of(
            WITHERED_SAPLING,
            WITHERED_LOG,
            STRIPPED_WITHERED_LOG,
            WITHERED_WOOD,
            STRIPPED_WITHERED_WOOD,
            WITHERED_LEAVES,
            WITHERED_PLANKS,
            WITHERED_DOOR,
            WITHERED_FENCE_GATE,
            WITHERED_BUTTON,
            WITHERED_FENCE,
            WITHERED_TRAPDOOR,
            WITHERED_STAIRS,
            WITHERED_SLAB,
            WITHERED_PRESSURE_PLATE,

            STAR_SAPLING,
            STAR_LOG,
            STRIPPED_STAR_LOG,
            STAR_WOOD,
            STRIPPED_STAR_WOOD,
            STAR_LEAVES,
            STAR_PLANKS,
            STAR_DOOR,
            STAR_FENCE_GATE,
            STAR_BUTTON,
            STAR_FENCE,
            STAR_TRAPDOOR,
            STAR_STAIRS,
            STAR_SLAB,
            STAR_PRESSURE_PLATE
    );
    public static List<ItemLike> OTHER_STUFF_LIST = List.of(
            LEAD_ORE,
            DEEPSLATE_LEAD_ORE,
            LEAD_BLOCK,
            RAW_LEAD_BLOCK,
            LEAD_INGOT,
            LEAD_NUGGET,
            SWEET_POTATO_BLOCK,
            LEAD_LANTERN,
            UNLIT_LANTERN
    );

    public static List<ItemLike> BALLERITE_STUFF_LIST = List.of(
            RAW_BALLERITE_BLOCK,
            COOKED_BALLERITE_BLOCK,
            BURNT_BALLERITE_BLOCK,
            CHARRED_BALLERITE_BLOCK,
            COMPRESSED_BALLERITE_BLOCK,
            BALLERITE_ORE,
            RAW_BALLERITE,
            COOKED_BALLERITE,
            CHARRED_BALLERITE,
            COMPRESSED_BALLERITE_INGOT
    );

    public static List<ItemLike> FOOD_LIST = List.of(
            BLUEBERRIES,
            BLACKBERRIES,
            STRAWBERRY,
            UNRIPE_STRAWBERRY,
            STRAWBERRY_SEEDS,

            SWEET_POTATO,
            BAKED_SWEET_POTATO,

            TOMATO,
            GRILLED_TOMATO,
            TOMATO_SEEDS,

            EGGPLANT,
            GRILLED_EGGPLANT,
            EGGPLANT_SEEDS,


            CORN_KERNELS,
            CORN_COB,
            GRILLED_CORN_COB,

            MILK_VILE,
            MILK_CHEESE,

            EGGS_SUNNY,
            EGGS_SCRAMBLED,
            EGGS_OMLETTE,

            IRON_CARROT,
            DIAMOND_CARROT,
            ENCHANTED_DIAMOND_CARROT,


            CHEESE_STEAK,
            CHEESE_PORK,
            CHEESE_MUTTON,
            CHEESE_FRIES,
            CHEESE_CHICKEN,

            STEAK_SANDWICH,
            PORK_SANDWICH,
            MUTTON_SANDWICH,
            CHICKEN_SANDWICH,
            FRIES_SANDWICH,
            CHEESE_SANDWICH,


            CHEESE_STEAK_SANDWICH,
            CHEESE_PORK_SANDWICH,
            CHEESE_MUTTON_SANDWICH,
            CHEESE_CHICKEN_SANDWICH,
            CHEESE_FRIES_SANDWICH
    );


    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> TOOLS = CREATIVE_MODE_TABS.register("tools", () -> CreativeModeTab.builder()
            .title(getTranslation("tools"))
            .withTabsBefore(CreativeModeTabs.COMBAT) // After the last tab (combat)
            .icon(() -> BALLERITE_PICKAXE.get().getDefaultInstance())
            .displayItems((parameters, output) -> {
                for (ItemLike item : TOOL_LIST) {
                    output.accept(item);
                }
            }).build());
    
    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> WOOD_BLOCKS = CREATIVE_MODE_TABS.register("wood_blocks", () -> CreativeModeTab.builder()
            .title(getTranslation("wood_blocks"))
            .withTabsBefore(TOOLS.getId()) // After our first itemgroup
            .icon(() -> new ItemStack(STAR_WOOD)) // You either `X_BLOCK.get().asItem().getDefaultInstance()` or just itemstack
            .displayItems((parameters, output) -> {
                for (ItemLike item  : WOOD_LIST) {
                    output.accept(item);
                }
            }).build());
    
    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> OTHER_STUFF = CREATIVE_MODE_TABS.register("other_stuff", () -> CreativeModeTab.builder()
            .title(getTranslation("other_stuff"))
            .withTabsBefore(WOOD_BLOCKS.getId())
            .icon(() -> new ItemStack(RAW_LEAD_BLOCK)) // You either `X_BLOCK.get().asItem().getDefaultInstance()` or just itemstack
            .displayItems((parameters, output) -> {
                for (ItemLike item  : OTHER_STUFF_LIST) {
                    output.accept(item);
                }
            }).build());

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> BALLERITE_STUFF = CREATIVE_MODE_TABS.register("ballerite_stuff", () -> CreativeModeTab.builder()
            .title(getTranslation("ballerite_stuff"))
            .withTabsBefore(OTHER_STUFF.getId())
            .icon(() -> new ItemStack(CHARRED_BALLERITE_BLOCK))
            .displayItems((parameters, output) -> {
                for (ItemLike item : BALLERITE_STUFF_LIST) {
                    output.accept(item);
                }
            }).build());


    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> FOOD_STUFF = CREATIVE_MODE_TABS.register("food_stuff", () -> CreativeModeTab.builder()
            .title(getTranslation("food_stuff"))
            .withTabsBefore(BALLERITE_STUFF.getId())
            .icon(() -> BLACKBERRIES.get().getDefaultInstance())
            .displayItems((parameters, output) -> {
                for (ItemLike item : FOOD_LIST) {
                    output.accept(item);
                }
            }).build());



    private static Component getTranslation(String key) {
        return Component.translatable(String.format("itemGroup.%s.%s", MODID, key));
    }
}
