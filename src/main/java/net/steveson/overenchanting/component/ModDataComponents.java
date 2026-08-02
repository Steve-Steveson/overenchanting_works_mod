package net.steveson.overenchanting.component;

import com.mojang.serialization.Codec;
import net.minecraft.core.component.DataComponentMap;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.enchantment.ConditionalEffect;
import net.minecraft.world.item.enchantment.TargetedConditionalEffect;
import net.minecraft.world.item.enchantment.effects.EnchantmentEntityEffect;
import net.minecraft.world.item.enchantment.effects.EnchantmentValueEffect;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.steveson.overenchanting.OverenchantingMod;
import net.steveson.overenchanting.util.RegistryUtils;

import java.util.List;
import java.util.function.UnaryOperator;

public class ModDataComponents {
//    public static final DeferredRegister<DataComponentType<?>> DATA_COMPONENT_TYPES =
//            DeferredRegister.createDataComponents(OverenchantingMod.MOD_ID);
    public static final RegistryUtils.EnchantmentEffectComponents ENCHANTMENT_EFFECT_COMPONENTS =
            RegistryUtils.createEnchantmentEffectComponents(OverenchantingMod.MOD_ID);



    public static final DeferredHolder<DataComponentType<?>, DataComponentType<List<ConditionalEffect<EnchantmentValueEffect>>>> TIPPED_AMMO_USE = ENCHANTMENT_EFFECT_COMPONENTS.registerComponentType(
            "tipped_ammo_use",
            builder -> builder.persistent(
                    ConditionalEffect.codec(EnchantmentValueEffect.CODEC, LootContextParamSets.ENCHANTED_ITEM).listOf()
            )
    );

    public static final DeferredHolder<DataComponentType<?>, DataComponentType<List<ConditionalEffect<EnchantmentEntityEffect>>>> HIT_BLOCK_LVL2 = ENCHANTMENT_EFFECT_COMPONENTS.registerComponentType(
            "hit_block_lvl2",
            builder -> builder.persistent(
                    ConditionalEffect.codec(EnchantmentEntityEffect.CODEC, LootContextParamSets.HIT_BLOCK).listOf()
            )
    );

    public static final DeferredHolder<DataComponentType<?>, DataComponentType<List<TargetedConditionalEffect<EnchantmentEntityEffect>>>> POST_ATTACK_LVL2 = ENCHANTMENT_EFFECT_COMPONENTS.registerComponentType(
            "post_attack_lvl2",
            builder -> builder.persistent(
                    TargetedConditionalEffect.codec(EnchantmentEntityEffect.CODEC, LootContextParamSets.ENCHANTED_DAMAGE).listOf()
            )
    );



//    private static <T>DeferredHolder<DataComponentType<?>, DataComponentType<T>> register(String name, UnaryOperator<DataComponentType.Builder<T>> builderUnaryOperator) {
//        System.out.println("QQQQ I AM REGISTERING " + name);
//        return DATA_COMPONENT_TYPES.register(name, ()-> builderUnaryOperator.apply(DataComponentType.builder()).build());
//    }

    public static void register(IEventBus eventBus) {
//        DATA_COMPONENT_TYPES.register(eventBus);
        ENCHANTMENT_EFFECT_COMPONENTS.register(eventBus);
    }
}
