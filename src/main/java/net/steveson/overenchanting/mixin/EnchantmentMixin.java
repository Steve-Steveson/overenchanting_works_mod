package net.steveson.overenchanting.mixin;

import net.minecraft.core.component.DataComponentType;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.ConditionalEffect;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.effects.EnchantmentValueEffect;
import net.steveson.overenchanting.component.ModDataComponents;
import org.apache.commons.lang3.mutable.MutableFloat;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(Enchantment.class)
public class EnchantmentMixin {

    @Inject(method = "modifyAmmoCount", at = @At(value = "TAIL"))
    public void modifyAmmoCount(ServerLevel level, int enchantmentLevel, ItemStack tool, MutableFloat ammoCount, CallbackInfo ci) {
        int clampedLvl = Mth.clamp(enchantmentLevel, 0, 2);
        this.modifyItemFilteredCount(ModDataComponents.TIPPED_AMMO_USE.get(), level, clampedLvl, tool, ammoCount);
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
