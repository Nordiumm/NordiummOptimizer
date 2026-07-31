package org.nordiumm.optimizer;

import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElementRegistry;
import net.minecraft.resources.Identifier;
import net.minecraft.client.Minecraft;

public class HudRenderer {

    public static void register() {

        HudElementRegistry.addLast(
                Identifier.fromNamespaceAndPath("nordiummoptimizer", "hud"),
                (graphics, deltaTracker) -> {

                    Minecraft client = Minecraft.getInstance();

                    graphics.text(
                            client.font,
                            "Nordiumm Optimizer",
                            10,
                            10,
                            0xFFFFFFFF
                    );

                    graphics.text(
                            client.font,
                            "Entites: " + NordiummOptimizer.entityOptimizer.totalEntities,
                            10,
                            25,
                            0xFFFFFFFF
                    );

                    graphics.text(
                            client.font,
                            "Item Frames: " + NordiummOptimizer.entityOptimizer.itemFrames,
                            10,
                            40,
                            0xFFFFFFFF
                    );

                    graphics.text(
                            client.font,
                            "Players: " + NordiummOptimizer.entityOptimizer.players,
                            10,
                            55,
                            0xFFFFFFFF
                    );

                    graphics.text(
                            client.font,
                            "Armor Stands: " + NordiummOptimizer.entityOptimizer.armorStands,
                            10,
                            70,
                            0xFFFFFFFF
                    );

                    graphics.text(
                            client.font,
                            "Other: " + NordiummOptimizer.entityOptimizer.other,
                            10,
                            85,
                            0xFFFFFFFF
                    );
                }
        );
    }
}