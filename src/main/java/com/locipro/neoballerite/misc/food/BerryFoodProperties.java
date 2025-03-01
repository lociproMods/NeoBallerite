package com.locipro.neoballerite.misc.food;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.component.Consumable;
import net.minecraft.world.item.consume_effects.ApplyStatusEffectsConsumeEffect;

import java.util.List;

public abstract class BerryFoodProperties {
    public static final Consumable DEFAULT_BERRY = Consumable.builder().consumeSeconds(0.8f).build();

    public static final FoodProperties BLUE = new FoodProperties.Builder().nutrition(3).saturationModifier(0.3F)
            .build();
    public static final FoodProperties BLACK = new FoodProperties.Builder().nutrition(3).saturationModifier(0.3F)
            .build();
    public static final FoodProperties STRAWBERRIES = new FoodProperties.Builder().nutrition(4).saturationModifier(0.5F)
            .build();
    public static final FoodProperties UNRIPE_STRAWBERRIES = new FoodProperties.Builder().nutrition(-1).saturationModifier(-0.2F)
            .build();
    //.effect(() -> new MobEffectInstance(MobEffects.POISON, 80, 0), 1F)
    public static final Consumable UNRIPE_STRAWBERIES_CONSUMABLE = Consumable.builder()
            .consumeSeconds(0.8f)
            .onConsume(
                    new ApplyStatusEffectsConsumeEffect(
                            new MobEffectInstance(MobEffects.POISON, 80, 0))
            ).build();
}
