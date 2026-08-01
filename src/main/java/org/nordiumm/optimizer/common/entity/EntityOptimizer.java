package org.nordiumm.optimizer.common.entity;

import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.decoration.ItemFrame;
import net.minecraft.world.entity.decoration.ArmorStand;
import net.minecraft.world.entity.player.Player;

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

            EntityCategory category = getCategory(entity);

            switch (category) {

                case ITEM_FRAME -> itemFrames++;

                case PLAYER -> players++;

                case ARMOR_STAND -> armorStands++;

                case OTHER -> other++;
            }
        }
    }


    private EntityCategory getCategory(net.minecraft.world.entity.Entity entity) {

        if (entity instanceof ItemFrame) {
            return EntityCategory.ITEM_FRAME;
        }

        if (entity instanceof Player) {
            return EntityCategory.PLAYER;
        }

        if (entity instanceof ArmorStand) {
            return EntityCategory.ARMOR_STAND;
        }

        return EntityCategory.OTHER;
    }
}