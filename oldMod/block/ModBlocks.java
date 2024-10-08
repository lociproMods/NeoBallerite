package net.locipro.balleritemod.block;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.locipro.balleritemod.BalleriteMod;
import net.locipro.balleritemod.block.custom.*;
import net.locipro.balleritemod.item.ModItemGroup;
import net.locipro.balleritemod.item.ModItems;
import net.locipro.balleritemod.util.foodcomponents.BalleriteFoodComponents;
import net.locipro.balleritemod.world.tree.ModSaplingGenerators;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.FenceBlock;
import net.minecraft.world.level.block.FenceGateBlock;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.SaplingBlock;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.TrapDoorBlock;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.material.MapColor;
import java.util.function.Supplier;

public class ModBlocks {
    public static void registerModBlocks() {
        BalleriteMod.LOGGER.info("Registering blocks for " + BalleriteMod.MOD_ID);
        addBlocksToItemGroups();
    }

    //region Ballerite blocks
    public static final Block RAW_BALLERITE_BLOCK = registerBlock("raw_ballerite_block",
            new RawBalleriteBlock(FabricBlockSettings.copyOf(Blocks.MUD).strength(0.5F, 0.5F)
                    .sound(SoundType.FUNGUS)), ModItemGroup.BALLERITE_BLOCKS, new FabricItemSettings().food(BalleriteFoodComponents.RAW_BALLERITE_BLOCK));
    public static final Block BALLERITE_BLOCK = registerBlock("ballerite_block",
            new BalleriteBlock(FabricBlockSettings.copyOf(Blocks.MUD).strength(0.4F, 0.4F).speedFactor(1.1F)
                    .sound(SoundType.FUNGUS)), ModItemGroup.BALLERITE_BLOCKS, new FabricItemSettings().food(BalleriteFoodComponents.BALLERITE_BLOCK));
    public static final Block BURNT_BALLERITE_BLOCK = registerBlock("burnt_ballerite_block",
            new BurntBallerite(FabricBlockSettings.copyOf(Blocks.COAL_BLOCK).mapColor(MapColor.NETHER).strength(6F, 6F).speedFactor(0.4F)
                    .sound(SoundType.TUFF).requiresCorrectToolForDrops()), ModItemGroup.BALLERITE_BLOCKS);
    public static final Block CHARRED_BALLERITE_BLOCK = registerBlock("charred_ballerite_block",
            new CharredBalleriteBlock(FabricBlockSettings.copyOf(Blocks.STONE).mapColor(MapColor.CRIMSON_HYPHAE).requiresCorrectToolForDrops().strength(5F, 6F)
                    .sound(SoundType.BASALT).requiresCorrectToolForDrops()), ModItemGroup.BALLERITE_BLOCKS);
    public static final Block COMPRESSED_BALLERITE_BLOCK = registerBlock("compressed_ballerite_block",
            new CompressedBalleriteBlock(FabricBlockSettings.copyOf(Blocks.IRON_BLOCK).mapColor(MapColor.GRASS).requiresCorrectToolForDrops().strength(7F, 7F)
                    .sound(SoundType.METAL).requiresCorrectToolForDrops()), ModItemGroup.BALLERITE_BLOCKS);

    public static final Block BALLERITE_ORE = registerBlock("ballerite_ore",
            new DropExperienceBlock(UniformInt.of(2, 5),
                    FabricBlockSettings.copyOf(Blocks.IRON_ORE).strength(3F, 3F).requiresCorrectToolForDrops()), ModItemGroup.BALLERITE_MATERIALS);
    //endregion

    //region Withered Blocks
    public static final Block WITHERED_LOG = registerBlock("withered_log",
            new RotatedPillarBlock(FabricBlockSettings.copyOf(Blocks.OAK_LOG).strength(3f)), ModItemGroup.BALLERITE_BLOCKS);
    public static final Block WITHERED_WOOD = registerBlock("withered_wood",
            new RotatedPillarBlock(FabricBlockSettings.copyOf(Blocks.OAK_WOOD).strength(3f)), ModItemGroup.BALLERITE_BLOCKS);
    public static final Block STRIPPED_WITHERED_LOG = registerBlock("stripped_withered_log",
            new RotatedPillarBlock(FabricBlockSettings.copyOf(Blocks.STRIPPED_OAK_LOG).strength(3f)), ModItemGroup.BALLERITE_BLOCKS);
    public static final Block STRIPPED_WITHERED_WOOD = registerBlock("stripped_withered_wood",
            new RotatedPillarBlock(FabricBlockSettings.copyOf(Blocks.STRIPPED_OAK_WOOD).strength(3f)), ModItemGroup.BALLERITE_BLOCKS);
    public static final Block WITHERED_PLANKS = registerBlock("withered_planks",
            new Block(FabricBlockSettings.copyOf(Blocks.OAK_PLANKS)), ModItemGroup.BALLERITE_BLOCKS);
    public static final Block WITHERED_STAIRS = registerBlock("withered_stairs",
            new StairBlock(WITHERED_PLANKS.defaultBlockState(), FabricBlockSettings.ofFullCopy(Blocks.OAK_STAIRS)), ModItemGroup.BALLERITE_BLOCKS);
    public static final Block WITHERED_SLAB = registerBlock("withered_slab",
            new SlabBlock(FabricBlockSettings.ofFullCopy(Blocks.OAK_SLAB)), ModItemGroup.BALLERITE_BLOCKS);
    public static final Block WITHERED_FENCE = registerBlock("withered_fence",
            new FenceBlock(FabricBlockSettings.ofFullCopy(Blocks.OAK_FENCE)), ModItemGroup.BALLERITE_BLOCKS);
    public static final Block WITHERED_FENCE_GATE = registerBlock("withered_fence_gate",
            new FenceGateBlock(WoodType.OAK, FabricBlockSettings.ofFullCopy(Blocks.OAK_FENCE_GATE)), ModItemGroup.BALLERITE_BLOCKS);
    public static final Block WITHERED_DOOR = registerBlock("withered_door",
            new DoorBlock(BlockSetType.OAK, FabricBlockSettings.ofFullCopy(Blocks.OAK_DOOR)), ModItemGroup.BALLERITE_BLOCKS);
    public static final Block WITHERED_TRAPDOOR = registerBlock("withered_trapdoor",
            new TrapDoorBlock(BlockSetType.OAK, FabricBlockSettings.ofFullCopy(Blocks.OAK_TRAPDOOR)), ModItemGroup.BALLERITE_BLOCKS);
    public static final Block WITHERED_PRESSURE_PLATE = registerBlock("withered_pressure_plate",
            ModPressurePlate.createWoodenPressurePlate(), ModItemGroup.BALLERITE_BLOCKS);
    public static final Block WITHERED_BUTTON = registerBlock("withered_button",
            ModButtonBlock.createWoodenButtonBlock(BlockSetType.OAK), ModItemGroup.BALLERITE_BLOCKS);
    public static final Block WITHERED_LEAVES= registerBlock("withered_leaves",
            new LeavesBlock(FabricBlockSettings.copyOf(Blocks.OAK_LEAVES)), ModItemGroup.BALLERITE_BLOCKS);
    public static final Block WITHERED_SAPLING = registerBlock("withered_sapling",
            new SaplingBlock(ModSaplingGenerators.WITHERED, FabricBlockSettings.copyOf(Blocks.OAK_SAPLING).strength(3f)), ModItemGroup.BALLERITE_BLOCKS);
//endregion

    //region Star Blocks
    public static final Block STAR_LOG = registerBlock("star_log",
            new RotatedPillarBlock(FabricBlockSettings.copyOf(Blocks.ACACIA_LOG).strength(3f)), ModItemGroup.BALLERITE_BLOCKS);
    public static final Block STAR_WOOD = registerBlock("star_wood",
            new RotatedPillarBlock(FabricBlockSettings.copyOf(Blocks.ACACIA_WOOD).strength(3f)), ModItemGroup.BALLERITE_BLOCKS);
    public static final Block STRIPPED_STAR_LOG = registerBlock("stripped_star_log",
            new RotatedPillarBlock(FabricBlockSettings.copyOf(Blocks.STRIPPED_ACACIA_LOG).strength(3f)), ModItemGroup.BALLERITE_BLOCKS);
    public static final Block STRIPPED_STAR_WOOD = registerBlock("stripped_star_wood",
            new RotatedPillarBlock(FabricBlockSettings.copyOf(Blocks.STRIPPED_ACACIA_WOOD).strength(3f)), ModItemGroup.BALLERITE_BLOCKS);
    public static final Block STAR_PLANKS = registerBlock("star_planks",
            new Block(FabricBlockSettings.copyOf(Blocks.ACACIA_PLANKS)), ModItemGroup.BALLERITE_BLOCKS);
    public static final Block STAR_STAIRS = registerBlock("star_stairs",
            new StairBlock(STAR_PLANKS.defaultBlockState(), FabricBlockSettings.ofFullCopy(Blocks.ACACIA_STAIRS)), ModItemGroup.BALLERITE_BLOCKS);
    public static final Block STAR_SLAB = registerBlock("star_slab",
            new SlabBlock(FabricBlockSettings.ofFullCopy(Blocks.ACACIA_SLAB)), ModItemGroup.BALLERITE_BLOCKS);
    public static final Block STAR_FENCE = registerBlock("star_fence",
            new FenceBlock(FabricBlockSettings.ofFullCopy(Blocks.ACACIA_FENCE)), ModItemGroup.BALLERITE_BLOCKS);
    public static final Block STAR_FENCE_GATE = registerBlock("star_fence_gate",
            new FenceGateBlock(WoodType.ACACIA, FabricBlockSettings.ofFullCopy(Blocks.ACACIA_FENCE_GATE)), ModItemGroup.BALLERITE_BLOCKS);
    public static final Block STAR_DOOR = registerBlock("star_door",
            new DoorBlock(BlockSetType.ACACIA, FabricBlockSettings.ofFullCopy(Blocks.ACACIA_DOOR)), ModItemGroup.BALLERITE_BLOCKS);
    public static final Block STAR_TRAPDOOR = registerBlock("star_trapdoor",
            new TrapDoorBlock(BlockSetType.ACACIA, FabricBlockSettings.ofFullCopy(Blocks.ACACIA_TRAPDOOR)), ModItemGroup.BALLERITE_BLOCKS);
    public static final Block STAR_PRESSURE_PLATE = registerBlock("star_pressure_plate",
            ModPressurePlate.createWoodenPressurePlate(), ModItemGroup.BALLERITE_BLOCKS);
    public static final Block STAR_BUTTON = registerBlock("star_button",
            ModButtonBlock.createWoodenButtonBlock(BlockSetType.ACACIA), ModItemGroup.BALLERITE_BLOCKS);
    public static final Block STAR_LEAVES= registerBlock("star_leaves",
            new LeavesBlock(FabricBlockSettings.copyOf(Blocks.ACACIA_LEAVES).luminance(10).noOcclusion().noCollission()), ModItemGroup.BALLERITE_BLOCKS);
    public static final Block STAR_SAPLING = registerBlock("star_sapling",
            new SaplingBlock(ModSaplingGenerators.STAR, FabricBlockSettings.copyOf(Blocks.ACACIA_SAPLING).instabreak()), ModItemGroup.BALLERITE_BLOCKS);
    //endregion

    //region Mod doors, trapdoors, buttons, pressure plates.
    public static final Block COBBLESTONE_DOOR = registerBlock("cobblestone_door",
            new DoorBlock(BlockSetType.STONE, FabricBlockSettings.copyOf(Blocks.STONE).strength(3F, 6F).requiresCorrectToolForDrops().noOcclusion().sound(SoundType.STONE)), ModItemGroup.BALLERITE_BLOCKS);
    public static final Block COBBLESTONE_TRAPDOOR = registerBlock("cobblestone_trapdoor",
            new TrapDoorBlock(BlockSetType.STONE, FabricBlockSettings.copyOf(Blocks.STONE).strength(2f).requiresCorrectToolForDrops().noOcclusion().sound(SoundType.STONE)), ModItemGroup.BALLERITE_BLOCKS);

    public static final Block COPPER_DOOR = registerBlock("copper_door",
            new DoorBlock(BlockSetType.IRON, FabricBlockSettings.copyOf(Blocks.COPPER_BLOCK).strength(5f, 5f).requiresCorrectToolForDrops().noOcclusion().sound(SoundType.COPPER)), ModItemGroup.BALLERITE_BLOCKS);
    public static final Block COPPER_TRAPDOOR = registerBlock("copper_trapdoor",
            new TrapDoorBlock(BlockSetType.IRON, FabricBlockSettings.copyOf(Blocks.COPPER_BLOCK).strength(2f).requiresCorrectToolForDrops().noOcclusion().sound(SoundType.COPPER)), ModItemGroup.BALLERITE_BLOCKS);
    public static final Block COPPER_PRESSURE_PLATE = registerBlock("copper_pressure_plate",
            ModPressurePlate.createMetalPressurePlate(), ModItemGroup.BALLERITE_BLOCKS);
    public static final Block COPPER_BUTTON = registerBlock("copper_button",
            ModButtonBlock.createMetalButtonBlock(FabricBlockSettings.copyOf(Blocks.COPPER_BLOCK).strength(0.75f, 0).requiresCorrectToolForDrops().noCollission().sound(SoundType.COPPER)), ModItemGroup.BALLERITE_BLOCKS);   //endregion

    //region LEAD
    public static final Block LEAD_BLOCK = registerBlock("lead_block",
	        new Block(FabricBlockSettings.copyOf(Blocks.IRON_BLOCK).mapColor(MapColor.LAPIS).requiresCorrectToolForDrops().strength(5f, 6f)
                    .sound(SoundType.METAL)), ModItemGroup.BALLERITE_MATERIALS);
    public static final Block LEAD_ORE = registerBlock("lead_ore",
            new Block(FabricBlockSettings.ofFullCopy(Blocks.IRON_ORE)), ModItemGroup.BALLERITE_MATERIALS);
    public static final Block DEEPSLATE_LEAD_ORE = registerBlock("deepslate_lead_ore",
            new Block(FabricBlockSettings.ofFullCopy(Blocks.DEEPSLATE_IRON_ORE)), ModItemGroup.BALLERITE_MATERIALS);
    //endregion

    //region FARMING
    public static final Block EGGPLANT_CROP = registerBlockWithoutItem("eggplant_crop",
            new EggplantCropBlock(FabricBlockSettings.ofFullCopy(Blocks.WHEAT).noOcclusion().noCollission()));

    public static final Block CORN_CROP = registerBlockWithoutItem("corn_crop",
            new CornCropBlock(FabricBlockSettings.ofFullCopy(Blocks.WHEAT).noOcclusion().noCollission()));
    public static final Block TOMATO_CROP = registerBlockWithoutItem("tomato_crop",
            new TomatoCropBlock(FabricBlockSettings.ofFullCopy(Blocks.WHEAT).noOcclusion().noCollission()));
    public static final Block TOMATO_FLOWER = registerBlockWithoutItem("tomato_flower",
            new TomatoFlowerBlock(FabricBlockSettings.ofFullCopy(Blocks.POPPY).instabreak().noCollission().noOcclusion()));

    public static final SweetPotatoCropBlock SWEET_POTATO_CROP = (SweetPotatoCropBlock) registerBlockWithoutItem("sweet_potato_crop",
            new SweetPotatoCropBlock(FabricBlockSettings.ofFullCopy(Blocks.WHEAT).noOcclusion().noCollission()));
    public static final Block SWEET_POTATO_BLOCK = registerBlock("sweet_potato_block",
            new Block(FabricBlockSettings.ofFullCopy(Blocks.PUMPKIN).strength(1.0f).sound(SoundType.WOOD)), ModItemGroup.BALLERITE_PLANTS);


    public static final Block BLUE_BERRY_BUSH = registerBlockWithoutItem("blue_berry_bush",
            new ModBerryBushBlock(FabricBlockSettings.copyOf(Blocks.SWEET_BERRY_BUSH).randomTicks().noCollission().noOcclusion().sound(SoundType.SWEET_BERRY_BUSH), 1, new Supplier<Item>() {
                @Override
                public Item get() {
                    return ModItems.BLUE_BERRIES;
                }
            }));

    public static final Supplier<Item> BLACK_BERRY_SUPPLIER = () -> ModItems.BLACK_BERRIES;
    public static final Block BLACK_BERRY_BUSH = registerBlockWithoutItem("black_berry_bush",
            new ModBerryBushBlock(FabricBlockSettings.copyOf(Blocks.SWEET_BERRY_BUSH).randomTicks().noCollission().noOcclusion().sound(SoundType.SWEET_BERRY_BUSH), 1, BLACK_BERRY_SUPPLIER));



    public static final Block STRAWBERRY_BUSH = registerBlockWithoutItem("strawberry_bush",
            new StrawberryBushBlock(FabricBlockSettings.copyOf(Blocks.SWEET_BERRY_BUSH).randomTicks().noCollission().sound(SoundType.SWEET_BERRY_BUSH)));




    //endregion


    private static void addBlocksToItemGroups() {
        //add here if u want ig homie cuh blud
    }



    //region Registry commands
    private static Block registerBlock(String name, Block block, CreativeModeTab itemGroup, FabricItemSettings settings) {
        registerBlockItem(name, block ,itemGroup, settings);
        return Registry.register(BuiltInRegistries.BLOCK, new ResourceLocation(BalleriteMod.MOD_ID, name), block);
    }
    private static Block registerBlock(String name, Block block, CreativeModeTab itemGroup) {
        registerBlockItem(name, block ,itemGroup, new FabricItemSettings());
        return Registry.register(BuiltInRegistries.BLOCK, new ResourceLocation(BalleriteMod.MOD_ID, name), block);
    }
    private static Item registerBlockItem(String name, Block block, CreativeModeTab itemGroup, FabricItemSettings settings) {
        Item item = Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(BalleriteMod.MOD_ID, name),
                new BlockItem(block, settings));
        ResourceKey<CreativeModeTab> groupKey = BuiltInRegistries.CREATIVE_MODE_TAB.getResourceKey(itemGroup).orElseThrow(() -> {
                    return new IllegalStateException("Unregistered creative tab: " + itemGroup);
                });
        ItemGroupEvents.modifyEntriesEvent(groupKey).register(entries -> entries.accept(item));

        return item;
    }
    public static Block registerBlockWithoutItem(String name, Block block) {
        return Registry.register(BuiltInRegistries.BLOCK, new ResourceLocation(BalleriteMod.MOD_ID, name), block);
    }
    //endregion
}