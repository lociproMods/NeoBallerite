package com.locipro.neoballerite.item.custom;


import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

import java.util.Optional;

/** Currently only a helper class with some variables to assist in recipe generation.
 * Eventually will get dynamic item models to make all sandwiches just an instance of this singleton with some data components.**/
public class SandwichItem extends Item {
    public SandwichItem(Properties properties, Optional<Item> fillerItem, Optional<Item> cheesyFiller) {
        super(properties);
        fillerItem.ifPresent(item -> FILLER_ITEM = item);
        cheesyFiller.ifPresent(item -> CHEESY_FILLER = item);
    }
    private final Item BREAD_ITEM = Items.BREAD;
    private Item FILLER_ITEM;
    private Item CHEESY_FILLER;

    public Item getBreadItem() {
        return BREAD_ITEM;
    }

    public Item getFillerItem() {
        if (FILLER_ITEM != null) {
            return FILLER_ITEM;
        }
        return null;
    }
    public Item getCheesyFillerItem() {
        if (CHEESY_FILLER != null) {
            return CHEESY_FILLER;
        }
        return null;
    }

    public boolean hasCheese() {
        return CHEESY_FILLER != null;
    }
}
