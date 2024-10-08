package net.locipro.balleritemod.item.custom;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.locipro.balleritemod.item.ModCuttingToolItem;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import java.util.List;

public class KnifeItem extends ModCuttingToolItem {
    public KnifeItem() {
        super(new FabricItemSettings().stacksTo(1));
    }
    @Override
    public void appendHoverText(ItemStack itemStack, Level world, List<Component> tooltip, TooltipFlag tooltipContext) {
        tooltip.add(Component.literal("Use with bread and meat to make sandwiches.").withStyle(ChatFormatting.GRAY));
        tooltip.add(Component.literal("Use with fruits to make jam.").withStyle(ChatFormatting.GRAY));
        if (Screen.hasShiftDown()) {
            tooltip.add( Component.literal("-Won't be used in crafting").withStyle(ChatFormatting.DARK_AQUA));
        }
    }
}
