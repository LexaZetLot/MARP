package net.comand_marp.marp.block.custom;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import org.jetbrains.annotations.Nullable;


public class DirtBlock extends Block {
    public static final IntProperty LAYERS = IntProperty.of("layers", 0, 7);


    public DirtBlock(Settings settings) {
        super(settings);
        setDefaultState(getStateManager().getDefaultState().with(LAYERS, 7));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(LAYERS);
    }

    @Override
    public void afterBreak(World world, PlayerEntity player, BlockPos pos, BlockState state, @Nullable BlockEntity blockEntity, ItemStack tool) {
        if (!(world instanceof ServerWorld)) return;

        int layers = state.get(LAYERS);
        if (layers > 0) {
            BlockState newState = state.with(LAYERS, layers - 1);
            world.setBlockState(pos, newState, Block.NOTIFY_ALL);

            this.spawnBreakParticles(world, player, pos, state);

            world.emitGameEvent(GameEvent.BLOCK_DESTROY, pos, GameEvent.Emitter.of(player, state));

        } else {
            super.afterBreak(world, player, pos, state, blockEntity, tool);
        }
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        int layer = state.get(LAYERS);
        float height = (layer + 1) * 2.0F;
        return Block.createCuboidShape(0, 0, 0, 16, height, 16);
    }


    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return getOutlineShape(state, world, pos, context);
    }

    @Override
    protected float calcBlockBreakingDelta(BlockState state, PlayerEntity player, BlockView world, BlockPos pos) {
        int layers = state.get(LAYERS);
        return super.calcBlockBreakingDelta(state, player, world, pos) * (1.0f + (7 - layers) * 0.2f);
    }

    @Override
    public void onBlockBreakStart(BlockState state, World world, BlockPos pos, PlayerEntity player) {
    }
}
