package net.comand_marp.marp.trim;

import net.comand_marp.marp.MARP;
import net.comand_marp.marp.item.ModItems;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.equipment.trim.ArmorTrimPattern;
import net.minecraft.registry.*;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;

import java.util.Optional;

public class ModTrimPatterns {
    public static final RegistryKey<ArmorTrimPattern> KAUPEN = of("kaupen");

    public static void bootstrap(Registerable<ArmorTrimPattern> registry) {
        register(registry, ModItems.KAUPEN_SMITHING_TEMPLATE, KAUPEN);
    }

    public static Optional<RegistryEntry.Reference<ArmorTrimPattern>> get(RegistryWrapper.WrapperLookup registries, ItemStack stack) {
        return registries.getOrThrow(RegistryKeys.TRIM_PATTERN).streamEntries().filter(pattern -> stack.itemMatches(pattern.value().templateItem())).findFirst();
    }

    public static void register(Registerable<ArmorTrimPattern> registry, Item template, RegistryKey<ArmorTrimPattern> key) {
        ArmorTrimPattern armorTrimPattern = new ArmorTrimPattern(key.getValue(), Registries.ITEM.getEntry(template), Text.translatable(Util.createTranslationKey("trim_pattern", key.getValue())), false);
        registry.register(key, armorTrimPattern);
    }

    private static RegistryKey<ArmorTrimPattern> of(String id) {
        return RegistryKey.of(RegistryKeys.TRIM_PATTERN, Identifier.of(MARP.MOD_ID, id));
    }
}
