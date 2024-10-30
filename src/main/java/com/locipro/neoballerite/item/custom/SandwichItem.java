package com.locipro.neoballerite.item.custom;


import com.locipro.neoballerite.component.NeoDataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/** Currently only a helper class with some variables to assist in recipe generation.
 * Eventually will get dynamic item models to make all sandwiches just an instance of this singleton with some data components.**/
public class SandwichItem extends Item {
    public SandwichItem() {
        super(new Item.Properties());
    }
    public static List<Optional<Item>> getIngredientsOfSandwich(ItemStack stack) {
        List<Optional<Item>> list = new ArrayList<>();
        if (stack.has(NeoDataComponents.SANDWICH_BREAD)) {
            list.add(Optional.ofNullable(stack.get(NeoDataComponents.SANDWICH_BREAD)));
        }
        if (stack.has(NeoDataComponents.SANDWICH_MEAT)) {
            list.add(Optional.ofNullable(stack.get(NeoDataComponents.SANDWICH_MEAT)));
        }
        if (stack.has(NeoDataComponents.SANDWICH_CHEESE)) {
            list.add(Optional.ofNullable(stack.get(NeoDataComponents.SANDWICH_CHEESE)));
        }
        return list;
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        for (var x : getIngredientsOfSandwich(stack)) {
            if (x.isPresent()) {
                tooltipComponents.add(Component.literal(x.toString()));
            }
        }
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
    }
}
