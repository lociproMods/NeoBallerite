package com.locipro.neoballerite.misc.food;

import com.locipro.neoballerite.item.custom.CheeseItem;
import com.locipro.neoballerite.util.ModTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.component.Consumable;
import net.minecraft.world.item.consume_effects.ApplyStatusEffectsConsumeEffect;

import java.util.List;
import java.util.Map;

public abstract class CheeseFoodProperties {
    public static final FoodProperties CHEESE = new FoodProperties.Builder()
            .nutrition(3)
            .saturationModifier(0.4F)
            .build();
    public static final FoodProperties WARPED = new FoodProperties.Builder()
            .nutrition(5)
            .saturationModifier(0.4F).build();
    public static final Consumable WARPED_C = Consumable.builder()
            .consumeSeconds(0.8f)
            .onConsume(new ApplyStatusEffectsConsumeEffect(List.of(
                    new MobEffectInstance(MobEffects.DARKNESS, 25, 1, false, false),
                    new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 400, 1, true, true)
            )))
            .build();

    // Wait why aren't these used? why are they here
    public static FoodProperties getCheeseFoodProperties(Item item) {
        if (item instanceof CheeseItem cheeseItem) {
            return cheeseTagToFoodProperties.get(ModTags.getFungalTagForCheese(cheeseItem));
        }
        throw new IllegalArgumentException(String.format("Item needs to be of cheese type. Can't be %s", item));
    }
    public static Consumable getCheeseConsumable(Item item) {
        if (item instanceof CheeseItem cheeseItem) {
            return cheeseTagToConsumable.get(ModTags.getFungalTagForCheese(cheeseItem));
        }
        throw new IllegalArgumentException(String.format("Item needs to be of cheese type. Can't be %s", item));
    }
    private static final Map<TagKey<Item>, FoodProperties> cheeseTagToFoodProperties = Map.of(
            ModTags.Items.OVERWORLD_FUNGALS, CHEESE,
            ModTags.Items.NETHER_FUNGALS, WARPED
    );
    private static final Map<TagKey<Item>, Consumable> cheeseTagToConsumable = Map.of(
            ModTags.Items.OVERWORLD_FUNGALS, BerryFoodProperties.DEFAULT_BERRY,
            ModTags.Items.NETHER_FUNGALS, WARPED_C
    );
    
}
