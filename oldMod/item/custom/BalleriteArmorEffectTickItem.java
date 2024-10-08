package net.locipro.balleritemod.item.custom;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.locipro.balleritemod.item.ModArmorMaterials;
import net.locipro.balleritemod.item.StatusEffectArmorItem;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import java.util.List;
public class BalleriteArmorEffectTickItem extends StatusEffectArmorItem {
    public BalleriteArmorEffectTickItem(Type type, Properties settings) {
        super(ModArmorMaterials.BALLERITE, type, settings, MobEffects.LUCK);
    }public BalleriteArmorEffectTickItem(Type type) {
        super(ModArmorMaterials.BALLERITE, type, new FabricItemSettings(), MobEffects.LUCK);
    }
    @Override
    public void appendHoverText(ItemStack stack, Level world, List<Component> tooltip, TooltipFlag context) {
        tooltip.add( Component.literal("Organic ballerite armor").withStyle(ChatFormatting.GRAY));
        if (Screen.hasShiftDown()) {
            tooltip.add( Component.literal("-Wearing a full suit gives you luck").withStyle(ChatFormatting.DARK_AQUA));
        }
    }
}
