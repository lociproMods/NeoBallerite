package com.locipro.neoballerite.item;


import com.google.common.collect.ImmutableSet;
import com.locipro.neoballerite.NeoBallerite;
import com.locipro.neoballerite.block.ModBlocks;
import com.locipro.neoballerite.component.NeoDataComponents;
import com.locipro.neoballerite.item.armor.*;
import com.locipro.neoballerite.item.custom.*;
import com.locipro.neoballerite.item.tool.*;
import com.locipro.neoballerite.misc.food.*;
import com.locipro.neoballerite.util.ModTags;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BannerPatternLayers;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

import static com.locipro.neoballerite.NeoBallerite.MODID;

public class ModItems {

    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(MODID);

    // The problem is, creative tabs get initialized before items :( so this is too late.
    // Actually false, the problem is that itemlike is nullable, so all the lists are null by default. they only ever get an actual value later, but else all elements are null
    // Actually not false? I dont know anymore. Fuck this shit.
    // T O D O : bug (one which I will not work on rn)
    // We could have multiple item deferred registers, and loop over those in our ModCreativeTabs, so We'd have a registery for every tab.
    // I'm just going to *not* do all this jazz.
    /*public static <T extends Item> @NotNull DeferredItem<T> registerItem(String name, Supplier<T> itemSupplier, List<ItemLike> ... groups) {
        DeferredItem<T> item = ITEMS.register(name, itemSupplier);
        for (List<ItemLike> group : groups) {
            if (group != null) {
                group.add(item);
            }
        }
        return item;
    }*/
    private static DeferredItem<JamItem> registerJamItem(String name, Supplier<JamItem> itemSup) {
        DeferredItem<JamItem> item = ITEMS.register(name, itemSup);
        NeoJams.JAMS.add(item);
        return item;
    }

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


    
    
    //region ballerite tools
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


    public static final DeferredItem<ArmorItem> BALLERITE_HELMET = ITEMS.register("ballerite_helmet",
            () -> new SetEffectsItem(
                    NeoArmorMaterials.BALLERITE,
                    ArmorItem.Type.HELMET,
                    new Item.Properties().durability(ArmorItem.Type.HELMET.getDurability(35)),
                    MobEffects.LUCK,
                    80,
                    2
            ) {
                @Override
                public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
                    tooltipComponents.add(Component.literal("Wearing a full suit grants you +2 luck.")
                            .withStyle(ChatFormatting.ITALIC)
                            .withStyle(ChatFormatting.GREEN));
                    super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
                }
            });
    public static final DeferredItem<ArmorItem> BALLERITE_CHESTPLATE = ITEMS.register("ballerite_chestplate",
            () -> new BalleriteArmorItem(
                    ArmorItem.Type.CHESTPLATE,
                    new Item.Properties().durability(ArmorItem.Type.HELMET.getDurability(35))
            ));
    public static final DeferredItem<ArmorItem> BALLERITE_LEGGINGS = ITEMS.register("ballerite_leggings",
            () -> new BalleriteArmorItem(
                    ArmorItem.Type.LEGGINGS,
                    new Item.Properties().durability(ArmorItem.Type.HELMET.getDurability(35))
            ));
    public static final DeferredItem<ArmorItem> BALLERITE_BOOTS = ITEMS.register("ballerite_boots",
            () -> new BalleriteArmorItem(
                    ArmorItem.Type.BOOTS,
                    new Item.Properties().durability(ArmorItem.Type.HELMET.getDurability(35))
            ));

    // I think this will look for "textures/entity/horse/armor/horse_armor_ballerite_horse_armor"
    // nvm it just looks for "horse_armor_ballerite", it doesn't use the item's key, it uses the material's key for the texture.
    public static final DeferredItem<Item> BALLERITE_HORSE_ARMOR = ITEMS.register("ballerite_horse_armor",
            () -> new AnimalArmorItem(NeoArmorMaterials.BALLERITE, AnimalArmorItem.BodyType.EQUESTRIAN, true, new Item.Properties().stacksTo(1)));

    public static final DeferredItem<ArmorItem> LEAVES_BOOTS = ITEMS.register("leaves_boots",
            () -> new BushNegatingArmorItem(
                    NeoArmorMaterials.LEAVES,
                    ArmorItem.Type.BOOTS,
                    new Item.Properties()
                            .durability(77)
                            .component(NeoDataComponents.CAN_NEGATE_BUSH_SLOW.value(), true)
                            .component(NeoDataComponents.ADDED_DURABILITY.value(), 0)
            ));

    //endregion


    //region Lead
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



    public static final DeferredItem<ArmorItem> LEAD_HELMET = ITEMS.register("lead_helmet",
            () -> new SetEffectsItem(
                    NeoArmorMaterials.LEAD,
                    ArmorItem.Type.HELMET,
                    new Item.Properties().durability(ArmorItem.Type.HELMET.getDurability(23)),
                    MobEffects.MOVEMENT_SLOWDOWN,
                    80,
                    0
            ) {
                @Override
                public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
                    tooltipComponents.add(Component.literal(
                            "Heavy piece of armor with solid knockback resistance."
                    ));
                    tooltipComponents.add(Component.literal("Wearing a full suit slows you down")
                            .withStyle(ChatFormatting.ITALIC)
                            .withStyle(ChatFormatting.DARK_BLUE));
                    super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
                }
            });
    public static final DeferredItem<ArmorItem> LEAD_CHESTPLATE = ITEMS.register("lead_chestplate",
            () -> new LeadArmorItem(
                    ArmorItem.Type.CHESTPLATE,
                    new Item.Properties().durability(ArmorItem.Type.HELMET.getDurability(23))
            ));
    public static final DeferredItem<ArmorItem> LEAD_LEGGINGS = ITEMS.register("lead_leggings",
            () -> new LeadArmorItem(
                    ArmorItem.Type.LEGGINGS,
                    new Item.Properties().durability(ArmorItem.Type.HELMET.getDurability(23))
            ));
    public static final DeferredItem<ArmorItem> LEAD_BOOTS = ITEMS.register("lead_boots",
            () -> new LeadArmorItem(
                    ArmorItem.Type.BOOTS,
                    new Item.Properties().durability(ArmorItem.Type.HELMET.getDurability(23))
            ));

    //endregion
    
    //region CLAYMORES
    public static final DeferredItem<NeoClaymoreItem> WOODEN_CLAYMORE = ITEMS.register("wooden_claymore",
            () -> new NeoClaymoreItem(Tiers.WOOD, new Item.Properties()));
    public static final DeferredItem<NeoClaymoreItem> STONE_CLAYMORE = ITEMS.register("stone_claymore",
            () -> new NeoClaymoreItem(Tiers.STONE, new Item.Properties()));
    public static final DeferredItem<NeoClaymoreItem> IRON_CLAYMORE = ITEMS.register("iron_claymore",
            () -> new NeoClaymoreItem(Tiers.IRON, new Item.Properties()));
    public static final DeferredItem<NeoClaymoreItem> LEAD_CLAYMORE = ITEMS.register("lead_claymore",
            () -> new NeoClaymoreItem(ModTiers.LEAD_TIER, new Item.Properties()).doesPoison());
    public static final DeferredItem<NeoClaymoreItem> GOLD_CLAYMORE = ITEMS.register("gold_claymore",
            () -> new NeoClaymoreItem(Tiers.GOLD, new Item.Properties()));
    public static final DeferredItem<NeoClaymoreItem> DIAMOND_CLAYMORE = ITEMS.register("diamond_claymore",
            () -> new NeoClaymoreItem(Tiers.DIAMOND, new Item.Properties()) {
                @Override
                public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
                    Level level = attacker.level();
                    if (level instanceof ServerLevel serverLevel) {
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
            () -> new NeoClaymoreItem(ModTiers.BALLERITE_TIER, new Item.Properties()) {
                @Override
                public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
                    Level level = attacker.level();
                    if (level instanceof ServerLevel serverLevel) {
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
            }.doesPoison());

    //endregion


    //region foods?
    public static final DeferredItem<Item> KNIFE = ITEMS.register("knife",
            () -> new KnifeItem(new Item.Properties().setNoRepair().durability(32), 3f));

    public static final DeferredItem<Item> DIAMOND_KNIFE = ITEMS.register("diamond_knife",
            () -> new KnifeItem(new Item.Properties().setNoRepair().durability(524), 4.5f));


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

    public static final DeferredItem<Item> TOMATO = ITEMS.register("tomato",
            () -> new Item(new Item.Properties().food(CropFoodProperties.TOMATO)));
    public static final DeferredItem<Item> GRILLED_TOMATO = ITEMS.register("grilled_tomato",
            () -> new Item(new Item.Properties().food(CropFoodProperties.TOMATO)));
    public static final DeferredItem<BlockItem> TOMATO_SEEDS = ITEMS.registerSimpleBlockItem("tomato_seeds",
            ModBlocks.TOMATO_CROP, new Item.Properties());

    public static final DeferredItem<Item> EGGPLANT = ITEMS.register("eggplant",
            () -> new Item(new Item.Properties().food(CropFoodProperties.EGGPLANT)));
    public static final DeferredItem<Item> GRILLED_EGGPLANT = ITEMS.register("grilled_eggplant",
            () -> new Item(new Item.Properties().food(CropFoodProperties.GRILLED_EGGPLANT)));
    public static final DeferredItem<BlockItem> EGGPLANT_SEEDS = ITEMS.registerSimpleBlockItem("eggplant_seeds",
            ModBlocks.EGGPLANT_CROP, new Item.Properties());

    public static final DeferredItem<BlockItem> SWEET_POTATO = ITEMS.registerSimpleBlockItem("sweet_potato",
            ModBlocks.SWEET_POTATO_CROP, new Item.Properties().food(CropFoodProperties.SWEET_POTATO));
    public static final DeferredItem<Item> BAKED_SWEET_POTATO = ITEMS.register("baked_sweet_potato",
            () -> new Item(new Item.Properties().food(CropFoodProperties.BAKED_SWEET_POTATO)));


    public static final DeferredItem<BlockItem> CORN_KERNELS = ITEMS.registerSimpleBlockItem("corn_kernels",
            ModBlocks.CORN_CROP, new Item.Properties().food(CropFoodProperties.KERNELS));
    public static final DeferredItem<Item> CORN_COB = ITEMS.register("corn_cob",
            () -> new Item(new Item.Properties().food(CropFoodProperties.COB)));
    public static final DeferredItem<Item> GRILLED_CORN_COB = ITEMS.register("grilled_corn_cob",
            () -> new Item(new Item.Properties().food(CropFoodProperties.GRILLED_COB)));



    public static final DeferredItem<Item> MILK_VILE = ITEMS.register("milk_vile",
            () -> new MilkVileItem(new Item.Properties().food(DrinkFoodProperties.MILK)));

    public static final DeferredItem<CheeseItem> MILK_CHEESE = ITEMS.register("milk_cheese",
            () -> new CheeseItem(new Item.Properties().food(CheeseFoodProperties.CHEESE))
                    .cheeseType(CheeseItem.CheeseTypes.OVERWORLD));

    public static final DeferredItem<Item> IRON_CARROT = ITEMS.register("iron_carrot",
            () -> new Item(new Item.Properties().food(CropFoodProperties.IRON_CARROT)) {
                @Override
                public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
                    tooltipComponents.add(Component.literal("Don't have to worry about anemia, heh.").withStyle(ChatFormatting.GRAY));
                    super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
                }
            });
    public static final DeferredItem<Item> DIAMOND_CARROT = ITEMS.register("diamond_carrot",
            () -> new Item(new Item.Properties().food(CropFoodProperties.DIAMOND_CARROT)) {
                @Override
                public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
                    tooltipComponents.add(Component.literal("Might be a little hard to bite into").withStyle(ChatFormatting.GRAY));
                    super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
                }
            });


    public static final DeferredItem<Item> ENCHANTED_DIAMOND_CARROT = ITEMS.register("enchanted_diamond_carrot",
            () -> new Item(new Item.Properties().food(CropFoodProperties.ENCHANTED_DIAMOND_CARROT).component(DataComponents.ENCHANTMENT_GLINT_OVERRIDE, true)) {
                @Override
                public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
                    tooltipComponents.add(Component.literal("Might be a little harder to bite into").withStyle(ChatFormatting.GRAY));
                    super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
                }
            });

    /*public static final DeferredItem<Item> ENCHANTED_DIAMOND_CARROT = registerItemTest("enchanted_diamond_carrot",
            () -> new Item(new Item.Properties().food(CropFoodProperties.ENCHANTED_DIAMOND_CARROT).component(DataComponents.ENCHANTMENT_GLINT_OVERRIDE, true)) {
                @Override
                public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
                    tooltipComponents.add(Component.literal("Might be a little harder to bite into").withStyle(ChatFormatting.GRAY));
                    super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
                }
            }, ModCreativeTabs.TEST);*/

    public static final DeferredItem<Item> EGGS_SUNNY = ITEMS.register("eggs_sunny",
            () -> new Item(new Item.Properties().food(EggFoodProperties.SUNNY)));
    public static final DeferredItem<Item> EGGS_SCRAMBLED = ITEMS.register("eggs_scrambled",
            () -> new Item(new Item.Properties().food(EggFoodProperties.SCRAMBLED)));
    public static final DeferredItem<Item> EGGS_OMLETTE = ITEMS.register("eggs_omlette",
            () -> new Item(new Item.Properties().food(EggFoodProperties.OMLETTE)));



    // What a mess...
    public static final DeferredItem<Item> CHEESE_STEAK = ITEMS.register("cheese_steak",
            () -> new Item(new Item.Properties().food(CheeseFoodProperties.CHEESE_STEAK)));
    public static final DeferredItem<Item> CHEESE_PORK = ITEMS.register("cheese_pork",
            () -> new Item(new Item.Properties().food(CheeseFoodProperties.CHEESE_PORK)));
    public static final DeferredItem<Item> CHEESE_MUTTON = ITEMS.register("cheese_mutton",
            () -> new Item(new Item.Properties().food(CheeseFoodProperties.CHEESE_MUTTON)));
    public static final DeferredItem<Item> CHEESE_CHICKEN = ITEMS.register("cheese_chicken",
            () -> new Item(new Item.Properties().food(CheeseFoodProperties.CHEESE_CHICKEN)));
    public static final DeferredItem<Item> CHEESE_FRIES = ITEMS.register("cheese_fries",
            () -> new Item(new Item.Properties().food(CheeseFoodProperties.CHEESE_FRIES)));

    public static final DeferredItem<SandwichItem> STEAK_SANDWICH = ITEMS.register("steak_sandwich",
            () -> new SandwichItem(new Item.Properties().food(SandwichFoodProperties.STEAK_SANDWICH), Optional.of(Items.COOKED_BEEF), Optional.empty() ));
    public static final DeferredItem<SandwichItem> PORK_SANDWICH = ITEMS.register("pork_sandwich",
            () -> new SandwichItem(new Item.Properties().food(SandwichFoodProperties.PORK_SANDWICH), Optional.of(Items.COOKED_PORKCHOP), Optional.empty()));
    public static final DeferredItem<SandwichItem> MUTTON_SANDWICH = ITEMS.register("mutton_sandwich",
            () -> new SandwichItem(new Item.Properties().food(SandwichFoodProperties.MUTTON_SANDWICH), Optional.of(Items.COOKED_MUTTON), Optional.empty()));
    public static final DeferredItem<SandwichItem> CHICKEN_SANDWICH = ITEMS.register("chicken_sandwich",
            () -> new SandwichItem(new Item.Properties().food(SandwichFoodProperties.CHICKEN_SANDWICH), Optional.of(Items.COOKED_CHICKEN), Optional.empty()));
    
    
    public static final DeferredItem<SandwichItem> FRIES_SANDWICH = ITEMS.register("fries_sandwich",
            () -> new SandwichItem(new Item.Properties().food(SandwichFoodProperties.FRIES_SANDWICH), Optional.empty(), Optional.empty()).tag(ModTags.Items.POTATOES));
    public static final DeferredItem<SandwichItem> CHEESE_SANDWICH = ITEMS.register("cheese_sandwich",
            () -> new SandwichItem(new Item.Properties().food(SandwichFoodProperties.CHEESE_SANDWICH), Optional.of(MILK_CHEESE.get()), Optional.empty()));

    public static final DeferredItem<SandwichItem> CHEESE_STEAK_SANDWICH = ITEMS.register("cheese_steak_sandwich",
            () -> new SandwichItem(new Item.Properties().food(SandwichFoodProperties.CHEESE_STEAK_SANDWICH), Optional.of(Items.COOKED_BEEF), Optional.of(CHEESE_STEAK.get())));
    public static final DeferredItem<SandwichItem> CHEESE_PORK_SANDWICH = ITEMS.register("cheese_pork_sandwich",
            () -> new SandwichItem(new Item.Properties().food(SandwichFoodProperties.CHEESE_PORK_SANDWICH), Optional.of(Items.COOKED_PORKCHOP), Optional.of(CHEESE_PORK.get())));
    public static final DeferredItem<SandwichItem> CHEESE_MUTTON_SANDWICH = ITEMS.register("cheese_mutton_sandwich",
            () -> new SandwichItem(new Item.Properties().food(SandwichFoodProperties.MUTTON_CHEESE_SANDWICH), Optional.of(Items.COOKED_MUTTON), Optional.of(CHEESE_MUTTON.get())));
    public static final DeferredItem<SandwichItem> CHEESE_CHICKEN_SANDWICH = ITEMS.register("cheese_chicken_sandwich",
            () -> new SandwichItem(new Item.Properties().food(SandwichFoodProperties.CHICKEN_CHEESE_SANDWICH), Optional.of(Items.COOKED_CHICKEN), Optional.of(CHEESE_CHICKEN.get())));

    public static final DeferredItem<SandwichItem> CHEESE_FRIES_SANDWICH = ITEMS.register("cheese_fries_sandwich",
            () -> new SandwichItem(new Item.Properties().food(SandwichFoodProperties.CHEESE_FRIES_SANDWICH), Optional.empty(), Optional.of(CHEESE_FRIES.get())).tag(ModTags.Items.POTATOES));

    //endregion


    //region jams
    public static final DeferredItem<JamItem> JAM_BLUEBERRIES = registerJamItem("jam_blueberries",
            () -> new JamItem(BLUEBERRIES));
    public static final DeferredItem<JamItem> JAM_BLACKBERRIES = registerJamItem("jam_blackberries",
            () -> new JamItem(BLACKBERRIES));
    public static final DeferredItem<JamItem> JAM_SWEETBERRIES = registerJamItem("jam_sweetberries",
            () -> new JamItem(Items.SWEET_BERRIES));
    public static final DeferredItem<JamItem> JAM_STRAWBERRIES = registerJamItem("jam_strawberries",
            () -> new JamItem(STRAWBERRY));
    public static final DeferredItem<JamItem> JAM_TOMATOES = registerJamItem("jam_tomatoes",
            () -> new JamItem(TOMATO));
    //enregion

    /*private static boolean postRegisterRan = false;
    public static void postRegister(){
        NeoBallerite.LOGGER.debug("STARTED POST REGISTER");
        if (postRegisterRan) {
            NeoBallerite.LOGGER.error("CAN'T RUN postRegister() MORE THAN ONCE.");
            return;
        }
        for (DeferredHolder<Item, ? extends Item> item : ITEMS.getEntries()) {
            if (item.get() instanceof JamItem) {
                NeoBallerite.LOGGER.debug("Added {} entry to NeoJams.JAMS list. Success == {}", item, NeoJams.JAMS.add(item.get()));
            }
        }
        postRegisterRan = true;
    }*/

    /*public static final DeferredItem<Item> LEAD_SHIELD = ITEMS.register("lead_shield",
            () -> new ShieldItem(new Item.Properties().durability(420).component(DataComponents.BANNER_PATTERNS, BannerPatternLayers.EMPTY)));
*/
}
