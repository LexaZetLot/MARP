package net.comand_marp.marp.block.custom.block_layers;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class DirtBlock extends Block implements LayersBlock {

    public static final IntProperty LAYERS = IntProperty.of("layers", 0, 7);

    public DirtBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState().with(LAYERS, 7));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(LAYERS);
    }

    @Override
    public int getLayers(BlockState state) {
        return state.get(LAYERS);
    }

    @Override
    public BlockState withDecreasedLayers(BlockState state) {
        int current = state.get(LAYERS);
        return state.with(LAYERS, Math.max(0, current - 1));
    }

    @Override
    public boolean canPeelFromSide(Direction side) {
        return side == Direction.UP;
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        int layer = state.get(LAYERS);
        float height = (layer + 1) * 2.0F; // от 2 до 16 пикселей
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
    public void afterBreak(World world, PlayerEntity player, BlockPos pos, BlockState state, @Nullable BlockEntity blockEntity, ItemStack tool) {
        super.afterBreak(world, player, pos, state, blockEntity, tool);
    }
}