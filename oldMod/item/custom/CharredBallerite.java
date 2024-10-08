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

public class CharredBallerite extends Item{

    public CharredBallerite() {
        super(new FabricItemSettings());
    }
    @Override
    public void appendHoverText(ItemStack itemStack, Level world, List<Component> tooltip, TooltipFlag tooltipContext) {
        tooltip.add( Component.literal("Charred Ball.").withStyle(ChatFormatting.GRAY));
        if (Screen.hasShiftDown()) {
            tooltip.add( Component.literal("--Biofuel").withStyle(ChatFormatting.DARK_GREEN));
        }
    }
}
