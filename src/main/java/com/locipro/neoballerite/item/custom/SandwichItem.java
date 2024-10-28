package com.locipro.neoballerite.item.custom;


import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

/** Currently only a helper class with some variables to assist in recipe generation.
 * Eventually will get dynamic item models to make all sandwiches just an instance of this singleton with some data components.**/
public class SandwichItem extends Item {
    public SandwichItem() {
        super(new Item.Properties());
    }

    // ok but how does it get its model? and texture?

    private Item BREAD_ITEM;
    private Item MEAT_ITEM;
    private Item CHEESE_ITEM;

    // Just realized all this isn't gonna work
    // Shit needs to be PER ITEMSTACK, not for the entire class.
    // Shiiiii gotta check data components or item properties or smth like taht, check the vid in \to do
    public Item getBreadItem() {
        if (BREAD_ITEM != null) {
            return BREAD_ITEM;
        }
        throw new NullPointerException("Bread item is null (how did you make a sandwich without bread)");
    }
    public Item getMeatItem() {
        if (MEAT_ITEM != null) {
            return MEAT_ITEM;
        }
        throw new NullPointerException("Bread item is null");
    }
    public Item getCheeseItem() {
        if (CHEESE_ITEM != null) {
            return CHEESE_ITEM;
        }
        throw new NullPointerException("Cheese item is null");
    }


    public boolean hasCheese() {
        return CHEESE_ITEM != null;
    }
    public boolean hasMeat() {
        return MEAT_ITEM != null;
    }
}
