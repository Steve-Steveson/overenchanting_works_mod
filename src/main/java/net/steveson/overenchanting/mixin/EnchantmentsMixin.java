package net.steveson.overenchanting.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.EnchantmentTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentEffectComponents;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.item.enchantment.LevelBasedValue;
import net.minecraft.world.item.enchantment.effects.MultiplyValue;
import net.minecraft.world.item.enchantment.effects.SetValue;
import net.minecraft.world.level.storage.loot.predicates.MatchTool;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Enchantments.class)
public class EnchantmentsMixin {

//    @Inject(method = "bootstrap", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/enchantment/Enchantment$Builder;withEffect(Lnet/minecraft/core/component/DataComponentType;)Lnet/minecraft/world/item/enchantment/Enchantment$Builder;"))
//    private static void infinityTwo(BootstrapContext<Enchantment> context, CallbackInfo ci) {
//
//    }

    //silk = 21?


//    @WrapOperation(method = "bootstrap",
//            at = @At(
//                    value = "INVOKE",
//                    target = "Lnet/minecraft/world/item/enchantment/Enchantments;register(Lnet/minecraft/data/worldgen/BootstrapContext;Lnet/minecraft/resources/ResourceKey;Lnet/minecraft/world/item/enchantment/Enchantment$Builder;)V",
//                    ordinal = 27
//            ))
//    private static void infinityPlus(BootstrapContext<Enchantment> context, ResourceKey<Enchantment> key, Enchantment.Builder builder, Operation<Void> original) {
//
//        builder.withEffect(EnchantmentEffectComponents.REPAIR_WITH_XP, new MultiplyValue(LevelBasedValue.constant(2.0F)));
//    }

//    @WrapOperation(method = "bootstrap",
//            at = @At(
//                    value = "INVOKE",
//                    target = "Lnet/minecraft/world/item/enchantment/Enchantments;register(Lnet/minecraft/data/worldgen/BootstrapContext;Lnet/minecraft/resources/ResourceKey;Lnet/minecraft/world/item/enchantment/Enchantment$Builder;)V",
//                    ordinal = 27
//            ))
//    private static void infinityPlus(BootstrapContext<Enchantment> context, ResourceKey<Enchantment> key, Enchantment.Builder builder, Operation<Void> original) {
//        System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
//        System.out.println(key + " QQQQQQQQQQQQQQQQQQ");
//    }



//    @Redirect(method = "bootstrap",
//            at = @At(
//                    value = "INVOKE",
//                    target = "Lnet/minecraft/world/item/enchantment/Enchantments;register(Lnet/minecraft/data/worldgen/BootstrapContext;Lnet/minecraft/resources/ResourceKey;Lnet/minecraft/world/item/enchantment/Enchantment$Builder;)V",
//                    ordinal = 27))
//    private static void infinityTooth(BootstrapContext<Enchantment> context, ResourceKey<Enchantment> key, Enchantment.Builder builder) {
//
//        builder.withEffect(EnchantmentEffectComponents.REPAIR_WITH_XP, new MultiplyValue(LevelBasedValue.constant(2.0F)));
//
//
//        System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
//        System.out.println(key + " QQQQQQQQQQQQQQQQQQ");
//    }



//    @ModifyArg(method = "bootstrap",
//            at = @At(
//                    value = "INVOKE",
//                    target = "Lnet/minecraft/world/item/enchantment/Enchantments;register(Lnet/minecraft/data/worldgen/BootstrapContext;Lnet/minecraft/resources/ResourceKey;Lnet/minecraft/world/item/enchantment/Enchantment$Builder;)V",
//                    ordinal = 27),
//            index = 3)
//    private static Enchantment.Builder infinityToo(BootstrapContext<Enchantment> context) {
//
//        HolderGetter<Enchantment> holdergetter1 = context.lookup(Registries.ENCHANTMENT);
//        HolderGetter<Item> holdergetter2 = context.lookup(Registries.ITEM);
//
//        return Enchantment.enchantment(
//                        Enchantment.definition(
//                                holdergetter2.getOrThrow(ItemTags.BOW_ENCHANTABLE),
//                                1,
//                                1,
//                                Enchantment.constantCost(20),
//                                Enchantment.constantCost(50),
//                                8,
//                                EquipmentSlotGroup.MAINHAND
//                        )
//                )
//                .exclusiveWith(holdergetter1.getOrThrow(EnchantmentTags.BOW_EXCLUSIVE))
//                .withEffect(
//                        EnchantmentEffectComponents.AMMO_USE,
//                        new SetValue(LevelBasedValue.constant(0.0F)),
//                        MatchTool.toolMatches(ItemPredicate.Builder.item().of(Items.ARROW))
//                )
//                .withEffect(
//                        EnchantmentEffectComponents.AMMO_USE,
//                        new SetValue(LevelBasedValue.constant(0.5F)),
//                        MatchTool.toolMatches(ItemPredicate.Builder.item().of(Items.TIPPED_ARROW))
//                )
//                ;
//
//    }


//    @ModifyArg(method = "bootstrap",
//            at = @At(
//                    value = "INVOKE",
//                    target = "Lnet/minecraft/world/item/enchantment/Enchantments;register(Lnet/minecraft/data/worldgen/BootstrapContext;Lnet/minecraft/resources/ResourceKey;Lnet/minecraft/world/item/enchantment/Enchantment$Builder;)V",
//                    ordinal = 27),
//            index = 2)
//    private static Enchantment.Builder infinityToo(Enchantment.Builder builder) {
//        return builder
////                .withEffect(
////                        EnchantmentEffectComponents.AMMO_USE,
////                        new SetValue(LevelBasedValue.constant(0.0F)),
////                        MatchTool.toolMatches(ItemPredicate.Builder.item().of(Items.TIPPED_ARROW))
////                )
//                .withEffect(EnchantmentEffectComponents.REPAIR_WITH_XP, new MultiplyValue(LevelBasedValue.constant(2.0F)))
//                ;
//
//    }
}
