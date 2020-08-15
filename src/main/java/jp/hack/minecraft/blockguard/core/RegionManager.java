package jp.hack.minecraft.blockguard.core;

import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockBreakEvent;

import java.io.File;
import java.util.*;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPhysicsEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityExplodeEvent;

public class RegionManager implements Listener {
    private static final RegionManager singleton = new RegionManager();
    public static synchronized RegionManager getInstance(){
        return singleton;
    }

    private Map<String, Region> regions = new HashMap<>();
    private RegionGenerator generator;

    private RegionManager(){
    }

    public Region findRegion(String id){
        return regions.get(id);
    }

    public Region createRegion(RegionPlugin plugin, String id, Vectors vectors){
        Region region = generator.createRegion(plugin, id);
        regions.put(id, region);
        return region;
    }

    public Boolean deleteRegion(RegionPlugin plugin, String id) {
        File regionFolder = new File(plugin.getDataFolder(), id);
        if (!regionFolder.delete()) {
            return false;
        }
        plugin.getConfiguration().deleteRegion(id);

        regions.remove(id);
        return true;
    }

    public List<String> getIds() {
        return new ArrayList<>(regions.keySet());
    }

    public void setGenerator(RegionGenerator generator){
        this.generator = generator;
    }

    public void saveRegion(String id, Region region) {
        regions.put(id, region);
    }

    public void loadRegion(RegionPlugin plugin){
        if(generator!=null) {
            List<String> regionList = plugin.getConfiguration().getRegionList();
            if(!regionList.isEmpty())
            regionList.stream().forEach(id -> {
                if (!regions.containsKey(id)) {
                    regions.put(id, generator.createRegion(plugin, id));
                }
            });
        }
    }


    @EventHandler
    public void onBlockPlaceEvent(BlockPlaceEvent event) {
        Location loc = event.getBlock().getLocation();
        if (!regions.isEmpty()) {
            for (Iterator<Region> ite = regions.values().iterator(); ite.hasNext(); ) {
                Region r = ite.next();
                System.out.println(r.getVectors());
                System.out.println(r.getRegionArea());
                if (r.getRegionArea().contains(loc.getBlockX(), loc.getBlockY(), loc.getBlockZ())) {
                    System.out.println("affds");
                    r.onBlockPlaceEvent(event);
                    break;
                }
            }
        }
    }

    @EventHandler
    public void onBlockBreakEvent(BlockBreakEvent event) {
        Location loc = event.getBlock().getLocation();
        if (!regions.isEmpty()) {
            for (Iterator<Region> ite = regions.values().iterator(); ite.hasNext(); ) {
                Region r = ite.next();
                System.out.println(r.getVectors());
                System.out.println(r.getRegionArea());
                if (r.getRegionArea().contains(loc.getBlockX(), loc.getBlockY(), loc.getBlockZ())) {
                    System.out.println("affds");
                    r.onBlockBreakEvent(event);
                    break;
                }
            }
        }
    }

    @EventHandler
    public void onEntityExplodeEvent(EntityExplodeEvent event) {
        Location loc = event.getLocation();
        if (!regions.isEmpty()) {
            for (Iterator<Region> ite = regions.values().iterator(); ite.hasNext(); ) {
                Region r = ite.next();
                System.out.println(r.getVectors());
                System.out.println(r.getRegionArea());
                if (r.getRegionArea().contains(loc.getBlockX(), loc.getBlockY(), loc.getBlockZ())) {
                    System.out.println("affds");
                    r.onEntityExplodeEvent(event);
                    break;
                }
            }
        }
    }

    @EventHandler
    public void onBlockPhysicsEvent(BlockPhysicsEvent event) {
        Location loc = event.getBlock().getLocation();
        if (!regions.isEmpty()) {
            for (Iterator<Region> ite = regions.values().iterator(); ite.hasNext(); ) {
                Region r = ite.next();
                if (r.getRegionArea().contains(loc.getBlockX(), loc.getBlockY(), loc.getBlockZ())) {
                    r.onBlockPhysicsEvent(event);
                    break;
                }
            }
        }
    }

    public boolean isCreated(String id){
        return regions.containsKey(id);
    }

    public interface RegionGenerator {
        Region createRegion(RegionPlugin plugin, String id);
    }
}
