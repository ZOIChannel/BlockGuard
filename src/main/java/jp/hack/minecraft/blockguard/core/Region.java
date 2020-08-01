package jp.hack.minecraft.blockguard.core;

import org.bukkit.World;
import org.bukkit.configuration.serialization.ConfigurationSerializable;

import java.rmi.NoSuchObjectException;
import java.util.*;

public class Region implements ConfigurationSerializable {
    private String id;
    private boolean isWorking;
    private List<UUID> members = new ArrayList<>();
    private List<UUID> operators = new ArrayList<>();
    private Vector minPos;
    private Vector maxPos;

    Region(String id, Vector minPos, Vector maxPos){
        this.id = id;
        this.isWorking = true;
        this.minPos = minPos;
        this.maxPos = maxPos;
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
        return minPos;
    }

    public void setMinPos(Vector minPos) {
        this.minPos = minPos;
    }

    public Vector getMaxPos() {
        return maxPos;
    }

    public void setMaxPos(Vector maxPos) {
        this.maxPos = maxPos;
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


    @Override
    public Map<String, Object> serialize() {
        Map<String, Object> result = new LinkedHashMap<>();
        result.put("id", id);
        result.put("isWorking", isWorking);
        result.put("members", members);
        result.put("operators", operators);
        result.put("minPos", minPos);
        result.put("maxPos", maxPos);
        return result;
    }

    public static Region deserialize(Map<String, Object> args) throws NoSuchObjectException {
        Region region;

        if (args.containsKey("id") && args.containsKey("minPos") && args.containsKey("maxPos")) {
            region = new Region((String) args.get("id"), (Vector) args.get("minPos"), (Vector) args.get("maxPos"));
        } else {
            throw new NoSuchObjectException("There is not \"id\" or \"minPos\" or \"maxPos\".");
        }

        return region;
    }
}
