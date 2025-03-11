package net.comand_marp.marp;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;

import java.util.List;

public class MARPClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		ItemTooltipCallback.EVENT.register((stack, context, tooltip, type) -> {
			if (isChiselItem(stack)) {
				appendTooltip(stack, type, tooltip);
			}
		});
	}

	private static boolean isChiselItem(ItemStack stack) {
		return stack.getItem().toString().equals("marp:chisel"); // Укажите правильный ID предмета
	}

	private static void appendTooltip(ItemStack stack, List<Text> tooltip, TooltipType type) {
		if (Screen.hasShiftDown()) {
			tooltip.add(Text.translatable("tooltip.marp.chisel.shift_down"));
		} else {
			tooltip.add(Text.translatable("tooltip.marp.chisel"));
		}
	}
}