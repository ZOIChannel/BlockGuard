package jp.hack.minecraft.blockguard.core;

import jp.hack.minecraft.blockguard.utils.RegionConfiguration;
import org.bukkit.Bukkit;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.BoundingBox;
import org.bukkit.util.Vector;

import java.rmi.NoSuchObjectException;
import java.util.*;

public class Region implements ConfigurationSerializable {
    private static RegionPlugin plugin;
    private String id;
    private boolean isWorking;
    private List<UUID> members = new ArrayList<>();
    private List<UUID> operators = new ArrayList<>();
    private List<String> flags = new ArrayList<>();
    private Vectors vectors;
    private BoundingBox regionArea;
    private RegionConfiguration configuration;

    public Region(RegionPlugin plugin, String id) {
        this.plugin = plugin;
        this.id = id;
        this.isWorking = true;
        this.configuration = RegionConfiguration.create(plugin, id);
    }

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

    public List<String> getFlags() {
        return flags;
    }

    public void setFlags(List<String> flags) {
        this.flags = flags;
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

    public void addPlayer(UUID uuid){
        members.add(uuid);
    }

    public void removePlayer(UUID uuid){
        members.remove(uuid);
    }

    public void isTherePlayer(UUID uuid){
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

    public void addFlags(String flagName){
        flags.add(flagName);
    }

    public void removeFlags(String flagName){
        flags.remove(flagName);
    }

    public Boolean isThereFlags(String flagName){
        return flags.contains(flagName);
    }


    public RegionConfiguration getConfiguration() {
        return configuration;
    }

    public void onBlockBreakEvent(BlockBreakEvent e) {}

    public BoundingBox getRegionArea() {
        if(regionArea == null) {
            Vector min = vectors.getMin();
            Vector max = vectors.getMax();
            regionArea = new BoundingBox(min.getX(), min.getY(), min.getZ(), max.getX(), max.getY(), max.getZ());
        }
        return regionArea;
    }

    @Override
    public Map<String, Object> serialize() {
        Map<String, Object> result = new LinkedHashMap<>();
        result.put("id", id);
        result.put("vectors", vectors);
        result.put("isWorking", isWorking);
        result.put("members", members);
        result.put("operators", operators);
        result.put("flags", flags);

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
        region.setMembers((List<UUID>) args.get("members"));
        region.setOperators((List<UUID>) args.get("operators"));
        region.setFlags((List<String>) args.get("flags"));

        return region;
    }
}
