package net.locipro.balleritemod.block.custom;

import java.util.List;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.BlockGetter;

import net.minecraft.world.level.block.Block;
//Get from burning Cooked ball block in furnace/blast furnace
public class BurntBallerite extends Block {
 
    public BurntBallerite(Properties settings) {
        super(settings);
    }
    @Override
    public void appendHoverText(ItemStack itemStack, BlockGetter world, List<Component> tooltip, TooltipFlag tooltipContext) {
        tooltip.add( Component.literal("How did you get here?").withStyle(ChatFormatting.GRAY));
        tooltip.add( Component.literal("Just a few more steps...").withStyle(ChatFormatting.DARK_GRAY));
        if (Screen.hasShiftDown()) {
            tooltip.add(Component.literal("--Organic").withStyle(ChatFormatting.DARK_GREEN));
        }


    }
}