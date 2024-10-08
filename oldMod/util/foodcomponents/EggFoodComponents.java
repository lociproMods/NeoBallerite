package net.locipro.balleritemod.util.foodcomponents;

import net.minecraft.world.food.FoodProperties;

public abstract class EggFoodComponents {
    public static final FoodProperties EGG_SUNNY = (new FoodProperties.Builder()).nutrition(4).saturationMod(0.6F)
    .build();
    public static final FoodProperties EGG_SCRAMBLED = (new FoodProperties.Builder()).nutrition(4).saturationMod(0.5F)
    .build();
    public static final FoodProperties EGG_OMLETTE = (new FoodProperties.Builder()).nutrition(8).saturationMod(0.9F)
    .build();
   
}
