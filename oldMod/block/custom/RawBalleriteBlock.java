package net.locipro.balleritemod.block.custom;

import java.util.List;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;

public class RawBalleriteBlock extends Block {
 
    public RawBalleriteBlock(Properties settings) {
        super(settings);
    }
    public void appendHoverText(ItemStack itemStack, BlockGetter world, List<Component> tooltip, TooltipFlag tooltipContext) {
        tooltip.add( Component.literal("Raw Messy block....").withStyle(ChatFormatting.GRAY));
        tooltip.add( Component.literal("Why did you make this?? *don't eat it*").withStyle(ChatFormatting.DARK_GRAY));
        if (Screen.hasShiftDown()) {
            tooltip.add( Component.literal("--Cookable").withStyle(ChatFormatting.AQUA));
        }

    }
}