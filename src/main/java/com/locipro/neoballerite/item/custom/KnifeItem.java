package com.locipro.neoballerite.item.custom;

import com.locipro.neoballerite.NeoBallerite;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class KnifeItem extends Item {
    public KnifeItem(Properties properties) {
        super(properties
                .attributes(ItemAttributeModifiers.builder()
                        .add(
                            Attributes.ATTACK_DAMAGE,
                            new AttributeModifier(BASE_ATTACK_DAMAGE_ID, 3.0f, AttributeModifier.Operation.ADD_VALUE),
                            EquipmentSlotGroup.MAINHAND)
                        .build()));
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        tooltipComponents.add(Component.literal("Used with bread and foods to make sandwiches.").withStyle(ChatFormatting.GRAY));
        if (Screen.hasShiftDown()) {
            tooltipComponents.add(Component.literal("--Irrepairable tool").withStyle(ChatFormatting.DARK_GRAY));
        }
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
    }

    // This works but apparently it's getting removed soon and also returning true is safer?
    // Yeah people r saying this is getting removed and getCraftingRemainingItem will just return
    // an empty itemstack.
    @Override
    public boolean hasCraftingRemainingItem(ItemStack stack) {
//        return stack.getDamageValue() + 1 < stack.getMaxDamage();
        return true;
    }

    @Override
    public @NotNull ItemStack getCraftingRemainingItem(ItemStack itemStack) {
        ItemStack stack = itemStack.copy();
        if (stack.getDamageValue() + 1 >= stack.getMaxDamage()) {
            return new ItemStack(Items.AIR);
        }
        stack.setDamageValue(stack.getDamageValue() + 1);
        return stack;
    }
}
