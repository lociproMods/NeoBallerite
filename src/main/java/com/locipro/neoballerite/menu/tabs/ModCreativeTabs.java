package com.locipro.neoballerite.menu.tabs;

import static com.locipro.neoballerite.NeoBallerite.MODID;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import static com.locipro.neoballerite.item.ModItems.*;
import static com.locipro.neoballerite.block.ModBlocks.*;

public class ModCreativeTabs {

    // Create a Deferred Register to hold CreativeModeTabs which will all be registered under the "neoballerite" namespace
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);


    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> BALLERITE_TOOLS = CREATIVE_MODE_TABS.register("ballerite_tools", () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup.ballerite_tools"))
            .withTabsBefore(CreativeModeTabs.COMBAT) // After the last tab (combat)
            .icon(() -> BALL_DOWSER.get().getDefaultInstance())
            .displayItems((parameters, output) -> {
                output.accept(BALL_DOWSER.get());
                output.accept(BALLERITE_SWORD.get());
                output.accept(BALLERITE_PICKAXE.get());
                output.accept(BALLERITE_AXE.get());
                output.accept(BALLERITE_SHOVEL.get());
                output.accept(BALLERITE_HOE.get()); 
                
                output.accept(LEAD_SWORD.get());
                output.accept(LEAD_PICKAXE.get());
                output.accept(LEAD_AXE.get());
                output.accept(LEAD_SHOVEL.get());
                output.accept(LEAD_HOE.get());


                output.accept(WOODEN_CLAYMORE.get());
                output.accept(STONE_CLAYMORE.get());
                output.accept(IRON_CLAYMORE.get());
                output.accept(LEAD_CLAYMORE.get());
                output.accept(GOLD_CLAYMORE.get());
                output.accept(BALLERITE_CLAYMORE.get());
                output.accept(DIAMOND_CLAYMORE.get());
                output.accept(NETHERITE_CLAYMORE.get());

            }).build());
    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> BALLERITE_BLOCKS = CREATIVE_MODE_TABS.register("ballerite_blocks", () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup.ballerite_blocks"))
            .withTabsBefore(ResourceLocation.fromNamespaceAndPath(MODID, "ballerite_tools")) // After our first itemgroup
            .icon(() -> new ItemStack(COMPRESSED_BALLERITE_BLOCK)) // You either `X_BLOCK.get().asItem().getDefaultInstance()` or just itemstack
            .displayItems((parameters, output) -> {
                output.accept(RAW_BALLERITE_BLOCK);
                output.accept(COOKED_BALLERITE_BLOCK);
                output.accept(BURNT_BALLERITE_BLOCK);
                output.accept(CHARRED_BALLERITE_BLOCK);
                output.accept(COMPRESSED_BALLERITE_BLOCK);
                output.accept(BALLERITE_ORE);
                output.accept(LEAD_ORE);
                output.accept(DEEPSLATE_LEAD_ORE);
                output.accept(LEAD_BLOCK);
                output.accept(RAW_LEAD_BLOCK);
            }).build());
    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> WOODEN_BLOCKS = CREATIVE_MODE_TABS.register("wooden_blocks", () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup.wooden_blocks"))
            .withTabsBefore(ResourceLocation.fromNamespaceAndPath(MODID, "ballerite_blocks"))
            .icon(() -> new ItemStack(WITHERED_PLANKS)) // You either `X_BLOCK.get().asItem().getDefaultInstance()` or just itemstack
            .displayItems((parameters, output) -> {
                output.accept(WITHERED_SAPLING);
                output.accept(WITHERED_LOG);
                output.accept(STRIPPED_WITHERED_LOG);
                output.accept(WITHERED_WOOD);
                output.accept(STRIPPED_WITHERED_WOOD);
                output.accept(WITHERED_LEAVES);
                output.accept(WITHERED_PLANKS);
                output.accept(WITHERED_DOOR);
                output.accept(WITHERED_FENCE_GATE);
                output.accept(WITHERED_BUTTON);
                output.accept(WITHERED_FENCE);
                output.accept(WITHERED_TRAPDOOR);
                output.accept(WITHERED_STAIRS);
                output.accept(WITHERED_SLAB);
                output.accept(WITHERED_PRESSURE_PLATE);
                
                output.accept(STAR_SAPLING);
                output.accept(STAR_LOG);
                output.accept(STRIPPED_STAR_LOG);
                output.accept(STAR_WOOD);
                output.accept(STRIPPED_STAR_WOOD);
                output.accept(STAR_LEAVES);
                output.accept(STAR_PLANKS);
                output.accept(STAR_DOOR);
                output.accept(STAR_FENCE_GATE);
                output.accept(STAR_BUTTON);
                output.accept(STAR_FENCE);
                output.accept(STAR_TRAPDOOR);
                output.accept(STAR_STAIRS);
                output.accept(STAR_SLAB);
                output.accept(STAR_PRESSURE_PLATE);

            }).build());
    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> BALLERITE_ITEMS = CREATIVE_MODE_TABS.register("ballerite_items", () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup.ballerite_items"))
            .withTabsBefore(ResourceLocation.fromNamespaceAndPath(MODID, "ballerite_blocks"))
            .icon(() -> COMPRESSED_BALLERITE_INGOT.get().getDefaultInstance())
            .displayItems((parameters, output) -> {
                output.accept(RAW_BALLERITE);
                output.accept(COOKED_BALLERITE);
                output.accept(CHARRED_BALLERITE);
                output.accept(COMPRESSED_BALLERITE_INGOT);
                output.accept(LEAD_INGOT);
                output.accept(LEAD_NUGGET);
                output.accept(BLUEBERRIES);
                output.accept(BLACKBERRIES);
                output.accept(STRAWBERRY);
                output.accept(UNRIPE_STRAWBERRY);
                output.accept(STRAWBERRY_SEEDS);
            }).build());

}
