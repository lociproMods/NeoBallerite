package com.locipro.neoballerite.misc.food;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

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
            .fast()
            .build();

    public static FoodProperties IRON_CARROT = new FoodProperties.Builder()
            .nutrition(4)
            .saturationModifier(0.6f)
            .effect(() -> new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 1200, 0), 1.0f)
            .build();
}
