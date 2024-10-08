package net.locipro.balleritemod.item.custom;
import java.util.List;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

public class CompressedBallerite extends Item{

    public CompressedBallerite() {
        super(new FabricItemSettings());
    }
    @Override
    public void appendHoverText(ItemStack itemStack, Level world, List<Component> tooltip, TooltipFlag tooltipContext) {
        if (Screen.hasShiftDown()) {
            tooltip.add( Component.literal("--Organic").withStyle(ChatFormatting.DARK_GREEN));
        }
    }
}
