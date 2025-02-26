package com.locipro.neoballerite.recipe;

import com.locipro.neoballerite.NeoBallerite;
import com.locipro.neoballerite.component.NeoDataComponents;
import com.locipro.neoballerite.item.ModItems;
import com.locipro.neoballerite.item.NeoSandwiches;
import com.locipro.neoballerite.util.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.world.item.ItemStack;
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


    private static final Predicate<ItemStack> pBREAD = stack -> NeoSandwiches.BREAD_MAP.contains(stack.getItem());
    private static final Predicate<ItemStack> pMEAT = stack -> NeoSandwiches.MEAT_MAP.contains(stack.getItem());
    private static final Predicate<ItemStack> pCHEESE = stack -> NeoSandwiches.CHEESE_MAP.contains(stack.getItem());
    private static final Predicate<ItemStack> pKNIFE = stack -> stack.is(ModTags.Items.KNIVES);
    private static final Predicate<ItemStack> pSANDWICH = stack -> stack.is(ModItems.SANDWICH);

    private static final List<Predicate<ItemStack>> MATCH_PREDICATE = List.of(
            pBREAD, pMEAT, pCHEESE, pSANDWICH
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
            if (output.getFirst().isEmpty() && pSANDWICH.test(stack)) {
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
            ItemStack sandwich = output.getFirst().get().copy();
            if (output.get(2).isPresent() && sandwich.has(NeoDataComponents.SANDWICH_MEAT)) {
                return null;
            }
            if (output.get(3).isPresent() && sandwich.has(NeoDataComponents.SANDWICH_CHEESE)) {
                return null;
            }
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
        boolean dimensionsMatch = input.width() * input.height() >= 4;

        return hasKnife
            && getItemsToCombine(input) != null
                && dimensionsMatch;
    }

    @Override
    public @NotNull ItemStack assemble(CraftingInput input, HolderLookup.Provider registries) {
        List<Optional<ItemStack>> items = getItemsToCombine(input);
        assert items != null;

        ItemStack output = ModItems.SANDWICH.get().getDefaultInstance();


        if (items.getFirst().isPresent()) {
            assert canAddToSandwich(items.getFirst().get());
            ItemStack sandwich = items.getFirst().get().copy();
            // Because of getItemsToCombine filtering, it either doesn't have this, or that.
            // If it doesn't have meat, then there HAS to be cheese in the list.
            if (sandwich.has(NeoDataComponents.SANDWICH_MEAT)) {
                sandwich.set(NeoDataComponents.SANDWICH_CHEESE, items.get(3).get().getItem());
            }
            else {
                sandwich.set(NeoDataComponents.SANDWICH_MEAT, items.get(2).get().getItem());
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

        return output;

    }


    @Override
    public @NotNull RecipeSerializer<? extends CustomRecipe> getSerializer() {
        return NeoRecipeSerializers.SANDWICH_RECIPE_SERIALIZER.get();
    }
}