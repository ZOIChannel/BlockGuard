package jp.hack.minecraft.blockguard.core;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.util.Vector;

import java.rmi.NoSuchObjectException;
import java.util.LinkedHashMap;
import java.util.Map;

public class Vectors implements ConfigurationSerializable {
    private Vector max;
    private Vector min;
    private String worldName;

    public Vector getMax() {
        return max;
    }

    public void setMax(Vector max) {
        this.max = max;
    }

    public Vector getMin() {
        return min;
    }

    public void setMin(Vector min) {
        this.min = min;
    }

    public String getWorldName() {
        return worldName;
    }

    public void setWorldName(String worldName) {
        this.worldName = worldName;
    }

    public Vectors() {}

    public Vectors(Vector min, Vector max, String worldName) {
        this.min = min;
        this.max = max;
        this.worldName = worldName;
    }

    @Override
    public Map<String, Object> serialize() {
        Map<String, Object> result = new LinkedHashMap<>();
        result.put("min", min);
        result.put("max", max);
        result.put("worldName", worldName);
        return result;
    }

    public static Vectors deserialize(Map<String, Object> args) {
        Vectors vectors = new Vectors();

        if(args.containsKey("min")) {
            vectors.setMin( (Vector) args.get("min"));
        }
        if(args.containsKey("max")) {
            vectors.setMax( (Vector) args.get("max"));
        }
        if(args.containsKey("worldName")) {
            vectors.setWorldName( (String) args.get("worldName"));
        }
        return vectors;
    }
}
