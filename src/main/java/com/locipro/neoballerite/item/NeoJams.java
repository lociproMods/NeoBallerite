package com.locipro.neoballerite.item;

/*import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.registries.DeferredHolder;

import java.util.Map;
import static com.locipro.neoballerite.item.ModItems.*;*/

import com.locipro.neoballerite.item.custom.JamItem;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.registries.DeferredItem;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public abstract class NeoJams {
    public static Set<DeferredItem<JamItem>> JAMS = new HashSet<>();
}
