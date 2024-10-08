package net.locipro.balleritemod.data;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

import static net.locipro.balleritemod.BalleriteMod.MOD_ID;
import static net.locipro.balleritemod.block.ModBlocks.*;
import java.util.concurrent.CompletableFuture;

/**
 * <i>Register your implementation using FabricDataGenerator.Pack.addProvider in a net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint.</i> <br>
 * Declare your tags, then in configure() use <i>getOrCreateTagBuilder(yourDeclaredTagHere).add(blockThatGetsAddedToTag)</i><br>
 * To add to vanilla tags, just use <i>BlockTags.BLOCK_TAG</i> as the argument in getOrCreateTagBuilder
 */
public class ModBlockTagGenerator extends FabricTagProvider.BlockTagProvider {
    public ModBlockTagGenerator(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }
    public static final TagKey<Block> BALLERITE_BLOCKS = TagKey.create(Registries.BLOCK, new ResourceLocation(MOD_ID, "ballerite_blocks"));
    public static final TagKey<Block> WITHERED_LOGS = TagKey.create(Registries.BLOCK, new ResourceLocation(MOD_ID, "withered_logs"));
    public static final TagKey<Block> STAR_LOGS = TagKey.create(Registries.BLOCK, new ResourceLocation(MOD_ID, "star_logs"));
    public static final TagKey<Block> STAR_BLOCKS = TagKey.create(Registries.BLOCK, new ResourceLocation(MOD_ID, "star_blocks"));
    public static final TagKey<Block> WITHERED_BLOCKS = TagKey.create(Registries.BLOCK, new ResourceLocation(MOD_ID, "withered_blocks"));

    public static final TagKey<Block> WOODEN_STAIRS = TagKey.create(Registries.BLOCK, new ResourceLocation(MOD_ID, "wooden_stairs"));
    public static final TagKey<Block> WOODEN_SLABS = TagKey.create(Registries.BLOCK, new ResourceLocation(MOD_ID, "wooden_slabs"));
    public static final TagKey<Block> WOODEN_FENCES = TagKey.create(Registries.BLOCK, new ResourceLocation(MOD_ID, "wooden_fences"));
    public static final TagKey<Block> WOODEN_BUTTONS = TagKey.create(Registries.BLOCK, new ResourceLocation(MOD_ID, "wooden_buttons"));
    public static final TagKey<Block> WOODEN_DOORS = TagKey.create(Registries.BLOCK, new ResourceLocation(MOD_ID, "wooden_doors"));
    public static final TagKey<Block> WOODEN_TRAPDOORS = TagKey.create(Registries.BLOCK, new ResourceLocation(MOD_ID, "wooden_trapdoors"));
    public static final TagKey<Block> WOODEN_PRESSURE_PLATES = TagKey.create(Registries.BLOCK, new ResourceLocation(MOD_ID, "wooden_pressure_plates"));



    @Override
    protected void addTags(HolderLookup.Provider arg) {
        //region MOD-TAGS
        tag(BALLERITE_BLOCKS)
                .add(reverseLookup(RAW_BALLERITE_BLOCK))
                .add(reverseLookup(BALLERITE_BLOCK))
                .add(reverseLookup(CHARRED_BALLERITE_BLOCK))
                .add(reverseLookup(BURNT_BALLERITE_BLOCK))
                .add(reverseLookup(COMPRESSED_BALLERITE_BLOCK))
                .add(reverseLookup(BALLERITE_ORE));
        
        
        tag(WITHERED_LOGS)
                .add(reverseLookup(WITHERED_LOG))
                .add(reverseLookup(WITHERED_WOOD))
                .add(reverseLookup(STRIPPED_WITHERED_LOG))
                .add(reverseLookup(STRIPPED_WITHERED_WOOD));
        
        tag(STAR_LOGS)
                .add(reverseLookup(STAR_LOG))
                .add(reverseLookup(STAR_WOOD))
                .add(reverseLookup(STRIPPED_STAR_LOG))
                .add(reverseLookup(STRIPPED_STAR_WOOD));
        
        tag(WITHERED_BLOCKS)
                .addTag(WITHERED_LOGS)
                .add(reverseLookup(WITHERED_PLANKS))
                .add(reverseLookup(WITHERED_SLAB))
                .add(reverseLookup(WITHERED_STAIRS))
                .add(reverseLookup(WITHERED_DOOR))
                .add(reverseLookup(WITHERED_TRAPDOOR))
                .add(reverseLookup(WITHERED_BUTTON))
                .add(reverseLookup(WITHERED_PRESSURE_PLATE))
                .add(reverseLookup(WITHERED_FENCE))
                .add(reverseLookup(WITHERED_FENCE_GATE));

        tag(STAR_BLOCKS)
                .addTag(STAR_LOGS)
                .add(reverseLookup(STAR_PLANKS))
                .add(reverseLookup(STAR_SLAB))
                .add(reverseLookup(STAR_STAIRS))
                .add(reverseLookup(STAR_DOOR))
                .add(reverseLookup(STAR_TRAPDOOR))
                .add(reverseLookup(STAR_BUTTON))
                .add(reverseLookup(STAR_PRESSURE_PLATE))
                .add(reverseLookup(STAR_FENCE))
                .add(reverseLookup(STAR_FENCE_GATE));


        tag(WOODEN_STAIRS)
                .add(reverseLookup(WITHERED_STAIRS))
                .add(reverseLookup(STAR_STAIRS));
        tag(WOODEN_SLABS)
                .add(reverseLookup(WITHERED_SLAB))
                .add(reverseLookup(STAR_SLAB));
        tag(WOODEN_FENCES)
                .add(reverseLookup(WITHERED_FENCE))
                .add(reverseLookup(STAR_FENCE));
        tag(WOODEN_BUTTONS)
                .add(reverseLookup(WITHERED_BUTTON))
                .add(reverseLookup(STAR_BUTTON));
        tag(WOODEN_DOORS)
                .add(reverseLookup(WITHERED_DOOR))
                .add(reverseLookup(STAR_DOOR));
        tag(WOODEN_TRAPDOORS)
                .add(reverseLookup(WITHERED_TRAPDOOR))
                .add(reverseLookup(STAR_TRAPDOOR));
        tag(WOODEN_PRESSURE_PLATES)
                .add(reverseLookup(WITHERED_PRESSURE_PLATE))
                .add(reverseLookup(STAR_PRESSURE_PLATE));
        //endregion
        //region VANILLA-TAGS
        tag(BlockTags.MINEABLE_WITH_AXE)
                .addTag(WITHERED_BLOCKS)
                .addTag(STAR_BLOCKS)
                .add(reverseLookup(SWEET_POTATO_BLOCK));
        tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(reverseLookup(BALLERITE_ORE)).add(reverseLookup(BURNT_BALLERITE_BLOCK)).add(reverseLookup(CHARRED_BALLERITE_BLOCK)).add(reverseLookup(COMPRESSED_BALLERITE_BLOCK))
                .add(reverseLookup(COBBLESTONE_DOOR)).add(reverseLookup(COBBLESTONE_TRAPDOOR))
                .add(reverseLookup(COPPER_DOOR)).add(reverseLookup(COPPER_TRAPDOOR)).add(reverseLookup(COPPER_PRESSURE_PLATE)).add(reverseLookup(COPPER_BUTTON))
                .add(reverseLookup(LEAD_ORE)).add(reverseLookup(DEEPSLATE_LEAD_ORE)).add(reverseLookup(LEAD_BLOCK));
        tag(BlockTags.MINEABLE_WITH_SHOVEL)
                .add(reverseLookup(RAW_BALLERITE_BLOCK)).add(reverseLookup(BALLERITE_BLOCK));

        tag(BlockTags.NEEDS_STONE_TOOL)
                .add(reverseLookup(BALLERITE_ORE))
                .add(reverseLookup(COBBLESTONE_DOOR)).add(reverseLookup(COBBLESTONE_TRAPDOOR))
                .add(reverseLookup(LEAD_ORE));
        tag(BlockTags.NEEDS_IRON_TOOL)
                .add(reverseLookup(CHARRED_BALLERITE_BLOCK)).add(reverseLookup(COMPRESSED_BALLERITE_BLOCK))
                .add(reverseLookup(COPPER_DOOR)).add(reverseLookup(COPPER_TRAPDOOR)).add(reverseLookup(COPPER_PRESSURE_PLATE)).add(reverseLookup(COPPER_BUTTON))
                .add(reverseLookup(LEAD_BLOCK)).add(reverseLookup(DEEPSLATE_LEAD_ORE));


        tag(BlockTags.CROPS)
                .add(reverseLookup(EGGPLANT_CROP))
                .add(reverseLookup(TOMATO_CROP))
                .add(reverseLookup(SWEET_POTATO_CROP));


        tag(BlockTags.FLOWERS)
                .add(reverseLookup(TOMATO_FLOWER));

        tag(BlockTags.DOORS)
                .add(reverseLookup(COBBLESTONE_DOOR))
                .add(reverseLookup(COPPER_DOOR));
        tag(BlockTags.TRAPDOORS)
                .add(reverseLookup(COBBLESTONE_TRAPDOOR))
                .add(reverseLookup(COPPER_TRAPDOOR));
        tag(BlockTags.BUTTONS)
                .add(reverseLookup(COPPER_BUTTON));


        tag(BlockTags.LEAVES)
                .add(reverseLookup(WITHERED_LEAVES))
                .add(reverseLookup(STAR_LEAVES));
        tag(BlockTags.LOGS)
                .addTag(STAR_LOGS)
                .addTag(WITHERED_LOGS);
        tag(BlockTags.PLANKS)
                .add(reverseLookup(WITHERED_PLANKS))
                .add(reverseLookup(STAR_PLANKS));
        tag(BlockTags.SAPLINGS)
                .add(reverseLookup(WITHERED_SAPLING))
                .add(reverseLookup(STAR_SAPLING));
        tag(BlockTags.SOUL_SPEED_BLOCKS)
                .add(reverseLookup(BURNT_BALLERITE_BLOCK));

        tag(BlockTags.FENCE_GATES)
                .add(reverseLookup(WITHERED_FENCE_GATE))
                .add(reverseLookup(STAR_FENCE_GATE));

        tag(BlockTags.WOODEN_BUTTONS)
                .addTag(WOODEN_BUTTONS);
        tag(BlockTags.WOODEN_DOORS)
                .addTag(WOODEN_DOORS);
        tag(BlockTags.WOODEN_FENCES)
                .addTag(WOODEN_FENCES);
        tag(BlockTags.WOODEN_PRESSURE_PLATES)
                .addTag(WOODEN_PRESSURE_PLATES);
        tag(BlockTags.WOODEN_SLABS)
                .addTag(WOODEN_SLABS);
        tag(BlockTags.WOODEN_STAIRS)
                .addTag(WOODEN_STAIRS);
        tag(BlockTags.WOODEN_TRAPDOORS)
                .addTag(WOODEN_TRAPDOORS);



        //endregion
    }
}