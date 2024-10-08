package net.locipro.balleritemod.item.custom;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.locipro.balleritemod.item.ModArmorMaterials;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import java.util.List;

public class BalleriteArmorItem extends ArmorItem {
    public BalleriteArmorItem(Type type) {
        super(ModArmorMaterials.BALLERITE, type, new FabricItemSettings());
    }
    @Override
    public void appendHoverText(ItemStack stack, Level world, List<Component> tooltip, TooltipFlag context) {
        tooltip.add( Component.literal("Organic ballerite armor").withStyle(ChatFormatting.GRAY));
        if (Screen.hasShiftDown()) {
            tooltip.add( Component.literal("-Wearing a full suit gives you luck").withStyle(ChatFormatting.DARK_AQUA));
        }
    }
}
