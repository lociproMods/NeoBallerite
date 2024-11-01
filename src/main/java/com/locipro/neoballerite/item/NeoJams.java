package com.locipro.neoballerite.item;



import com.locipro.neoballerite.item.custom.JamItem;
import net.neoforged.neoforge.registries.DeferredItem;

import java.util.ArrayList;
import java.util.List;

public abstract class NeoJams {
    private static final int MAX_JAMS = 32;
    public static List<DeferredItem<JamItem>> JAMS = new ArrayList<>(MAX_JAMS);
}
