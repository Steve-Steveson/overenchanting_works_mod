package net.steveson.overenchanting.mixin;

import net.minecraft.core.component.DataComponentType;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.*;
import net.minecraft.world.item.enchantment.effects.EnchantmentEntityEffect;
import net.minecraft.world.item.enchantment.effects.EnchantmentValueEffect;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.phys.Vec3;
import net.steveson.overenchanting.component.ModDataComponents;
import org.apache.commons.lang3.mutable.MutableFloat;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;
import java.util.function.Consumer;

import static net.minecraft.world.item.enchantment.Enchantment.blockHitContext;
import static net.minecraft.world.item.enchantment.Enchantment.doPostAttack;

@Mixin(Enchantment.class)
public abstract class EnchantmentMixin {

    @Shadow
    public abstract <T> List<T> getEffects(DataComponentType<List<T>> component);

    @Shadow
    public static <T> void applyEffects(List<ConditionalEffect<T>> conditionalEffects, LootContext context, Consumer<T> applier) {
    }


    @Inject(method = "modifyAmmoCount", at = @At(value = "TAIL"))
    public void modifyAmmoCount(ServerLevel level, int enchantmentLevel, ItemStack tool, MutableFloat ammoCount, CallbackInfo ci) {
        int clampedLvl = Mth.clamp(enchantmentLevel, 0, 2);
        this.modifyItemFilteredCount(ModDataComponents.TIPPED_AMMO_USE.get(), level, clampedLvl, tool, ammoCount);
    }

    @Inject(method = "onHitBlock", at = @At(value = "TAIL"))
    public void onHitBlock(ServerLevel level, int enchantmentLevel, EnchantedItemInUse item, Entity entity, Vec3 pos, BlockState state, CallbackInfo ci) {
        if (enchantmentLevel > 1) {
            applyEffects(
                    this.getEffects(ModDataComponents.HIT_BLOCK_LVL2.get()),
                    blockHitContext(level, enchantmentLevel, entity, pos, state),
                    p_346325_ -> p_346325_.apply(level, enchantmentLevel, item, entity, pos)
            );
        }
    }

    @Inject(method = "doPostAttack(Lnet/minecraft/server/level/ServerLevel;ILnet/minecraft/world/item/enchantment/EnchantedItemInUse;Lnet/minecraft/world/item/enchantment/EnchantmentTarget;Lnet/minecraft/world/entity/Entity;Lnet/minecraft/world/damagesource/DamageSource;)V",
            at = @At(value = "TAIL"))
    public void doMorePostAttack(ServerLevel level, int enchantmentLevel, EnchantedItemInUse item, EnchantmentTarget target, Entity entity, DamageSource damageSource, CallbackInfo ci) {
        if (enchantmentLevel > 1) {
            for (TargetedConditionalEffect<EnchantmentEntityEffect> targetedconditionaleffect : this.getEffects(ModDataComponents.POST_ATTACK_LVL2.get())) {
                if (target == targetedconditionaleffect.enchanted()) {
                    doPostAttack(targetedconditionaleffect, level, enchantmentLevel, item, entity, damageSource);
                }
            }
        }
    }


    @Shadow
    public void modifyItemFilteredCount(
            DataComponentType<List<ConditionalEffect<EnchantmentValueEffect>>> componentType,
            ServerLevel level,
            int enchantmentLevel,
            ItemStack tool,
            MutableFloat value
    ) {

    }
}
