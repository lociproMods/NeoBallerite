package com.locipro.neoballerite.datagen;

import com.locipro.neoballerite.util.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;
import static com.locipro.neoballerite.NeoBallerite.MODID;
import static com.locipro.neoballerite.item.ModItems.*;
import static com.locipro.neoballerite.block.ModBlocks.*;
import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends ItemTagsProvider {


    public ModItemTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, CompletableFuture<TagLookup<Block>> blockTags, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, blockTags, MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(ModTags.Items.BALLERITE_ITEMS)
                .add(RAW_BALLERITE.get())
                .add(COOKED_BALLERITE.get())
                .add(CHARRED_BALLERITE.get())
                .add(COMPRESSED_BALLERITE_INGOT.get());

        tag(ModTags.Items.WITHERED_LOGS)
                .add(WITHERED_LOG.asItem())
                .add(STRIPPED_WITHERED_LOG.asItem())
                .add(WITHERED_WOOD.asItem())
                .add(STRIPPED_WITHERED_WOOD.asItem());
        tag(ModTags.Items.STAR_LOGS)
                .add(STAR_LOG.asItem())
                .add(STRIPPED_STAR_LOG.asItem())
                .add(STAR_WOOD.asItem())
                .add(STRIPPED_STAR_WOOD.asItem());
        
        
        
        tag(ItemTags.SWORD_ENCHANTABLE)
                .add(WOODEN_CLAYMORE.get())
                .add(STONE_CLAYMORE.get())
                .add(IRON_CLAYMORE.get())
                .add(LEAD_CLAYMORE.get())
                .add(GOLD_CLAYMORE.get())
                .add(DIAMOND_CLAYMORE.get())
                .add(BALLERITE_CLAYMORE.get())
                .add(NETHERITE_CLAYMORE.get());

        tag(ItemTags.SWORDS)
                .add(BALLERITE_SWORD.get())
                .add(LEAD_SWORD.get());

        tag(ItemTags.PICKAXES)
                .add(BALLERITE_PICKAXE.get())
                .add(LEAD_PICKAXE.get());
        
        tag(ItemTags.AXES)
                .add(BALLERITE_AXE.get())
                .add(LEAD_AXE.get());

        tag(ItemTags.SHOVELS)
                .add(BALLERITE_SHOVEL.get())
                .add(LEAD_SHOVEL.get());

        tag(ItemTags.HOES)
                .add(BALLERITE_HOE.get())
                .add(LEAD_HOE.get());

        tag(ItemTags.ARMOR_ENCHANTABLE)
                .add(BALLERITE_HELMET.get())
                .add(BALLERITE_CHESTPLATE.get())
                .add(BALLERITE_LEGGINGS.get())
                .add(BALLERITE_BOOTS.get())
                .add(LEAD_HELMET.get())
                .add(LEAD_CHESTPLATE.get())
                .add(LEAD_LEGGINGS.get())
                .add(LEAD_BOOTS.get());
        tag(ItemTags.HEAD_ARMOR)
                .add(BALLERITE_HELMET.get())
                .add(LEAD_HELMET.get());
        tag(ItemTags.CHEST_ARMOR)
                .add(BALLERITE_CHESTPLATE.get())
                .add(LEAD_CHESTPLATE.get());
        tag(ItemTags.LEG_ARMOR)
                .add(BALLERITE_LEGGINGS.get())
                .add(LEAD_LEGGINGS.get());
        tag(ItemTags.FOOT_ARMOR)
                .add(BALLERITE_BOOTS.get())
                .add(LEAD_BOOTS.get())
                .add(LEAVES_BOOTS.get());


        
        tag(ItemTags.PLANKS)
                .add(WITHERED_PLANKS.asItem())
                .add(STAR_PLANKS.asItem());

        tag(ItemTags.LOGS)
                .addTags(ModTags.Items.WITHERED_LOGS, ModTags.Items.STAR_LOGS);

        tag(ItemTags.LEAVES)
                .add(WITHERED_LEAVES.get().asItem())
                .add(STAR_LEAVES.get().asItem());

        tag(ItemTags.SAPLINGS)
                .add(WITHERED_SAPLING.get().asItem())
                .add(STAR_SAPLING.get().asItem());
        tag(ItemTags.LOGS_THAT_BURN)
                .add(WITHERED_LOG.get().asItem())
                .add(STRIPPED_WITHERED_LOG.get().asItem())
                .add(WITHERED_WOOD.get().asItem())
                .add(STRIPPED_WITHERED_WOOD.get().asItem());
        tag(ItemTags.WOODEN_FENCES)
                .add(WITHERED_FENCE.get().asItem())
                .add(STAR_FENCE.get().asItem());
        tag(ItemTags.FENCE_GATES)
                .add(WITHERED_FENCE_GATE.get().asItem())
                .add(STAR_FENCE_GATE.get().asItem());
        tag(ItemTags.WOODEN_DOORS)
                .add(WITHERED_DOOR.get().asItem())
                .add(STAR_DOOR.get().asItem());
        tag(ItemTags.WOODEN_TRAPDOORS)
                .add(WITHERED_TRAPDOOR.get().asItem())
                .add(STAR_TRAPDOOR.get().asItem());
        tag(ItemTags.WOODEN_PRESSURE_PLATES)
                .add(WITHERED_PRESSURE_PLATE.get().asItem())
                .add(STAR_PRESSURE_PLATE.get().asItem());
        tag(ItemTags.WOODEN_BUTTONS)
                .add(WITHERED_BUTTON.get().asItem())
                .add(STAR_DOOR.get().asItem());
        tag(ItemTags.WOODEN_SLABS)
                .add(WITHERED_SLAB.get().asItem())
                .add(STAR_SLAB.get().asItem());
        tag(ItemTags.WOODEN_STAIRS)
                .add(WITHERED_STAIRS.get().asItem())
                .add(STAR_STAIRS.get().asItem());



        tag(ModTags.Items.WITHERED_BLOCKS)
                .add(WITHERED_LOG.get().asItem())
                .add(STRIPPED_WITHERED_LOG.get().asItem())
                .add(WITHERED_WOOD.get().asItem())
                .add(STRIPPED_WITHERED_WOOD.get().asItem())
                .add(WITHERED_PLANKS.get().asItem())
                .add(WITHERED_SLAB.get().asItem())
                .add(WITHERED_STAIRS.get().asItem())
                .add(WITHERED_DOOR.get().asItem())
                .add(WITHERED_TRAPDOOR.get().asItem())
                .add(WITHERED_BUTTON.get().asItem())
                .add(WITHERED_PRESSURE_PLATE.get().asItem())
                .add(WITHERED_FENCE.get().asItem())
                .add(WITHERED_FENCE_GATE.get().asItem());
        tag(ModTags.Items.STAR_BLOCKS)
                .add(STAR_LOG.get().asItem())
                .add(STRIPPED_STAR_LOG.get().asItem())
                .add(STAR_WOOD.get().asItem())
                .add(STRIPPED_STAR_WOOD.get().asItem())
                .add(STAR_PLANKS.get().asItem())
                .add(STAR_SLAB.get().asItem())
                .add(STAR_STAIRS.get().asItem())
                .add(STAR_DOOR.get().asItem())
                .add(STAR_TRAPDOOR.get().asItem())
                .add(STAR_BUTTON.get().asItem())
                .add(STAR_PRESSURE_PLATE.get().asItem())
                .add(STAR_FENCE.get().asItem())
                .add(STAR_FENCE_GATE.get().asItem());
        
        tag(ModTags.Items.CLAYMORES)
                .add(WOODEN_CLAYMORE.get())
                .add(STONE_CLAYMORE.get())
                .add(IRON_CLAYMORE.get())
                .add(LEAD_CLAYMORE.get())
                .add(GOLD_CLAYMORE.get())
                .add(DIAMOND_CLAYMORE.get())
                .add(BALLERITE_CLAYMORE.get());


    }
}
