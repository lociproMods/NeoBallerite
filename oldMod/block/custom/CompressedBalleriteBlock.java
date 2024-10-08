package net.locipro.balleritemod.block.custom;

import java.util.List;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
//Get from charring Burnt balls in a Charring station
public class CompressedBalleriteBlock extends Block{

    public CompressedBalleriteBlock(Properties settings) {
        super(settings);
    }
    public void appendHoverText(ItemStack itemStack, BlockGetter world, List<Component> tooltip, TooltipFlag tooltipContext) {
        tooltip.add(Component.literal("Compressed block of Processed ballerite").withStyle(ChatFormatting.GRAY));
        if (Screen.hasShiftDown()) {
            tooltip.add(Component.literal("--Organic").withStyle(ChatFormatting.DARK_GREEN));
        }
    }
}
