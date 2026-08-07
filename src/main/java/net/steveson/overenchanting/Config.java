package net.steveson.overenchanting;

import java.util.List;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.common.ModConfigSpec;

// An example config class. This is not required, but it's a good idea to have one to keep your config organized.
// Demonstrates how to use Neo's config APIs
public class Config {
    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();

    public static final ModConfigSpec.BooleanValue SWIM_MINE_FASTER = BUILDER
            .comment(" Whether higher levels of Aqua Affinity should reduce the dig speed penalty for not touching the sea floor")
            .define("aquaAffinityForSwimming", true);

    public static final ModConfigSpec.DoubleValue AQUA_AFFINITY_2_SPEED = BUILDER
            .comment(" How fast should a swimming player mine with Aqua Affinity 2?")
            .comment(" Units are in multiples of mining speed standing on land")
            .defineInRange("aquaAffinity2DigSpeed", 1, 0.2, 1.8);

    public static final ModConfigSpec.DoubleValue AQUA_AFFINITY_SPEED_PER_LEVEL = BUILDER
            .comment(" How much additional dig speed should a swimming player get with Aqua Affinity for each level beyond 2?")
            .comment(" Vanilla MAX: 0.8")
            .defineInRange("aquaAffinityDigSpeedPerLevel", 0.4, 0, Double.MAX_VALUE);

    public static final ModConfigSpec.DoubleValue VANILLA_AQUA_AFFINITY_SPEED_PER_LEVEL = BUILDER
            .comment(" How much additional dig speed should a swimming player get with Aqua Affinity for each level beyond 2?")
            .defineInRange("aquaAffinityDigSpeedPerLevel", 0.8, 0, Double.MAX_VALUE);

//    public static final ModConfigSpec.BooleanValue BOBBING_MINE_FASTER = BUILDER
//            .comment(" Whether Aqua Affinity 3 and above should give dig speed bonuses when floating with your head above water")
//            .comment(" Turn this off if you change the speed bonus you get from Aqua Affinity through a datapack and don't want weird dig speeds ")
//            .define("aquaAffinityForBobbingByLevel", true);

//    public static final ModConfigSpec.IntValue SPAWN_DELAY = BUILDER
//            .comment("The delay value for Spawners picked up with Silk Touch 2 in tick")
//            .comment("After mobs spawn, the delay is randomized between 200 and 800")
//            .comment("At 0, mobs can spawn instantly upon block placement")
//            .defineInRange("magicNumber", 0, 0, Short.MAX_VALUE);



    static final ModConfigSpec SPEC = BUILDER.build();

    private static boolean validateItemName(final Object obj) {
        return obj instanceof String itemName && BuiltInRegistries.ITEM.containsKey(ResourceLocation.parse(itemName));
    }
}
