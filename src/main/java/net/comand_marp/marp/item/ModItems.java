package net.comand_marp.marp.item;

import net.comand_marp.marp.MARP;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;


public class ModItems {
    public static final Item ITEM_KNOW_1 = registerItem("item_know_1 ", new Item(new Item.Settings()));

    private static Item registerItem(String id, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(MARP.MOD_ID, id), item);
    }
    public static void registerModItems() {
        MARP.LOGGER.info("Registering Mod Items for " + MARP.MOD_ID);
    }
}
