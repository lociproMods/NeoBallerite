package com.locipro.neoballerite.item;


import com.locipro.neoballerite.item.custom.BallDowserItem;
import com.locipro.neoballerite.misc.food.BalleriteFoodProperties;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.List;

import static com.locipro.neoballerite.NeoBallerite.MODID;

public class ModItems {

    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(MODID);

    public static final DeferredItem<Item> BALL_DOWSER = ITEMS.register("ball_dowser",
            () -> new BallDowserItem(new Item.Properties()));
    public static final DeferredItem<Item> COMPRESSED_BALLERITE_INGOT = ITEMS.register("compressed_ballerite_ingot",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> RAW_BALLERITE = ITEMS.register("raw_ballerite",
            () -> new Item(new Item.Properties().food(BalleriteFoodProperties.RAW_BALLERITE)){
                @Override
                public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
                    tooltipComponents.add( Component.literal("Raw ballerite... Suspicious mineral").withStyle(ChatFormatting.GRAY));
                    tooltipComponents.add( Component.literal("Probably shouldn't eat it..").withStyle(ChatFormatting.DARK_GRAY));
                    if (Screen.hasShiftDown()) {
                        tooltipComponents.add( Component.literal("--Cookable").withStyle(ChatFormatting.AQUA));
                        tooltipComponents.add( Component.literal("--Compressible").withStyle(ChatFormatting.AQUA));
                    }
                }
            });
    public static final DeferredItem<Item> COOKED_BALLERITE = ITEMS.register("cooked_ballerite",
            () -> new Item(new Item.Properties().food(BalleriteFoodProperties.COOKED_BALLERITE)){
                @Override
                public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
                    tooltipComponents.add(  Component.literal("Cooked ballerite... still weird.").withStyle(ChatFormatting.GRAY));
                    tooltipComponents.add(  Component.literal("Probably shouldn't eat it..").withStyle(ChatFormatting.DARK_GRAY));
                    if (Screen.hasShiftDown()) {
                        tooltipComponents.add( Component.literal("--Compressible").withStyle(ChatFormatting.AQUA));
                    }
                }
            });
    public static final DeferredItem<Item> CHARRED_BALLERITE = ITEMS.register("charred_ballerite",
            () -> new Item(new Item.Properties()){
                @Override
                public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
                    tooltipComponents.add( Component.literal("Charred Ball.").withStyle(ChatFormatting.GRAY));
                    if (Screen.hasShiftDown()) {
                        tooltipComponents.add( Component.literal("--Biofuel").withStyle(ChatFormatting.DARK_GREEN));
                    }
                }
            });




}
