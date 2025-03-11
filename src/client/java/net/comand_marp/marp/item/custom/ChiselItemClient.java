package net.comand_marp.marp.item.custom;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;

import java.util.List;

public class ChiselItemClient {
    public static void appendTooltip(ItemStack stack, List<Text> tooltip) {
        if (Screen.hasShiftDown()) {
            tooltip.add(Text.translatable("tooltip.marp.chisel.shift_down"));
        } else {
            tooltip.add(Text.translatable("tooltip.marp.chisel"));
        }
    }
}