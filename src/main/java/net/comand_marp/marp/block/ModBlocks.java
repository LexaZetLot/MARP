package net.comand_marp.marp.block;

import net.comand_marp.marp.MARP;
import net.comand_marp.marp.block.custom.Road;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.*;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.Items;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

import java.util.function.Function;

public class ModBlocks {
    public static final Block GRASS_BLOCK;
    public static final Block HAY_BLOCK;

    public static final Block COPPER_ORE_BLOCK;
    public static final Block STONE_BLOCK;

    public static final Block ROAD;

    public static final Block STONE_STAIRS ;
    public static final Block STONE_SLAB;

    public static final Block STONE_BUTTON;
    public static final Block STONE_PRESSURE_PLATE;

    public static final Block STONE_FENCE;
    public static final Block STONE_FENCE_GATE;
    public static final Block STONE_WALL;

    public static final Block STONE_DOOR;
    public static final Block STONE_TRAPDOOR;

    static {
        GRASS_BLOCK = registerBlock("grass_block", Block::new, Block.Settings.create().strength(1.0F).sounds(BlockSoundGroup.GRASS));
        HAY_BLOCK = registerBlock("hay_block", Block::new, Block.Settings.create().strength(1.0F).sounds(BlockSoundGroup.GRASS));

        COPPER_ORE_BLOCK = registerBlock("copper_ore_block", Block::new, Block.Settings.create().strength(3.0F).requiresTool().sounds(BlockSoundGroup.STONE));

        STONE_BLOCK = registerBlock("stone_block", Block::new, Block.Settings.create().strength(3.0F).requiresTool().sounds(BlockSoundGroup.STONE));
        ROAD = registerBlock("road", Road::new, Block.Settings.create().strength(1.0F).sounds(BlockSoundGroup.GRASS));

        STONE_STAIRS = registerBlock("stone_stairs", (settings) -> new StairsBlock(ModBlocks.STONE_BLOCK.getDefaultState(), settings), Block.Settings.create().strength(3.0F).requiresTool().sounds(BlockSoundGroup.STONE));
        STONE_SLAB = registerBlock("stone_slab", SlabBlock::new, AbstractBlock.Settings.create().strength(2f).requiresTool());

        STONE_BUTTON = registerBlock("stone_button", (settings) -> new ButtonBlock(BlockSetType.STONE, 2, settings.noCollision()), AbstractBlock.Settings.create().strength(2f).requiresTool());
        STONE_PRESSURE_PLATE = registerBlock("stone_pressure_plate", (settings) -> new PressurePlateBlock(BlockSetType.STONE, settings), AbstractBlock.Settings.create().strength(2f).requiresTool());

        STONE_FENCE = registerBlock("stone_fence", FenceBlock::new, AbstractBlock.Settings.create().strength(2f).requiresTool());
        STONE_FENCE_GATE = registerBlock("stone_fence_gate", (settings) -> new FenceGateBlock(WoodType.OAK, settings), AbstractBlock.Settings.create().strength(2f).requiresTool());
        STONE_WALL = registerBlock("stone_wall", WallBlock::new, AbstractBlock.Settings.create().strength(2f).requiresTool());

        STONE_DOOR = registerBlock("stone_door", (settings) -> new DoorBlock(BlockSetType.STONE, settings.nonOpaque()), AbstractBlock.Settings.create().strength(2f).requiresTool());
        STONE_TRAPDOOR = registerBlock("stone_trapdoor", (settings) -> new TrapdoorBlock(BlockSetType.STONE, settings.nonOpaque()), AbstractBlock.Settings.create().strength(2f).requiresTool());
    }

    private static Block registerBlock(String name, Function<AbstractBlock.Settings, Block> factory, Block.Settings settings) {
        final Identifier identifier = Identifier.of(MARP.MOD_ID, name);
        final RegistryKey<Block> registryKey = RegistryKey.of(RegistryKeys.BLOCK, identifier);

        final Block block = Blocks.register(registryKey, factory, settings);
        Items.register(block);
        MARP.LOGGER.info("Successfully registered block: {}", identifier);
        return block;
    }

    public static void registerModBlock() {
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS).register(entries ->
            entries.add(ModBlocks.STONE_BLOCK)
        );
        MARP.LOGGER.info("Registering Mod Items for " + MARP.MOD_ID);
    }
}
