package net.locipro.balleritemod.util.foodcomponents;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

public abstract class BalleriteFoodComponents {
    
    public static final FoodProperties BALLERITE = (new FoodProperties.Builder()).nutrition(1).saturationMod(0.1F)
    .effect(new MobEffectInstance(MobEffects.CONFUSION, 200, 0), 0.8F).fast().alwaysEat()
    .build();
    public static final FoodProperties RAW_BALLERITE = (new FoodProperties.Builder()).nutrition(0).saturationMod(0F)
    .effect(new MobEffectInstance(MobEffects.CONFUSION, 400, 1), 1F).fast().alwaysEat()
    .build();
    public static final FoodProperties RAW_BALLERITE_BLOCK = (new FoodProperties.Builder()).nutrition(-4).saturationMod(-0.4F)
    .effect(new MobEffectInstance(MobEffects.CONFUSION, 1200, 2), 1F).alwaysEat()
    .build();
    public static final FoodProperties BALLERITE_BLOCK = (new FoodProperties.Builder()).nutrition(4).saturationMod(0.4F)
    .effect(new MobEffectInstance(MobEffects.CONFUSION, 100, 0), 0.5F).alwaysEat()
    .build();

    
}
