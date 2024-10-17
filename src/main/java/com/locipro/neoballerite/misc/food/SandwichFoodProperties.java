package com.locipro.neoballerite.misc.food;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

public abstract class SandwichFoodProperties {
    public static final FoodProperties STEAK_SANDWICH = new FoodProperties.Builder().nutrition(12).saturationModifier(0.8F)
            .effect(() -> new MobEffectInstance(MobEffects.REGENERATION, 100, 0), 1F)
            .build();
    public static final FoodProperties PORK_SANDWICH = new FoodProperties.Builder().nutrition(12).saturationModifier(0.8F)
            .effect(() -> new MobEffectInstance(MobEffects.REGENERATION, 100, 0), 1F)
            .build();
    public static final FoodProperties CHICKEN_SANDWICH = new FoodProperties.Builder().nutrition(12).saturationModifier(0.8F)
            .effect(() -> new MobEffectInstance(MobEffects.REGENERATION, 100, 0), 1F)
            .build();
    public static final FoodProperties MUTTON_SANDWICH = new FoodProperties.Builder().nutrition(12).saturationModifier(0.8F)
            .effect(() -> new MobEffectInstance(MobEffects.REGENERATION, 100, 0), 1F)
            .build();
    public static final FoodProperties FRIES_SANDWICH = new FoodProperties.Builder().nutrition(8).saturationModifier(0.8F)
            .effect(() -> new MobEffectInstance(MobEffects.REGENERATION, 100, 0), 1F)
            .build();


    public static final FoodProperties CHEESE_STEAK_SANDWICH = new FoodProperties.Builder().nutrition(14).saturationModifier(1F)
            .effect(() -> new MobEffectInstance(MobEffects.REGENERATION, 100, 0), 1F)
            .effect(() -> new MobEffectInstance(MobEffects.ABSORPTION, 800, 1), 1F)
            .build();
    public static final FoodProperties CHEESE_PORK_SANDWICH = new FoodProperties.Builder().nutrition(14).saturationModifier(1F)
            .effect(() -> new MobEffectInstance(MobEffects.REGENERATION, 100, 0), 1F)
            .effect(() -> new MobEffectInstance(MobEffects.ABSORPTION, 800, 1), 1F)
            .build();
    public static final FoodProperties CHICKEN_CHEESE_SANDWICH = new FoodProperties.Builder().nutrition(11).saturationModifier(1F)
            .effect(() -> new MobEffectInstance(MobEffects.REGENERATION, 100, 0), 1F)
            .effect(() -> new MobEffectInstance(MobEffects.ABSORPTION, 800, 1), 1F)
            .build();
    public static final FoodProperties MUTTON_CHEESE_SANDWICH = new FoodProperties.Builder().nutrition(11).saturationModifier(1F)
            .effect(() -> new MobEffectInstance(MobEffects.REGENERATION, 100, 0), 1F)
            .effect(() -> new MobEffectInstance(MobEffects.ABSORPTION, 800, 1), 1F)
            .build();
    public static final FoodProperties CHEESE_FRIES_SANDWICH = new FoodProperties.Builder().nutrition(10).saturationModifier(1F)
            .effect(() -> new MobEffectInstance(MobEffects.REGENERATION, 100, 0), 1F)
            .effect(() -> new MobEffectInstance(MobEffects.ABSORPTION, 800, 1), 1F)
            .build();


    public static final FoodProperties CHEESE_SANDWICH = new FoodProperties.Builder().nutrition(6).saturationModifier(0.8F)
            .build();
}
