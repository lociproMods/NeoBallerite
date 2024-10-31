package com.locipro.neoballerite.item.custom;


import com.locipro.neoballerite.component.NeoDataComponents;
import com.locipro.neoballerite.item.util.FoodMath;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.UseAnim;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/** Currently only a helper class with some variables to assist in recipe generation.
 * Eventually will get dynamic item models to make all sandwiches just an instance of this singleton with some data components.**/
public class SandwichItem extends Item {
    private final static int MAX_SANDWICH_EFFECTS = 8;

    public SandwichItem() {
        super(new Item.Properties());
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        for (var x : getIngredientsOfSandwich(stack)) {
            if (x.isPresent()) {
                tooltipComponents.add(Component.literal(x.toString()));
            }
        }
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
    }

    // Calculate food here broooo.
    @Override
    public @Nullable FoodProperties getFoodProperties(ItemStack stack, @Nullable LivingEntity entity) {
        return getCombinedFoodProperties(getIngredientsOfSandwich(stack), entity);
    }

    @Override
    public @NotNull UseAnim getUseAnimation(ItemStack stack) {
        return UseAnim.EAT;
    }

    // Returns a FoodProperties instance that has the combined properties of all present items. (Uses default ItemStack.)
    public static FoodProperties getCombinedFoodProperties(List<Optional<Item>> items, LivingEntity entity) {
        // Initialize a list with empty optionals
        List<Optional<FoodProperties>> properties = new ArrayList<>(items.size());
        for (var x : items) {
            properties.add(Optional.empty());
        }


        // Get food properties of all items.
        for (int i = 0; i < items.size(); i ++) {
            if (items.get(i).isPresent()) {
                // Might need to refactor all sandwiches to have components of ItemStack, not Item.
                properties.add(i, Optional.ofNullable(items.get(i).get().getFoodProperties(items.get(i).get().getDefaultInstance(), entity)));
            }
        }

        List<FoodProperties.PossibleEffect> effects = new ArrayList<>(MAX_SANDWICH_EFFECTS);
        int nutrition = 0;
        float saturation = 0f;
        // This is SATURATION, not saturationModifier.
        // Have to calculate modifier later in builder.

        // Add food properties.
        for (var x : properties) {
            if (x.isPresent()) {
                var property = x.get();
                nutrition += property.nutrition();
                saturation += property.saturation();
                if (!property.effects().isEmpty()) {
                    effects.addAll(property.effects());
                }
            }
        }

        float saturationModifier = FoodMath.saturationModifierFromSaturationAndNutrition(
                saturation, nutrition
        ); // Formula from "net.minecraft.world.food.FoodConstants.saturationByModifier", do algebra to find sat modifier based on saturation and nutrition.


        FoodProperties.Builder foodBuilder = new FoodProperties.Builder();
        foodBuilder.nutrition(nutrition);
        foodBuilder.saturationModifier(saturationModifier);
        for (var effect : effects) {
            foodBuilder.effect(effect.effectSupplier(), effect.probability());
        }


        return foodBuilder.build();
    }
    public static List<Optional<Item>> getIngredientsOfSandwich(ItemStack stack) {
        List<Optional<Item>> list = new ArrayList<>(3);
        if (stack.has(NeoDataComponents.SANDWICH_BREAD)) {
            list.add(Optional.ofNullable(stack.get(NeoDataComponents.SANDWICH_BREAD)));
        }
        if (stack.has(NeoDataComponents.SANDWICH_MEAT)) {
            list.add(Optional.ofNullable(stack.get(NeoDataComponents.SANDWICH_MEAT)));
        }
        if (stack.has(NeoDataComponents.SANDWICH_CHEESE)) {
            list.add(Optional.ofNullable(stack.get(NeoDataComponents.SANDWICH_CHEESE)));
        }
        return list;
    }
}
