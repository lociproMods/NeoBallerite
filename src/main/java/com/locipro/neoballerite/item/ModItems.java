package com.locipro.neoballerite.item;


import com.locipro.neoballerite.item.custom.*;
import com.locipro.neoballerite.misc.food.BalleriteFoodProperties;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.*;
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

    public static final DeferredItem<Item> LEAD_INGOT = ITEMS.register("lead_ingot",
            () -> new Item(new Item.Properties()));


    

    public static final DeferredItem<SwordItem> BALLERITE_SWORD = ITEMS.register("ballerite_sword",
            () -> new BalleriteSwordItem(
                    ModTiers.BALLERITE_TIER,
                    new Item.Properties().attributes(
                            SwordItem.createAttributes(
                                    ModTiers.BALLERITE_TIER,
                                    3f,
                                    -2.4f
                            )
                    )
            ));
    public static final DeferredItem<PickaxeItem> BALLERITE_PICKAXE = ITEMS.register("ballerite_pickaxe",
            () -> new BalleritePickaxeItem(
                    ModTiers.BALLERITE_TIER,
                    new Item.Properties().attributes(
                            PickaxeItem.createAttributes(
                                    ModTiers.BALLERITE_TIER,
                                    1,
                                    -2.8f)
                    )
            ));
    public static final DeferredItem<AxeItem> BALLERITE_AXE = ITEMS.register("ballerite_axe",
            () -> new BalleriteAxeItem(
                    ModTiers.BALLERITE_TIER,
                    new Item.Properties().attributes(
                            AxeItem.createAttributes(
                                    ModTiers.BALLERITE_TIER,
                                    6f,
                                    -3f
                            )
                    )
            ));
    public static final DeferredItem<ShovelItem> BALLERITE_SHOVEL = ITEMS.register("ballerite_shovel",
            () -> new BalleriteShovelItem(
                    ModTiers.BALLERITE_TIER,
                    new Item.Properties().attributes(
                            ShovelItem.createAttributes(
                                    ModTiers.BALLERITE_TIER,
                                    1.6f,
                                    -2.9f
                            )
                    )
            ));
    public static final DeferredItem<HoeItem> BALLERITE_HOE = ITEMS.register("ballerite_hoe",
            () -> new HoeItem(
                    ModTiers.BALLERITE_TIER,
                    new Item.Properties().attributes(
                            HoeItem.createAttributes(
                                    ModTiers.BALLERITE_TIER,
                                    -1f,
                                    -1f
                            )
                    )
            ));





    public static final DeferredItem<SwordItem> LEAD_SWORD = ITEMS.register("lead_sword",
            () -> new PoisonousSwordItem(
                    ModTiers.LEAD_TIER,
                    new Item.Properties().attributes(
                            SwordItem.createAttributes(
                                    ModTiers.LEAD_TIER,
                                    3f,
                                    -2.4f
                            )
                    )
            ));
    public static final DeferredItem<PickaxeItem> LEAD_PICKAXE = ITEMS.register("lead_pickaxe",
            () -> new PickaxeItem(
                    ModTiers.LEAD_TIER,
                    new Item.Properties().attributes(
                            PickaxeItem.createAttributes(
                                    ModTiers.LEAD_TIER,
                                    1,
                                    -2.8f)
                    )
            ));
    public static final DeferredItem<AxeItem> LEAD_AXE = ITEMS.register("lead_axe",
            () -> new AxeItem(
                    ModTiers.LEAD_TIER,
                    new Item.Properties().attributes(
                            AxeItem.createAttributes(
                                    ModTiers.LEAD_TIER,
                                    6f,
                                    -3f
                            )
                    )
            ));
    public static final DeferredItem<ShovelItem> LEAD_SHOVEL = ITEMS.register("lead_shovel",
            () -> new ShovelItem(
                    ModTiers.LEAD_TIER,
                    new Item.Properties().attributes(
                            ShovelItem.createAttributes(
                                    ModTiers.LEAD_TIER,
                                    1.4f,
                                    -3.1f
                            )
                    )
            ));
    public static final DeferredItem<HoeItem> LEAD_HOE = ITEMS.register("lead_hoe",
            () -> new HoeItem(
                    ModTiers.LEAD_TIER,
                    new Item.Properties().attributes(
                            HoeItem.createAttributes(
                                    ModTiers.LEAD_TIER,
                                    0f,
                                    -2f
                            )
                    )
            ));


}
