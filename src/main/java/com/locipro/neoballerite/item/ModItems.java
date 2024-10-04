package com.locipro.neoballerite.item;


import com.locipro.neoballerite.item.custom.BallDowserItem;
import com.locipro.neoballerite.misc.food.BalleriteFoodProperties;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import static com.locipro.neoballerite.NeoBallerite.MODID;

public class ModItems {

    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(MODID);

    public static final DeferredItem<Item> BALL_DOWSER = ITEMS.register("ball_dowser",
            () -> new BallDowserItem(new Item.Properties()));
    public static final DeferredItem<Item> COMPRESSED_BALLERITE_INGOT = ITEMS.register("compressed_ballerite_ingot",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> RAW_BALLERITE = ITEMS.register("raw_ballerite",
            () -> new Item(new Item.Properties().food(BalleriteFoodProperties.RAW_BALLERITE)));




}
