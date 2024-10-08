package net.locipro.balleritemod.util.foodcomponents;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

public abstract class PastaFoodComponents {
    public static final FoodProperties RAW_PASTA = new FoodProperties.Builder().nutrition(2).saturationMod(-0.1f)
    .effect(new MobEffectInstance(MobEffects.CONFUSION, 40), 1f).fast().build();
    public static final FoodProperties COOKED_PASTA = new FoodProperties.Builder().nutrition(4).saturationMod(0.1f)
    .build();
    public static final FoodProperties SPAGHETTI = new FoodProperties.Builder().nutrition(6).saturationMod(0.4f)
    .build();
    public static final FoodProperties CHEESE_SPAGHETTI = new FoodProperties.Builder().nutrition(10).saturationMod(0.6f)
    .effect(new MobEffectInstance(MobEffects.ABSORPTION, 240), 1f).build();
}
