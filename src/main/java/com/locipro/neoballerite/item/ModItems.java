package com.locipro.neoballerite.item;


import com.locipro.neoballerite.block.ModBlocks;
import com.locipro.neoballerite.item.custom.*;
import com.locipro.neoballerite.misc.food.BalleriteFoodProperties;
import com.locipro.neoballerite.misc.food.BerryFoodProperties;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockBehaviour;
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
    public static final DeferredItem<Item> RAW_LEAD = ITEMS.register("raw_lead",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> LEAD_NUGGET = ITEMS.register("lead_nugget",
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

    
    //region CLAYMORES
    public static final DeferredItem<NeoClaymoreItem> WOODEN_CLAYMORE = ITEMS.register("wooden_claymore",
            () -> new NeoClaymoreItem(Tiers.WOOD, new Item.Properties()));
    public static final DeferredItem<NeoClaymoreItem> STONE_CLAYMORE = ITEMS.register("stone_claymore",
            () -> new NeoClaymoreItem(Tiers.STONE, new Item.Properties()));
    public static final DeferredItem<NeoClaymoreItem> IRON_CLAYMORE = ITEMS.register("iron_claymore",
            () -> new NeoClaymoreItem(Tiers.IRON, new Item.Properties()));
    public static final DeferredItem<NeoClaymoreItem> LEAD_CLAYMORE = ITEMS.register("lead_claymore",
            () -> new NeoClaymoreItem(ModTiers.LEAD_TIER, new Item.Properties()));
    public static final DeferredItem<NeoClaymoreItem> GOLD_CLAYMORE = ITEMS.register("gold_claymore",
            () -> new NeoClaymoreItem(Tiers.GOLD, new Item.Properties()));
    public static final DeferredItem<NeoClaymoreItem> DIAMOND_CLAYMORE = ITEMS.register("diamond_claymore",
            () -> new NeoClaymoreItem(Tiers.DIAMOND, new Item.Properties()) {
                @Override
                public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
                    Level level = attacker.level();
                    if (level instanceof ServerLevel) {
                        ServerLevel serverLevel = (ServerLevel) level;
                        serverLevel.sendParticles(
                                ParticleTypes.ELECTRIC_SPARK,
                                target.getX(),
                                target.getY() + 1.5,
                                target.getZ(),
                                12,
                                0,
                                0,
                                0,
                                0.3f
                        );
                    }
                    return super.hurtEnemy(stack, target, attacker);
                }
            });
    public static final DeferredItem<NeoClaymoreItem> NETHERITE_CLAYMORE = ITEMS.register("netherite_claymore",
            () -> new NeoClaymoreItem(Tiers.NETHERITE, new Item.Properties()));
    public static final DeferredItem<NeoClaymoreItem> BALLERITE_CLAYMORE = ITEMS.register("ballerite_claymore",
            () -> new NeoClaymoreItem(ModTiers.BALLERITE_TIER, new Item.Properties(), true) {
                @Override
                public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
                    Level level = attacker.level();
                    if (level instanceof ServerLevel) {
                        ServerLevel serverLevel = (ServerLevel) level;
                        serverLevel.sendParticles(
                                ParticleTypes.GLOW,
                                target.getX(),
                                target.getY() + 0.5,
                                target.getZ(),
                                12,
                                0,
                                0,
                                0,
                                0.3f
                        );
                    }
                    return super.hurtEnemy(stack, target, attacker);
                }
            });

    //endregion


    public static final DeferredItem<Item> KNIFE = ITEMS.register("knife",
            () -> new KnifeItem(new Item.Properties().setNoRepair().durability(32)));


    public static final DeferredItem<BlockItem> BLUEBERRIES = ITEMS.registerSimpleBlockItem("blueberries",
            ModBlocks.BLUEBERRY_BUSH, new Item.Properties().food(BerryFoodProperties.BLUE));
    public static final DeferredItem<BlockItem> BLACKBERRIES = ITEMS.registerSimpleBlockItem("blackberries",
            ModBlocks.BLACKBERRY_BUSH, new Item.Properties().food(BerryFoodProperties.BLACK));

    public static final DeferredItem<Item> STRAWBERRY = ITEMS.register("strawberry",
            () -> new Item(new Item.Properties().food(BerryFoodProperties.STRAWBERRIES)));
    public static final DeferredItem<Item> UNRIPE_STRAWBERRY = ITEMS.register("unripe_strawberry",
            () -> new Item(new Item.Properties().food(BerryFoodProperties.UNRIPE_STRAWBERRIES)));
    public static final DeferredItem<BlockItem> STRAWBERRY_SEEDS = ITEMS.registerSimpleBlockItem("strawberry_seeds",
            ModBlocks.STRAWBERRY_BUSH, new Item.Properties());

}
