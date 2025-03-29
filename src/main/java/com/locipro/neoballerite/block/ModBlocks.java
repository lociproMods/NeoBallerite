package com.locipro.neoballerite.block;


import static com.locipro.neoballerite.NeoBallerite.MODID;


import com.locipro.neoballerite.NeoBallerite;
import com.locipro.neoballerite.block.custom.*;
import com.locipro.neoballerite.item.ModItems;
import com.locipro.neoballerite.misc.food.BalleriteFoodProperties;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
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
import java.util.function.Function;
import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(MODID);


    private static <T extends Block> void registerBlockItem(String name, DeferredBlock<T> block) {
        ModItems.ITEMS.registerSimpleBlockItem(name, block, new Item.Properties());
    }
    private static <T extends Block> void registerBlockItem(String name, DeferredBlock<T> block, Item.Properties properties) {
        ModItems.ITEMS.registerSimpleBlockItem(name, block, properties);
    }
    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Function<BlockBehaviour.Properties, ? extends T> func, BlockBehaviour.Properties props) {
        DeferredBlock<T> ret = BLOCKS.registerBlock(name, func, props);
        registerBlockItem(name, ret);
        return ret;
    }
    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Function<BlockBehaviour.Properties, ? extends T> func, BlockBehaviour.Properties props, Item.Properties itemProperties) {
        DeferredBlock<T> ret = BLOCKS.registerBlock(name, func, props);
        registerBlockItem(name, ret, itemProperties);
        return ret;
    }
    private static <T extends Block> DeferredBlock<T> registerBlockWithoutItem(String name, Function<BlockBehaviour.Properties, ? extends T> func, BlockBehaviour.Properties props) {
        return BLOCKS.registerBlock(name, func, props);
    }

/*
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(Qollective.MODID);

    private static <T extends Block> void registerBlockItem(DeferredBlock<T> block, Item.Properties properties) {
        QolItems.ITEMS.registerSimpleBlockItem(block, new Item.Properties());
    }

    private static <T extends Block> DeferredBlock<T> registerBlockWithItem(String name, Function<BlockBehaviour.Properties, ? extends T> func, BlockBehaviour.Properties props) {
        DeferredBlock<T> toReturn = BLOCKS.registerBlock(name, func, props);
        registerBlockItem(toReturn, new Item.Properties());
        return toReturn;
    }
    public static final DeferredBlock<TickingTorch> UNLIT_TORCH = BLOCKS.registerBlock("unlit_torch",
            UnlitTorchBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.TORCH)
                    .lightLevel(state -> 0));

    public static final DeferredBlock<TickingTorch> UNLIT_WALL_TORCH = BLOCKS.registerBlock("unlit_wall_torch",
            UnlitWallTorchBlock::new, BlockBehaviour.Properties.of()
                    .noCollission()
                    .instabreak()
                    .lightLevel(p_220869_ -> 0)
                    .sound(SoundType.WOOD)
                    .pushReaction(PushReaction.DESTROY));*/


    

    // Wood Types should have a mod id qualifier
    // I think block set types are "a combined place where mojang can define the sounds and behaviour of a block set" (Dr.gigaherz 8/12/23 neoforge discord)
    
    //public static final BlockSetType WITHERED_SET = BlockSetType.register(new BlockSetType("withered"));
            
    public static final WoodType WITHERED = WoodType.register(new WoodType(MODID + ":withered", BlockSetType.OAK));
            
    //public static final BlockSetType STAR_SET = BlockSetType.register(new BlockSetType("star"));
            
    public static final WoodType STAR = WoodType.register(new WoodType(MODID + ":star", BlockSetType.ACACIA));





    public static final DeferredBlock<Block> RAW_BALLERITE_BLOCK = registerBlock("raw_ballerite_block",
            (properties) -> new Block(properties){
                @Override
                public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
                    tooltipComponents.add( Component.literal("Raw Messy block....").withStyle(ChatFormatting.GRAY));
                    tooltipComponents.add( Component.literal("Why did you make this?? *don't eat it*").withStyle(ChatFormatting.DARK_GRAY));
                    if (Screen.hasShiftDown()) {
                        tooltipComponents.add( Component.literal("--Cookable").withStyle(ChatFormatting.AQUA));
                    }
                }
            },BlockBehaviour.Properties.ofFullCopy(Blocks.MUD).strength(0.5f, 0.5f).sound(SoundType.FUNGUS)
            ,new Item.Properties().food(BalleriteFoodProperties.RAW_BALLERITE_BLOCK)
                    .component(DataComponents.CONSUMABLE, BalleriteFoodProperties.RAW_BALLERITE_BLOCK_CONSUMABLE));

    public static final DeferredBlock<Block> COOKED_BALLERITE_BLOCK = registerBlock("cooked_ballerite_block",
            (properties) -> new Block(properties) {
                @Override
                public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
                    tooltipComponents.add( Component.literal("Warm block from this weird mineral called ballerite...").withStyle(ChatFormatting.GRAY));
                    tooltipComponents.add( Component.literal("Maybe try cooking it again? OR, just eat it..").withStyle(ChatFormatting.GRAY));
                    if (Screen.hasShiftDown()) {
                        tooltipComponents.add( Component.literal("-Running speed faster than grass").withStyle(ChatFormatting.BLACK));
                    }
                }
            }, BlockBehaviour.Properties.ofFullCopy(Blocks.MUD)
                    .strength(0.4f, 0.5f)
                    .sound(SoundType.FUNGUS)
                    .speedFactor(1.1f),
            new Item.Properties().food(BalleriteFoodProperties.COOKED_BALLERITE_BLOCK)
                    .component(DataComponents.CONSUMABLE, BalleriteFoodProperties.COOKED_BALLERITE_BLOCK_CONSUMABLE)); // BlockItem properties


    public static final DeferredBlock<Block> BURNT_BALLERITE_BLOCK = registerBlock("burnt_ballerite_block",
            (properties) -> new Block(properties){
                @Override
                public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
                    tooltipComponents.add( Component.literal("How did you get here?").withStyle(ChatFormatting.GRAY));
                    tooltipComponents.add( Component.literal("Just a few more steps...").withStyle(ChatFormatting.DARK_GRAY));
                    if (Screen.hasShiftDown()) {
                        tooltipComponents.add(Component.literal("--Organic").withStyle(ChatFormatting.DARK_GREEN));
                    }
                }
            },
            BlockBehaviour.Properties.ofFullCopy(Blocks.COAL_BLOCK)
                    .strength(5.0f, 4.0f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.TUFF)
                    .mapColor(MapColor.NETHER)
                    .speedFactor(0.4f)
            );


    public static final DeferredBlock<Block> CHARRED_BALLERITE_BLOCK = registerBlock("charred_ballerite_block",
            (properties) -> new Block(properties){
                @Override
                public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
                    tooltipComponents.add(Component.literal("Charred Ball Block.").withStyle(ChatFormatting.GRAY));
                    tooltipComponents.add(Component.literal("Just one more time...").withStyle(ChatFormatting.DARK_AQUA));
                    if (Screen.hasShiftDown()) {
                        tooltipComponents.add(Component.literal("--Biofuel").withStyle(ChatFormatting.DARK_GREEN));
                    }
                }
            },BlockBehaviour.Properties.of()
                    .strength(5.0f, 6.0f)
                    .requiresCorrectToolForDrops()
                    .ignitedByLava()
                    .sound(SoundType.BASALT)
                    .mapColor(MapColor.CRIMSON_NYLIUM));


    /*  public static final DeferredBlock<IronChestBlock> IRON_CHEST = registerWithItem("iron_chest", IronChestBlock::new, () -> properties, IronChestsTypes.IRON, false);
*/
    public static final DeferredBlock<Block> COMPRESSED_BALLERITE_BLOCK = registerBlock("compressed_ballerite_block",
            CompressedBalleriteBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK));


    public static final DeferredBlock<Block> BALLERITE_ORE = registerBlock("ballerite_ore",
            (properties) -> new DropExperienceBlock(UniformInt.of(3, 4), properties),
            BlockBehaviour.Properties.ofFullCopy(Blocks.COPPER_ORE));

   public static final DeferredBlock<Block> LEAD_ORE = registerBlock("lead_ore",
            (props) -> new DropExperienceBlock(UniformInt.of(3, 4), props), BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_ORE));
    public static final DeferredBlock<Block> DEEPSLATE_LEAD_ORE = registerBlock("deepslate_lead_ore",
            (props) -> new DropExperienceBlock(UniformInt.of(3, 4), props), BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE_IRON_ORE));
   public static final DeferredBlock<Block> LEAD_BLOCK = registerBlock("lead_block",
           Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK));
   public static final DeferredBlock<Block> RAW_LEAD_BLOCK = registerBlock("raw_lead_block",
           Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.RAW_IRON_BLOCK));

    // There's something like "BlockFamily" ? maybe check that out

    public static final DeferredBlock<SaplingBlock> WITHERED_SAPLING = registerBlock("withered_sapling",
            (props) -> new SaplingBlock(NeoTreeGrowers.WITHERED, props), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SAPLING));


    public static final DeferredBlock<RotatedPillarBlock> WITHERED_LOG = registerBlock("withered_log",
            (props) -> new RotatedPillarBlock(props) {
                @Override
                public @Nullable BlockState getToolModifiedState(BlockState state, UseOnContext context, ItemAbility itemAbility, boolean simulate) {
                    if (ItemAbilities.AXE_STRIP == itemAbility) {
                        return STRIPPED_WITHERED_LOG.get().defaultBlockState().setValue(RotatedPillarBlock.AXIS, state.getValue(RotatedPillarBlock.AXIS));
                    }
                    return super.getToolModifiedState(state, context, itemAbility, simulate);
                }
            }, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LOG));
    public static final DeferredBlock<RotatedPillarBlock> STRIPPED_WITHERED_LOG = registerBlock("stripped_withered_log",
            RotatedPillarBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_LOG));

    public static final DeferredBlock<RotatedPillarBlock> WITHERED_WOOD = registerBlock("withered_wood",
            (props) -> new RotatedPillarBlock(props) {
                @Override
                public @Nullable BlockState getToolModifiedState(BlockState state, UseOnContext context, ItemAbility itemAbility, boolean simulate) {
                    if (ItemAbilities.AXE_STRIP == itemAbility) {
                        return STRIPPED_WITHERED_WOOD.get().defaultBlockState().setValue(RotatedPillarBlock.AXIS, state.getValue(RotatedPillarBlock.AXIS));
                    }
                    return super.getToolModifiedState(state, context, itemAbility, simulate);
                }
            }, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WOOD));
    public static final DeferredBlock<RotatedPillarBlock> STRIPPED_WITHERED_WOOD = registerBlock("stripped_withered_wood",
            RotatedPillarBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_WOOD));


    public static final DeferredBlock<Block> WITHERED_LEAVES = registerBlock("withered_leaves",
            (p) -> new CustomDistanceLeavesBlock(p, 10), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES));

    public static final DeferredBlock<Block> WITHERED_PLANKS = registerBlock("withered_planks",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS));

    public static final DeferredBlock<StairBlock> WITHERED_STAIRS = registerBlock("withered_stairs",
            (p) -> new StairBlock(WITHERED_PLANKS.get().defaultBlockState(), p), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_STAIRS));
    public static final DeferredBlock<SlabBlock> WITHERED_SLAB = registerBlock("withered_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SLAB));

    public static final DeferredBlock<PressurePlateBlock> WITHERED_PRESSURE_PLATE = registerBlock("withered_pressure_plate",
            (p) -> new PressurePlateBlock(BlockSetType.OAK, p), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PRESSURE_PLATE));
    public static final DeferredBlock<ButtonBlock> WITHERED_BUTTON = registerBlock("withered_button",
            (p) -> new ButtonBlock(BlockSetType.OAK, 20, p), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_BUTTON).noCollission());

    public static final DeferredBlock<FenceBlock> WITHERED_FENCE = registerBlock("withered_fence",
            FenceBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE));
    public static final DeferredBlock<FenceGateBlock> WITHERED_FENCE_GATE = registerBlock("withered_fence_gate",
            (p) -> new FenceGateBlock(WITHERED, p), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE_GATE));


    public static final DeferredBlock<DoorBlock> WITHERED_DOOR = registerBlock("withered_door",
            (p) -> new DoorBlock(BlockSetType.OAK, p), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_DOOR).noOcclusion());
    public static final DeferredBlock<TrapDoorBlock> WITHERED_TRAPDOOR = registerBlock("withered_trapdoor",
            (p) -> new TrapDoorBlock(BlockSetType.OAK, p), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE_GATE).noOcclusion());

    public static final DeferredBlock<StandingSignBlock> WITHERED_SIGN = registerBlock("withered_sign",
            (p) -> new StandingSignBlockFix(
                    WITHERED,
                    p,
                    ModItems.WITHERED_SIGN::get
            ), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SIGN));
    public static final DeferredBlock<CeilingHangingSignBlock> WITHERED_HANGING_SIGN = registerBlock("withered_hanging_sign",
            (p) -> new CeilingHangingSignBlockFix(
                    WITHERED,
                    p,
                    ModItems.WITHERED_HANGING_SIGN::get
            ), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_HANGING_SIGN));
    
    
    public static final DeferredBlock<WallSignBlock> WITHERED_WALL_SIGN = registerBlock("withered_wall_sign",
            (p) -> new WallSignBlockFix(
                    WITHERED,
                    p,
                    ModItems.WITHERED_SIGN::get
            ),  BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WALL_SIGN));

    public static final DeferredBlock<WallHangingSignBlock> WITHERED_WALL_HANGING_SIGN = registerBlock("withered_wall_hanging_sign",
            (p) -> new WallHangingSignBlockFix(
                    WITHERED,
                    p,
                    ModItems.WITHERED_HANGING_SIGN::get
            ), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WALL_HANGING_SIGN));
    


    /* 
    *  
    );*/



    public static final DeferredBlock<SaplingBlock> STAR_SAPLING = registerBlock("star_sapling",
            (p) -> new SaplingBlock(NeoTreeGrowers.STAR, p), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SAPLING));


    public static final DeferredBlock<RotatedPillarBlock> STAR_LOG = registerBlock("star_log",
            (p) -> new RotatedPillarBlock(p) {
                @Override
                public @Nullable BlockState getToolModifiedState(BlockState state, UseOnContext context, ItemAbility itemAbility, boolean simulate) {
                    if (ItemAbilities.AXE_STRIP == itemAbility) {
                        return STRIPPED_STAR_LOG.get().defaultBlockState().setValue(RotatedPillarBlock.AXIS, state.getValue(RotatedPillarBlock.AXIS));
                    }
                    return super.getToolModifiedState(state, context, itemAbility, simulate);
                }
            }, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LOG));
    public static final DeferredBlock<RotatedPillarBlock> STRIPPED_STAR_LOG = registerBlock("stripped_star_log",
            RotatedPillarBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_ACACIA_LOG));

    public static final DeferredBlock<RotatedPillarBlock> STAR_WOOD = registerBlock("star_wood",
            (p) -> new RotatedPillarBlock(p) {
                @Override
                public @Nullable BlockState getToolModifiedState(BlockState state, UseOnContext context, ItemAbility itemAbility, boolean simulate) {
                    if (ItemAbilities.AXE_STRIP == itemAbility) {
                        return STRIPPED_STAR_WOOD.get().defaultBlockState().setValue(RotatedPillarBlock.AXIS, state.getValue(RotatedPillarBlock.AXIS));
                    }
                    return super.getToolModifiedState(state, context, itemAbility, simulate);
                }
            }, BlockBehaviour.Properties.ofFullCopy(Blocks.ACACIA_WOOD));
    public static final DeferredBlock<RotatedPillarBlock> STRIPPED_STAR_WOOD = registerBlock("stripped_star_wood",
            RotatedPillarBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_ACACIA_WOOD));


    public static final DeferredBlock<Block> STAR_LEAVES = registerBlock("star_leaves",
            (p) -> new CustomDistanceLeavesBlock(p, 12),
            BlockBehaviour.Properties
                    .ofFullCopy(Blocks.ACACIA_LEAVES)
                    .noCollission()
                    .noOcclusion()
                    .lightLevel(state -> 10));
//    public static final DeferredBlock<SaplingBlock> STAR_SAPLING = registerBlock("star_sapling",
//            () -> new SaplingBlock())

    public static final DeferredBlock<Block> STAR_PLANKS = registerBlock("star_planks",
            Block::new ,BlockBehaviour.Properties.ofFullCopy(Blocks.ACACIA_PLANKS));

    public static final DeferredBlock<StairBlock> STAR_STAIRS = registerBlock("star_stairs",
            (p) -> new StairBlock(STAR_PLANKS.get().defaultBlockState(), p), BlockBehaviour.Properties.ofFullCopy(Blocks.ACACIA_STAIRS));
    public static final DeferredBlock<SlabBlock> STAR_SLAB = registerBlock("star_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.ACACIA_SLAB));

    public static final DeferredBlock<PressurePlateBlock> STAR_PRESSURE_PLATE = registerBlock("star_pressure_plate",
            (p) -> new PressurePlateBlock(BlockSetType.ACACIA, p), BlockBehaviour.Properties.ofFullCopy(Blocks.ACACIA_PRESSURE_PLATE));
    public static final DeferredBlock<ButtonBlock> STAR_BUTTON = registerBlock("star_button",
            (p) -> new ButtonBlock(BlockSetType.ACACIA, 20, p), BlockBehaviour.Properties.ofFullCopy(Blocks.ACACIA_BUTTON).noCollission());

    public static final DeferredBlock<FenceBlock> STAR_FENCE = registerBlock("star_fence",
            FenceBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.ACACIA_FENCE));
    public static final DeferredBlock<FenceGateBlock> STAR_FENCE_GATE = registerBlock("star_fence_gate",
            (p) -> new FenceGateBlock(STAR, p), BlockBehaviour.Properties.ofFullCopy(Blocks.ACACIA_FENCE_GATE));


    public static final DeferredBlock<DoorBlock> STAR_DOOR = registerBlock("star_door",
            (p) -> new DoorBlock(BlockSetType.ACACIA, p), BlockBehaviour.Properties.ofFullCopy(Blocks.ACACIA_DOOR).noOcclusion());
    public static final DeferredBlock<TrapDoorBlock> STAR_TRAPDOOR = registerBlock("star_trapdoor",
            (p) -> new TrapDoorBlock(BlockSetType.ACACIA, p), BlockBehaviour.Properties.ofFullCopy(Blocks.ACACIA_FENCE_GATE).noOcclusion());

    public static final DeferredBlock<StandingSignBlock> STAR_SIGN = registerBlock("star_sign",
            (p) -> new StandingSignBlockFix(
                    STAR,
                    p,
                    ModItems.STAR_SIGN::get
            ), BlockBehaviour.Properties.ofFullCopy(Blocks.ACACIA_SIGN));
    public static final DeferredBlock<CeilingHangingSignBlock> STAR_HANGING_SIGN = registerBlock("star_hanging_sign",
            (p) -> new CeilingHangingSignBlockFix(
                    STAR,
                    p,
                    ModItems.STAR_HANGING_SIGN::get
            ), BlockBehaviour.Properties.ofFullCopy(Blocks.ACACIA_HANGING_SIGN));


    public static final DeferredBlock<WallSignBlock> STAR_WALL_SIGN = registerBlock("star_wall_sign",
            (p) -> new WallSignBlockFix(
                    STAR,
                    p,
                    ModItems.STAR_SIGN::get
            ), BlockBehaviour.Properties.ofFullCopy(Blocks.ACACIA_WALL_SIGN));

    public static final DeferredBlock<WallHangingSignBlock> STAR_WALL_HANGING_SIGN = registerBlock("star_wall_hanging_sign",
            (p) -> new WallHangingSignBlockFix(
                    STAR,
                    p,
                    ModItems.STAR_HANGING_SIGN::get
            ), BlockBehaviour.Properties.ofFullCopy(Blocks.ACACIA_WALL_HANGING_SIGN));





    public static final DeferredBlock<Block> BLUEBERRY_BUSH = registerBlockWithoutItem("blueberry_bush",
            (p) -> new NeoBerryBushBlock(p,
                    () -> ModItems.BLUEBERRIES, 1), BlockBehaviour.Properties.ofFullCopy(Blocks.SWEET_BERRY_BUSH));
    public static final DeferredBlock<Block> BLACKBERRY_BUSH = registerBlockWithoutItem("blackberry_bush",
            (p) -> new NeoBerryBushBlock(p,
                    () -> ModItems.BLACKBERRIES, 1), BlockBehaviour.Properties.ofFullCopy(Blocks.SWEET_BERRY_BUSH));
    public static final DeferredBlock<Block> STRAWBERRY_BUSH = registerBlockWithoutItem("strawberry_bush",
            (p) -> new StrawBerryBushBlock(p,
                    ModItems.STRAWBERRY, ModItems.UNRIPE_STRAWBERRY, ModItems.STRAWBERRY_SEEDS), BlockBehaviour.Properties.ofFullCopy(Blocks.SWEET_BERRY_BUSH));

    public static final DeferredBlock<Block> TOMATO_BUSH = registerBlockWithoutItem("tomato_bush",
            TomatoBushBlock::new, BlockBehaviour.Properties.of()
                    .noCollission()
                    .instabreak()
                    .mapColor(MapColor.PLANT)
                    .sound(SoundType.GRASS)
                    .offsetType(BlockBehaviour.OffsetType.XZ)
                    .pushReaction(PushReaction.DESTROY));

    public static final DeferredBlock<Block> TOMATO_CROP = registerBlockWithoutItem("tomato_crop",
            TomatoCropBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.WHEAT));
    public static final DeferredBlock<Block> EGGPLANT_CROP = registerBlockWithoutItem("eggplant_crop",
            EggplantCropBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.WHEAT));
    public static final DeferredBlock<Block> SWEET_POTATO_CROP = registerBlockWithoutItem("sweet_potato_crop",
            SweetPotatoCropBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.WHEAT));


    public static final DeferredBlock<Block> SWEET_POTATO_BLOCK = registerBlock("sweet_potato_block",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.PUMPKIN));


    public static final DeferredBlock<Block> CORN_CROP = registerBlockWithoutItem("corn_crop",
            CornCropBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.PITCHER_CROP));

    public static final DeferredBlock<LanternBlock> LEAD_LANTERN = registerBlock("lead_lantern",
            LanternBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.LANTERN).lightLevel(state -> 12));
    public static final DeferredBlock<LanternBlock> UNLIT_LANTERN = registerBlock("unlit_lantern",
            (p) -> new UnlitLanternBlock(p, () -> Blocks.LANTERN), BlockBehaviour.Properties.ofFullCopy(Blocks.LANTERN));
}
