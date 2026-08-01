package org.nordiumm.optimizer.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;

import org.nordiumm.optimizer.client.hud.HudRenderer;
import org.nordiumm.optimizer.common.entity.EntityOptimizer;
import org.nordiumm.optimizer.common.config.ConfigManager;
import org.nordiumm.optimizer.common.optimizer.VisibilityCache;

public class NordiummOptimizerClient implements ClientModInitializer {

    public static EntityOptimizer entityOptimizer;
    private static long lastCacheCleanup = 0;

    @Override
    public void onInitializeClient() {

        entityOptimizer = new EntityOptimizer();


        ConfigManager.load();

        HudRenderer.register();

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            entityOptimizer.tick();

            if (client.level != null) {
                long tick = client.level.getGameTime();
                if (tick - lastCacheCleanup >= 200) {
                    VisibilityCache.cleanup(tick);
                    lastCacheCleanup = tick;
                }
            }
        });

        System.out.println("[NordiummOptimizer] Client loaded!");
    }
}