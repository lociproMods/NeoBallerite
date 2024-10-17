package com.locipro.neoballerite.misc.food;

import net.minecraft.world.food.FoodProperties;

public abstract class EggFoodProperties {
    public static final FoodProperties SUNNY = new FoodProperties.Builder()
            .nutrition(4)
            .saturationModifier(0.6F)
            .build();
    public static final FoodProperties SCRAMBLED = new FoodProperties.Builder()
            .nutrition(4)
            .saturationModifier(0.5F)
            .build();
    // Same saturation of golden carrot.
    public static final FoodProperties OMLETTE = new FoodProperties.Builder()
            .nutrition(9)
            .saturationModifier(1.2F)
            .build();
}
