package jp.hack.minecraft.blockguard.core;

import jp.hack.minecraft.blockguard.utils.RegionConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockFromToEvent;
import org.bukkit.event.block.BlockPhysicsEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.BoundingBox;
import org.bukkit.util.Vector;

import java.util.*;

public class Region {
    //メンバ変数
    private static RegionPlugin plugin;
    private String id;
    private Boolean isWorking;
    private List<UUID> members = new ArrayList<>();
    private List<UUID> operators = new ArrayList<>();
    private Vectors vectors;
    private BoundingBox regionArea;
    private final RegionConfiguration configuration;

    // flags
    public enum RegionFlagType {
        SPREADFIRE,
        EXPLODETNT,
        SPREADLIQUID,
        INVADEMOB
    }

    Map<RegionFlagType, Boolean> flags = new HashMap<>();


    public Region(RegionPlugin plugin, String id) {
        this.plugin = plugin;
        this.id = id;
        this.isWorking = true;
        this.configuration = RegionConfiguration.create(plugin, id);

        //flag setup
        flags.put(RegionFlagType.SPREADFIRE, false);
        flags.put(RegionFlagType.EXPLODETNT, false);
        flags.put(RegionFlagType.SPREADLIQUID, false);
        flags.put(RegionFlagType.INVADEMOB, true);
<<<<<<< HEAD
=======

        CheckMonster = new BukkitRunnable() {
            @Override
            public void run() {
                if (!isWorking) return;
                if(!isFlag(RegionFlagType.INVADEMOB)) return;
                // WorldName = getVectors().getWorldName();
                if(WorldName == null) return;
                List<LivingEntity> Entities = plugin
                        .getServer()
                        .getWorld(WorldName)
                        .getLivingEntities();
                for (LivingEntity entity : Entities) {
                    if (entity instanceof Monster) {
                        if (getRegionArea().contains(entity.getLocation().getX(), entity.getLocation().getY(), entity.getLocation().getZ())) entity.remove();
                    }
                }
            }
        };

        CheckMonster.runTaskTimer(plugin, 0, 20);
>>>>>>> d425b452912969e376f8458d85d6293a0fd490ad
    }


    public String getWorldName() {
        return WorldName;
    }

    public void setWorldName(String worldName) {
        WorldName = worldName;
    }


    //getter
    public RegionPlugin getPlugin() {
        return plugin;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isWorking() {
        return isWorking;
    }

    public void setWorking(Boolean working) {
        isWorking = working;
    }

    public List<UUID> getMembers() {
        if (members.isEmpty()) {
            members = configuration.getMembers();
        }
        return members;
    }

    public void setMembers(List<UUID> members) {
        this.members = members;
    }

    public List<UUID> getOperators() {
        if (operators.isEmpty()) {
            operators = configuration.getOperators();
        }
        return operators;
    }

    public void setOperators(List<UUID> operators) {
        this.operators = operators;
    }

    public Vectors getVectors() {
        if (vectors == null) {
            vectors = configuration.getVectors();
        }
        return vectors;
    }

    public void setVectors(Vectors vectors) {
        this.vectors = vectors;
    }

    public void addMember(UUID uuid) {
        members.add(uuid);
    }

    public void removeMember(UUID uuid) {
        members.remove(uuid);
    }

    public void isThereMember(UUID uuid) {
        members.contains(uuid);
    }

    public void addOperator(UUID uuid) {
        operators.add(uuid);
    }

    public void removeOperator(UUID uuid) {
        operators.remove(uuid);
    }

    public void isThereOperator(UUID uuid) {
        operators.contains(uuid);
    }

    public RegionConfiguration getConfiguration() {
        return configuration;
    }

    public void setRegionArea(BoundingBox regionArea) {
        this.regionArea = regionArea;
    }

    public BoundingBox getRegionArea() {
        if (regionArea == null) {
            Vector min = configuration.getVectors().getMin();
            Vector max = configuration.getVectors().getMax();
            regionArea = new BoundingBox(min.getX(), min.getY(), min.getZ(), max.getX() + 1, max.getY() + 1, max.getZ() + 1);
        }

        return regionArea;
    }

    public void setFlag(RegionFlagType type, Boolean boo) {
        flags.put(type, boo);
    }

    public Boolean isFlag(RegionFlagType type) {
        return flags.get(type);
    }

    //setter

    @EventHandler
    public void onBlockPlaceEvent(BlockPlaceEvent e) {
    }

    @EventHandler
    public void onBlockBreakEvent(BlockBreakEvent e) {
    }

    @EventHandler
    public void onEntityExplodeEvent(EntityExplodeEvent e) {
    }

    @EventHandler
    public void onBlockPhysicsEvent(BlockPhysicsEvent e) {
    }

    @EventHandler
    public void onBlockFromToEvent(BlockFromToEvent event){
    }

    @EventHandler
    public void onEntitySpawnEvent(EntitySpawnEvent e) {
    }
}
