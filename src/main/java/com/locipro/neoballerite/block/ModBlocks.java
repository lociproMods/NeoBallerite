package com.locipro.neoballerite.block;


import static com.locipro.neoballerite.NeoBallerite.MODID;


import com.locipro.neoballerite.block.custom.CompressedBalleriteBlock;
import com.locipro.neoballerite.item.ModItems;
import com.locipro.neoballerite.misc.food.BalleriteFoodProperties;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

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

    // PRESS SHIFT TWICE TO SEARCH FOR NON PROJECT STUFF LIKE net.minecraft.blocks
    /*    public static final DeferredBlock<Block> X = registerBlock("x",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength()
                    .mapColor()));*/
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
            }, new Item.Properties().food(BalleriteFoodProperties.RAW_BALLERITE_BLOCK));

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
                        tooltipComponents.add( Component.literal("-Running speed faster than grass").withStyle(ChatFormatting.AQUA));
                    }
                }
            }, new Item.Properties().food(BalleriteFoodProperties.COOKED_BALLERITE_BLOCK)); // BlockItem properties

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


    // There's something like "BlockFamily" ? maybe check that out

    public static final DeferredBlock<RotatedPillarBlock> WITHERED_LOG = registerBlock("withered_log",
            () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LOG)));
    public static final DeferredBlock<RotatedPillarBlock> STRIPPED_WITHERED_LOG = registerBlock("stripped_withered_log",
            () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_LOG)));
    // TODO implement Strip-ables. check AxeItem for some funky shit there... idkk
    public static final DeferredBlock<RotatedPillarBlock> WITHERED_WOOD = registerBlock("withered_wood",
            () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WOOD)));
    public static final DeferredBlock<RotatedPillarBlock> STRIPPED_WITHERED_WOOD = registerBlock("stripped_withered_wood",
            () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_WOOD)));

    // TODO temporarily no lototable.
    public static final DeferredBlock<LeavesBlock> WITHERED_LEAVES = registerBlock("withered_leaves",
            () -> new LeavesBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES).noLootTable()));


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
            () -> new FenceGateBlock(WoodType.OAK, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE_GATE)));


    public static final DeferredBlock<DoorBlock> WITHERED_DOOR = registerBlock("withered_door",
            () -> new DoorBlock(BlockSetType.OAK, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_DOOR).noOcclusion()));
    public static final DeferredBlock<TrapDoorBlock> WITHERED_TRAPDOOR = registerBlock("withered_trapdoor",
            () -> new TrapDoorBlock(BlockSetType.OAK, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE_GATE).noOcclusion()));


}
