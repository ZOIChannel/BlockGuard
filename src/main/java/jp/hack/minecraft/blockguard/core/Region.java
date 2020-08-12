package jp.hack.minecraft.blockguard.core;

import jp.hack.minecraft.blockguard.utils.RegionConfiguration;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPhysicsEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.block.BlockSpreadEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.util.BoundingBox;
import org.bukkit.util.Vector;

import java.rmi.NoSuchObjectException;
import java.util.*;

public class Region implements ConfigurationSerializable {
    //メンバ変数
    private static RegionPlugin plugin;
    private String id;
    private boolean isWorking;
    private List<UUID> members = new ArrayList<>();
    private List<UUID> operators = new ArrayList<>();
    private Vectors vectors;
    private BoundingBox regionArea;
    private RegionConfiguration configuration;

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

    public void setWorking(boolean working) {
        isWorking = working;
    }

    public List<UUID> getMembers() {
        return members;
    }

    public void setMembers(List<UUID> members) {
        this.members = members;
    }

    public List<UUID> getOperators() {
        return operators;
    }

    public void setOperators(List<UUID> operators) {
        this.operators = operators;
    }

    public Vector getMinPos() {
        return vectors.getMin();
    }

    public Vectors getVectors() {
        return vectors;
    }

    public void setVectors(Vectors vectors) {
        this.vectors = vectors;
    }

    public Vector getMaxPos() {
        return vectors.getMin();
    }

    public void setMaxPos(Vector maxPos) {
        this.vectors.setMax(maxPos);
    }

    public void addMember(UUID uuid){
        members.add(uuid);
    }

    public void removeMember(UUID uuid){
        members.remove(uuid);
    }

    public void isThereMember(UUID uuid){
        members.contains(uuid);
    }

    public void addOperator(UUID uuid){
        operators.add(uuid);
    }

    public void removeOperator(UUID uuid){
        operators.remove(uuid);
    }

    public void isThereOperator(UUID uuid){
        operators.contains(uuid);
    }

    public RegionConfiguration getConfiguration() {
        return configuration;
    }

    public BoundingBox getRegionArea() {
        if(regionArea == null) {
            Vector min = vectors.getMin();
            Vector max = vectors.getMax();
            regionArea = new BoundingBox(min.getX(), min.getY(), min.getZ(), max.getX()+1, max.getY()+1, max.getZ()+1);
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
    public void onBlockPlaceEvent(BlockPlaceEvent e) {}

    @EventHandler
    public void onBlockBreakEvent(BlockBreakEvent e) {}

    @EventHandler
    public void onEntityExplodeEvent(EntityExplodeEvent e) {}

    @EventHandler
    public void onBlockPhysicsEvent(BlockPhysicsEvent e) {}

    @Override
    public Map<String, Object> serialize() {
        Map<String, Object> result = new LinkedHashMap<>();
        result.put("id", id);
        result.put("vectors", vectors);
        result.put("isWorking", isWorking);
        result.put("members", members);
        result.put("operators", operators);

        return result;
    }

    public static Region deserialize(Map<String, Object> args) throws NoSuchObjectException {
        Region region;

        if (args.containsKey("id")) {
            region = new Region(plugin, (String) args.get("id"));
        } else {
            throw new NoSuchObjectException("There is not \"id\" or \"vectors\".");
        }
        region.setVectors((Vectors) args.get("vectors"));
        region.setWorking((Boolean) args.get("isWoking"));

        if(args.get("members") != null) {
            region.setMembers((List<UUID>) args.get("members"));
        } else {
            region.setMembers(new ArrayList<>());
        }
        if(args.get("operators") != null) {
            region.setOperators((List<UUID>) args.get("operators"));
        } else {
            region.setOperators(new ArrayList<>());
        }

        return region;
    }
}
