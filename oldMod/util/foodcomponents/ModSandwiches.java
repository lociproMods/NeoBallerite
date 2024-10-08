package net.locipro.balleritemod.util.foodcomponents;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

public abstract class ModSandwiches {

    public static final FoodProperties STEAK_SANDWICH = new FoodProperties.Builder().nutrition(12).saturationMod(0.8F)
    .effect(new MobEffectInstance(MobEffects.REGENERATION, 100, 0), 1F)
    .build();
    public static final FoodProperties PORK_SANDWICH = new FoodProperties.Builder().nutrition(12).saturationMod(0.8F)
    .effect(new MobEffectInstance(MobEffects.REGENERATION, 100, 0), 1F)
    .build();
    public static final FoodProperties CHICKEN_SANDWICH = new FoodProperties.Builder().nutrition(12).saturationMod(0.8F)
    .effect(new MobEffectInstance(MobEffects.REGENERATION, 100, 0), 1F)
    .build();
    public static final FoodProperties MUTTON_SANDWICH = new FoodProperties.Builder().nutrition(12).saturationMod(0.8F)
    .effect(new MobEffectInstance(MobEffects.REGENERATION, 100, 0), 1F)
    .build();
    public static final FoodProperties FRIES_SANDWICH = new FoodProperties.Builder().nutrition(8).saturationMod(0.8F)
    .effect(new MobEffectInstance(MobEffects.REGENERATION, 100, 0), 1F)
    .build();


    public static final FoodProperties STEAK_SANDWICH_CHEESE = new FoodProperties.Builder().nutrition(14).saturationMod(1F)
    .effect(new MobEffectInstance(MobEffects.REGENERATION, 100, 0), 1F)
    .effect(new MobEffectInstance(MobEffects.ABSORPTION, 800, 1), 1F)
    .build();
    public static final FoodProperties PORK_SANDWICH_CHEESE = new FoodProperties.Builder().nutrition(14).saturationMod(1F)
    .effect(new MobEffectInstance(MobEffects.REGENERATION, 100, 0), 1F)
    .effect(new MobEffectInstance(MobEffects.ABSORPTION, 800, 1), 1F)
    .build();
    public static final FoodProperties CHICKEN_SANDWICH_CHEESE = new FoodProperties.Builder().nutrition(11).saturationMod(1F)
    .effect(new MobEffectInstance(MobEffects.REGENERATION, 100, 0), 1F)
    .effect(new MobEffectInstance(MobEffects.ABSORPTION, 800, 1), 1F)
    .build();
    public static final FoodProperties MUTTON_SANDWICH_CHEESE = new FoodProperties.Builder().nutrition(11).saturationMod(1F)
    .effect(new MobEffectInstance(MobEffects.REGENERATION, 100, 0), 1F)
    .effect(new MobEffectInstance(MobEffects.ABSORPTION, 800, 1), 1F)
    .build();
    public static final FoodProperties FRIES_SANDWICH_CHEESE = new FoodProperties.Builder().nutrition(10).saturationMod(1F)
    .effect(new MobEffectInstance(MobEffects.REGENERATION, 100, 0), 1F)
    .effect(new MobEffectInstance(MobEffects.ABSORPTION, 800, 1), 1F)
    .build();
    

    public static final FoodProperties CHEESE_SANDWICH = new FoodProperties.Builder().nutrition(6).saturationMod(0.8F)
    .build();
}
