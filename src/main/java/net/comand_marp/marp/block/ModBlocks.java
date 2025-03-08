package net.comand_marp.marp.block;

import net.comand_marp.marp.MARP;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.Items;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

import java.util.function.Function;

public class ModBlocks {
    public static final Block GRASS_BLOCK = registerBlock("grass_block", Block::new, Block.Settings.create().strength(1.0F).sounds(BlockSoundGroup.GRASS));
    public static final Block HAY_BLOCK = registerBlock("hay_block", Block::new, Block.Settings.create().strength(1.0F).sounds(BlockSoundGroup.GRASS));

    public static final Block COPPER_ORE_BLOCK = registerBlock("copper_ore_block", Block::new, Block.Settings.create().strength(3.0F).requiresTool().sounds(BlockSoundGroup.STONE));


    private static Block registerBlock(String name, Function<AbstractBlock.Settings, Block> factory, Block.Settings settings) {
        final Identifier identifier = Identifier.of("marp", name);
        final RegistryKey<Block> registryKey = RegistryKey.of(RegistryKeys.BLOCK, identifier);

        final Block block = Blocks.register(registryKey, factory, settings);
        Items.register(block);
        return block;
    }

    public static void registerModBlock() {
        MARP.LOGGER.info("Registering Mod Items for " + MARP.MOD_ID);
    }
}
