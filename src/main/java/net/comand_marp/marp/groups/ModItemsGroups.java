package net.comand_marp.marp.groups;

import net.comand_marp.marp.MARP;
import net.comand_marp.marp.block.ModBlocks;
import net.comand_marp.marp.item.ModItems;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemsGroups {
    public static final ItemGroup GRASS_ITEMS_GROUP = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(MARP.MOD_ID, "grass"),
            FabricItemGroup.builder().icon(() -> new ItemStack(ModItems.GRASS))
                    .displayName(Text.translatable("itemgroup.marp.grass"))
                    .entries((displayContext, entries) -> {
                        entries.add(ModItems.COPPER_ORE);
                        entries.add(ModItems.GRASS);
                        entries.add(ModItems.HAY);
                    }).build());

    public static final ItemGroup GRASS_ITEMS_BLOCK_GROUP = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(MARP.MOD_ID, "block_grass"),
            FabricItemGroup.builder().icon(() -> new ItemStack(ModBlocks.BLOCK_GRASS))
                    .displayName(Text.translatable("itemgroup.marp.block_grass"))
                    .entries((displayContext, entries) -> {
                        entries.add(ModBlocks.BLOCK_GRASS);
                        entries.add(ModBlocks.BLOCK_HAY);
                    }).build());

    public static void registerItemGroups() {
        MARP.LOGGER.info("Registering Item Groups for " + MARP.MOD_ID);

    }
}
