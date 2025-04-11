package net.comand_marp.marp.gendata;

import net.comand_marp.marp.MARP;
import net.comand_marp.marp.block.ModBlocks;
import net.comand_marp.marp.item.ModItems;
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.client.data.BlockStateModelGenerator;
import net.minecraft.client.data.ItemModelGenerator;
import net.minecraft.client.data.Models;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        BlockStateModelGenerator.BlockTexturePool stonePool = blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.STONE_BLOCK);

        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.ROAD);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.COPPER_ORE_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.HAY_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.GRASS_BLOCK);

        stonePool.stairs(ModBlocks.STONE_STAIRS);
        stonePool.slab(ModBlocks.STONE_SLAB);

        stonePool.button(ModBlocks.STONE_BUTTON);
        stonePool.pressurePlate(ModBlocks.STONE_PRESSURE_PLATE);

        stonePool.fence(ModBlocks.STONE_FENCE);
        stonePool.fenceGate(ModBlocks.STONE_FENCE_GATE);
        stonePool.wall(ModBlocks.STONE_WALL);

        blockStateModelGenerator.registerDoor(ModBlocks.STONE_DOOR);
        blockStateModelGenerator.registerTrapdoor(ModBlocks.STONE_TRAPDOOR);
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(ModItems.HAY, Models.GENERATED);
        itemModelGenerator.register(ModItems.GRASS, Models.GENERATED);
        itemModelGenerator.register(ModItems.COPPER_ORE, Models.GENERATED);
        itemModelGenerator.register(ModItems.BEER, Models.GENERATED);
        itemModelGenerator.register(ModItems.CHISEL, Models.GENERATED);
        itemModelGenerator.register(ModItems.PIECE_STONE, Models.GENERATED);
        itemModelGenerator.register(ModItems.COPPER_INGOT, Models.GENERATED);
        itemModelGenerator.register(ModItems.OAK_LOG, Models.GENERATED);

        itemModelGenerator.register(ModItems.COPPER_SWORD, Models.HANDHELD);
        itemModelGenerator.register(ModItems.COPPER_PICKAXE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.COPPER_SHOVEL, Models.HANDHELD);
        itemModelGenerator.register(ModItems.COPPER_AXE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.COPPER_HOE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.COPPER_HAMMER, Models.HANDHELD);

        itemModelGenerator.registerArmor(ModItems.COPPER_HELMET, RegistryKey.of(RegistryKey.ofRegistry(Identifier.ofVanilla("equipment_asset")), Identifier.of(MARP.MOD_ID, "copper_ingot")), "helmet", false);
        itemModelGenerator.registerArmor(ModItems.COPPER_CHESTPLATE, RegistryKey.of(RegistryKey.ofRegistry(Identifier.ofVanilla("equipment_asset")), Identifier.of(MARP.MOD_ID, "copper_ingot")), "chestplate", false);
        itemModelGenerator.registerArmor(ModItems.COPPER_LEGGINGS, RegistryKey.of(RegistryKey.ofRegistry(Identifier.ofVanilla("equipment_asset")), Identifier.of(MARP.MOD_ID, "copper_ingot")), "leggings", false);
        itemModelGenerator.registerArmor(ModItems.COPPER_BOOTS, RegistryKey.of(RegistryKey.ofRegistry(Identifier.ofVanilla("equipment_asset")), Identifier.of(MARP.MOD_ID, "copper_ingot")), "boots", false);

        itemModelGenerator.register(ModItems.COPPER_HORSE_ARMOR, Models.GENERATED);
    }
}
