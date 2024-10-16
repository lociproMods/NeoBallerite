package com.locipro.neoballerite.misc.food;

import net.minecraft.world.food.FoodProperties;

public class FungalFoodProperties {
    public static final FoodProperties CHEESE = new FoodProperties.Builder()
            .nutrition(3)
            .saturationModifier(0.4F)
            .fast()
            .build();
}
