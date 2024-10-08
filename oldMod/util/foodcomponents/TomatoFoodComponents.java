package net.locipro.balleritemod.util.foodcomponents;

import net.minecraft.world.food.FoodProperties;

public abstract class TomatoFoodComponents {
    public static final FoodProperties TOMATO = (new FoodProperties.Builder()).nutrition(3).saturationMod(0.5F)
    .build();
    public static final FoodProperties GRILLED_TOMATO = (new FoodProperties.Builder()).nutrition(6).saturationMod(0.75F)
    .build();
}
