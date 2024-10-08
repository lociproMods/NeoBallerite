package net.locipro.balleritemod.util.foodcomponents;

import net.minecraft.world.food.FoodProperties;

public abstract class CornFoodComponents {
    public static final FoodProperties COB = new FoodProperties.Builder().nutrition(4).saturationMod(0.4F)
            .build();
    public static final FoodProperties GRILLED = new FoodProperties.Builder().nutrition(6).saturationMod(0.5F)
            .build();
    public static final FoodProperties KERNEL = new FoodProperties.Builder().nutrition(2).saturationMod(0.2F)
            .build();

}
