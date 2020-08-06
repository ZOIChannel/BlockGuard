package jp.hack.minecraft.blockguard.core;

import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.util.BoundingBox;
import org.bukkit.util.Vector;

import java.rmi.NoSuchObjectException;
import java.util.*;

public class Region implements ConfigurationSerializable {
    private String id;
    private boolean isWorking;
    private List<UUID> members = new ArrayList<>();
    private List<UUID> operators = new ArrayList<>();
    private Vectors vectors;
    private BoundingBox regionArea;

    public Region(String id, Vector minPos, Vector maxPos){
        this.id = id;
        this.isWorking = true;
        this.vectors = new Vectors(minPos, maxPos);
    }

    public Region(String id, Vectors vectors) {
        this.id = id;
        this.isWorking = true;
        this.vectors = vectors;
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

    public void setMinPos(Vector minPos) {
        this.vectors.setMin(minPos);
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

    public void onBlockBreakEvent(BlockBreakEvent e) {}

    public BoundingBox getRegionArea() {
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
        return result;
    }

    public static Region deserialize(Map<String, Object> args) throws NoSuchObjectException {
        Region region;

        if (args.containsKey("id") && args.containsKey("vectors")) {
            region = new Region((String) args.get("id"), (Vectors) args.get("vectors"));
        } else {
            throw new NoSuchObjectException("There is not \"id\" or \"vectors\".");
        }
        region.setWorking((Boolean) args.get("isWoking"));
        region.setMembers((List<UUID>) args.get("members"));
        region.setOperators((List<UUID>) args.get("operators"));
        return region;
    }
}
