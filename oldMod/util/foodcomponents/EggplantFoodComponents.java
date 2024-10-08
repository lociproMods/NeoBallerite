package net.locipro.balleritemod.util.foodcomponents;

import net.minecraft.world.food.FoodProperties;

public abstract class EggplantFoodComponents {
    public static final FoodProperties EGGPLANT = (new FoodProperties.Builder()).nutrition(3).saturationMod(0.4F)
    .build();
    public static final FoodProperties GRILLED_EGGPLANT = (new FoodProperties.Builder()).nutrition(6).saturationMod(0.5F)
    .build();
}
