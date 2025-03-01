package com.locipro.neoballerite.misc.food;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.component.Consumable;
import net.minecraft.world.item.consume_effects.ApplyStatusEffectsConsumeEffect;

import java.util.List;

public abstract class CropFoodProperties {
    public static final FoodProperties TOMATO = new FoodProperties.Builder()
            .nutrition(3)
            .saturationModifier(0.5F)
            .build();
    public static final FoodProperties GRILLED_TOMATO = new FoodProperties.Builder()
            .nutrition(6)
            .saturationModifier(0.75F)
            .build();

    public static final FoodProperties EGGPLANT = new FoodProperties.Builder()
            .nutrition(3)
            .saturationModifier(0.4F)
            .build();
    public static final FoodProperties GRILLED_EGGPLANT = new FoodProperties.Builder()
            .nutrition(6)
            .saturationModifier(0.5F)
            .build();

    public static final FoodProperties SWEET_POTATO = new FoodProperties.Builder()
            .nutrition(2)
            .saturationModifier(0.2f)
            .build();
    public static final FoodProperties BAKED_SWEET_POTATO = new FoodProperties.Builder()
            .nutrition(6)
            .saturationModifier(0.5f)
            .build();

    public static final FoodProperties COB = new FoodProperties.Builder()
            .nutrition(4)
            .saturationModifier(0.4F)
            .build();
    public static final FoodProperties GRILLED_COB = new FoodProperties.Builder()
            .nutrition(6)
            .saturationModifier(0.5F)
            .build();
    public static final FoodProperties KERNELS = new FoodProperties.Builder()
            .nutrition(1)
            .saturationModifier(0.0F)
            .build();
    public static final FoodProperties CORN_BREAD = new FoodProperties.Builder()
            .nutrition(7)
            .saturationModifier(0.7f)
            .build();

    public static FoodProperties IRON_CARROT = new FoodProperties.Builder()
            .nutrition(4)
            .saturationModifier(0.6f)
            .build();
    public static Consumable IRON_CARROT_C = Consumable.builder()
            .onConsume(new ApplyStatusEffectsConsumeEffect(
                    new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 1200, 0)
            ))
            .build();

    /*new MobEffectInstance(MobEffects.REGENERATION, 400, 1),
                    new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 6000, 0),
                    new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 6000, 0),
                    new MobEffectInstance(MobEffects.ABSORPTION, 2400, 3)*/
    public static FoodProperties DIAMOND_CARROT = new FoodProperties.Builder()
            .nutrition(10)
            .saturationModifier(0.8f)
            .alwaysEdible()
            .build();
    public static Consumable DIAMOND_CARROT_C = Consumable.builder().onConsume(new ApplyStatusEffectsConsumeEffect(
            List.of(
               new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 5500, 0),
               new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 4500, 0),
               new MobEffectInstance(MobEffects.ABSORPTION, 2400, 4),
               new MobEffectInstance(MobEffects.REGENERATION, 250, 1)
            )
    )).build();

    public static FoodProperties ENCHANTED_DIAMOND_CARROT = new FoodProperties.Builder()
            .nutrition(12)
            .saturationModifier(1.0f)
            .alwaysEdible()
            .build();
    public static Consumable ENCHANTED_DIAMOND_CARROT_C = Consumable.builder().onConsume(new ApplyStatusEffectsConsumeEffect(
            List.of(
                    new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 10000, 1),
                    new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 10000, 0),
                    new MobEffectInstance(MobEffects.ABSORPTION, 5000, 4),
                    new MobEffectInstance(MobEffects.REGENERATION, 500, 2)
            )
    )).build();
}
