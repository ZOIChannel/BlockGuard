package jp.hack.minecraft.blockguard.core;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class Bean {
    private JavaPlugin plugin;
    private String id;
    private boolean isWorking = true;
    private Map<String, String> Flags = new ConcurrentHashMap<>();
    private Map<UUID, Player> players = new ConcurrentHashMap<>();
    private Map<UUID, Player> operators = new ConcurrentHashMap<>();

    public Bean(JavaPlugin plugin, String id){
        this.plugin = plugin;
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
        players.put(player.getUniqueId(),player);
    }
    public void removePlayer(UUID uuid){
        players.remove(uuid);
    }
    public void isTherePlayer(UUID uuid){
        players.containsKey(uuid);
    }
    public Player findPlayer(UUID uuid){
        return players.get(uuid);
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
}
