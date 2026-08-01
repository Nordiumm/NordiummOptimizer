package org.nordiumm.optimizer.common.optimizer;

import net.minecraft.client.renderer.culling.Frustum;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.decoration.ItemFrame;

public class OptimizationManager {

    public static boolean shouldRender(Entity entity, Frustum frustum) {

        if (entity instanceof ItemFrame) {

            boolean result = VisibilityChecker.canSee(entity, frustum);

//            System.out.println("[NordiummOptimizer] " + entity.getName().getString() + " render: " + result);

            return result;
        }

        return true;
    }
}