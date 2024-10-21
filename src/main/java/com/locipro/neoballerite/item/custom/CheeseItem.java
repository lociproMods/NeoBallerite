package com.locipro.neoballerite.item.custom;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

/** If a type is not selected then it can be crafted by ALL fungals.**/
public class CheeseItem extends Item {
    public enum CheeseTypes {
        OVERWORLD,
        NETHER,
        END
    }
    private CheeseTypes cheeseType;
    public CheeseItem(Properties properties) {
        super(properties);
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        tooltipComponents.add(Component.literal("Cheese type is : " + StringUtils.capitalize(cheeseType.toString().toLowerCase())).withStyle(ChatFormatting.GRAY));

        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
    }

    public CheeseItem cheeseType(CheeseTypes type) {
        this.cheeseType = type;
        return this;
    }
    public CheeseTypes getCheeseType() {
        return cheeseType;
    }
}
