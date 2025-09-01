package com.smokecraft.smokecraft;

import com.smokecraft.smokecraft.config.SmokecraftConfig;
import com.smokecraft.smokecraft.init.BlockInit;
import com.smokecraft.smokecraft.init.CreativeTabInit;
import com.smokecraft.smokecraft.init.ItemInit;
import net.minecraft.world.entity.player.Player;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.fml.loading.FMLEnvironment;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(Smokecraft.MOD_ID)
public class Smokecraft {
    public static final String MOD_ID = "smokecraft";
    public static final Logger LOGGER = LogManager.getLogger();

    public Smokecraft(IEventBus modEventBus, ModContainer modContainer) {
        // Register deferred registries
        ItemInit.ITEMS.register(modEventBus);
        BlockInit.BLOCKS.register(modEventBus);
        CreativeTabInit.CREATIVE_MODE_TABS.register(modEventBus);

        // Register mod setup
        modEventBus.addListener(this::commonSetup);

        // Register client setup for client-side only
        if (FMLEnvironment.dist == Dist.CLIENT) {
            modEventBus.addListener(this::clientSetup);
        }

        // Register config
        modContainer.registerConfig(ModConfig.Type.COMMON, SmokecraftConfig.COMMON_CONFIG);

        // Register ourselves for server and other game events
        NeoForge.EVENT_BUS.register(this);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        LOGGER.info("Smokecraft common setup");
    }

    private void clientSetup(final FMLClientSetupEvent event) {
        LOGGER.info("Smokecraft client setup");
    }

    // Health warning on first join
    @SubscribeEvent
    public void onPlayerJoin(PlayerEvent.PlayerLoggedInEvent event) {
        Player player = event.getEntity();
        if (!player.level().isClientSide && SmokecraftConfig.SHOW_HEALTH_WARNING.get()) {
            // Show health warning message
            player.displayClientMessage(net.minecraft.network.chat.Component.literal(
                "§e[Smokecraft] §fReminder: This mod is for entertainment only. Smoking is harmful to health. Always smoke responsibly in real life!"
            ), false);
        }
    }
}