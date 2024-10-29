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
import org.antlr.v4.runtime.misc.Triple;
import org.apache.commons.lang3.ObjectUtils;
import org.jetbrains.annotations.NotNull;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Predicate;

public class SandwichRecipe extends CustomRecipe {


    private static final Predicate<ItemStack> pBREAD = stack -> NeoSandwiches.BREAD_MAP.containsKey(stack.getItem());
    private static final Predicate<ItemStack> pMEAT = stack -> NeoSandwiches.MEAT_MAP.containsKey(stack.getItem());
    private static final Predicate<ItemStack> pCHEESE = stack -> NeoSandwiches.CHEESE_MAP.containsKey(stack.getItem());
    private static final Predicate<ItemStack> pKNIFE = stack -> stack.is(ModTags.Items.KNIVES);

    private static final List<Predicate<ItemStack>> MATCH_PREDICATE = List.of(
            pBREAD, pMEAT, pCHEESE
    );



    public SandwichRecipe(CraftingBookCategory category) {
        super(category);
    }
    private static boolean canAddToSandwich(CraftingInput input) {
        List<ItemStack> sandwiches = NeoRecipeHelper.findItems(input, List.of(stack -> stack.is(ModItems.DEFAULT_SANDWICH)));
        if (!sandwiches.isEmpty()) {
            ItemStack sandwich = sandwiches.getFirst();
            if (!sandwich.has(NeoDataComponents.SANDWICH_BREAD)) {
                // If the sandwich doesn't have bread, then it doesn't exist (hasn't been crafted legally)
                return false;
            }
            // Simplify if statement? No! Way more readable this way.
            if (sandwich.has(NeoDataComponents.SANDWICH_CHEESE) && sandwich.has(NeoDataComponents.SANDWICH_MEAT)) {
                // If the sandwich already has both cheese AND meat then it can't be added to.
                return false;
            }
            return true;
        }
        return false;
    }
    private static boolean inputHasSandwich(CraftingInput input) {
        return NeoRecipeHelper.anyPresent(input, List.of(stack -> stack.is(ModItems.DEFAULT_SANDWICH)));
    }

    // Sandwich, bread, meat, cheese, in that order.
    private static LinkedList<ItemStack> getItemsToCombine(CraftingInput input) {
        LinkedList<ItemStack> output = new LinkedList<>();
        if (inputHasSandwich(input)) {
            if (canAddToSandwich(input)) {
                // Todo
                return null;
            }
            return null;
        }


        // No sandwich present
        output.addFirst(null);

        // FIND ITEMS IS NOT WORKING? TODO THIS IS THE PROBLEM. UGH
        List<ItemStack> inputs = NeoRecipeHelper.findItems(input, MATCH_PREDICATE);

        for (ItemStack stack : inputs) {
            if (output.get(1) == null && pBREAD.test(stack)) {
                output.add(1, stack);
            }
            if (output.get(2) == null && pMEAT.test(stack)) {
                output.add(2, stack);
            }
            if (output.get(3) == null && pCHEESE.test(stack)) {
                output.add(3, stack);
            }
        }


        NeoBallerite.LOGGER.debug("getItemsToCombine output is {}", output.isEmpty() ? null : output);
        output.iterator().forEachRemaining(
                stack -> NeoBallerite.LOGGER.debug("output :{} and size {}", stack, output.size())
        );
        return output.isEmpty() ? null : output;
    }

    @Override
    public boolean matches(CraftingInput input, Level level) {
        boolean hasKnife = NeoRecipeHelper.anyPresent(input, List.of(pKNIFE));


        return hasKnife
            && getItemsToCombine(input) != null;
    }

    @Override
    public ItemStack assemble(CraftingInput input, HolderLookup.Provider registries) {
        return ModItems.DEFAULT_SANDWICH.get().getDefaultInstance();
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
