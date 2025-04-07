package net.comand_marp.marp.component;

import net.comand_marp.marp.MARP;
import net.minecraft.component.ComponentType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;

import java.util.function.UnaryOperator;

public class ModDataComponentTypes {
    public static final ComponentType<BlockPos> COORDINATES = registerComponentType("coordinates", builder -> builder.codec(BlockPos.CODEC));


    private static <T> ComponentType<T> registerComponentType(String name, UnaryOperator<ComponentType.Builder<T>> builderOperator) {
        Identifier identifier = Identifier.of(MARP.MOD_ID, name);
        ComponentType<T> componentType = builderOperator.apply(ComponentType.builder()).build();

        Registry.register(Registries.DATA_COMPONENT_TYPE, identifier, componentType);
        MARP.LOGGER.info("Successfully registered component type: {}", identifier);

        return componentType;
    }

    public static void registerDataComponentTypes() {
        MARP.LOGGER.info("Registering Data Component Types for " + MARP.MOD_ID);
    }
}