package org.nordiumm.optimizer.client.hud;

import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElementRegistry;
import net.minecraft.resources.Identifier;
import net.minecraft.client.Minecraft;
import org.nordiumm.optimizer.client.NordiummOptimizerClient;
import org.nordiumm.optimizer.common.config.OptimizerConfig;

public class HudRenderer {

    public static void register() {

        HudElementRegistry.addLast(
                Identifier.fromNamespaceAndPath("nordiummoptimizer", "hud"),
                (graphics, deltaTracker) -> {

                    Minecraft client = Minecraft.getInstance();

                    if (!OptimizerConfig.enabled) {
                        return;
                    }

                    graphics.text(
                            client.font,
                            "Nordiumm Optimizer",
                            10,
                            10,
                            0xFFFFFFFF
                    );
                    if (OptimizerConfig.showTotalEntities) {
                        graphics.text(
                                client.font,
                                "Entities: " + NordiummOptimizerClient.entityOptimizer.totalEntities,
                                10,
                                25,
                                0xFFFFFFFF
                        );
                    }

                    if (OptimizerConfig.showItemFrames) {
                        graphics.text(
                                client.font,
                                "Item Frames: " + NordiummOptimizerClient.entityOptimizer.itemFrames,
                                10,
                                40,
                                0xFFFFFFFF
                        );
                    }

                    if (OptimizerConfig.showPlayers) {
                        graphics.text(
                                client.font,
                                "Players: " + NordiummOptimizerClient.entityOptimizer.players,
                                10,
                                55,
                                0xFFFFFFFF
                        );
                    }

                    if (OptimizerConfig.showArmorStands) {
                        graphics.text(
                                client.font,
                                "Armor Stands: " + NordiummOptimizerClient.entityOptimizer.armorStands,
                                10,
                                70,
                                0xFFFFFFFF
                        );
                    }

                    if (OptimizerConfig.showOther) {
                        graphics.text(
                                client.font,
                                "Other: " + NordiummOptimizerClient.entityOptimizer.other,
                                10,
                                85,
                                0xFFFFFFFF
                        );
                    }
                }
        );
    }
}