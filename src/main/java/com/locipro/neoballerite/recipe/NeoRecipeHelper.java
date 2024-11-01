package com.locipro.neoballerite.recipe;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingInput;
import net.minecraft.world.item.crafting.RecipeInput;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;
import java.util.function.Predicate;

// unused.
public class NeoRecipeHelper {
    // Thank you TeamPneuamatic
    // Modified to fit my needs
    /** Returns ALL items that match predicates in the crafting input. **/
    public static List<ItemStack> findAllItems(RecipeInput inv, List<Predicate<ItemStack>> predicates) {

        List<ItemStack> res = new ArrayList<>();
        BitSet matchedSlots = new BitSet(inv.size());


        for (var pred : predicates) {
            for (int i = 0; i < inv.size(); i++) {
                if (!matchedSlots.get(i)) { // If the item hasn't already been added.
                    ItemStack stack = inv.getItem(i);
                    if (pred.test(stack)) {
                        res.add(stack);
                        matchedSlots.set(i);
                        break;
                    }
                }
            }
        }


        return res;
    }

    // Thank you TeamPneuamatic
    // Modified to fit my needs
    /** Returns items that match predicates, but returns nothing if there are extra items. **/
    public static List<ItemStack> findItems(RecipeInput inv, List<Predicate<ItemStack>> predicates) {

        List<ItemStack> res = new ArrayList<>();
        BitSet matchedSlots = new BitSet(inv.size());


        for (var pred : predicates) {
            boolean found = false;
            for (int i = 0; i < inv.size(); i++) {
                if (!matchedSlots.get(i)) { // If the item hasn't already been added.
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

    // Thank you TeamPneuamatic
    // Modified to fit my needs
    /** Returns all items that match predicates in the crafting input, returning nothing if there are extra items..
     * Has an allowed predicate which lets items of a certain type be ignored when filtering
     * These won't be returned in the list, but won't cause the function to return nothing if found.**/
    public static List<ItemStack> findItemsWithAllowed(RecipeInput inv, List<Predicate<ItemStack>> predicates, List<Predicate<ItemStack>> ignoredPredicates) {

        List<ItemStack> res = new ArrayList<>();
        BitSet matchedSlots = new BitSet(inv.size());


        for (var pred : predicates) {
            //boolean found = false;
            for (int i = 0; i < inv.size(); i++) {
                if (!matchedSlots.get(i)) { // If the item hasn't already been added.
                    ItemStack stack = inv.getItem(i);
                    if (pred.test(stack)) { // Predicate always fails for some reason.
                        res.add(stack);
                        matchedSlots.set(i);
                        //found = true;
                        break;
                    }
                }
            }
            //if (!found) return List.of();
        }

        // check any unmatched slots for extraneous items ignoring allowed predicates
        for (int i = 0; i < inv.size(); i++) {
            for (var pred : ignoredPredicates) {
                if (!matchedSlots.get(i)
                        && !inv.getItem(i).isEmpty()
                        && !pred.test(inv.getItem(i))) return List.of();
            }
        }
        return res;
    }
    public static boolean allPresent(CraftingInput inv, List<Predicate<ItemStack>> predicates) {
        return findAllItems(inv, predicates).size() == predicates.size();
    }
    public static boolean anyPresent(CraftingInput inv, List<Predicate<ItemStack>> predicates) {
        return !findAllItems(inv, predicates).isEmpty();
    }
}
/* Ingredient.of(new ItemStack(Items.DIAMOND_SWORD)) returns an ingredient that accepts an item stack. Be aware that counts and data components are ignored.*/