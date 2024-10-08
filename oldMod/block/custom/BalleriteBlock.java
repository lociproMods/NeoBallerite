package net.locipro.balleritemod.block.custom;

import java.util.List;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;

//Get from cooking raw ball block
public class BalleriteBlock extends Block {
    public BalleriteBlock(Properties settings) {
        super(settings);
    }
    public void appendHoverText(ItemStack itemStack, BlockGetter world, List<Component> tooltip, TooltipFlag tooltipContext) {
        tooltip.add( Component.literal("Warm block from this weird mineral called ballerite...").withStyle(ChatFormatting.GRAY));
        tooltip.add( Component.literal("Maybe try cooking it again? OR, just eat it..").withStyle(ChatFormatting.GRAY));
        if (Screen.hasShiftDown()) {
            tooltip.add( Component.literal("-Running speed faster than grass").withStyle(ChatFormatting.AQUA));
        }
    }
}
