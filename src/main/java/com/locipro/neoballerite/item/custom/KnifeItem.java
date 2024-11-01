package com.locipro.neoballerite.item.custom;

import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class KnifeItem extends Item {
    /* Base damage for iron knife is 3.0f*/
    public KnifeItem(Properties properties, float baseDamage) {
        super(properties
                .attributes(ItemAttributeModifiers.builder()
                        .add(
                            Attributes.ATTACK_DAMAGE,
                            new AttributeModifier(BASE_ATTACK_DAMAGE_ID, baseDamage, AttributeModifier.Operation.ADD_VALUE),
                            EquipmentSlotGroup.MAINHAND)
                        .add(Attributes.ATTACK_SPEED,
                                new AttributeModifier(BASE_ATTACK_SPEED_ID, -1.6, AttributeModifier.Operation.ADD_VALUE),
                                EquipmentSlotGroup.MAINHAND)
                        .build()));
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        tooltipComponents.add(Component.literal("Used with bread and foods to make sandwiches. Also makes jams when used with fruits!").withStyle(ChatFormatting.GRAY));
        if (Screen.hasShiftDown()) {
            tooltipComponents.add(Component.literal("--Irreparable tool").withStyle(ChatFormatting.DARK_GRAY));
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

    @Override
    public void postHurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        stack.hurtAndBreak(1, attacker, EquipmentSlot.MAINHAND);
    }
}
