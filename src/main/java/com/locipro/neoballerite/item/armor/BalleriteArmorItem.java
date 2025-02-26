package com.locipro.neoballerite.item.armor;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.equipment.ArmorType;

import java.util.List;

public class BalleriteArmorItem extends ArmorItem {
    public BalleriteArmorItem(ArmorType type, Properties properties) {
        super(NeoArmorMaterials.BALLERITE, type, properties);
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        tooltipComponents.add(Component.literal("Wearing a full suit grants you +2 luck.")
                .withStyle(ChatFormatting.ITALIC)
                .withStyle(ChatFormatting.GREEN));
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
    }
}
