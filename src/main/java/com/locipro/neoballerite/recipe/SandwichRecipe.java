package com.locipro.neoballerite.recipe;

import com.locipro.neoballerite.NeoBallerite;
import com.locipro.neoballerite.component.NeoDataComponents;
import com.locipro.neoballerite.item.ModItems;
import com.locipro.neoballerite.item.NeoSandwiches;
import com.locipro.neoballerite.util.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.food.FoodConstants;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.world.item.crafting.CraftingInput;
import net.minecraft.world.item.crafting.CustomRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;
import java.util.function.Predicate;

public class SandwichRecipe extends CustomRecipe {


    private static final Predicate<ItemStack> pBREAD = stack -> NeoSandwiches.BREAD_MAP.containsKey(stack.getItem());
    private static final Predicate<ItemStack> pMEAT = stack -> NeoSandwiches.MEAT_MAP.containsKey(stack.getItem());
    private static final Predicate<ItemStack> pCHEESE = stack -> NeoSandwiches.CHEESE_MAP.containsKey(stack.getItem());
    private static final Predicate<ItemStack> pKNIFE = stack -> stack.is(ModTags.Items.KNIVES);
    private static final Predicate<ItemStack> pSandwich = stack -> stack.is(ModItems.SANDWICH);

    private static final List<Predicate<ItemStack>> MATCH_PREDICATE = List.of(
            pBREAD, pMEAT, pCHEESE, pSandwich
    );


    public SandwichRecipe(CraftingBookCategory category) {
        super(category);
    }

    // [oSandwich, oBread, oMeat, oCheese]
    @Nullable
    private static List<Optional<ItemStack>> getItemsToCombine(CraftingInput input) {
        List<Optional<ItemStack>> output = new ArrayList<>(4);
        for (int i = 0; i < 4; i ++) {
            output.add(i, Optional.empty());
        }

        // Get all items.
        List<ItemStack> inputs = NeoRecipeHelper.findItemsWithAllowed(input, MATCH_PREDICATE, List.of(pKNIFE));
        if (inputs.isEmpty()) {
            return null;
        }

        // Set indexes of list.
        for (ItemStack stack : inputs) {
            if (output.getFirst().isEmpty() && pSandwich.test(stack)) {
                output.set(0, Optional.ofNullable(stack));
            }
            else if (output.get(1).isEmpty() && pBREAD.test(stack)) {
                output.set(1, Optional.ofNullable(stack));
            }
            else if (output.get(2).isEmpty() && pMEAT.test(stack)) {
                output.set(2, Optional.ofNullable(stack));
            }
            else if (output.get(3).isEmpty() && pCHEESE.test(stack)) {
                output.set(3, Optional.ofNullable(stack));
            }
        }




        // Return list based on valid conditions.


        // Got neither bread nor sandwich? bye!
        if (output.getFirst().isEmpty() && output.get(1).isEmpty()) {
            return null;
        }

        // If you have a sandwich, and it can't be added to, fuck off.
        if (output.getFirst().isPresent() && !canAddToSandwich(output.getFirst().get())) {
            return null;
        }

        // If sandwich is found, delete bread. Just in case.
        if (output.getFirst().isPresent()) {
            output.set(1, Optional.empty());
        }

        int presentItems = 0;
        for (var x : output) {
            if (x.isPresent()) {
                presentItems++;
            }
        }
        // If there are less than 2 items, what are we making dude...
        return presentItems < 2 ? null : output;
    }

    private static boolean canAddToSandwich(ItemStack sandwich) {
        if (!sandwich.has(NeoDataComponents.SANDWICH_BREAD)) {
            // If the sandwich doesn't have bread, then it doesn't exist (hasn't been crafted legally)
            NeoBallerite.LOGGER.error("Tried using an illegally obtained sandwich in crafting");
            return false;
        }
        // Simplify if statement? No! Way more readable this way.
        if (sandwich.has(NeoDataComponents.SANDWICH_CHEESE) && sandwich.has(NeoDataComponents.SANDWICH_MEAT)) {
            // If the sandwich already has both cheese AND meat then it can't be added to.
            return false;
        }
        return true;
    }



    @Override
    public boolean matches(CraftingInput input, Level level) {
        boolean hasKnife = NeoRecipeHelper.anyPresent(input, List.of(pKNIFE));

        return hasKnife
            && getItemsToCombine(input) != null;
    }

    @Override
    public @NotNull ItemStack assemble(CraftingInput input, HolderLookup.Provider registries) {
        List<Optional<ItemStack>> items = getItemsToCombine(input);
        assert items != null;
        ArrayList<Optional<FoodProperties>> itemFood = new ArrayList<>(items.size());
        for (var x : items) {
            itemFood.add(Optional.empty());
        }

        // Get food properties where applicable.
        for (int i = 0; i < items.size(); i ++) {
            if (items.get(i).isPresent()) {
                itemFood.add(i, Optional.ofNullable(items.get(i).get().get(DataComponents.FOOD)));
            }
        }
        List<FoodProperties.PossibleEffect> effects = new ArrayList<>(itemFood.size());
        int nutrition = 0;
        float saturation = 0f;
        // This is SATURATION, not saturationModifier.
        // Have to calculate modifier later in builder.

        // Add food properties.
        for (var x : itemFood) {
            if (x.isPresent()) {
                var properties = x.get();
                nutrition += properties.nutrition();
                saturation += properties.saturation();
                if (!properties.effects().isEmpty()) {
                    effects.addAll(properties.effects());
                }
            }
        }

        ItemStack output = ModItems.SANDWICH.get().getDefaultInstance();


        if (items.getFirst().isPresent()) {
            assert canAddToSandwich(items.getFirst().get());
            ItemStack sandwich = items.getFirst().get().copy();
            // BUGGED, if you have a sandwich with bread and beef
            // and you try adding beef, it fails (good)
            // if you try adding cheese, it works (good)
            // If you REPLACE cheese with beef it still thinks you're adding cheese? (wtf)
            if (sandwich.has(NeoDataComponents.SANDWICH_CHEESE)) {
                sandwich.set(NeoDataComponents.SANDWICH_MEAT, items.get(2).get().getItem()); // .get().get().get() :sob:
            }else {
                sandwich.set(NeoDataComponents.SANDWICH_CHEESE, items.get(3).get().getItem());
            }
            output = sandwich;
        }else {
            // If there's no sandwich to add to
            // Well, since there's no sandwich there HAS to be bread!
            // If there's bread but no meat or cheese, we won't even be able to get here due to getItemsToCombine returning null.
            assert items.get(1).isPresent();
            assert !(items.get(2).isEmpty() && items.get(3).isEmpty());

            output.set(NeoDataComponents.SANDWICH_BREAD, items.get(1).get().getItem());
            if (items.get(2).isPresent()) {
                output.set(NeoDataComponents.SANDWICH_MEAT, items.get(2).get().getItem());
            }
            if (items.get(3).isPresent()) {
                output.set(NeoDataComponents.SANDWICH_CHEESE, items.get(3).get().getItem());
            }
        }





        // Add food. For both cases.
        float saturationModifier = saturation / (2 * (float) nutrition); // Formula from "net.minecraft.world.food.FoodConstants.saturationByModifier", do algebra to find sat modifier based on saturation and nutrition.
        addFoodProperties(nutrition, saturationModifier, effects, output);
        return output;

    }

    private static void addFoodProperties(int nutrition, float saturation, List<FoodProperties.PossibleEffect> effects, ItemStack output) {
        FoodProperties.Builder foodBuilder = new FoodProperties.Builder();
        foodBuilder.nutrition(nutrition);
        foodBuilder.saturationModifier(saturation);
        for (var effect : effects) {
            foodBuilder.effect(effect.effectSupplier(), effect.probability());
        }
        output.set(DataComponents.FOOD, foodBuilder.build());
    }

    @Override
    public boolean canCraftInDimensions(int width, int height) {
        return width * height >= 4;
    }


    @Override
    public @NotNull RecipeSerializer<?> getSerializer() {
        return NeoRecipeSerializers.SANDWICH_RECIPE_SERIALIZER.get();
    }
}
// Working sandwich maker (from bread and ingredients)
/*if (items.get(0) == ItemStack.EMPTY) {
            // Well, since there's no sandwich there HAS to be bread!
            // If there's bread but no meat or cheese... What are we to do?
            if (items.get(1) == ItemStack.EMPTY ||
                    (items.get(2) == ItemStack.EMPTY && items.get(3) == ItemStack.EMPTY)) {
                return ItemStack.EMPTY;
            }

            ItemStack sammich = ModItems.SANDWICH.get().getDefaultInstance();
            FoodProperties.Builder sammichF = new FoodProperties.Builder();

            // TODO make list of mob effect instances to take from items.
            int nutrition = 0;
            float saturation = 0;

            // there HAS to be bread if there's no sandwich, so this will always have something.
            ItemStack bread = items.get(1).copy();
            // There will always be at least one of these
            ItemStack meat = items.get(2).copy() != ItemStack.EMPTY ? items.get(2) : null;
            ItemStack cheese = items.get(3).copy() != ItemStack.EMPTY ? items.get(3) : null;

            FoodProperties breadF = bread.has(DataComponents.FOOD) ? bread.get(DataComponents.FOOD) : null;
            FoodProperties meatF = null;
            FoodProperties cheeseF = null;
            if (meat != null) {
                 meatF = meat.has(DataComponents.FOOD) ? meat.get(DataComponents.FOOD) : null;
            }
            if (cheese != null) {
               cheeseF = cheese.has(DataComponents.FOOD) ? cheese.get(DataComponents.FOOD) : null;
            }


            sammich.set(NeoDataComponents.SANDWICH_BREAD, bread.getItem());
            if (breadF != null) {
                nutrition += breadF.nutrition();
                saturation += breadF.saturation();
            }

            if (meat != null) {
                sammich.set(NeoDataComponents.SANDWICH_MEAT, meat.getItem());
                if (meatF != null) {
                    nutrition += meatF.nutrition();
                    saturation += meatF.saturation();
                }
            }
            if (cheese != null) {
                sammich.set(NeoDataComponents.SANDWICH_CHEESE, cheese.getItem());
                if (cheeseF != null) {
                    nutrition += cheeseF.nutrition();
                    saturation += cheeseF.saturation();
                }
            }

            sammichF.nutrition(nutrition);
            sammichF.saturationModifier(saturation);
            sammich.set(DataComponents.FOOD, sammichF.build());

            return sammich;
        }*/