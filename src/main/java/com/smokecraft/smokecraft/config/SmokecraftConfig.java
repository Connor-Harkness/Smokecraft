package com.smokecraft.smokecraft.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class SmokecraftConfig {
    
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec COMMON_CONFIG;

    // General settings
    public static final ForgeConfigSpec.BooleanValue SHOW_HEALTH_WARNING;
    public static final ForgeConfigSpec.BooleanValue COSMETIC_ONLY;
    
    // Cigarette settings
    public static final ForgeConfigSpec.IntValue CIGARETTE_USE_DURATION;
    public static final ForgeConfigSpec.IntValue CIGARETTE_HUNGER_DRAIN;
    public static final ForgeConfigSpec.BooleanValue CIGARETTE_BUTT_ENABLED;
    public static final ForgeConfigSpec.DoubleValue CIGARETTE_BUTT_COMPOST_CHANCE;
    
    // Lighter settings
    public static final ForgeConfigSpec.IntValue LIGHTER_DURABILITY;
    
    // Effects (only applied when cosmetic_only is false)
    public static final ForgeConfigSpec.BooleanValue ENABLE_NICOTINE_RUSH;
    public static final ForgeConfigSpec.IntValue NICOTINE_RUSH_DURATION;
    public static final ForgeConfigSpec.IntValue NICOTINE_RUSH_AMPLIFIER;
    
    public static final ForgeConfigSpec.BooleanValue ENABLE_HUNGER_EFFECT;
    public static final ForgeConfigSpec.BooleanValue ENABLE_NAUSEA_EFFECT;
    public static final ForgeConfigSpec.IntValue NAUSEA_DURATION;
    public static final ForgeConfigSpec.IntValue NAUSEA_AMPLIFIER;
    
    // Particles and visuals
    public static final ForgeConfigSpec.DoubleValue SMOKE_PARTICLE_DENSITY;
    public static final ForgeConfigSpec.BooleanValue ENABLE_EMBER_TIP;

    static {
        BUILDER.comment("Smokecraft Configuration");
        
        BUILDER.push("general");
        SHOW_HEALTH_WARNING = BUILDER
            .comment("Show health warning message when player joins")
            .define("show_health_warning", true);
        COSMETIC_ONLY = BUILDER
            .comment("When true, cigarettes only provide visual/audio effects with no gameplay impact")
            .define("cosmetic_only", true);
        BUILDER.pop();

        BUILDER.push("cigarette");
        CIGARETTE_USE_DURATION = BUILDER
            .comment("How long it takes to smoke a cigarette (in ticks, 20 ticks = 1 second)")
            .defineInRange("use_duration", 80, 20, 400);
        CIGARETTE_HUNGER_DRAIN = BUILDER
            .comment("How much hunger smoking a cigarette drains (only when not cosmetic_only)")
            .defineInRange("hunger_drain", 1, 0, 10);
        CIGARETTE_BUTT_ENABLED = BUILDER
            .comment("Whether cigarette butts are dropped after smoking")
            .define("butt_enabled", false);
        CIGARETTE_BUTT_COMPOST_CHANCE = BUILDER
            .comment("Chance for cigarette butts to be compostable (0.0 to 1.0)")
            .defineInRange("butt_compost_chance", 0.1, 0.0, 1.0);
        BUILDER.pop();

        BUILDER.push("lighter");
        LIGHTER_DURABILITY = BUILDER
            .comment("Durability of the lighter (number of uses)")
            .defineInRange("durability", 100, 1, 1000);
        BUILDER.pop();

        BUILDER.push("effects");
        ENABLE_NICOTINE_RUSH = BUILDER
            .comment("Enable nicotine rush effect (speed boost, only when not cosmetic_only)")
            .define("enable_nicotine_rush", false);
        NICOTINE_RUSH_DURATION = BUILDER
            .comment("Duration of nicotine rush effect in ticks")
            .defineInRange("nicotine_rush_duration", 200, 20, 1200);
        NICOTINE_RUSH_AMPLIFIER = BUILDER
            .comment("Amplifier for nicotine rush effect (0 = level 1)")
            .defineInRange("nicotine_rush_amplifier", 0, 0, 5);
        
        ENABLE_HUNGER_EFFECT = BUILDER
            .comment("Enable hunger drain when smoking (only when not cosmetic_only)")
            .define("enable_hunger_effect", false);
        ENABLE_NAUSEA_EFFECT = BUILDER
            .comment("Enable nausea effect for new smokers (only when not cosmetic_only)")
            .define("enable_nausea_effect", false);
        NAUSEA_DURATION = BUILDER
            .comment("Duration of nausea effect in ticks")
            .defineInRange("nausea_duration", 100, 20, 600);
        NAUSEA_AMPLIFIER = BUILDER
            .comment("Amplifier for nausea effect (0 = level 1)")
            .defineInRange("nausea_amplifier", 0, 0, 3);
        BUILDER.pop();

        BUILDER.push("visuals");
        SMOKE_PARTICLE_DENSITY = BUILDER
            .comment("Density of smoke particles (0.0 to 1.0, higher = more particles)")
            .defineInRange("smoke_particle_density", 0.5, 0.0, 1.0);
        ENABLE_EMBER_TIP = BUILDER
            .comment("Enable glowing ember tip on cigarettes when smoking")
            .define("enable_ember_tip", true);
        BUILDER.pop();

        COMMON_CONFIG = BUILDER.build();
    }
}