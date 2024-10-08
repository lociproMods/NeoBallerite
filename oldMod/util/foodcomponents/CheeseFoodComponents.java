package net.locipro.balleritemod.util.foodcomponents;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

public abstract class CheeseFoodComponents {
    public static final FoodProperties CHEESE = new FoodProperties.Builder().nutrition(3).saturationMod(0.4F).fast()
    .build();
    public static final FoodProperties CHEESE_STEAK = new FoodProperties.Builder().nutrition(11).saturationMod(0.8F)
    .effect(new MobEffectInstance(MobEffects.REGENERATION, 100, 0), 1F)
    .build();
    public static final FoodProperties CHEESE_PORK = new FoodProperties.Builder().nutrition(11).saturationMod(0.8F)
    .effect(new MobEffectInstance(MobEffects.REGENERATION, 100, 0), 1F)
    .build();
    public static final FoodProperties CHEESE_MUTTON = new FoodProperties.Builder().nutrition(9).saturationMod(0.7F)
    .effect(new MobEffectInstance(MobEffects.REGENERATION, 100, 0), 1F)
    .build();
    public static final FoodProperties CHEESE_CHICKEN = new FoodProperties.Builder().nutrition(8).saturationMod(0.7F)
    .effect(new MobEffectInstance(MobEffects.REGENERATION, 100, 0), 1F)
    .build();
    public static final FoodProperties CHEESE_FRIES = new FoodProperties.Builder().nutrition(6).saturationMod(0.3F).fast()
    .build();
    
}
