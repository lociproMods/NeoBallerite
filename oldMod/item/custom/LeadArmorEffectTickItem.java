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

public class LeadArmorEffectTickItem extends StatusEffectArmorItem {
    public LeadArmorEffectTickItem(Type type, Properties settings) {
        super(ModArmorMaterials.LEAD, type, settings, MobEffects.UNLUCK);
    }
    public LeadArmorEffectTickItem(Type type) {
        super(ModArmorMaterials.LEAD, type, new FabricItemSettings(), MobEffects.UNLUCK);
    }
    @Override
    public void appendHoverText(ItemStack stack, Level world, List<Component> tooltip, TooltipFlag context) {
        tooltip.add( Component.literal("Heavy lead armor").withStyle(ChatFormatting.GRAY));
        if (Screen.hasShiftDown()) {
            tooltip.add( Component.literal("-Wearing a full suit lowers your IQ").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add( Component.literal("due to lead poisoning.").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add( Component.literal("--gives unluck debuff").withStyle(ChatFormatting.ITALIC, ChatFormatting.DARK_GRAY));
        }
    }
}
