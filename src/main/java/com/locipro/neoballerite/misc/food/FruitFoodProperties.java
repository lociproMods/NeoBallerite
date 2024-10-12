package com.locipro.neoballerite.misc.food;

import net.minecraft.world.food.FoodProperties;

public class FruitFoodProperties {
    public static final FoodProperties TOMATO = new FoodProperties.Builder()
            .nutrition(3)
            .saturationModifier(0.5F)
            .build();
    public static final FoodProperties GRILLED_TOMATO = new FoodProperties.Builder()
            .nutrition(6)
            .saturationModifier(0.75F)
            .build();
}
