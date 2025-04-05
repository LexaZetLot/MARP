package net.comand_marp.marp.gendata;

import net.comand_marp.marp.block.ModBlocks;
import net.comand_marp.marp.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.Item;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.entry.LeafEntry;
import net.minecraft.loot.function.ApplyBonusLootFunction;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.loot.LootTable;

import java.util.concurrent.CompletableFuture;

public class ModLootTableProvider extends FabricBlockLootTableProvider  {
    public ModLootTableProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generate() {
        addDrop(ModBlocks.ROAD);

        addDrop(ModBlocks.STONE_BLOCK, multipleOreDrops(ModBlocks.STONE_BLOCK, ModItems.PIECE_STONE, 4, 7));
        addDrop(ModBlocks.COPPER_ORE_BLOCK, multipleOreDrops(ModBlocks.COPPER_ORE_BLOCK, ModItems.COPPER_ORE, 2, 5));
        addDrop(ModBlocks.GRASS_BLOCK, multipleOreDrops(ModBlocks.GRASS_BLOCK, ModItems.GRASS, 9));
        addDrop(ModBlocks.HAY_BLOCK, multipleOreDrops(ModBlocks.HAY_BLOCK, ModItems.HAY, 9));

        addDrop(ModBlocks.STONE_STAIRS);
        addDrop(ModBlocks.STONE_SLAB, slabDrops(ModBlocks.STONE_SLAB));

        addDrop(ModBlocks.STONE_BUTTON);
        addDrop(ModBlocks.STONE_PRESSURE_PLATE);

        addDrop(ModBlocks.STONE_WALL);
        addDrop(ModBlocks.STONE_FENCE);
        addDrop(ModBlocks.STONE_FENCE_GATE);

        addDrop(ModBlocks.STONE_DOOR, doorDrops(ModBlocks.STONE_DOOR));
        addDrop(ModBlocks.STONE_TRAPDOOR);
    }

    public LootTable.Builder multipleOreDrops(Block drop, Item item, float minDrops, float maxDrops) {
        RegistryWrapper.Impl<Enchantment> impl = this.registries.getOrThrow(RegistryKeys.ENCHANTMENT);
        return this.dropsWithSilkTouch(drop, this.applyExplosionDecay(drop, ((LeafEntry.Builder<?>)
                ItemEntry.builder(item).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(minDrops, maxDrops))))
                .apply(ApplyBonusLootFunction.oreDrops(impl.getOrThrow(Enchantments.FORTUNE)))));
    }

    public LootTable.Builder multipleOreDrops(Block drop, Item item, float count) {
        RegistryWrapper.Impl<Enchantment> impl = this.registries.getOrThrow(RegistryKeys.ENCHANTMENT);
        return this.dropsWithSilkTouch(drop, this.applyExplosionDecay(drop,
                ItemEntry.builder(item)
                        .apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(count))) // Фиксированное количество
                        .apply(ApplyBonusLootFunction.oreDrops(impl.getOrThrow(Enchantments.FORTUNE)))
        ));
    }
}
