package net.steveson.overenchanting.loot;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.functions.CopyCustomDataFunction;
import net.minecraft.world.level.storage.loot.functions.LootItemConditionalFunction;
import net.minecraft.world.level.storage.loot.functions.LootItemFunctionType;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;

import java.util.List;

public class CopyBlockEntityFunction extends LootItemConditionalFunction {
    public static final MapCodec<CopyBlockEntityFunction> CODEC = RecordCodecBuilder.mapCodec(
            instance -> commonFields(instance)
                    .apply(instance, CopyBlockEntityFunction::new)
    );

    protected CopyBlockEntityFunction(List<LootItemCondition> predicates) {
        super(predicates);
    }

    @Override
    public LootItemFunctionType<? extends LootItemConditionalFunction> getType() {
        return ModLootItemFunctions.COPY_BLOCK_ENTITY.get();
    }

    @Override
    protected ItemStack run(ItemStack stack, LootContext context) {

        BlockEntity be = context.getParamOrNull(LootContextParams.BLOCK_ENTITY);

        if (be != null) {
            be.saveToItem(stack, context.getLevel().registryAccess());
        }

        return stack;
    }
}
