package com.locipro.neoballerite.misc.food;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

public abstract class VanillaFoodProperties {
    // I think something about data components can be used to add custom components to vanilla items, not sure.
    // who's the idiot who told you that? the fuck are you on about? what did you even mean by vanilla food components?
    // Ok this guy is not an idiot he's actually right, stfu old me 2.0

    public static FoodProperties SUGAR = new FoodProperties.Builder()
            .nutrition(1)
            .saturationModifier(0)
            .effect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 40, 0), 1.0f)
            .alwaysEdible()
            .fast()
            .build();
    public static FoodProperties GLISTERING_MELON = new FoodProperties.Builder()
            .nutrition(3)
            .saturationModifier(0.1f)
            .effect(() -> new MobEffectInstance(MobEffects.REGENERATION, 40, 0), 1.0f)
            .alwaysEdible()
            .fast()
            .build();
    public static FoodProperties FERMENTED_SPIDER_EYE = new FoodProperties.Builder()
            .nutrition(1)
            .saturationModifier(0)
            .effect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 30, 0), 1.0f)
            .alwaysEdible()
            .fast()
            .build();
    public static FoodProperties MAGMA_CREAM = new FoodProperties.Builder()
            .nutrition(1)
            .saturationModifier(0)
            .effect(() -> new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 45, 0), 1.0f)
            .alwaysEdible()
            .fast()
            .build();
}
