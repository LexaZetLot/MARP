package net.comand_marp.marp.events;

import net.comand_marp.marp.MARP;
import net.comand_marp.marp.block.custom.block_layers.LayersBlock;
import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.hit.BlockHitResult;

public class BlockEventHandler {

    public static void register() {
        PlayerBlockBreakEvents.BEFORE.register((world, player, pos, state, blockEntity) -> {
            Block block = state.getBlock();

            if (!(block instanceof LayersBlock peelable)) return true;

            if (!(player.raycast(5.0D, 0.0F, false) instanceof BlockHitResult hit)) return true;
            if (!hit.getBlockPos().equals(pos)) return true;


            int layers = peelable.getLayers(state);
            MARP.LOGGER.info("Layers {}", layers);
            if (layers > 0) {
                BlockState newState = peelable.withDecreasedLayers(state);
                world.setBlockState(pos, newState, Block.NOTIFY_ALL);
                world.emitGameEvent(player, net.minecraft.world.event.GameEvent.BLOCK_DESTROY, pos);
                return false;
            }

            return true;
        });
    }
}