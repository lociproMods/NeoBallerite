package com.locipro.neoballerite.misc.food;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

public abstract class CheeseFoodProperties {
    public static final FoodProperties CHEESE = new FoodProperties.Builder()
            .nutrition(3)
            .saturationModifier(0.4F)
            .fast()
            .build();
    public static final FoodProperties WARPED = new FoodProperties.Builder()
            .nutrition(5)
            .saturationModifier(0.4F)
            .fast()
            .effect(() -> new MobEffectInstance(MobEffects.DARKNESS, 15, 1, false, false), 1.0f)
            .effect(() -> new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 400, 1, true, true), 1.0f)
            .build();


    public static final FoodProperties CHEESE_STEAK = new FoodProperties.Builder().nutrition(11).saturationModifier(0.8F)
            .effect(() -> new MobEffectInstance(MobEffects.REGENERATION, 100, 0), 1F)
            .build();
    public static final FoodProperties CHEESE_PORK = new FoodProperties.Builder().nutrition(11).saturationModifier(0.8F)
            .effect(() -> new MobEffectInstance(MobEffects.REGENERATION, 100, 0), 1F)
            .build();
    public static final FoodProperties CHEESE_MUTTON = new FoodProperties.Builder().nutrition(9).saturationModifier(0.7F)
            .effect(() -> new MobEffectInstance(MobEffects.REGENERATION, 100, 0), 1F)
            .build();
    public static final FoodProperties CHEESE_CHICKEN = new FoodProperties.Builder().nutrition(8).saturationModifier(0.7F)
            .effect(() -> new MobEffectInstance(MobEffects.REGENERATION, 100, 0), 1F)
            .build();
    public static final FoodProperties CHEESE_FRIES = new FoodProperties.Builder().nutrition(6).saturationModifier(0.3F).fast()
            .build();
    
}
