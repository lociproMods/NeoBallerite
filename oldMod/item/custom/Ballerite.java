package net.locipro.balleritemod.item.custom;



import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import java.util.List;

public class Ballerite extends Item{
    public Ballerite(Properties settings) {
        super(settings);

    }
    @Override
    public void appendHoverText(ItemStack itemStack, Level world, List<Component> tooltip, TooltipFlag tooltipContext) {
 
        tooltip.add(  Component.literal("Ballerite... still weird.").withStyle(ChatFormatting.GRAY));
        tooltip.add(  Component.literal("Probably shouldn't eat it..").withStyle(ChatFormatting.DARK_GRAY));

    }
}