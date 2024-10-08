package net.locipro.balleritemod.block.custom;


import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import java.util.List;
import net.minecraft.network.chat.Component;



//Get from charring Burnt balls in a Charring station
public class CharredBalleriteBlock extends Block {

    public CharredBalleriteBlock(Properties settings) {
        super(settings);
    }
    public void appendHoverText(ItemStack itemStack, BlockGetter world, List<Component> tooltip, TooltipFlag tooltipContext) {
        tooltip.add(Component.literal("Charred Ball Block.").withStyle(ChatFormatting.GRAY));
        tooltip.add(Component.literal("Just one more time...").withStyle(ChatFormatting.DARK_AQUA));
        if (Screen.hasShiftDown()) {
            tooltip.add(Component.literal("--Biofuel").withStyle(ChatFormatting.DARK_GREEN));
        }
    }
}
