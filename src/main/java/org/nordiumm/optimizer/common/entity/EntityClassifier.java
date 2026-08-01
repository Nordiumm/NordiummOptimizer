package org.nordiumm.optimizer.common.entity;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.decoration.ArmorStand;
import net.minecraft.world.entity.decoration.ItemFrame;
import net.minecraft.world.entity.player.Player;

public class EntityClassifier {

    public static EntityCategory getCategory(Entity entity) {

        if (entity instanceof Player) {
            return EntityCategory.PLAYER;
        }

        if (entity instanceof ItemFrame) {
            return EntityCategory.ITEM_FRAME;
        }

        if (entity instanceof ArmorStand) {
            return EntityCategory.ARMOR_STAND;
        }

        return EntityCategory.OTHER;
    }
}