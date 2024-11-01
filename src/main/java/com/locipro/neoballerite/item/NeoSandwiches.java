package com.locipro.neoballerite.item;

import com.locipro.neoballerite.NeoBallerite;
import com.locipro.neoballerite.component.NeoDataComponents;
import net.minecraft.core.component.DataComponentMap;
import net.minecraft.core.component.DataComponentPatch;
import net.minecraft.core.component.PatchedDataComponentMap;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import org.checkerframework.checker.units.qual.K;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class NeoSandwiches {
    public static final Map<Item, Float> BREAD_MAP = Map.of(
            Items.BREAD, 1f
    );
    public static final Map<Item, Float> MEAT_MAP = Map.of(
            Items.COOKED_BEEF, 1f,
            Items.COOKED_PORKCHOP, 2f,
            Items.COOKED_MUTTON, 3f,
            Items.COOKED_CHICKEN, 4f
    );
    public static final Map<Item, Float> CHEESE_MAP = Map.of(
            ModItems.MILK_CHEESE.get(), 1f,
            ModItems.WARPED_CHEESE.get(), 2f
    );
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

    public static final Set<ItemStack> POSSIBLE_SANDWICHES = new HashSet<>(POSSIBLE_SANDWICH_PERMUTATIONS);

    /** TODO massive stinky optimization **/
    public static void makeAllSandwichStacks() {
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
    }

    private static <K, V> K getKey(Map<K, V> map, V value) {
        for (Map.Entry<K, V> entry : map.entrySet()) {
            if (entry.getValue().equals(value)) {
                return entry.getKey();
            }
        }
        return null;
    }
}
