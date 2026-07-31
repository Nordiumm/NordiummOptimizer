package org.nordiumm.optimizer;

import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.decoration.ItemFrame;

public class EntityOptimizer {

    public int totalEntities = 0;
    public int itemFrames = 0;
    public int players = 0;
    public int armorStands = 0;
    public int other = 0;

    private int scanTimer = 0;

    public void tick() {

        scanTimer++;

        if (scanTimer > 10) {
            scanTimer = 0;
            scanWorld();
        }
    }

    public void scanWorld() {

        Minecraft client = Minecraft.getInstance();

        if (client.level == null) {
            return;
        }

        totalEntities = 0;
        itemFrames = 0;
        players = 0;
        armorStands = 0;
        other = 0;

        for (var entity : client.level.entitiesForRendering()) {

            totalEntities++;

            if (entity instanceof ItemFrame) {
                itemFrames++;
            } else if (entity instanceof net.minecraft.world.entity.player.Player) {
                players++;
            } else if (entity instanceof net.minecraft.world.entity.decoration.ArmorStand) {
                armorStands++;
            } else {
                other++;
            }
        }
    }
}