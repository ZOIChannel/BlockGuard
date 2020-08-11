package jp.hack.minecraft.blockguard.core;

import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockBreakEvent;

import java.util.*;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockExplodeEvent;
import org.bukkit.event.block.BlockSpreadEvent;

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

    public Region createRegion(RegionPlugin plugin, String id){
        Region region = generator.createRegion(plugin, id);
        regions.put(id, region);
        return region;
    }

    public Boolean deleteRegion(RegionPlugin plugin, String id) {
        if (findRegion(id).getConfiguration().delete()) {
            return false;
        }
        regions.remove(id);
        return true;
    }

    public List<String> getIds() {
        return new ArrayList<>(regions.keySet());
    }

    public void setGenerator(RegionGenerator generator){
        this.generator = generator;
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

    @EventHandler
    public void onBlockExplodeEvent(BlockExplodeEvent event) {
        Location loc = event.getBlock().getLocation();
        for(Iterator<Region> ite=regions.values().iterator(); ite.hasNext();) {
            Region r = ite.next();
            if(r.getRegionArea().contains(loc.getBlockX(), loc.getBlockY(), loc.getBlockZ())) {
                r.onBlockExplodeEvent(event);
                break;
            } else {
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onBlockSpreadEvent(BlockSpreadEvent event) {
        Location loc = event.getSource().getLocation();
        for(Iterator<Region> ite=regions.values().iterator(); ite.hasNext();) {
            Region r = ite.next();
            if(r.getRegionArea().contains(loc.getBlockX(), loc.getBlockY(), loc.getBlockZ())) {
                r.onBlockSpreadEvent(event);
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
        Region createRegion(RegionPlugin plugin, String id);
    }
}
