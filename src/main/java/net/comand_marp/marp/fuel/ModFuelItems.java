package net.comand_marp.marp.fuel;

import net.comand_marp.marp.item.ModItems;
import net.fabricmc.fabric.api.registry.FuelRegistryEvents;

public class ModFuelItems {
    public static void registerFuels() {
        FuelRegistryEvents.BUILD.register((builder, context) -> {
            builder.add(ModItems.OAK_LOG, 300);
        });
    }
}