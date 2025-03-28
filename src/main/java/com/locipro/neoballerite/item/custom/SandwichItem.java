package com.locipro.neoballerite.item.custom;


import com.locipro.neoballerite.component.NeoDataComponents;
import com.locipro.neoballerite.item.NeoSandwiches;
import com.locipro.neoballerite.item.util.FoodMath;
import com.mojang.datafixers.util.Pair;
import net.minecraft.ChatFormatting;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUseAnimation;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.Consumable;
import net.minecraft.world.item.consume_effects.ApplyStatusEffectsConsumeEffect;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

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

    @Override
    public InteractionResult use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        int countOfIngredients = 0;
        var listOfIngredients = getIngredientsOfSandwich(stack);
        for (var o : listOfIngredients) if (o.isPresent()) countOfIngredients++;

        Pair<FoodProperties, Consumable.Builder> combinedComponents = getCombinedProperties(listOfIngredients);
        stack.set(DataComponents.FOOD, combinedComponents.getFirst());

        Consumable.Builder consumableBuilder = getConsumableBuilder(combinedComponents, countOfIngredients);
        stack.set(DataComponents.CONSUMABLE, consumableBuilder.build());
        return super.use(level, player, hand);
    }

    private static Consumable.Builder getConsumableBuilder(Pair<FoodProperties, Consumable.Builder> combinedComponents, int countOfIngredients) {
        Consumable.Builder consumableBuilder = combinedComponents.getSecond();
        switch (countOfIngredients) {
            case 2:
                consumableBuilder.onConsume(
                        new ApplyStatusEffectsConsumeEffect(
                                new MobEffectInstance(MobEffects.ABSORPTION, 400)
                        )
                );
            case 3:
                consumableBuilder.onConsume(
                        new ApplyStatusEffectsConsumeEffect(
                                List.of(
                                        new MobEffectInstance(MobEffects.ABSORPTION, 1200, 1),
                                        new MobEffectInstance(MobEffects.REGENERATION, 800, 1)
                                )
                        )
                );
        }
        return consumableBuilder;
    }

    /*
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
    }*/


    @Override
    public @NotNull ItemUseAnimation getUseAnimation(ItemStack stack) {
        return ItemUseAnimation.EAT;
    }

    /**
        BEWARE : BPD CODING STYLE
     **/
    @Override
    public @NotNull Component getName(ItemStack stack) {
        // Resolve existing translation keys first.
        Component currentComponent = Component.translatable(getDescriptionId());
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



    public static Pair<FoodProperties, Consumable.Builder> getCombinedProperties(List<Optional<Item>> items) {
        List<FoodProperties> properties = new ArrayList<>(items.size());
        List<Consumable> consumables = new ArrayList<>(items.size());

        for (var item : items) {
            if (item.isPresent()) {
                FoodProperties p = item.get().components().get(DataComponents.FOOD);
                if (p != null) properties.add(p);

                Consumable c = item.get().components().get(DataComponents.CONSUMABLE);
                if (c != null) consumables.add(c);
            }
        }

        int nutrition = 0;
        float saturation = 0;
        for (var p : properties) {
            if (p != null) {
                nutrition += p.nutrition();
                saturation += p.saturation();
            }
        }


        FoodProperties.Builder foodBuilder = new FoodProperties.Builder()
                .nutrition(nutrition)
                .saturationModifier(FoodMath.saturationModifierFromSaturationAndNutrition(saturation, nutrition));

        Consumable.Builder consumableBuilder = Consumable.builder().consumeSeconds(1.0f);
        for (var c : consumables) {
            if (c != null && !c.onConsumeEffects().isEmpty()) {
                for (var consumeEffect : c.onConsumeEffects()) {
                    consumableBuilder.onConsume(consumeEffect);
                }
            }
        }
        return Pair.of(foodBuilder.build(), consumableBuilder);

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
