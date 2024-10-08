package net.locipro.balleritemod.item;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.locipro.balleritemod.BalleriteMod;
import net.locipro.balleritemod.block.ModBlocks;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class ModItemGroup {

    public static final CreativeModeTab BALLERITE_MATERIALS = Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, new ResourceLocation(BalleriteMod.MOD_ID, "ballerite_materials"),
            FabricItemGroup.builder()
                    .title(Component.translatable("itemgroup.ballerite_materials"))
                    .icon(() -> new ItemStack(ModItems.RAW_BALLERITE)).build());
    public static final CreativeModeTab BALLERITE_FOOD = Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, new ResourceLocation(BalleriteMod.MOD_ID, "ballerite_food"),
            FabricItemGroup.builder()
                    .title(Component.translatable("itemgroup.ballerite_food"))
                    .icon(() -> new ItemStack(ModItems.CHEESE)).build());
    public static final CreativeModeTab BALLERITE_TOOLS = Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, new ResourceLocation(BalleriteMod.MOD_ID, "ballerite_tools"),
            FabricItemGroup.builder()
                    .title(Component.translatable("itemgroup.ballerite_tools"))
                    .icon(() -> new ItemStack(ModItems.LEAD_PICKAXE)).build());
    public static final CreativeModeTab BALLERITE_BLOCKS = Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, new ResourceLocation(BalleriteMod.MOD_ID, "ballerite_blocks"),
            FabricItemGroup.builder()
                    .title(Component.translatable("itemgroup.ballerite_blocks"))
                    .icon(() -> new ItemStack(ModBlocks.BURNT_BALLERITE_BLOCK)).build());

    public static final CreativeModeTab BALLERITE_PLANTS = Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, new ResourceLocation(BalleriteMod.MOD_ID, "ballerite_plants"),
            FabricItemGroup.builder()
                    .title(Component.translatable("itemgroup.ballerite_plants"))
                    .icon(() -> new ItemStack(ModItems.TOMATO_SEEDS)).build());
    public static void registerItemGroups() {
        BalleriteMod.LOGGER.info("Registering item groups for " + BalleriteMod.MOD_ID);

    }
    public static void addToItemGroup(Item item, CreativeModeTab itemGroup) {
        ResourceKey<CreativeModeTab> groupRegistryKey = BuiltInRegistries.CREATIVE_MODE_TAB.getResourceKey(itemGroup).orElseThrow(() -> {
            return new IllegalStateException("Unregistered creative tab: " + itemGroup);
        });
        ItemGroupEvents.modifyEntriesEvent(groupRegistryKey).register(entries -> entries.accept(item));
    }

    public static void addToItemGroup(Item item, ResourceKey<CreativeModeTab> groupKey) {
        ItemGroupEvents.modifyEntriesEvent(groupKey).register(entries -> entries.accept(item));
    }


}
