package net.locipro.balleritemod.item;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class ModCuttingToolItem extends Item {
    public ModCuttingToolItem(Properties settings) {
        super(settings);
    }

    @Override
    public boolean hasCraftingRemainingItem() {
        return true;
    }

    @Override
    public ItemStack getRecipeRemainder(ItemStack stack) {
        return new ItemStack(this);
    }
}
