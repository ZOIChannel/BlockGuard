package jp.hack.minecraft.blockguard.core;

import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockBreakEvent;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import org.bukkit.event.Listener;

public class RegionManager implements Listener {
    private static class SingletonHolder {
        private static final AtomicInteger ids = new AtomicInteger();
        private static final RegionManager singleton = new RegionManager();
        private SingletonHolder() { }
    }
    public static synchronized RegionManager getInstance(){
        return SingletonHolder.singleton;
    }

    private final ExecutorService pool;
    private Map<String, Region> regions = new HashMap<>();
    private RegionGenerator generator;

    private RegionManager(){
        pool = Executors.newCachedThreadPool(runnable -> {
            Thread thread = new Thread(runnable, "game thread " + SingletonHolder.ids.incrementAndGet());
            thread.setDaemon(true);
            return thread;
        });
    }

    public void release() {
        if(pool!=null) pool.shutdown();
        regions.clear();
    }


    public Region findRegion(String id){
        return regions.get(id);
    }

    public Region createRegion(RegionPlugin plugin, String id){
        Region region =  generator.createRegion(plugin, id);
        regions.put(id, region);
        return region;
    }

    public void deleteRegion(String id) {
        regions.remove(id);
    }

    public void setGenerator(RegionGenerator generator){
        this.generator = generator;
    }

    public void loadRegion(RegionPlugin plugin){
        if(generator!=null) {
            plugin.getConfiguration().getRegionList().stream().forEach(id -> {
                if (!regions.containsKey(id)) {
                    regions.put(id, generator.createRegion(plugin, id));
                }
            });
        }
    }

    @EventHandler
    public void onBlockBreakEvent(BlockBreakEvent event) {
        Location loc = event.getBlock().getLocation();
        for(Iterator<Region> ite=regions.values().iterator(); ite.hasNext();) {
            Region r = ite.next();
            if(r.getRegionArea().contains(loc.getBlockX(), loc.getBlockY(), loc.getBlockZ())) {
                r.onBlockBreakEvent(event);
                break;
            } else {
                event.setCancelled(true);
            }
        }
    }

    public boolean isCreated(String id){
        return regions.containsKey(id);
    }

    public interface RegionGenerator {
        Region createRegion(RegionPlugin plugin, String gameID);
    }
}
