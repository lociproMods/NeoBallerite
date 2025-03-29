package com.locipro.neoballerite.item;


import com.locipro.neoballerite.Config;
import com.locipro.neoballerite.block.ModBlocks;
import com.locipro.neoballerite.component.NeoDataComponents;
import com.locipro.neoballerite.item.armor.*;
import com.locipro.neoballerite.item.custom.*;
import com.locipro.neoballerite.item.tool.*;
import com.locipro.neoballerite.misc.food.*;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.Consumables;
import net.minecraft.world.item.equipment.ArmorType;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.List;
import java.util.function.Function;
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
    private static DeferredItem<JamItem> registerJamItem(String name, Function<Item.Properties, ? extends JamItem> func) {
        DeferredItem<JamItem> item = ITEMS.registerItem(name, func, new Item.Properties());
        NeoJams.JAMS.add(item);
        return item;
    }

/*    public static final DeferredItem<Item> BALL_DOWSER = ITEMS.register("ball_dowser",
            () -> new BallDowserItem(new Item.Properties()));*/
    public static final DeferredItem<Item> COMPRESSED_BALLERITE_INGOT = ITEMS.registerSimpleItem("compressed_ballerite_ingot");

    public static final DeferredItem<Item> RAW_BALLERITE = ITEMS.registerItem("raw_ballerite",
            (p) -> new Item(p){
                @Override
                public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
                    tooltipComponents.add( Component.literal("Raw ballerite... Suspicious mineral").withStyle(ChatFormatting.GRAY));
                    tooltipComponents.add( Component.literal("Probably shouldn't eat it..").withStyle(ChatFormatting.DARK_GRAY));
                    if (Screen.hasShiftDown()) {
                        tooltipComponents.add( Component.literal("--Cookable").withStyle(ChatFormatting.AQUA));
                        tooltipComponents.add( Component.literal("--Compressible").withStyle(ChatFormatting.AQUA));
                    }
                }
            }, new Item.Properties().food(BalleriteFoodProperties.RAW_BALLERITE)
                    .component(DataComponents.CONSUMABLE, BalleriteFoodProperties.RAW_BALLERITE_CONSUMABLE));
    public static final DeferredItem<Item> COOKED_BALLERITE = ITEMS.registerItem("cooked_ballerite",
            (p) -> new Item(p){
                @Override
                public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
                    tooltipComponents.add(  Component.literal("Cooked ballerite... still weird.").withStyle(ChatFormatting.GRAY));
                    tooltipComponents.add(  Component.literal("Probably shouldn't eat it..").withStyle(ChatFormatting.DARK_GRAY));
                    if (Screen.hasShiftDown()) {
                        tooltipComponents.add( Component.literal("--Compressible").withStyle(ChatFormatting.AQUA));
                    }
                }
            }, new Item.Properties().food(BalleriteFoodProperties.COOKED_BALLERITE)
                    .component(DataComponents.CONSUMABLE, BalleriteFoodProperties.COOKED_BALLERITE_CONSUMABLE));
    public static final DeferredItem<Item> CHARRED_BALLERITE = ITEMS.registerItem("charred_ballerite",
            (p) -> new Item(p){
                @Override
                public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
                    tooltipComponents.add( Component.literal("Charred Ball.").withStyle(ChatFormatting.GRAY));
                    if (Screen.hasShiftDown()) {
                        tooltipComponents.add( Component.literal("--Biofuel").withStyle(ChatFormatting.DARK_GREEN));
                    }
                }
            });

    public static final DeferredItem<Item> LEAD_INGOT = ITEMS.registerSimpleItem("lead_ingot");
    public static final DeferredItem<Item> RAW_LEAD = ITEMS.registerSimpleItem("raw_lead");
    public static final DeferredItem<Item> LEAD_NUGGET = ITEMS.registerSimpleItem("lead_nugget");

    

    public static final DeferredItem<SignItem> WITHERED_SIGN = ITEMS.registerItem("withered_sign_item",
            (p) -> new SignItem(ModBlocks.WITHERED_SIGN.get(), ModBlocks.WITHERED_WALL_SIGN.get(), p), new Item.Properties().stacksTo(16).useBlockDescriptionPrefix());

    public static final DeferredItem<SignItem> WITHERED_HANGING_SIGN = ITEMS.registerItem("withered_hanging_sign_item",
            (p) -> new HangingSignItem(ModBlocks.WITHERED_HANGING_SIGN.get(), ModBlocks.WITHERED_WALL_HANGING_SIGN.get(), p), new Item.Properties().stacksTo(16).useBlockDescriptionPrefix());


    public static final DeferredItem<SignItem> STAR_SIGN = ITEMS.registerItem("star_sign_item",
            (p) -> new SignItem(ModBlocks.STAR_SIGN.get(), ModBlocks.STAR_WALL_SIGN.get(), p), new Item.Properties().stacksTo(16).useBlockDescriptionPrefix());
    public static final DeferredItem<SignItem> STAR_HANGING_SIGN = ITEMS.registerItem("star_hanging_sign_item",
            (p) -> new HangingSignItem(ModBlocks.STAR_HANGING_SIGN.get(), ModBlocks.STAR_WALL_HANGING_SIGN.get(), p), new Item.Properties().stacksTo(16 ).useBlockDescriptionPrefix());

    //region ballerite tools
    public static final DeferredItem<SwordItem> BALLERITE_SWORD = ITEMS.registerItem("ballerite_sword",
            (p) -> new BalleriteSwordItem(
                    NeoToolMaterials.BALLERITE_TIER,
                    3,
                    -2.4f,
                    p
            ));
    public static final DeferredItem<PickaxeItem> BALLERITE_PICKAXE = ITEMS.registerItem("ballerite_pickaxe",
            (p) -> new BalleritePickaxeItem(
                    NeoToolMaterials.BALLERITE_TIER,
                    -2.8f,
                    1,
                    p
            ));
    public static final DeferredItem<AxeItem> BALLERITE_AXE = ITEMS.registerItem("ballerite_axe",
            (p) -> new BalleriteAxeItem(
                    NeoToolMaterials.BALLERITE_TIER,
                    6f,
                    -3f,
                    p
            ));
    public static final DeferredItem<ShovelItem> BALLERITE_SHOVEL = ITEMS.registerItem("ballerite_shovel",
            (p) -> new BalleriteShovelItem(
                    NeoToolMaterials.BALLERITE_TIER,
                    1.6f,
                    -2.9f,
                    p
            ));
    public static final DeferredItem<HoeItem> BALLERITE_HOE = ITEMS.registerItem("ballerite_hoe",
            (p) -> new HoeItem(
                    NeoToolMaterials.BALLERITE_TIER,
                    -1f,
                    1f,
                    p
            ));


    public static final DeferredItem<ArmorItem> BALLERITE_HELMET = ITEMS.registerItem("ballerite_helmet",
            (p) -> new SetEffectsItem(
                    NeoArmorMaterials.BALLERITE,
                    ArmorType.HELMET,
                    p,
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
            }.effectParticles().ambient());
    public static final DeferredItem<ArmorItem> BALLERITE_CHESTPLATE = ITEMS.registerItem("ballerite_chestplate",
            (p) -> new BalleriteArmorItem(
                    ArmorType.CHESTPLATE,
                    p
            ));
    public static final DeferredItem<ArmorItem> BALLERITE_LEGGINGS = ITEMS.registerItem("ballerite_leggings",
            (p) -> new BalleriteArmorItem(
                    ArmorType.LEGGINGS,
                    p
            ));
    public static final DeferredItem<ArmorItem> BALLERITE_BOOTS = ITEMS.registerItem("ballerite_boots",
            (p) -> new BalleriteArmorItem(
                    ArmorType.BOOTS,
                    p
            ));


    //TODO  https://docs.neoforged.net/docs/items/armor/#:~:text=render%20the%20equipment.-,.setAsset,-(ResourceKey.
    public static final DeferredItem<Item> BALLERITE_HORSE_ARMOR = ITEMS.registerItem("ballerite_horse_armor",
            (p) -> new AnimalArmorItem(NeoArmorMaterials.BALLERITE, AnimalArmorItem.BodyType.EQUESTRIAN, p));

    public static final DeferredItem<ArmorItem> LEAVES_BOOTS = ITEMS.registerItem("leaves_boots",
            (p) -> new BushNegatingArmorItem(
                    NeoArmorMaterials.LEAVES,
                    ArmorType.BOOTS,
                    p
            ), new Item.Properties()
                    .durability(77)
                    .component(NeoDataComponents.CAN_NEGATE_BUSH_SLOW.value(), true)
                    .component(NeoDataComponents.ADDED_DURABILITY.value(), 0));

    //endregion


    //region Lead
    public static final DeferredItem<SwordItem> LEAD_SWORD = ITEMS.registerItem("lead_sword",
            (p) -> new PoisonousSwordItem(
                    NeoToolMaterials.LEAD_TIER,
                    3f,
                    -2.4f,
                    p,
                    Config.lead_sword_does_poison));

    public static final DeferredItem<PickaxeItem> LEAD_PICKAXE = ITEMS.registerItem("lead_pickaxe",
            (p) -> new PickaxeItem(
                    NeoToolMaterials.LEAD_TIER,
                    1,
                    -2.8f,
                    p
            ));
    public static final DeferredItem<AxeItem> LEAD_AXE = ITEMS.registerItem("lead_axe",
            (p) -> new AxeItem(
                    NeoToolMaterials.LEAD_TIER,
                    6f,
                    -3f,
                    p
            ));
    public static final DeferredItem<ShovelItem> LEAD_SHOVEL = ITEMS.registerItem("lead_shovel",
            (p) -> new ShovelItem(
                    NeoToolMaterials.LEAD_TIER,
                    1.4f,
                    -3.1f,
                    p
            ));
    public static final DeferredItem<HoeItem> LEAD_HOE = ITEMS.registerItem("lead_hoe",
            (p) -> new HoeItem(
                    NeoToolMaterials.LEAD_TIER,
                    0f,
                    -2f,
                    p
            ));



    public static final DeferredItem<ArmorItem> LEAD_HELMET = ITEMS.registerItem("lead_helmet",
            (p) -> new SetEffectsItem(
                    NeoArmorMaterials.LEAD,
                    ArmorType.HELMET,
                    p,
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
            }.effectParticles().ambient());
    public static final DeferredItem<ArmorItem> LEAD_CHESTPLATE = ITEMS.registerItem("lead_chestplate",
            (p) -> new LeadArmorItem(
                    ArmorType.CHESTPLATE,
                    p
            ));
    public static final DeferredItem<ArmorItem> LEAD_LEGGINGS = ITEMS.registerItem("lead_leggings",
            (p) -> new LeadArmorItem(
                    ArmorType.LEGGINGS,
                    p
            ));
    public static final DeferredItem<ArmorItem> LEAD_BOOTS = ITEMS.registerItem("lead_boots",
            (p) -> new LeadArmorItem(
                    ArmorType.BOOTS,
                    p
            ));

    //endregion
    
    //region CLAYMORES
    public static final DeferredItem<NeoClaymoreItem> WOODEN_CLAYMORE = ITEMS.registerItem("wooden_claymore",
            (p) -> new NeoClaymoreItem(ToolMaterial.WOOD, p));
    public static final DeferredItem<NeoClaymoreItem> STONE_CLAYMORE = ITEMS.registerItem("stone_claymore",
            (p) -> new NeoClaymoreItem(ToolMaterial.STONE, p));
    public static final DeferredItem<NeoClaymoreItem> IRON_CLAYMORE = ITEMS.registerItem("iron_claymore",
            (p) -> new NeoClaymoreItem(ToolMaterial.IRON, p));
    public static final DeferredItem<NeoClaymoreItem> LEAD_CLAYMORE = ITEMS.registerItem("lead_claymore",
            (p) -> new NeoClaymoreItem(NeoToolMaterials.LEAD_TIER, p).doesPoison());
    public static final DeferredItem<NeoClaymoreItem> GOLD_CLAYMORE = ITEMS.registerItem("gold_claymore",
            (p) -> new NeoClaymoreItem(ToolMaterial.GOLD, p));
    public static final DeferredItem<NeoClaymoreItem> DIAMOND_CLAYMORE = ITEMS.registerItem("diamond_claymore",
            (p) -> new NeoClaymoreItem(ToolMaterial.DIAMOND, p) {
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
    public static final DeferredItem<NeoClaymoreItem> NETHERITE_CLAYMORE = ITEMS.registerItem("netherite_claymore",
            (p) -> new NeoClaymoreItem(ToolMaterial.NETHERITE, p));
    public static final DeferredItem<NeoClaymoreItem> BALLERITE_CLAYMORE = ITEMS.registerItem("ballerite_claymore",
            (p) -> new NeoClaymoreItem(NeoToolMaterials.BALLERITE_TIER, p) {
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
    public static final DeferredItem<Item> KNIFE = ITEMS.registerItem("knife",
            (p) -> new KnifeItem(p, 3f), new Item.Properties().durability(32).setNoCombineRepair());

    public static final DeferredItem<Item> DIAMOND_KNIFE = ITEMS.registerItem("diamond_knife",
            (p) -> new KnifeItem(p, 4.5f), new Item.Properties().durability(524).setNoCombineRepair());


    public static final DeferredItem<BlockItem> BLUEBERRIES = ITEMS.registerSimpleBlockItem("blueberries",
            ModBlocks.BLUEBERRY_BUSH, new Item.Properties().food(BerryFoodProperties.BLUE)
                    .component(DataComponents.CONSUMABLE, BerryFoodProperties.DEFAULT_BERRY));
    public static final DeferredItem<BlockItem> BLACKBERRIES = ITEMS.registerSimpleBlockItem("blackberries",
            ModBlocks.BLACKBERRY_BUSH, new Item.Properties().food(BerryFoodProperties.BLACK)
                    .component(DataComponents.CONSUMABLE, BerryFoodProperties.DEFAULT_BERRY));

    public static final DeferredItem<Item> STRAWBERRY = ITEMS.registerSimpleItem("strawberry",
            new Item.Properties().food(BerryFoodProperties.STRAWBERRIES)
                    .component(DataComponents.CONSUMABLE, BerryFoodProperties.DEFAULT_BERRY));
    public static final DeferredItem<Item> UNRIPE_STRAWBERRY = ITEMS.registerSimpleItem("unripe_strawberry",
            new Item.Properties().food(BerryFoodProperties.UNRIPE_STRAWBERRIES)
                    .component(DataComponents.CONSUMABLE, BerryFoodProperties.UNRIPE_STRAWBERIES_CONSUMABLE));
    public static final DeferredItem<BlockItem> STRAWBERRY_SEEDS = ITEMS.registerSimpleBlockItem("strawberry_seeds",
            ModBlocks.STRAWBERRY_BUSH, new Item.Properties());

    public static final DeferredItem<Item> TOMATO = ITEMS.registerSimpleItem("tomato",
            new Item.Properties().food(CropFoodProperties.TOMATO));
    public static final DeferredItem<Item> GRILLED_TOMATO = ITEMS.registerSimpleItem("grilled_tomato",
            new Item.Properties().food(CropFoodProperties.GRILLED_TOMATO));
    public static final DeferredItem<BlockItem> TOMATO_SEEDS = ITEMS.registerSimpleBlockItem("tomato_seeds",
            ModBlocks.TOMATO_CROP, new Item.Properties());

    public static final DeferredItem<Item> EGGPLANT = ITEMS.registerSimpleItem("eggplant",
            new Item.Properties().food(CropFoodProperties.EGGPLANT));
    public static final DeferredItem<Item> GRILLED_EGGPLANT = ITEMS.registerSimpleItem("grilled_eggplant",
            new Item.Properties().food(CropFoodProperties.GRILLED_EGGPLANT));
    public static final DeferredItem<BlockItem> EGGPLANT_SEEDS = ITEMS.registerSimpleBlockItem("eggplant_seeds",
            ModBlocks.EGGPLANT_CROP, new Item.Properties());

    public static final DeferredItem<BlockItem> SWEET_POTATO = ITEMS.registerSimpleBlockItem("sweet_potato",
            ModBlocks.SWEET_POTATO_CROP, new Item.Properties().food(CropFoodProperties.SWEET_POTATO));
    public static final DeferredItem<Item> BAKED_SWEET_POTATO = ITEMS.registerSimpleItem("baked_sweet_potato",
            new Item.Properties().food(CropFoodProperties.BAKED_SWEET_POTATO));


    public static final DeferredItem<BlockItem> CORN_KERNELS = ITEMS.registerSimpleBlockItem("corn_kernels",
            ModBlocks.CORN_CROP, new Item.Properties().food(CropFoodProperties.KERNELS)
                    .component(DataComponents.CONSUMABLE, BerryFoodProperties.DEFAULT_BERRY));
    public static final DeferredItem<Item> CORN_COB = ITEMS.registerSimpleItem("corn_cob",
            new Item.Properties().food(CropFoodProperties.COB));
    public static final DeferredItem<Item> GRILLED_CORN_COB = ITEMS.registerSimpleItem("grilled_corn_cob",
            new Item.Properties().food(CropFoodProperties.GRILLED_COB));



    public static final DeferredItem<Item> MILK_VILE = ITEMS.registerSimpleItem("milk_vile",
            new Item.Properties()
                    .food(DrinkFoodProperties.MILK_VILE)
                    .craftRemainder(Items.GLASS_BOTTLE)
                    .component(DataComponents.CONSUMABLE, Consumables.MILK_BUCKET)
                    .usingConvertsTo(Items.GLASS_BOTTLE));

    public static final DeferredItem<CheeseItem> MILK_CHEESE = ITEMS.registerItem("milk_cheese",
            (p) -> new CheeseItem(p, CheeseItem.CheeseTypes.OVERWORLD), new Item.Properties().food(CheeseFoodProperties.CHEESE)
                    .component(DataComponents.CONSUMABLE, BerryFoodProperties.DEFAULT_BERRY));
    public static final DeferredItem<CheeseItem> WARPED_CHEESE = ITEMS.registerItem("warped_cheese",
            (p) -> new CheeseItem(p, CheeseItem.CheeseTypes.NETHER), new Item.Properties().food(CheeseFoodProperties.WARPED)
                    .component(DataComponents.CONSUMABLE, CheeseFoodProperties.WARPED_C));

    public static final DeferredItem<Item> IRON_CARROT = ITEMS.registerItem("iron_carrot",
            (p) -> new Item(p) {
                @Override
                public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
                    tooltipComponents.add(Component.literal("Don't have to worry about anemia, heh.").withStyle(ChatFormatting.GRAY));
                    super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
                }
            }, new Item.Properties().food(CropFoodProperties.IRON_CARROT)
                    .component(DataComponents.CONSUMABLE, CropFoodProperties.IRON_CARROT_C));
    public static final DeferredItem<Item> DIAMOND_CARROT = ITEMS.registerItem("diamond_carrot",
            (p) -> new Item(p) {
                @Override
                public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
                    tooltipComponents.add(Component.literal("Might be a little hard to bite into").withStyle(ChatFormatting.GRAY));
                    super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
                }
            }, new Item.Properties().food(CropFoodProperties.DIAMOND_CARROT)
                    .component(DataComponents.CONSUMABLE, CropFoodProperties.DIAMOND_CARROT_C));


    public static final DeferredItem<Item> ENCHANTED_DIAMOND_CARROT = ITEMS.registerItem("enchanted_diamond_carrot",
            (p) -> new Item(p) {
                @Override
                public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
                    tooltipComponents.add(Component.literal("Might be a little harder to bite into").withStyle(ChatFormatting.GRAY));
                    super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
                }
            }, new Item.Properties().food(CropFoodProperties.ENCHANTED_DIAMOND_CARROT)
                    .component(DataComponents.ENCHANTMENT_GLINT_OVERRIDE, true)
                    .component(DataComponents.CONSUMABLE, CropFoodProperties.ENCHANTED_DIAMOND_CARROT_C));


    public static final DeferredItem<Item> EGGS_SUNNY = ITEMS.registerSimpleItem("eggs_sunny",
            new Item.Properties().food(EggFoodProperties.SUNNY));
    public static final DeferredItem<Item> EGGS_SCRAMBLED = ITEMS.registerSimpleItem("eggs_scrambled",
            new Item.Properties().food(EggFoodProperties.SCRAMBLED));
    public static final DeferredItem<Item> EGGS_OMLETTE = ITEMS.registerSimpleItem("eggs_omlette",
            new Item.Properties().food(EggFoodProperties.OMLETTE));



    public static final DeferredItem<SandwichItem> SANDWICH = ITEMS.registerItem("default_sandwich", properties -> new SandwichItem());
    public static final DeferredItem<Item> CORN_BREAD = ITEMS.registerSimpleItem("corn_bread",
            new Item.Properties().food(CropFoodProperties.CORN_BREAD));
    public static final DeferredItem<Item> CORN_BREAD_SLICE = ITEMS.registerSimpleItem("corn_bread_slice",
            new Item.Properties().food(CropFoodProperties.CORN_BREAD));
    //endregion


    //region jams
    public static final DeferredItem<JamItem> JAM_BLUEBERRIES = registerJamItem("jam_blueberries",
            (p) -> new JamItem(BLUEBERRIES));
    public static final DeferredItem<JamItem> JAM_BLACKBERRIES = registerJamItem("jam_blackberries",
            (p) -> new JamItem(BLACKBERRIES));
    public static final DeferredItem<JamItem> JAM_SWEETBERRIES = registerJamItem("jam_sweetberries",
            (p) -> new JamItem(Items.SWEET_BERRIES));
    public static final DeferredItem<JamItem> JAM_STRAWBERRIES = registerJamItem("jam_strawberries",
            (p) -> new JamItem(STRAWBERRY));
    public static final DeferredItem<JamItem> JAM_TOMATOES = registerJamItem("jam_tomatoes",
            (p) -> new JamItem(TOMATO));
    public static final DeferredItem<JamItem> JAM_SWEET_POTATOES = registerJamItem("jam_sweet_potatoes",
            (p) -> new JamItem(SWEET_POTATO));
    //enregion



    /*public static final DeferredItem<Item> LEAD_SHIELD = ITEMS.registerItem("lead_shield",
            () -> new ShieldItem(new Item.Properties().durability(420).component(DataComponents.BANNER_PATTERNS, BannerPatternLayers.EMPTY)));
*/
}
