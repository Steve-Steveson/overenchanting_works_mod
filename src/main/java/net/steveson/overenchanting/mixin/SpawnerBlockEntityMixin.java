package net.steveson.overenchanting.mixin;

import net.minecraft.world.level.block.entity.SpawnerBlockEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(SpawnerBlockEntity.class)
public class SpawnerBlockEntityMixin {

    @Inject(method = "onlyOpCanSetNbt", at = @At(value = "RETURN"), cancellable = true)
    public void onlyOpCanSetNbt(CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue(false);
    }
}
