package net.steveson.overenchanting.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(Player.class)
public abstract class PlayerMixin extends LivingEntity {

    protected PlayerMixin(EntityType<? extends LivingEntity> entityType, Level level) {
        super(entityType, level);
    }

//    @Inject(method = "getDigSpeed", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/ai/attributes/AttributeInstance;getValue()D"), locals = LocalCapture.CAPTURE_FAILHARD)
//    public void modifyDigSpeed(BlockState p_36282_, BlockPos pos, CallbackInfoReturnable<Float> cir, float f) {
//        if (!this.onGround() && this.getAttribute(Attributes.SUBMERGED_MINING_SPEED).getValue() > 1) {
//            System.out.println("XXXX I AM DIGGING WHILE SWIMMING");
//            f /= 0.36F;
//        }
//    }


//    @ModifyVariable(method = "getDigSpeed", at = @At(value = ""))
//    public float modifyDigSpeed() {
//
//    }


    @ModifyArg(method = "getDigSpeed",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/neoforged/neoforge/event/EventHooks;getBreakSpeed(Lnet/minecraft/world/entity/player/Player;Lnet/minecraft/world/level/block/state/BlockState;FLnet/minecraft/core/BlockPos;)F"
            ),
            index = 2
    )
        public float modifyDigSpeed(float original) {
        if (!this.onGround() &&
//                Causes crash Caused by: java.util.concurrent.ExecutionException: java.nio.file.FileSystemNotFoundException
//                I don't know why as deprecated method never did that to me before
//                this.isEyeInFluid(FluidTags.WATER) &&

                // without this, regular flying/jumping would dig a4 5/9 speed instead of 1/5
                this.isEyeInFluidType(net.neoforged.neoforge.common.NeoForgeMod.WATER_TYPE.value()) &&
                this.getAttribute(Attributes.SUBMERGED_MINING_SPEED).getValue() > 1) {
            return original / 0.36f;
        }

        return original;
    }
}
