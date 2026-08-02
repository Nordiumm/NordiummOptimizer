package org.nordiumm.optimizer.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;

import org.nordiumm.optimizer.client.hud.HudRenderer;
import org.nordiumm.optimizer.common.entity.EntityOptimizer;
import org.nordiumm.optimizer.common.config.ConfigManager;
import org.nordiumm.optimizer.common.optimizer.VisibilityCache;
import net.minecraft.world.phys.Vec3;

public class NordiummOptimizerClient implements ClientModInitializer {

    public static EntityOptimizer entityOptimizer;
    private static long lastCacheCleanup = 0;
    private static Vec3 lastCachePosition;

    @Override
    public void onInitializeClient() {

        entityOptimizer = new EntityOptimizer();


        ConfigManager.load();

        VisibilityCache.clear();

        HudRenderer.register();

        ClientTickEvents.END_CLIENT_TICK.register(client -> {

            entityOptimizer.tick();

            if (client.level != null && client.player != null) {

                Vec3 currentPosition = client.player.position();

                if (lastCachePosition == null) {
                    lastCachePosition = currentPosition;
                }

                double distance =
                        currentPosition.distanceTo(lastCachePosition);

                if (distance > 8) {
                    VisibilityCache.clear();
                    lastCachePosition = currentPosition;
                }
            }
        });

        System.out.println("[NordiummOptimizer] Client loaded!");
    }
}