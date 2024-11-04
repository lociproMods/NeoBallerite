package com.locipro.neoballerite.item;

import com.locipro.neoballerite.NeoBallerite;
import com.locipro.neoballerite.component.NeoDataComponents;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import java.util.*;

public class NeoSandwiches {
    public static final Set<Item> BREAD_MAP = new LinkedHashSet<>(List.of(
            Items.BREAD,
            ModItems.CORN_BREAD_SLICE.get()));
    public static final Set<Item> MEAT_MAP = new LinkedHashSet<>(List.of(
            Items.COOKED_BEEF,
            Items.COOKED_PORKCHOP,
            Items.COOKED_MUTTON,
            Items.COOKED_CHICKEN,
            Items.COOKED_RABBIT,
            Items.COOKED_COD,
            Items.COOKED_SALMON
    ));
    public static final Set<Item> CHEESE_MAP = new LinkedHashSet<>(List.of(
            ModItems.MILK_CHEESE.get(),
            ModItems.WARPED_CHEESE.get()
    ));

    // Nuh uh, doesn't account for bread-cheese or bread-warpedcheese
    //public static final int POSSIBLE_SANDWICH_PERMUTATIONS = BREAD_MAP.size() * MEAT_MAP.size() * CHEESE_MAP.size();
    // Bread map size is a common factor yo!
    /*public static final int POSSIBLE_SANDWICH_PERMUTATIONS =
                     BREAD_MAP.size() * MEAT_MAP.size() +
                    BREAD_MAP.size() * CHEESE_MAP.size() +
                    BREAD_MAP.size() * MEAT_MAP.size() * CHEESE_MAP.size();*/

    public static final int POSSIBLE_SANDWICH_PERMUTATIONS =
            BREAD_MAP.size() * (
                    MEAT_MAP.size()) +
                    CHEESE_MAP.size() +
                    MEAT_MAP.size() * CHEESE_MAP.size(); // Checks out!
    public static List<ItemStack> POSSIBLE_SANDWICHES = new ArrayList<>(POSSIBLE_SANDWICH_PERMUTATIONS);

    /*public static void makeAllSandwichStacks() {
        NeoBallerite.LOGGER.info("ATTEMPTING TO MAKE ALL {} SANDWICH PERMUTATIONS", POSSIBLE_SANDWICH_PERMUTATIONS);
        int count = 0;
        for (int i = 0; i < BREAD_MAP.size(); i++) {
            Item breadItem = getKey(BREAD_MAP, (float) i + 1); // Starts from index 0, But map is 1 indexed so we add 1. (Why? kys) ((ItemProperties can be 0, so there isn't anything stopping me from making the maps 0 indexed other than the fact that it's gonna be un-intuitive to read the model jsons.))
            for (int j = 0; j < MEAT_MAP.size(); j++) {
                Item meatItem = getKey(MEAT_MAP, (float) j + 1); // Now this should be cached. (figure it out later)
                for (int k = 0; k < CHEESE_MAP.size(); k ++) {
                    Item cheeseItem = getKey(CHEESE_MAP, (float) k + 1);
                    ItemStack sandwich = new ItemStack(ModItems.SANDWICH.get(), 1);
                    sandwich.set(NeoDataComponents.SANDWICH_BREAD, breadItem);
                    sandwich.set(NeoDataComponents.SANDWICH_MEAT, meatItem);
                    sandwich.set(NeoDataComponents.SANDWICH_CHEESE, cheeseItem);
                    count = POSSIBLE_SANDWICHES.add(sandwich) ? count + 1 : count;
                }
                ItemStack sandwich = new ItemStack(ModItems.SANDWICH.get(), 1);
                sandwich.set(NeoDataComponents.SANDWICH_BREAD, breadItem);
                sandwich.set(NeoDataComponents.SANDWICH_MEAT, meatItem);
                count = POSSIBLE_SANDWICHES.add(sandwich) ? count + 1 : count;
            }
            for (int k = 0; k < CHEESE_MAP.size(); k ++) {
                Item cheeseItem = getKey(CHEESE_MAP, (float) k + 1);
                ItemStack sandwich = new ItemStack(ModItems.SANDWICH.get(), 1);
                sandwich.set(NeoDataComponents.SANDWICH_BREAD, breadItem);
                sandwich.set(NeoDataComponents.SANDWICH_CHEESE, cheeseItem);
                count = POSSIBLE_SANDWICHES.add(sandwich) ? count + 1 : count;
            }
        }
        if (count != POSSIBLE_SANDWICH_PERMUTATIONS) {
            NeoBallerite.LOGGER.error("FAILURE TO INSTANTIATE ALL {} SANDWICH COMBOS.", POSSIBLE_SANDWICH_PERMUTATIONS);
        }else {
            NeoBallerite.LOGGER.info("SUCCESSFULLY INITIALIZED ALL {} SANDWICH COMBOS", POSSIBLE_SANDWICH_PERMUTATIONS);
        }
    }*/

    public static ArrayList<ItemStack> getAllSandwiches(int possiblePermutations) {
        ArrayList<ItemStack> res = new ArrayList<>(possiblePermutations);
        int count = 0;

        int bread;
        int meat;
        int cheese;

        // Hope this works...
        for (int i = 0; i < BREAD_MAP.size(); i++) {
            bread = i + 1;
            for (int j = 0; j <= MEAT_MAP.size(); j++) {
                meat = j;
                for (int k = 0; k <= CHEESE_MAP.size(); k ++) {
                    cheese = k;
                    ItemStack constructed = makeSandwichFromProperties(bread, meat, cheese);
                    if (res.contains(constructed)) {
                        throw new IllegalStateException("TRIED MAKING A SANDWICH INSTANCE WHICH ALREADY EXISTS IN THE LIST.");
                    }
                    if (constructed != ItemStack.EMPTY) {
                        count = res.add(makeSandwichFromProperties(bread, meat, cheese)) ? count + 1 : count;
                    }
                }
            }
        }

        // You goofball.. What a stupid hard-coded bodge.
        /*// The first is bread and no meat no cheese.
        res.removeFirst();
        count--;*/


        if (count != possiblePermutations) {
            NeoBallerite.LOGGER.error("FAILURE TO INSTANTIATE ALL {} SANDWICH COMBOS. current count : {}", possiblePermutations, count);
            return res;
        }
        NeoBallerite.LOGGER.info("SUCCESSFULLY INITIALIZED ALL {} SANDWICH COMBOS", possiblePermutations);
        return res;
    }

    public static ItemStack makeSandwichFromProperties(int bread, int meat, int cheese) {
        ItemStack stack = new ItemStack(ModItems.SANDWICH.get(), 1);
        if (bread != 0 && meat == 0 && cheese == 0) {
            return ItemStack.EMPTY;
        }

        if (bread != 0) {
            stack.set(NeoDataComponents.SANDWICH_BREAD, get(BREAD_MAP, bread - 1));
        }
        if (meat != 0) {
            stack.set(NeoDataComponents.SANDWICH_MEAT, get(MEAT_MAP, meat - 1));
        }
        if (cheese != 0) {
            stack.set(NeoDataComponents.SANDWICH_CHEESE, get(CHEESE_MAP, cheese - 1));
        }
        return stack;
    }

    private static <K, V> K getKey(Map<K, V> map, V value) {
        for (Map.Entry<K, V> entry : map.entrySet()) {
            if (entry.getValue().equals(value)) {
                return entry.getKey();
            }
        }
        return null;
    }
    // Prolly should make a util class and make my implementation of set have these
    public static <T> int getIndex(Set<T> set, T element) {
        if (set.contains(element)) {
            int result = 0;
            for (var x : set) {
                if (x.equals(element)) {
                    return result;
                }
                result++;
            }
        }
        return -1;
    }
    public static <T> T get(Set<T> set, int index) {
        List<T> list = new ArrayList<>(set);
        return list.get(index);
    }

    public static void init() {
        POSSIBLE_SANDWICHES = getAllSandwiches(POSSIBLE_SANDWICH_PERMUTATIONS);
    }
}
