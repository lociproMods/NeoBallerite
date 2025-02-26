package com.locipro.neoballerite.item.armor;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.equipment.ArmorType;

import java.util.List;

public class LeadArmorItem extends ArmorItem {
    public LeadArmorItem(ArmorType type, Properties properties) {
        super(NeoArmorMaterials.LEAD, type, properties);
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        tooltipComponents.add(Component.literal(
                "Heavy piece of armor with solid knockback resistance."
        ));
        tooltipComponents.add(Component.literal("Wearing a full suit slows you down")
                .withStyle(ChatFormatting.ITALIC)
                .withStyle(ChatFormatting.DARK_BLUE));
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
    }
}
