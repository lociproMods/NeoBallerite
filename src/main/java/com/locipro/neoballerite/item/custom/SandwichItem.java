package com.locipro.neoballerite.item.custom;


import com.locipro.neoballerite.NeoBallerite;
import com.locipro.neoballerite.component.NeoDataComponents;
import com.locipro.neoballerite.item.NeoSandwiches;
import com.locipro.neoballerite.item.util.FoodMath;
import net.minecraft.ChatFormatting;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
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


public class SandwichItem extends Item {
    private final static int MAX_SANDWICH_EFFECTS = 4;

    public SandwichItem() {
        super(new Item.Properties());
    }



    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        String bread = "";
        String meat = "";
        String cheese = "";

        for (var oIngredient : getIngredientsOfSandwich(stack)) {
            if (oIngredient.isPresent()) {
                Item ingredient = oIngredient.get();
                if (NeoSandwiches.BREAD_MAP.contains(ingredient)) {
                    bread = ingredient.getName(ingredient.getDefaultInstance()).getString();
                }
                if (NeoSandwiches.MEAT_MAP.contains(ingredient)) {
                    meat = ingredient.getName(ingredient.getDefaultInstance()).getString();
                }
                if (NeoSandwiches.CHEESE_MAP.contains(ingredient)) {
                    cheese = ingredient.getName(ingredient.getDefaultInstance()).getString();
                }
            }
        }
        if (!bread.isEmpty()) {
            tooltipComponents.add(Component.literal("Bread type : " + bread).withStyle(
                    ChatFormatting.GRAY, ChatFormatting.ITALIC
            ));
        }
        if (!meat.isEmpty()) {
            tooltipComponents.add(Component.literal("Meat type : " + meat).withStyle(
                    ChatFormatting.GRAY, ChatFormatting.ITALIC
            ));
        }
        if (!cheese.isEmpty()) {
            tooltipComponents.add(Component.literal("Cheese type : " + cheese).withStyle(
                    ChatFormatting.GRAY, ChatFormatting.ITALIC));
        }


        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
    }

    // Calculate food here broooo.
    @Override
    public @Nullable FoodProperties getFoodProperties(ItemStack stack, @Nullable LivingEntity entity) {
        var listOfIngredients = getIngredientsOfSandwich(stack);
        int count = 0;
        for (var o : listOfIngredients) {
            if (o.isPresent()) {
                count++;
            }
        }

        FoodProperties.Builder builder = getCombinedFoodPropertiesBuilder(listOfIngredients, entity);
        switch (count) {
            case 2:
                builder.effect(() -> new MobEffectInstance(MobEffects.ABSORPTION, 400), 1.0f);
            case 3:
                builder.effect(() -> new MobEffectInstance(MobEffects.ABSORPTION, 1200, 1), 1.0f);
                builder.effect(() -> new MobEffectInstance(MobEffects.REGENERATION, 800, 1), 1.0f);
        }


        return builder.build();
    }

    @Override
    public @NotNull UseAnim getUseAnimation(ItemStack stack) {
        return UseAnim.EAT;
    }

    /**
        BEWARE : BPD CODING STYLE
     **/
    @Override
    public Component getName(ItemStack stack) {
        // Resolve existing translation keys first.
        Component currentComponent = Component.translatable(getDescriptionId(stack));
        if (!currentComponent.getString().startsWith("item.")) {
            return currentComponent;
        }
        /*if (!Component.translatable(getDescriptionId(stack)).getString().isEmpty()) {
            return Component.literal(Component.translatable(getDescriptionId(stack)).getString());
        }*/
        // Will never be empty, because if there's no translation value
        // it defaults to a string literal of the translation key.
        // We have to check another way.


        List<Optional<Item>> ingredients = getIngredientsOfSandwich(stack);

        String name = "";

        String bread = "";
        String meat = "";
        String cheese = "";

        boolean defaultBread = false;

        for (var oIngredient : ingredients) {
            if (oIngredient.isPresent()) {
                Item ingredient = oIngredient.get();
                if (NeoSandwiches.BREAD_MAP.contains(ingredient)) {
                    bread = ingredient.getName(ingredient.getDefaultInstance()).getString();
                    // If it's the first bread, or DEFAULT bread
                    if (NeoSandwiches.getIndex(NeoSandwiches.BREAD_MAP, ingredient) == 0) {
                        defaultBread = true;
                    }
                }
                if (NeoSandwiches.MEAT_MAP.contains(ingredient)) {
                    meat = ingredient.getName(ingredient.getDefaultInstance()).getString()
                            .replace("Cooked ", "");
                }
                if (NeoSandwiches.CHEESE_MAP.contains(ingredient)) {
                    cheese = ingredient.getName(ingredient.getDefaultInstance()).getString();
                }
            }
        }

        // this is some weird ass style bro
        if (defaultBread) {
            name = "Sandwich O' ";
            if (!meat.isEmpty() && cheese.isEmpty()) {
                name += meat;
            }
            else if (!cheese.isEmpty() && meat.isEmpty()) {
                name += cheese;
            }else {
                name += cheese + " " + meat;
            }
            return Component.literal(name);
        }


        // why are you coding like this
        if (!cheese.isEmpty()) {
            name += cheese + " ";
        }
        if (!meat.isEmpty()) {
            name += meat + " ";
        }
        if (!bread.isEmpty()) {
            // how do I translate ON to every possible language? fuck it yall gotta cope. Pain au chocolat type shi.
            name += "on " + bread;
        }
        return Component.literal(name);
    }



    // Just in case you want it ig
    @Override
    public @NotNull String getDescriptionId(ItemStack stack) {
        String id = "item." + NeoBallerite.MODID + ".sandwich.";


        Optional<Item> bread = Optional.ofNullable(stack.get(NeoDataComponents.SANDWICH_BREAD));
        Optional<Item> meat =  Optional.ofNullable(stack.get(NeoDataComponents.SANDWICH_MEAT));
        Optional<Item> cheese = Optional.ofNullable(stack.get(NeoDataComponents.SANDWICH_CHEESE));

        if (bread.isPresent()) {
            id += BuiltInRegistries.ITEM.getKey(bread.get()).getPath().replace('/', '.');
        }
        if (meat.isPresent()) {
            id += "_" + BuiltInRegistries.ITEM.getKey(meat.get()).getPath().replace('/', '.');
        }
        if (cheese.isPresent()) {
            id += "_" + BuiltInRegistries.ITEM.getKey(cheese.get()).getPath().replace('/', '.');
        }
        //NeoBallerite.LOGGER.debug("id is {}", id);

        return id;
    }


    // Returns a FoodProperties instance that has the combined properties of all present items. (Uses default ItemStack.)
    public static FoodProperties.Builder getCombinedFoodPropertiesBuilder(List<Optional<Item>> items, LivingEntity entity) {
        // Initialize a list with empty optionals
        List<Optional<FoodProperties>> properties = new ArrayList<>(items.size());
        for (var ignored : items) {
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


        return foodBuilder;
    }
    /* Returns the sandwiches texture path (sandwich_bread_meat_cheese)*/
    public static String getPath(ItemStack sandwich) {
        String path = "sandwich_";


        Optional<Item> bread = Optional.ofNullable(sandwich.get(NeoDataComponents.SANDWICH_BREAD));
        Optional<Item> meat =  Optional.ofNullable(sandwich.get(NeoDataComponents.SANDWICH_MEAT));
        Optional<Item> cheese = Optional.ofNullable(sandwich.get(NeoDataComponents.SANDWICH_CHEESE));

        if (bread.isPresent()) {
            path += BuiltInRegistries.ITEM.getKey(bread.get()).getPath().replace('/', '.');
        }
        if (meat.isPresent()) {
            path += "_" + BuiltInRegistries.ITEM.getKey(meat.get()).getPath().replace('/', '.');
        }
        if (cheese.isPresent()) {
            path += "_" + BuiltInRegistries.ITEM.getKey(cheese.get()).getPath().replace('/', '.');
        }

        return path;
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
