package jp.hack.minecraft.blockguard.core;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.util.Vector;

import java.util.LinkedHashMap;
import java.util.Map;

public class Vectors implements ConfigurationSerializable {
    private Vector max;
    private Vector min;

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

    public Vectors(Vector min, Vector max) {
        this.min = min;
        this.max = max;
    }

    @Override
    public Map<String, Object> serialize() {
        Map<String, Object> result = new LinkedHashMap<>();
        result.put("min", min);
        result.put("max", max);
        return result;
    }
}
