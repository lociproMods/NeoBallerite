package net.locipro.balleritemod.item.custom;

import net.locipro.balleritemod.data.ModBlockTagGenerator;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import java.util.List;
import java.util.Objects;

public class BallDowser extends Item {

    public BallDowser(Properties settings) {
        super(settings);
    }

    @Override
    public void appendHoverText(ItemStack itemStack, Level world, List<Component> tooltip, TooltipFlag tooltipContext) {
        tooltip.add(  Component.literal("Right click on a block to scan for").withStyle(ChatFormatting.GRAY));
        tooltip.add(  Component.literal("any ballerite ore below.").withStyle(ChatFormatting.GRAY));
        if (Screen.hasShiftDown()) {
            tooltip.add(  Component.literal("--Detector").withStyle(ChatFormatting.BLUE));
        }
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {

        if (context.getLevel().isClientSide) {
            return super.useOn(context);
        }


        Player player = Objects.requireNonNull(context.getPlayer());
        if (context.getHand() == InteractionHand.MAIN_HAND) {
            player.getCooldowns().addCooldown(this, 30);
            BlockPos blockPosition = context.getClickedPos();
            boolean foundBlock = false;
            //context.getWorld().playSound(player, blockPosition, ModSounds.DOWSER_SOUND, SoundCategory.AMBIENT, 0.01f, 1f);

            for (int i = 0; i < blockPosition.getY(); i++) {
                //This creates a value, i, starting at 0, and for each loop, increases the value by 1.
                //The variable "blockBelow" changes every loop, to the block that's i+1 below each time.
                Block blockBelow = context.getLevel().getBlockState(blockPosition.below(i)).getBlock();
                BlockPos iteratingBlockPosition = blockPosition.offset(0, -i, 0);
                if (isValuable(blockBelow)) {
                    sendOreCoordinate(blockBelow, iteratingBlockPosition, player);
                    foundBlock = true;
                    break;
                }
            }
            if (!foundBlock) {
                player.displayClientMessage(Component.literal("No ballerite found."), false);
            }

        }
        context.getItemInHand().hurtAndBreak(1, player, e -> e.broadcastBreakEvent(EquipmentSlot.MAINHAND));
        return super.useOn(context);
    }
    private boolean isValuable(Block block) {
        return block.defaultBlockState().is(ModBlockTagGenerator.BALLERITE_BLOCKS);
    }
    private void sendOreCoordinate(Block blockFound, BlockPos blockPosition, Player player) {
        player.displayClientMessage(Component.literal("Found " + blockFound.asItem().getDescription().getString() + " At (" + blockPosition.getX() + " ," + blockPosition.getY() + " ," + blockPosition.getZ() + ")"), false);
    }
}