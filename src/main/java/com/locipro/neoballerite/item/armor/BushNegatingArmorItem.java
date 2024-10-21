package com.locipro.neoballerite.item.armor;

import com.locipro.neoballerite.component.NeoDataComponents;
import net.minecraft.ChatFormatting;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

import java.util.List;



public class BushNegatingArmorItem extends ArmorItem {
    public BushNegatingArmorItem(Holder<ArmorMaterial> material, Type type, Properties properties) {
        super(material, type, properties);
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        // All items get default values when registered, so their data components can't be null.
        if (!stack.get(NeoDataComponents.CAN_NEGATE_BUSH_SLOW)) {
            tooltipComponents.add(Component.literal("Will not negate speed de-buffs until repaired with leaves.").withStyle(ChatFormatting.RED).withStyle(ChatFormatting.ITALIC));
        }else {
            tooltipComponents.add(Component.literal("Negates berry bush damage and slightly negates the slow.").withStyle(ChatFormatting.GRAY));
        }
        if (stack.get(NeoDataComponents.ADDED_DURABILITY) > 0) {
            tooltipComponents.add(Component.literal("Upgraded to durability " + stack.getMaxDamage()));
        }
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
    }

    public void onBushEntry(BlockState blockState, Level level, ItemStack itemStack, Player player) {
        if (itemStack.get(NeoDataComponents.CAN_NEGATE_BUSH_SLOW)) {
            player.makeStuckInBlock(blockState, new Vec3(1.321232123F, 1.0f, 1.321232123f));
        }else {
            player.makeStuckInBlock(blockState, new Vec3(0.8F, 0.75, 0.8F));
        }


        if ((player.xOld != player.getX() || player.zOld != player.getZ()) && !level.isClientSide)
        {
            double travelledX = Math.abs(player.getX() - player.xOld);
            double travelledZ = Math.abs(player.getZ() - player.zOld);
            if (travelledX >= 0.25F || travelledZ >= 0.25F)
            {
                // Technically there's a redundant call here, no?
                // Damages until minimum damage, then does nothing.
                if (itemStack.getDamageValue() + 1 > itemStack.getMaxDamage()) {
                    itemStack.setDamageValue(itemStack.getMaxDamage());
                    return;
                }
                itemStack.setDamageValue(itemStack.getDamageValue() + 1);
            }
        }
    }



    @Override
    public void inventoryTick(ItemStack itemStack, Level level, Entity entity, int slotId, boolean isSelected) {
        if (entity instanceof ServerPlayer serverPlayer) {

            // I think this is better? because we don't set the property every tick.
            boolean can_negate = Boolean.TRUE.equals(itemStack.get(NeoDataComponents.CAN_NEGATE_BUSH_SLOW));
            if (itemStack.getDamageValue() >= itemStack.getMaxDamage() && can_negate) {
                itemStack.set(NeoDataComponents.CAN_NEGATE_BUSH_SLOW, false);
            }
            else if (itemStack.getDamageValue() < itemStack.getMaxDamage() && !can_negate) {
                itemStack.set(NeoDataComponents.CAN_NEGATE_BUSH_SLOW, true);
            }

            if (!itemStack.has(DataComponents.ENCHANTMENT_GLINT_OVERRIDE)) {
                if (itemStack.get(NeoDataComponents.ADDED_DURABILITY) > 0) {
                    itemStack.set(DataComponents.ENCHANTMENT_GLINT_OVERRIDE, true);
                }
            }

            super.inventoryTick(itemStack, level, entity, slotId, isSelected);
        }
    }

    // Nah, you ain't repairing this in a damn anvil homie.
    @Override
    public boolean isValidRepairItem(ItemStack toRepair, ItemStack repair) {
        return false;
    }
}
