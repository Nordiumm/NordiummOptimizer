package org.nordiumm.optimizer;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;

public class NordiummOptimizer implements ClientModInitializer {

    public static EntityOptimizer entityOptimizer;

    @Override
    public void onInitializeClient() {

        entityOptimizer = new EntityOptimizer();

        HudRenderer.register();

        ClientTickEvents.END_CLIENT_TICK.register(client -> {

            entityOptimizer.tick();

        });

        System.out.println("[NordiummOptimizer] Loaded!");
    }
}
