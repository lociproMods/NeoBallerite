package com.locipro.neoballerite.item.custom;


import net.minecraft.world.item.Item;

/** Currently only a helper class with some variables to assist in recipe generation.
 * Eventually will get dynamic item models to make all sandwiches just an instance of this singleton with some data components.**/
public class SandwichItem extends Item {
    public SandwichItem() {
        super(new Item.Properties());
    }
}
