package net.comand_marp.marp.block.custom.block_layers;

import net.minecraft.block.BlockState;
import net.minecraft.util.math.Direction;

public interface LayersBlock {
    int getLayers(BlockState state);
    BlockState withDecreasedLayers(BlockState state);
    boolean canPeelFromSide(Direction side);
}