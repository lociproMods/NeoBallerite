package com.locipro.neoballerite.misc.food;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

public abstract class BalleriteFoodProperties {
    // Confusion is Nausea.
    public static final FoodProperties COOKED_BALLERITE = (new FoodProperties.Builder()).nutrition(1).saturationModifier(0.1F)
            .effect( () -> new MobEffectInstance(MobEffects.CONFUSION, 200, 0), 0.8F).fast().alwaysEdible()
            .build();
    public static final FoodProperties RAW_BALLERITE = (new FoodProperties.Builder()).nutrition(0).saturationModifier(0F)
            .effect( () -> new MobEffectInstance(MobEffects.CONFUSION, 400, 1), 1F).fast().alwaysEdible()
            .build();
    public static final FoodProperties RAW_BALLERITE_BLOCK = (new FoodProperties.Builder()).nutrition(-4).saturationModifier(-0.4F)
            .effect( () -> new MobEffectInstance(MobEffects.CONFUSION, 1200, 2), 1F).alwaysEdible()
            .build();
    public static final FoodProperties COOKED_BALLERITE_BLOCK = (new FoodProperties.Builder()).nutrition(4).saturationModifier(0.4F)
            .effect( () -> new MobEffectInstance(MobEffects.CONFUSION, 100, 0), 0.5F).alwaysEdible()
            .build();

}
