package com.locipro.neoballerite.recipe;

import com.locipro.neoballerite.NeoBallerite;
import com.locipro.neoballerite.item.NeoSandwiches;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingInput;
import net.minecraft.world.item.crafting.RecipeInput;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;
import java.util.function.Predicate;

// unused.
public class NeoRecipeHelper {
    /** Thank you TeamPneuamatic **/
    public static List<ItemStack> findItems(RecipeInput inv, List<Predicate<ItemStack>> predicates) {

        List<ItemStack> res = new ArrayList<>();
        BitSet matchedSlots = new BitSet(inv.size());

        // Change to pred : predicates
        for (var pred : predicates) {
            boolean found = false;
            for (int i = 0; i < inv.size(); i++) {
                if (!matchedSlots.get(i)) {
                    ItemStack stack = inv.getItem(i);
                    if (pred.test(stack)) { // Predicate always fails for some reason.
                        res.add(stack);
                        matchedSlots.set(i);
                        found = true;
                        break;
                    }
                }
            }
            if (!found) return List.of();
        }

        // check any unmatched slots for extraneous items
        for (int i = 0; i < inv.size(); i++) {
            if (!matchedSlots.get(i) && !inv.getItem(i).isEmpty()) return List.of();
        }

        return res;
    }
    public static boolean allPresent(CraftingInput inv, List<Predicate<ItemStack>> predicates) {
        return findItems(inv, predicates).size() == predicates.size();
    }
    public static boolean anyPresent(CraftingInput inv, List<Predicate<ItemStack>> predicates) {
        for (var pred : predicates) {
            for (int i = 0; i < inv.size(); i++) {
                if (pred.test(inv.getItem(i))) {
                    return true;
                }
            }
        }
        return false;
    }
}
/* Ingredient.of(new ItemStack(Items.DIAMOND_SWORD)) returns an ingredient that accepts an item stack. Be aware that counts and data components are ignored.*/