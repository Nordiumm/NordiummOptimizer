package org.nordiumm.optimizer.common.optimizer;

import net.minecraft.world.entity.Entity;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class VisibilityCache {

    private static final Map<UUID, CacheEntry> CACHE = new HashMap<>();

    public static CacheEntry get(Entity entity) {
        return CACHE.get(entity.getUUID());
    }

    public static void put(Entity entity, boolean visible, long tick) {
        CACHE.put(entity.getUUID(), new CacheEntry(visible, tick));
    }

    public static void cleanup(long currentTick) {

        int removed = 0;

        var iterator = CACHE.entrySet().iterator();

        while (iterator.hasNext()) {

            var entry = iterator.next();

            if (currentTick - entry.getValue().tick() > 200) {
                iterator.remove();
                removed++;
            }
        }

        if (removed > 0) {
            System.out.println(
                    "[NordiummOptimizer] "
                            + removed
                            + " old visibility cache entries"
            );
        }
    }

    public record CacheEntry(boolean visible, long tick) {
    }

    public static void clear() {
        int size = CACHE.size();

        CACHE.clear();

        System.out.println(
                "[NordiummOptimizer] Cache cleared " + size + " entries"
        );
    }
}
