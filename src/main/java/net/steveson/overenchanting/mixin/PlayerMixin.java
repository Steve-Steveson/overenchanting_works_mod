package net.steveson.overenchanting.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.steveson.overenchanting.Config;
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
        double speed = this.getAttribute(Attributes.SUBMERGED_MINING_SPEED).getValue();
        System.out.println("original " + original);

        if (Config.SWIM_MINE_FASTER.get() &&
                !this.onGround() &&
//                Causes crash Caused by: java.util.concurrent.ExecutionException: java.nio.file.FileSystemNotFoundException
//                I don't know why as deprecated method never did that to me before
//                this.isEyeInFluid(FluidTags.WATER) &&

                // without this, regular flying/jumping would dig a4 5/9 speed instead of 1/5
                this.isInWater() &&
                speed > 1)
        {
            float point = Config.AQUA_AFFINITY_2_SPEED.get().floatValue();
            float slope = Config.AQUA_AFFINITY_SPEED_PER_LEVEL.get().floatValue();
            float level = ((float) speed - 1.8f) / 0.8f;

            if (this.isEyeInFluidType(net.neoforged.neoforge.common.NeoForgeMod.WATER_TYPE.value())) {
//                float f1 = (original - 0.36f) * slope * 6.25f + point;
                float f1 = (original/((float) speed)) * 5 * (slope * level + point);
                System.out.println("\teye in water " + f1);
                return f1;
//                return original / 0.36f;
            }
//            if (Config.BOBBING_MINE_FASTER.get() && speed > 1.8) {
                float f2 = original * 5 * (slope * level + point);
                System.out.println("\teye out of water " + f2);
                return f2;
//            }
//            System.out.println("\tlevel 2 bobbing " + (original * 5));
//            return original * 5;
        }

        return original;
    }
}
