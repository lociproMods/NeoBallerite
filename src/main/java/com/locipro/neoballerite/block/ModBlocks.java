package com.locipro.neoballerite.block;


import static com.locipro.neoballerite.NeoBallerite.MODID;

import com.locipro.neoballerite.block.custom.CharredBalleriteBlock;
import com.locipro.neoballerite.block.custom.CompressedBalleriteBlock;
import com.locipro.neoballerite.item.ModItems;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(MODID);

    private static <T extends Block> void registerBlockItem(String name, DeferredBlock<T> block) {
        ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }
    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Supplier<T> block) {
        DeferredBlock<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
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

    public static final DeferredBlock<Block> COMPRESSED_BALLERITE_BLOCK = registerBlock("compressed_ballerite_block",
            () -> new CompressedBalleriteBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK)));

    public static final DeferredBlock<Block> CHARRED_BALLERITE_BLOCK = registerBlock("charred_ballerite_block",
            () -> new CharredBalleriteBlock(BlockBehaviour.Properties.of()
                    .strength(5.0f, 6.0f)
                    .requiresCorrectToolForDrops()
                    .ignitedByLava()
                    .sound(SoundType.TUFF)
                    .mapColor(MapColor.CRIMSON_NYLIUM)));
    //public static final DeferredItem<FuelBlockItem> CHARRED_BALLERITE_BLOCK_ITEM = ModItems.ITEMS.register("charred_ballerite_block", () -> new FuelBlockItem(CHARRED_BALLERITE_BLOCK.get(), new Item.Properties(), 8000));

    public static final DeferredBlock<Block> BALLERITE_ORE = registerBlock("ballerite_ore",
            () -> new DropExperienceBlock(UniformInt.of(3, 4), BlockBehaviour.Properties.ofFullCopy(Blocks.COPPER_ORE)));

}
