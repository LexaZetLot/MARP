package net.comand_marp.marp.block.custom;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class Road extends Block {
    public Road(Settings settings) {
        super(settings);
    }

    @Override
    public void onSteppedOn(World world, BlockPos pos, BlockState state, Entity entity) {
        if (entity instanceof PlayerEntity player) {
            player.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, 20, 1, false, false));
        }
        super.onSteppedOn(world, pos, state, entity);
    }
}
