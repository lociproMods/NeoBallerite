package net.locipro.balleritemod.util.foodcomponents;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

public abstract class PurpleCarrotFoodComponents {
    public static final FoodProperties PURPLE_CARROT = new FoodProperties.Builder().nutrition(8).saturationMod(1.2f).effect(new MobEffectInstance(MobEffects.REGENERATION, 800, 1), 1.0f).effect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 8000, 0), 1.0f).effect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 8000, 0), 1.0f).effect(new MobEffectInstance(MobEffects.ABSORPTION, 3000, 3), 1.0f).alwaysEat().build();
}
