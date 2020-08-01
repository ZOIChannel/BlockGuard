package jp.hack.minecraft.blockguard.core;

import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.entity.Player;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;

public class Region implements ConfigurationSerializable {
    private String id;
    private boolean isWorking = true;
    private Map<String, String> Flags = new ConcurrentHashMap<>();
    private Map<UUID, Player> members = new ConcurrentHashMap<>();
    private Map<UUID, Player> operators = new ConcurrentHashMap<>();
    private Vector min = new Vector();
    private Vector max = new Vector();

    public Region(String id){
        this.id = id;
        this.isWorking = true;
    }

    public void setFlag(String flagName, String flagContent){
        Flags.getOrDefault(flagName,flagContent);
    }
    public String getFlag(String flagName){
        return Flags.get(flagName);
    }

    public void addPlayer(Player player){
        members.put(player.getUniqueId(),player);
    }
    public void removePlayer(UUID uuid){
        members.remove(uuid);
    }
    public void isTherePlayer(UUID uuid){
        members.containsKey(uuid);
    }
    public Player findPlayer(UUID uuid){
        return members.get(uuid);
    }

    public void addOperator(Player player){
        operators.put(player.getUniqueId(),player);
    }
    public void removeOperator(UUID uuid){
        operators.remove(uuid);
    }
    public void isThereOperator(UUID uuid){
        operators.containsKey(uuid);
    }
    public Player findOperator(UUID uuid){
        return operators.get(uuid);
    }

    @Override
    public Map<String, Object> serialize() {
        Map<String, Object> result = new LinkedHashMap<>();
        result.put("id", id);
        result.put("isWorking", isWorking);
        result.put("members", members);
        result.put("operetors", operators);
        return result;
    }
}
