package net.locipro.balleritemod.item.custom;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class JamItem extends Item {

    private Item BASE_ITEM;
    private String BASE_ITEM_ID;
    public String BASE_ITEM_NAME;
    private boolean nameIsPlural;
    public JamItem(FoodProperties foodComponent, Item baseItem) {
        super(new FabricItemSettings().stacksTo(64).food(foodComponent));
        initializeBaseItemProperties(baseItem);
    }
    public JamItem(Properties settings, FoodProperties foodComponent, Item baseItem) {
        super(settings.stacksTo(64).food(foodComponent));
        initializeBaseItemProperties(baseItem);
    }
    public JamItem(Item baseItem) {
        super(new FabricItemSettings().stacksTo(64));
        initializeBaseItemProperties(baseItem);
    }
    public JamItem(Properties settings, Item baseItem) {
        super(settings.stacksTo(64));
        initializeBaseItemProperties(baseItem);
    }

    public Item getBaseItem() {
        return BASE_ITEM;
    }
    public String getSingularBaseItemAsString() {
        return BASE_ITEM_NAME;
    }
    public String getPluralBaseItemAsString() {
        if (nameIsPlural) {
            return BASE_ITEM_NAME;
        }
        else {
            if (BASE_ITEM_NAME.endsWith("o")) {
                return BASE_ITEM_NAME + "es";
            }
            if (BASE_ITEM_NAME.endsWith("oy")) {
                return BASE_ITEM_NAME.substring(0, BASE_ITEM_NAME.length()-2) + "s";
            }
            if (BASE_ITEM_NAME.endsWith("y")) {
                return BASE_ITEM_NAME.substring(0, BASE_ITEM_NAME.length()-1) + "ies";
            }
            return BASE_ITEM_NAME + "s";
        }
    }

    private void initializeBaseItemProperties(Item baseItem) {
        BASE_ITEM = baseItem;
        BASE_ITEM_ID = BuiltInRegistries.ITEM.getKey(BASE_ITEM).toString();
        int indexOfId = BASE_ITEM_ID.indexOf(':');
        //Adding 1 to account for the ":" after the modid.
        String placeholder = BASE_ITEM_ID.substring(indexOfId + 1).replace("_", " ");
        BASE_ITEM_NAME = placeholder.substring(0, 1).toUpperCase() + placeholder.substring(1);

        if (BASE_ITEM_NAME.endsWith("ies")) {
            nameIsPlural = true;
        }
        if (BASE_ITEM_NAME.endsWith("oys")) {
            nameIsPlural = true;
        }
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level world, List<Component> tooltip, TooltipFlag context) {
        tooltip.add( Component.literal("Jam made out of " + getPluralBaseItemAsString() + ".").withStyle(ChatFormatting.GREEN));
        if (Screen.hasShiftDown()) {
            tooltip.add( Component.literal("--Jam item").withStyle(ChatFormatting.DARK_GREEN));
            tooltip.add( Component.literal("--Valuable trade").withStyle(ChatFormatting.DARK_PURPLE));
        }
    }
}
