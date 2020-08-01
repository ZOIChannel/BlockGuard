package jp.hack.minecraft.blockguard.core;

import org.bukkit.configuration.serialization.ConfigurationSerializable;
import java.util.*;

public class Region implements ConfigurationSerializable {
    private String id;
    private boolean isWorking;
    private List<UUID> members = new ArrayList<>();
    private List<UUID> operators = new ArrayList<>();
    private Vector min = new Vector();
    private Vector max = new Vector();

    public Region(String id, Vector min, Vector max){
        this.id = id;
        this.isWorking = true;
        this.min = min;
        this.max = max;
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
        result.put("operetors", operators);
        result.put("minPos", min);
        result.put("maxPos", max);
        return result;
    }
}
