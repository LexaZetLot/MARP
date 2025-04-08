package net.comand_marp.marp.item;

import net.comand_marp.marp.MARP;
import net.comand_marp.marp.food.ModFoodComponents;
import net.comand_marp.marp.item.custom.ChiselItem;
import net.minecraft.item.*;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.List;
import java.util.function.Function;


public class ModItems {
    public static final Item GRASS = registerItem("grass", Item::new, new Item.Settings());
    public static final Item HAY = registerItem("hay", Item::new, new Item.Settings());

    public static final Item PIECE_STONE = registerItem("piece_stone", Item::new, new Item.Settings());
    public static final Item COPPER_ORE = registerItem("copper_ore", Item::new, new Item.Settings());
    public static final Item COPPER_INGOT = registerItem("copper_ingot", Item::new, new Item.Settings());

    public static final Item CHISEL = registerItem("chisel", ChiselItem::new, new ChiselItem.Settings().maxDamage(32));

    public static final Item BEER = registerItem("beer",
            settings -> new Item(settings.food(ModFoodComponents.BEER)) {
                @Override
                public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
                    tooltip.add(Text.translatable("tooltip.marp.beer.tooltip"));
                    super.appendTooltip(stack, context, tooltip, type);
                }
            },
            new Item.Settings().food(ModFoodComponents.BEER)
    );

    public static final Item COPPER_SWORD = registerItem("copper_sword", (settings) -> new SwordItem(ModToolMaterials.COPPER, 3, -2.4f, settings), new Item.Settings().registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(MARP.MOD_ID, "copper_sword"))));
    public static final Item COPPER_PICKAXE = registerItem("copper_pickaxe", (settings) -> new PickaxeItem(ModToolMaterials.COPPER, 1, -2.8f, settings), new Item.Settings().registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(MARP.MOD_ID, "copper_pickaxe"))));
    public static final Item COPPER_SHOVEL = registerItem("copper_shovel", (settings) -> new ShovelItem(ModToolMaterials.COPPER, 1.5f, -3.0f, settings), new Item.Settings().registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(MARP.MOD_ID, "copper_shovel"))));
    public static final Item COPPER_AXE = registerItem("copper_axe", (settings) -> new AxeItem(ModToolMaterials.COPPER, 6, -3.2f, settings), new Item.Settings().registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(MARP.MOD_ID, "copper_axe"))));
    public static final Item COPPER_HOE = registerItem("copper_hoe", (settings) -> new HoeItem(ModToolMaterials.COPPER, 0, -3f, settings), new Item.Settings().registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(MARP.MOD_ID, "copper_hoe"))));

    public static final Item OAK_LOG = registerItem("oak_log", Item::new, new Item.Settings());

    private static Item registerItem(String name, Function<Item.Settings, Item> factory, Item.Settings settings) {
        final RegistryKey<Item> registryKey = RegistryKey.of(RegistryKeys.ITEM, Identifier.of(MARP.MOD_ID, name));
        return Items.register(registryKey, factory, settings);
    }

    public static void registerModItems() {
        MARP.LOGGER.info("Registering Mod Items for " + MARP.MOD_ID);
    }
}