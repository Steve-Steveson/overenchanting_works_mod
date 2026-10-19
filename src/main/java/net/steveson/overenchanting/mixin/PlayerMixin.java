package net.steveson.overenchanting.mixin;

import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.steveson.overenchanting.Config;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(Player.class)
public abstract class PlayerMixin extends LivingEntity {

    @Shadow
    public abstract Inventory getInventory();

    protected PlayerMixin(EntityType<? extends LivingEntity> entityType, Level level) {
        super(entityType, level);
    }



    @Unique
    private final HolderLookup.Provider REGISTRIES = this.level().registryAccess();

    // 2. Get the specific Enchantment Registry Lookup
    @Unique
    private final HolderLookup.RegistryLookup<Enchantment> ENCHANTMENT_REGISTRY = REGISTRIES.lookupOrThrow(Registries.ENCHANTMENT);

    // 3. Resolve the ResourceKey to a Holder
//    @Unique
//    private final Optional<Holder.Reference<Enchantment>> holderOpt = ENCHANTMENT_REGISTRY.get(Enchantments.AQUA_AFFINITY);

    @Unique
//    Holder<Enchantment> enchantmentHolder = holderOpt.get();
    private final Holder<Enchantment> ENCHANTMENT_HOLDER_A_A = ENCHANTMENT_REGISTRY.get(Enchantments.AQUA_AFFINITY).get();



    @ModifyArg(method = "getDigSpeed",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/neoforged/neoforge/event/EventHooks;getBreakSpeed(Lnet/minecraft/world/entity/player/Player;Lnet/minecraft/world/level/block/state/BlockState;FLnet/minecraft/core/BlockPos;)F"
            ),
            index = 2
    )
    public float modifyDigSpeed(float original) {

// 3. Resolve the ResourceKey to a Holder
// Enchantments.PROTECTION is a ResourceKey<Enchantment>
//        Optional<Holder.Reference<Enchantment>> holderOpt = ENCHANTMENT_REGISTRY.get(Enchantments.AQUA_AFFINITY);


        Inventory inventory = this.getInventory();
        ItemStack helmetStack = inventory.getArmor(3);
        System.out.println(helmetStack);
//            Optional<Holder.Reference<Enchantment>> protectionHolderOpt = Registries.ENCHANTMENT.
//                    .get(Enchantments.PROTECTION);

//            Holder<Enchantment> enchantmentHolder = Enchantments.AQUA_AFFINITY;
//        Holder<Enchantment> enchantmentHolder = holderOpt.get();

//        int lvl = helmetStack.getEnchantmentLevel(enchantmentHolder);
        int lvl = helmetStack.getEnchantmentLevel(ENCHANTMENT_HOLDER_A_A);

        System.out.println(lvl);



        double subMineSpeed = this.getAttribute(Attributes.SUBMERGED_MINING_SPEED).getValue();
        System.out.println("original " + original);

        if (Config.SWIM_MINE_FASTER.get() &&
                !this.onGround() &&
//                Causes crash Caused by: java.util.concurrent.ExecutionException: java.nio.file.FileSystemNotFoundException
//                I don't know why as deprecated method never did that to me before
//                this.isEyeInFluid(FluidTags.WATER) &&

                // without this, regular flying/jumping would dig a4 5/9 speed instead of 1/5
                this.isInWater() &&
//                subMineSpeed > 1)
                lvl > 1)
        {
            float point = Config.AQUA_AFFINITY_2_SPEED.get().floatValue();
            float slope = Config.AQUA_AFFINITY_SPEED_PER_LEVEL.get().floatValue();
//            float level = ((float) speed - 1.8f) / 0.8f;
            float lvlBeyond2 = lvl - 2;

            float wetMineSpeed = (slope * lvlBeyond2 + point);
//            if (wetMineSpeed > subMineSpeed){
//
//            }

            if (this.isEyeInFluidType(net.neoforged.neoforge.common.NeoForgeMod.WATER_TYPE.value())) {
//                float f1 = (original - 0.36f) * slope * 6.25f + point;
                float f1 = (original/((float) subMineSpeed)) * 5 * wetMineSpeed;
//                float f1 = (original/((float) subMineSpeed)) * 5 * (slope * level + point);
                System.out.println("\teye in water " + f1);
                return f1;
//                return original / 0.36f;
            }
//            if (Config.BOBBING_MINE_FASTER.get() && speed > 1.8) {
                float f2 = original * 5 * wetMineSpeed;
//                float f2 = original * 5 * (slope * level + point);
                System.out.println("\teye out of water " + f2);
                return f2;
//            }
//            System.out.println("\tlevel 2 bobbing " + (original * 5));
//            return original * 5;
        }

        return original;
    }
}
