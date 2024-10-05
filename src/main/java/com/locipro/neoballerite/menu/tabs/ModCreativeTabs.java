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
            .displayItems((parameters, output) -> output.accept(BALL_DOWSER.get())).build());
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
            }).build());

}
