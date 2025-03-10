package com.locipro.neoballerite.block;


import static com.locipro.neoballerite.NeoBallerite.MODID;


import com.locipro.neoballerite.block.custom.*;
import com.locipro.neoballerite.item.ModItems;
import com.locipro.neoballerite.misc.food.BalleriteFoodProperties;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.*;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.neoforged.neoforge.common.ItemAbilities;
import net.neoforged.neoforge.common.ItemAbility;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(MODID);

    private static <T extends Block> void registerBlockItem(String name, DeferredBlock<T> block) {
        ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }
    private static <T extends Block> void registerBlockItem(String name, DeferredBlock<T> block, Item.Properties properties) {
        ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), properties));
    }
    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Supplier<T> block) {
        DeferredBlock<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }
    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Supplier<T> block, Item.Properties properties) {
        DeferredBlock<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn, properties);
        return toReturn;
    }
    private static <T extends Block> DeferredBlock<T> registerBlockWithoutItem(String name, Supplier<T> block) {
        return BLOCKS.register(name, block);
    }
    

    // Wood Types should have a mod id qualifier
    // I think block set types are "a combined place where mojang can define the sounds and behaviour of a block set" (Dr.gigaherz 8/12/23 neoforge discord)
    
    //public static final BlockSetType WITHERED_SET = BlockSetType.register(new BlockSetType("withered"));
            
    public static final WoodType WITHERED = WoodType.register(new WoodType(MODID + ":withered", BlockSetType.OAK));
            
    //public static final BlockSetType STAR_SET = BlockSetType.register(new BlockSetType("star"));
            
    public static final WoodType STAR = WoodType.register(new WoodType(MODID + ":star", BlockSetType.ACACIA));





    public static final DeferredBlock<Block> RAW_BALLERITE_BLOCK = registerBlock("raw_ballerite_block",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.MUD).strength(0.5f, 0.5f).sound(SoundType.FUNGUS)) {
                @Override
                public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
                    tooltipComponents.add( Component.literal("Raw Messy block....").withStyle(ChatFormatting.GRAY));
                    tooltipComponents.add( Component.literal("Why did you make this?? *don't eat it*").withStyle(ChatFormatting.DARK_GRAY));
                    if (Screen.hasShiftDown()) {
                        tooltipComponents.add( Component.literal("--Cookable").withStyle(ChatFormatting.AQUA));
                    }
                }
            }, new Item.Properties().food(BalleriteFoodProperties.RAW_BALLERITE_BLOCK)
                    .component(DataComponents.CONSUMABLE, BalleriteFoodProperties.RAW_BALLERITE_BLOCK_CONSUMABLE));

    public static final DeferredBlock<Block> COOKED_BALLERITE_BLOCK = registerBlock("cooked_ballerite_block",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.MUD)
                    .strength(0.4f, 0.5f)
                    .sound(SoundType.FUNGUS)
                    .speedFactor(1.1f)) {
                @Override
                public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
                    tooltipComponents.add( Component.literal("Warm block from this weird mineral called ballerite...").withStyle(ChatFormatting.GRAY));
                    tooltipComponents.add( Component.literal("Maybe try cooking it again? OR, just eat it..").withStyle(ChatFormatting.GRAY));
                    if (Screen.hasShiftDown()) {
                        tooltipComponents.add( Component.literal("-Running speed faster than grass").withStyle(ChatFormatting.BLACK));
                    }
                }
            }, new Item.Properties().food(BalleriteFoodProperties.COOKED_BALLERITE_BLOCK)
                    .component(DataComponents.CONSUMABLE, BalleriteFoodProperties.COOKED_BALLERITE_BLOCK_CONSUMABLE)); // BlockItem properties

    public static final DeferredBlock<Block> BURNT_BALLERITE_BLOCK = registerBlock("burnt_ballerite_block",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.COAL_BLOCK)
                    .strength(5.0f, 4.0f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.TUFF)
                    .mapColor(MapColor.NETHER)
                    .speedFactor(0.4f)){
                @Override
                public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
                    tooltipComponents.add( Component.literal("How did you get here?").withStyle(ChatFormatting.GRAY));
                    tooltipComponents.add( Component.literal("Just a few more steps...").withStyle(ChatFormatting.DARK_GRAY));
                    if (Screen.hasShiftDown()) {
                        tooltipComponents.add(Component.literal("--Organic").withStyle(ChatFormatting.DARK_GREEN));
                    }
                }
            });
    public static final DeferredBlock<Block> CHARRED_BALLERITE_BLOCK = registerBlock("charred_ballerite_block",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(5.0f, 6.0f)
                    .requiresCorrectToolForDrops()
                    .ignitedByLava()
                    .sound(SoundType.BASALT)
                    .mapColor(MapColor.CRIMSON_NYLIUM)){
                @Override
                public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
                    tooltipComponents.add(Component.literal("Charred Ball Block.").withStyle(ChatFormatting.GRAY));
                    tooltipComponents.add(Component.literal("Just one more time...").withStyle(ChatFormatting.DARK_AQUA));
                    if (Screen.hasShiftDown()) {
                        tooltipComponents.add(Component.literal("--Biofuel").withStyle(ChatFormatting.DARK_GREEN));
                    }
                }
            });

    public static final DeferredBlock<Block> COMPRESSED_BALLERITE_BLOCK = registerBlock("compressed_ballerite_block",
            () -> new CompressedBalleriteBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK)));


    public static final DeferredBlock<Block> BALLERITE_ORE = registerBlock("ballerite_ore",
            () -> new DropExperienceBlock(UniformInt.of(3, 4), BlockBehaviour.Properties.ofFullCopy(Blocks.COPPER_ORE)));
   public static final DeferredBlock<Block> LEAD_ORE = registerBlock("lead_ore",
            () -> new DropExperienceBlock(UniformInt.of(3, 4), BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_ORE)));
    public static final DeferredBlock<Block> DEEPSLATE_LEAD_ORE = registerBlock("deepslate_lead_ore",
            () -> new DropExperienceBlock(UniformInt.of(3, 4), BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE_IRON_ORE)));
   public static final DeferredBlock<Block> LEAD_BLOCK = registerBlock("lead_block",
           () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK)));
   public static final DeferredBlock<Block> RAW_LEAD_BLOCK = registerBlock("raw_lead_block",
           () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.RAW_IRON_BLOCK)));

    // There's something like "BlockFamily" ? maybe check that out

    public static final DeferredBlock<SaplingBlock> WITHERED_SAPLING = registerBlock("withered_sapling",
            () -> new SaplingBlock(NeoTreeGrowers.WITHERED, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SAPLING)));


    public static final DeferredBlock<RotatedPillarBlock> WITHERED_LOG = registerBlock("withered_log",
            () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LOG)) {
                @Override
                public @Nullable BlockState getToolModifiedState(BlockState state, UseOnContext context, ItemAbility itemAbility, boolean simulate) {
                    if (ItemAbilities.AXE_STRIP == itemAbility) {
                        return STRIPPED_WITHERED_LOG.get().defaultBlockState();
                    }
                    return super.getToolModifiedState(state, context, itemAbility, simulate);
                }
            });
    public static final DeferredBlock<RotatedPillarBlock> STRIPPED_WITHERED_LOG = registerBlock("stripped_withered_log",
            () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_LOG)));

    public static final DeferredBlock<RotatedPillarBlock> WITHERED_WOOD = registerBlock("withered_wood",
            () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WOOD)) {
                @Override
                public @Nullable BlockState getToolModifiedState(BlockState state, UseOnContext context, ItemAbility itemAbility, boolean simulate) {
                    if (ItemAbilities.AXE_STRIP == itemAbility) {
                        return STRIPPED_WITHERED_WOOD.get().defaultBlockState();
                    }
                    return super.getToolModifiedState(state, context, itemAbility, simulate);
                }
            });
    public static final DeferredBlock<RotatedPillarBlock> STRIPPED_WITHERED_WOOD = registerBlock("stripped_withered_wood",
            () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_WOOD)));


    public static final DeferredBlock<Block> WITHERED_LEAVES = registerBlock("withered_leaves",
            () -> new CustomDistanceLeavesBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES), 10));
//    public static final DeferredBlock<SaplingBlock> WITHERED_SAPLING = registerBlock("withered_sapling",
//            () -> new SaplingBlock())

    public static final DeferredBlock<Block> WITHERED_PLANKS = registerBlock("withered_planks",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)));

    public static final DeferredBlock<StairBlock> WITHERED_STAIRS = registerBlock("withered_stairs",
            () -> new StairBlock(WITHERED_PLANKS.get().defaultBlockState(),  BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_STAIRS)));
    public static final DeferredBlock<SlabBlock> WITHERED_SLAB = registerBlock("withered_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_STAIRS)));

    public static final DeferredBlock<PressurePlateBlock> WITHERED_PRESSURE_PLATE = registerBlock("withered_pressure_plate",
            () -> new PressurePlateBlock(BlockSetType.OAK,  BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SLAB)));
    public static final DeferredBlock<ButtonBlock> WITHERED_BUTTON = registerBlock("withered_button",
            () -> new ButtonBlock(BlockSetType.OAK, 20, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_BUTTON).noCollission()));

    public static final DeferredBlock<FenceBlock> WITHERED_FENCE = registerBlock("withered_fence",
            () -> new FenceBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE)));
    public static final DeferredBlock<FenceGateBlock> WITHERED_FENCE_GATE = registerBlock("withered_fence_gate",
            () -> new FenceGateBlock(WITHERED, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE_GATE)));


    public static final DeferredBlock<DoorBlock> WITHERED_DOOR = registerBlock("withered_door",
            () -> new DoorBlock(BlockSetType.OAK, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_DOOR).noOcclusion()));
    public static final DeferredBlock<TrapDoorBlock> WITHERED_TRAPDOOR = registerBlock("withered_trapdoor",
            () -> new TrapDoorBlock(BlockSetType.OAK, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE_GATE).noOcclusion()));

    public static final DeferredBlock<StandingSignBlock> WITHERED_SIGN = registerBlock("withered_sign",
            () -> new StandingSignBlockFix(
                    WITHERED,
                    BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SIGN),
                    ModItems.WITHERED_SIGN::get
            ));
    public static final DeferredBlock<CeilingHangingSignBlock> WITHERED_HANGING_SIGN = registerBlock("withered_hanging_sign",
            () -> new CeilingHangingSignBlockFix(
                    WITHERED,
                    BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_HANGING_SIGN),
                    ModItems.WITHERED_HANGING_SIGN::get
            ));
    
    
    public static final DeferredBlock<WallSignBlock> WITHERED_WALL_SIGN = registerBlock("withered_wall_sign",
            () -> new WallSignBlockFix(
                    WITHERED,
                    BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WALL_SIGN),
                    ModItems.WITHERED_SIGN::get
            ));

    public static final DeferredBlock<WallHangingSignBlock> WITHERED_WALL_HANGING_SIGN = registerBlock("withered_wall_hanging_sign",
            () -> new WallHangingSignBlockFix(
                    WITHERED,
                    BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WALL_HANGING_SIGN),
                    ModItems.WITHERED_HANGING_SIGN::get
            ));
    


    /* 
    *  
    );*/



    public static final DeferredBlock<SaplingBlock> STAR_SAPLING = registerBlock("star_sapling",
            () -> new SaplingBlock(NeoTreeGrowers.STAR, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SAPLING)));


    public static final DeferredBlock<RotatedPillarBlock> STAR_LOG = registerBlock("star_log",
            () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LOG)) {
                @Override
                public @Nullable BlockState getToolModifiedState(BlockState state, UseOnContext context, ItemAbility itemAbility, boolean simulate) {
                    if (ItemAbilities.AXE_STRIP == itemAbility) {
                        return STRIPPED_STAR_LOG.get().defaultBlockState();
                    }
                    return super.getToolModifiedState(state, context, itemAbility, simulate);
                }
            });
    public static final DeferredBlock<RotatedPillarBlock> STRIPPED_STAR_LOG = registerBlock("stripped_star_log",
            () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_ACACIA_LOG)));

    public static final DeferredBlock<RotatedPillarBlock> STAR_WOOD = registerBlock("star_wood",
            () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.ACACIA_WOOD)) {
                @Override
                public @Nullable BlockState getToolModifiedState(BlockState state, UseOnContext context, ItemAbility itemAbility, boolean simulate) {
                    if (ItemAbilities.AXE_STRIP == itemAbility) {
                        return STRIPPED_STAR_WOOD.get().defaultBlockState();
                    }
                    return super.getToolModifiedState(state, context, itemAbility, simulate);
                }
            });
    public static final DeferredBlock<RotatedPillarBlock> STRIPPED_STAR_WOOD = registerBlock("stripped_star_wood",
            () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_ACACIA_WOOD)));


    public static final DeferredBlock<Block> STAR_LEAVES = registerBlock("star_leaves",
            () -> new CustomDistanceLeavesBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.ACACIA_LEAVES).noCollission().noOcclusion().lightLevel(state -> 10), 12));
//    public static final DeferredBlock<SaplingBlock> STAR_SAPLING = registerBlock("star_sapling",
//            () -> new SaplingBlock())

    public static final DeferredBlock<Block> STAR_PLANKS = registerBlock("star_planks",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.ACACIA_PLANKS)));

    public static final DeferredBlock<StairBlock> STAR_STAIRS = registerBlock("star_stairs",
            () -> new StairBlock(STAR_PLANKS.get().defaultBlockState(),  BlockBehaviour.Properties.ofFullCopy(Blocks.ACACIA_STAIRS)));
    public static final DeferredBlock<SlabBlock> STAR_SLAB = registerBlock("star_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.ACACIA_STAIRS)));

    public static final DeferredBlock<PressurePlateBlock> STAR_PRESSURE_PLATE = registerBlock("star_pressure_plate",
            () -> new PressurePlateBlock(BlockSetType.ACACIA,  BlockBehaviour.Properties.ofFullCopy(Blocks.ACACIA_SLAB)));
    public static final DeferredBlock<ButtonBlock> STAR_BUTTON = registerBlock("star_button",
            () -> new ButtonBlock(BlockSetType.ACACIA, 20, BlockBehaviour.Properties.ofFullCopy(Blocks.ACACIA_BUTTON).noCollission()));

    public static final DeferredBlock<FenceBlock> STAR_FENCE = registerBlock("star_fence",
            () -> new FenceBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.ACACIA_FENCE)));
    public static final DeferredBlock<FenceGateBlock> STAR_FENCE_GATE = registerBlock("star_fence_gate",
            () -> new FenceGateBlock(STAR, BlockBehaviour.Properties.ofFullCopy(Blocks.ACACIA_FENCE_GATE)));


    public static final DeferredBlock<DoorBlock> STAR_DOOR = registerBlock("star_door",
            () -> new DoorBlock(BlockSetType.ACACIA, BlockBehaviour.Properties.ofFullCopy(Blocks.ACACIA_DOOR).noOcclusion()));
    public static final DeferredBlock<TrapDoorBlock> STAR_TRAPDOOR = registerBlock("star_trapdoor",
            () -> new TrapDoorBlock(BlockSetType.ACACIA, BlockBehaviour.Properties.ofFullCopy(Blocks.ACACIA_FENCE_GATE).noOcclusion()));

    public static final DeferredBlock<StandingSignBlock> STAR_SIGN = registerBlock("star_sign",
            () -> new StandingSignBlockFix(
                    STAR,
                    BlockBehaviour.Properties.ofFullCopy(Blocks.ACACIA_SIGN),
                    ModItems.STAR_SIGN::get
            ));
    public static final DeferredBlock<CeilingHangingSignBlock> STAR_HANGING_SIGN = registerBlock("star_hanging_sign",
            () -> new CeilingHangingSignBlockFix(
                    STAR,
                    BlockBehaviour.Properties.ofFullCopy(Blocks.ACACIA_HANGING_SIGN),
                    ModItems.STAR_HANGING_SIGN::get
            ));


    public static final DeferredBlock<WallSignBlock> STAR_WALL_SIGN = registerBlock("star_wall_sign",
            () -> new WallSignBlockFix(
                    STAR,
                    BlockBehaviour.Properties.ofFullCopy(Blocks.ACACIA_WALL_SIGN),
                    ModItems.STAR_SIGN::get
            ));

    public static final DeferredBlock<WallHangingSignBlock> STAR_WALL_HANGING_SIGN = registerBlock("star_wall_hanging_sign",
            () -> new WallHangingSignBlockFix(
                    STAR,
                    BlockBehaviour.Properties.ofFullCopy(Blocks.ACACIA_WALL_HANGING_SIGN),
                    ModItems.STAR_HANGING_SIGN::get
            ));





    public static final DeferredBlock<Block> BLUEBERRY_BUSH = registerBlockWithoutItem("blueberry_bush",
            () -> new NeoBerryBushBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SWEET_BERRY_BUSH),
                    () -> ModItems.BLUEBERRIES, 1));
    public static final DeferredBlock<Block> BLACKBERRY_BUSH = registerBlockWithoutItem("blackberry_bush",
            () -> new NeoBerryBushBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SWEET_BERRY_BUSH),
                    () -> ModItems.BLACKBERRIES, 1));
    public static final DeferredBlock<Block> STRAWBERRY_BUSH = registerBlockWithoutItem("strawberry_bush",
            () -> new StrawBerryBushBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SWEET_BERRY_BUSH),
                    ModItems.STRAWBERRY, ModItems.UNRIPE_STRAWBERRY, ModItems.STRAWBERRY_SEEDS));

    public static final DeferredBlock<Block> TOMATO_BUSH = registerBlockWithoutItem("tomato_bush",
            () -> new TomatoBushBlock(BlockBehaviour.Properties.of()
                    .noCollission()
                    .instabreak()
                    .mapColor(MapColor.PLANT)
                    .sound(SoundType.GRASS)
                    .offsetType(BlockBehaviour.OffsetType.XZ)
                    .pushReaction(PushReaction.DESTROY)));

    public static final DeferredBlock<Block> TOMATO_CROP = registerBlockWithoutItem("tomato_crop",
            () -> new TomatoCropBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.WHEAT)));
    public static final DeferredBlock<Block> EGGPLANT_CROP = registerBlockWithoutItem("eggplant_crop",
            () -> new EggplantCropBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.WHEAT)));
    public static final DeferredBlock<Block> SWEET_POTATO_CROP = registerBlockWithoutItem("sweet_potato_crop",
            () -> new SweetPotatoCropBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.WHEAT)));


    public static final DeferredBlock<Block> SWEET_POTATO_BLOCK = registerBlock("sweet_potato_block",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.PUMPKIN)));


    public static final DeferredBlock<Block> CORN_CROP = registerBlockWithoutItem("corn_crop",
            () -> new CornCropBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.PITCHER_CROP)));

    public static final DeferredBlock<LanternBlock> LEAD_LANTERN = registerBlock("lead_lantern",
            () -> new LanternBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.LANTERN).lightLevel(state -> 12)));
    public static final DeferredBlock<LanternBlock> UNLIT_LANTERN = registerBlock("unlit_lantern",
            () -> new UnlitLanternBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.LANTERN), () -> Blocks.LANTERN));
}
