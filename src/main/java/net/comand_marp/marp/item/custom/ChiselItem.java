package net.comand_marp.marp.item.custom;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.CustomModelDataComponent;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public class ChiselItem extends Item {
    private static final Map<Block, Block> CHISEL_MAP = Map.of(
            Blocks.STONE, Blocks.STONE_BRICKS
    );

    public ChiselItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        World world = context.getWorld();
        BlockPos pos = context.getBlockPos();
        Block clickedBlock = world.getBlockState(pos).getBlock();
        ItemStack stack = context.getStack();

        if (CHISEL_MAP.containsKey(clickedBlock)) {
            if (!world.isClient()) {
                // Меняем блок
                world.setBlockState(pos, CHISEL_MAP.get(clickedBlock).getDefaultState());

                // Наносим урон предмету
                stack.damage(1, (ServerWorld) world, (ServerPlayerEntity) context.getPlayer(),
                        item -> Objects.requireNonNull(context.getPlayer())
                                .sendEquipmentBreakStatus(item, EquipmentSlot.MAINHAND));

                // Проигрываем звук
                world.playSound(null, pos, SoundEvents.BLOCK_GRINDSTONE_USE, SoundCategory.BLOCKS);

                // Меняем custom_model_data
                CustomModelDataComponent modelData = stack.get(DataComponentTypes.CUSTOM_MODEL_DATA);

                // Если компонента нет, создаем новый
                if (modelData == null) {
                    modelData = CustomModelDataComponent.DEFAULT;
                }

                // Переключаем модель, используя список floats
                List<Float> modelDataList = modelData.floats();
                int currentModel = 0;

                // Если список не пуст, берем текущее значение
                if (!modelDataList.isEmpty()) {
                    currentModel = modelDataList.getFirst().intValue(); // Преобразуем float в int
                }

                // Переключаем модель (например, с 0 на 1 или наоборот)
                int newModel = (currentModel == 0) ? 1 : 0;

                stack.set(DataComponentTypes.CUSTOM_MODEL_DATA, new CustomModelDataComponent(
                        List.of((float) newModel),
                        modelData.flags(),
                        modelData.strings(),
                        modelData.colors()
                ));

            }
            return ActionResult.SUCCESS;
        }
        return ActionResult.PASS;
    }
}