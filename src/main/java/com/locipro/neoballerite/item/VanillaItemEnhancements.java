package com.locipro.neoballerite.item;

import com.locipro.neoballerite.NeoBallerite;
import com.locipro.neoballerite.misc.food.VanillaFoodProperties;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.neoforged.neoforge.event.ModifyDefaultComponentsEvent;
import net.neoforged.neoforge.registries.RegisterEvent;

import java.util.List;
import java.util.Map;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD, modid = NeoBallerite.MODID)
public class VanillaItemEnhancements {
    private static Map<Item, FoodProperties> itemToFood = Map.of(
            Items.SUGAR, VanillaFoodProperties.SUGAR,
            Items.GLISTERING_MELON_SLICE, VanillaFoodProperties.GLISTERING_MELON,
            Items.FERMENTED_SPIDER_EYE, VanillaFoodProperties.FERMENTED_SPIDER_EYE,
            Items.MAGMA_CREAM, VanillaFoodProperties.MAGMA_CREAM
    );

    @SubscribeEvent
    public static void loadComplete(ModifyDefaultComponentsEvent event) {
       for (Item item : itemToFood.keySet()) {
           // To ensure we don't override anything vanilla *or* modded. We want to be a cuck mod!
           if (!item.components().has(DataComponents.FOOD)) {
               event.modify(item, builder -> builder.set(DataComponents.FOOD, itemToFood.get(item)));
           }
       }
    }
}
