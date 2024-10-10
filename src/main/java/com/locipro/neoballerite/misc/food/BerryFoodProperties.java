package com.locipro.neoballerite.misc.food;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

public abstract class BerryFoodProperties {
    public static final FoodProperties BLUE = (new FoodProperties.Builder()).nutrition(3).saturationModifier(0.3F).fast()
            .build();
    public static final FoodProperties BLACK = (new FoodProperties.Builder()).nutrition(3).saturationModifier(0.3F).fast()
            .build();
    public static final FoodProperties STRAWBERRIES = (new FoodProperties.Builder()).nutrition(4).saturationModifier(0.5F).fast()
            .build();
    public static final FoodProperties UNRIPE_STRAWBERRIES = (new FoodProperties.Builder()).nutrition(-1).saturationModifier(-0.2F).fast()
            .effect(new MobEffectInstance(MobEffects.POISON, 80, 0), 1F)
            .build();
}
