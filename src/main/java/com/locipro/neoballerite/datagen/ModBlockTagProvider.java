package com.locipro.neoballerite.datagen;

import com.locipro.neoballerite.util.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import static com.locipro.neoballerite.block.ModBlocks.*;
import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends BlockTagsProvider {


    public ModBlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, String modId) {
        super(output, lookupProvider, modId);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(BALLERITE_ORE.get())
                .add(BURNT_BALLERITE_BLOCK.get())
                .add(CHARRED_BALLERITE_BLOCK.get())
                .add(COMPRESSED_BALLERITE_BLOCK.get())
                .add(DEEPSLATE_LEAD_ORE.get())
                .add(RAW_LEAD_BLOCK.get())
                .add(LEAD_ORE.get())
                .add(LEAD_BLOCK.get())
                .add(LEAD_LANTERN.get())
                .add(UNLIT_LANTERN.get());
        tag(BlockTags.MINEABLE_WITH_SHOVEL)
                .add(RAW_BALLERITE_BLOCK.get())
                .add(COOKED_BALLERITE_BLOCK.get());

        tag(BlockTags.MINEABLE_WITH_AXE)
                .addTags(ModTags.Blocks.WITHERED_BLOCKS, ModTags.Blocks.STAR_BLOCKS)
                .add(SWEET_POTATO_BLOCK.get());

        tag(BlockTags.NEEDS_STONE_TOOL)
                .add(BALLERITE_ORE.get())
                .add(BURNT_BALLERITE_BLOCK.get())
                .add(LEAD_ORE.get())
                .add(DEEPSLATE_LEAD_ORE.get());
        tag(BlockTags.NEEDS_IRON_TOOL)
                .add(CHARRED_BALLERITE_BLOCK.get())
                .add(COMPRESSED_BALLERITE_BLOCK.get())
                .add(LEAD_BLOCK.get());

        tag(BlockTags.LEAVES)
                .add(WITHERED_LEAVES.get())
                .add(STAR_LEAVES.get());
        tag(BlockTags.FLOWERS)
                .add(TOMATO_BUSH.get());
        tag(BlockTags.CROPS)
                .add(TOMATO_CROP.get())
                .add(EGGPLANT_CROP.get())
                .add(SWEET_POTATO_CROP.get())
                .add(CORN_CROP.get());


        tag(BlockTags.SAPLINGS)
                .add(WITHERED_SAPLING.get())
                .add(STAR_SAPLING.get());
        tag(BlockTags.LOGS_THAT_BURN)
                .addTags(ModTags.Blocks.WITHERED_LOGS);
        tag(BlockTags.LOGS)
                .addTags(ModTags.Blocks.WITHERED_LOGS, ModTags.Blocks.STAR_LOGS);
        tag(BlockTags.WOODEN_FENCES)
                .add(WITHERED_FENCE.get())
                .add(STAR_FENCE.get());
        tag(BlockTags.FENCE_GATES)
                .add(WITHERED_FENCE_GATE.get())
                .add(STAR_FENCE_GATE.get());
        tag(BlockTags.WOODEN_DOORS)
                .add(WITHERED_DOOR.get())
                .add(STAR_DOOR.get());
        tag(BlockTags.WOODEN_TRAPDOORS)
                .add(WITHERED_TRAPDOOR.get())
                .add(STAR_TRAPDOOR.get());
        tag(BlockTags.WOODEN_PRESSURE_PLATES)
                .add(WITHERED_PRESSURE_PLATE.get())
                .add(STAR_PRESSURE_PLATE.get());
        tag(BlockTags.WOODEN_BUTTONS)
                .add(WITHERED_BUTTON.get())
                .add(STAR_DOOR.get());
        tag(BlockTags.WOODEN_SLABS)
                .add(WITHERED_SLAB.get())
                .add(STAR_SLAB.get());
        tag(BlockTags.WOODEN_STAIRS)
                .add(WITHERED_STAIRS.get())
                .add(STAR_STAIRS.get());

        tag(BlockTags.STANDING_SIGNS)
                .add(WITHERED_SIGN.get())
                .add(STAR_SIGN.get());
        tag(BlockTags.WALL_SIGNS)
                .add(WITHERED_WALL_SIGN.get())
                .add(STAR_WALL_SIGN.get());
        tag(BlockTags.CEILING_HANGING_SIGNS)
                .add(WITHERED_HANGING_SIGN.get())
                .add(STAR_HANGING_SIGN.get());
        tag(BlockTags.WALL_HANGING_SIGNS)
                .add(WITHERED_WALL_HANGING_SIGN.get())
                .add(STAR_WALL_HANGING_SIGN.get());

        tag(ModTags.Blocks.BALLERITE_BLOCKS)
                .add(BALLERITE_ORE.get())
                .add(RAW_BALLERITE_BLOCK.get())
                .add(COOKED_BALLERITE_BLOCK.get())
                .add(BURNT_BALLERITE_BLOCK.get())
                .add(CHARRED_BALLERITE_BLOCK.get())
                .add(COMPRESSED_BALLERITE_BLOCK.get());

        tag(ModTags.Blocks.INCORRECT_FOR_BALLERITE_TOOL)
                .addTags(BlockTags.INCORRECT_FOR_IRON_TOOL)
                .remove(Tags.Blocks.OBSIDIANS);

        // UNUSED
        tag(ModTags.Blocks.INCORRECT_FOR_LEAD_TOOL)
                .addTags(BlockTags.INCORRECT_FOR_GOLD_TOOL);
        
        tag(ModTags.Blocks.WITHERED_LOGS)
                .add(WITHERED_LOG.get())
                .add(STRIPPED_WITHERED_LOG.get())
                .add(WITHERED_WOOD.get())
                .add(STRIPPED_WITHERED_WOOD.get());
        tag(ModTags.Blocks.STAR_LOGS)
                .add(STAR_LOG.get())
                .add(STRIPPED_STAR_LOG.get())
                .add(STAR_WOOD.get())
                .add(STRIPPED_STAR_WOOD.get());

        
        tag(ModTags.Blocks.WITHERED_BLOCKS)
                .add(WITHERED_LOG.get())
                .add(STRIPPED_WITHERED_LOG.get())
                .add(WITHERED_WOOD.get())
                .add(STRIPPED_WITHERED_WOOD.get())
                .add(WITHERED_PLANKS.get())
                .add(WITHERED_SLAB.get())
                .add(WITHERED_STAIRS.get())
                .add(WITHERED_DOOR.get())
                .add(WITHERED_TRAPDOOR.get())
                .add(WITHERED_BUTTON.get())
                .add(WITHERED_PRESSURE_PLATE.get())
                .add(WITHERED_FENCE.get())
                .add(WITHERED_FENCE_GATE.get());
        tag(ModTags.Blocks.STAR_BLOCKS)
                .add(STAR_LOG.get())
                .add(STRIPPED_STAR_LOG.get())
                .add(STAR_WOOD.get())
                .add(STRIPPED_STAR_WOOD.get())
                .add(STAR_PLANKS.get())
                .add(STAR_SLAB.get())
                .add(STAR_STAIRS.get())
                .add(STAR_DOOR.get())
                .add(STAR_TRAPDOOR.get())
                .add(STAR_BUTTON.get())
                .add(STAR_PRESSURE_PLATE.get())
                .add(STAR_FENCE.get())
                .add(STAR_FENCE_GATE.get());



        tag(ModTags.Blocks.OVERWORLD_FUNGALS)
                .add(Blocks.BROWN_MUSHROOM)
                .add(Blocks.RED_MUSHROOM);
        tag(ModTags.Blocks.NETHER_FUNGALS)
                .add(Blocks.WARPED_FUNGUS)
                .add(Blocks.CRIMSON_FUNGUS);
        tag(ModTags.Blocks.END_FUNGALS)
                .add(Blocks.CHORUS_FLOWER);

        tag(ModTags.Blocks.FUNGALS)
                .addTags(ModTags.Blocks.OVERWORLD_FUNGALS, ModTags.Blocks.NETHER_FUNGALS, ModTags.Blocks.END_FUNGALS);



        tag(ModTags.Common.BERRY_BUSHES)
                .add(BLUEBERRY_BUSH.get())
                .add(BLACKBERRY_BUSH.get())
                .add(STRAWBERRY_BUSH.get());
        tag(Tags.Blocks.STORAGE_BLOCKS)
                .add(RAW_LEAD_BLOCK.get())
                .add(COMPRESSED_BALLERITE_BLOCK.get());
    }
}
