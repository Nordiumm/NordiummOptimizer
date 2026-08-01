package org.nordiumm.optimizer.common.optimizer;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.culling.Frustum;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import org.nordiumm.optimizer.common.optimizer.VisibilityCache.CacheEntry;

public class VisibilityChecker {

    private static final long CACHE_TIME = 10;

    public static boolean canSee(Entity entity, Frustum frustum) {

        Minecraft client = Minecraft.getInstance();

        if (client.player == null) {
            return true;
        }

        double distance = client.player.distanceTo(entity);
        long currentTick = client.level.getGameTime();

        if (distance > 128) {
            return false;
        }

        if (!frustum.isVisible(entity.getBoundingBox())) {
            return false;
        }

          CacheEntry cached = VisibilityCache.get(entity);
          if (cached != null) {
            if (currentTick - cached.tick() < CACHE_TIME) {
                return cached.visible();
            }
          }

        Vec3 start = client.gameRenderer.mainCamera().position();

        Vec3 end = entity.position();

        HitResult result = client.level.clip(
                new ClipContext(
                        start,
                        end,
                        ClipContext.Block.COLLIDER,
                        ClipContext.Fluid.NONE,
                        client.player
                )
        );

        boolean visible = result.getType() != HitResult.Type.BLOCK;

        VisibilityCache.put(
                entity,
                visible,
                currentTick
        );
        return visible;
    }
}
