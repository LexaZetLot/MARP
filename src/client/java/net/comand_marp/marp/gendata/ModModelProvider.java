package net.comand_marp.marp.gendata;

import net.comand_marp.marp.block.ModBlocks;
import net.comand_marp.marp.item.ModItems;
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.client.data.BlockStateModelGenerator;
import net.minecraft.client.data.ItemModelGenerator;
import net.minecraft.client.data.Models;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.ROAD);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.COPPER_ORE_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.HAY_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.GRASS_BLOCK);
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(ModItems.HAY, Models.GENERATED);
        itemModelGenerator.register(ModItems.GRASS, Models.GENERATED);
        itemModelGenerator.register(ModItems.COPPER_ORE, Models.GENERATED);
        itemModelGenerator.register(ModItems.BEAR, Models.GENERATED);
        itemModelGenerator.register(ModItems.CHISEL, Models.GENERATED);
        itemModelGenerator.register(ModItems.PIECE_STONE, Models.GENERATED);
        itemModelGenerator.register(ModItems.COPPER_INGOT, Models.GENERATED);
        itemModelGenerator.register(ModItems.OAK_LOG, Models.GENERATED);
    }
}
