package com.locipro.neoballerite.recipe;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingInput;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;
import java.util.function.Predicate;

// unused.
public class NeoRecipeHelper {
    /** Thank you TeamPneuamatic **/
    public static List<ItemStack> findItems(CraftingInput inv, List<Predicate<ItemStack>> predicates) {
        List<ItemStack> res = new ArrayList<>();
        BitSet matchedSlots = new BitSet(inv.size());

        for (var pred : predicates) {
            boolean found = false;
            for (int i = 0; i < inv.size(); i++) {
                if (!matchedSlots.get(i)) {
                    ItemStack stack = inv.getItem(i);
                    if (pred.test(stack)) {
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
}
/* Ingredient.of(new ItemStack(Items.DIAMOND_SWORD)) returns an ingredient that accepts an item stack. Be aware that counts and data components are ignored.*/