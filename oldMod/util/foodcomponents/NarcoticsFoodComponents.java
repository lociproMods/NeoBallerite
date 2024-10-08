package net.locipro.balleritemod.util.foodcomponents;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

public abstract class NarcoticsFoodComponents {
    public static final FoodProperties COCAINE = (new FoodProperties.Builder()).nutrition(-1).saturationMod(-0.4f).fast()
            .alwaysEat().effect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 600, 1, true, false), 1)
            .effect(new MobEffectInstance(MobEffects.HARM, 0, 1), 1)
            .effect(new MobEffectInstance(MobEffects.CONFUSION, 100, 1), 1)
            .build();
}
