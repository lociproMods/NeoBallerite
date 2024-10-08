package net.locipro.balleritemod.item.custom;

import java.util.List;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

public class RawBallerite extends Item {
    public RawBallerite(Properties settings) {
        super(settings);

    }
    @Override
    public void appendHoverText(ItemStack itemStack, Level world, List<Component> tooltip, TooltipFlag tooltipContext) {

        tooltip.add( Component.literal("Raw ballerite... Suspicious mineral").withStyle(ChatFormatting.GRAY));
        tooltip.add( Component.literal("Probably shouldn't eat it..").withStyle(ChatFormatting.DARK_GRAY));
        if (Screen.hasShiftDown()) {
            tooltip.add( Component.literal("--Cookable").withStyle(ChatFormatting.AQUA));
            tooltip.add( Component.literal("--Compressable").withStyle(ChatFormatting.AQUA));
        }


    }
}
