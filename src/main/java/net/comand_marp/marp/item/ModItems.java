package net.comand_marp.marp.item;

import net.comand_marp.marp.MARP;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

import java.util.function.Function;


public class ModItems {
    public static final Item GRASS = registerItem("grass", Item::new, new Item.Settings());
    public static final Item HAY = registerItem("hay", Item::new, new Item.Settings());

    public static final Item PIECE_STONE = registerItem("piece_stone", Item::new, new Item.Settings());
    public static final Item COPPER_ORE = registerItem("copper_ore", Item::new, new Item.Settings());
    public static final Item COPPER_INGOT = registerItem("copper_ingot", Item::new, new Item.Settings());

    private static Item registerItem(String name, Function<Item.Settings, Item> factory, Item.Settings settings) {
        final RegistryKey<Item> registryKey = RegistryKey.of(RegistryKeys.ITEM, Identifier.of(MARP.MOD_ID, name));
        return Items.register(registryKey, factory, settings);
    }

    public static void registerModItems() {
        MARP.LOGGER.info("Registering Mod Items for " + MARP.MOD_ID);
    }
}