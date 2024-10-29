package com.locipro.neoballerite.item;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

import java.util.HashMap;
import java.util.Map;

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

}
