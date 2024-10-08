package net.locipro.balleritemod.util.foodcomponents;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

public abstract class BerryFoodComponents {
    public static final FoodProperties BLUE_BERRY = (new FoodProperties.Builder()).nutrition(3).saturationMod(0.3F).fast()
    .build();
    public static final FoodProperties BLACK_BERRY = (new FoodProperties.Builder()).nutrition(3).saturationMod(0.3F).fast()
    .build();
    public static final FoodProperties STRAWBERRIES = (new FoodProperties.Builder()).nutrition(4).saturationMod(0.5F).fast()
    .build();
    public static final FoodProperties UNRIPE_STRAWBERRIES = (new FoodProperties.Builder()).nutrition(-1).saturationMod(-0.2F).fast()
    .effect(new MobEffectInstance(MobEffects.POISON, 80, 0), 1F)
    .build();
}
