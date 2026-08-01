package net.steveson.overenchanting.loot;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.storage.loot.functions.LootItemFunctionType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.steveson.overenchanting.OverenchantingMod;

public class ModLootItemFunctions {
    public static final DeferredRegister<LootItemFunctionType<?>> LOOT_ITEM_FUNCTION_TYPES =
            DeferredRegister.create(Registries.LOOT_FUNCTION_TYPE, OverenchantingMod.MOD_ID);


    public static final DeferredHolder<LootItemFunctionType<?>, LootItemFunctionType<CopyBlockEntityFunction>> COPY_BLOCK_ENTITY =
            LOOT_ITEM_FUNCTION_TYPES.register("copy_block_entity_data",()-> new LootItemFunctionType<>(CopyBlockEntityFunction.CODEC));



    public static void register(IEventBus eventBus) {
        LOOT_ITEM_FUNCTION_TYPES.register(eventBus);
    }

}
