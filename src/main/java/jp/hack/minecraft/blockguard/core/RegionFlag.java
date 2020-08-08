package jp.hack.minecraft.blockguard.core;

import org.bukkit.configuration.serialization.ConfigurationSerializable;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class RegionFlag extends RegionFlagType {
    Map<String, Boolean> flags = new HashMap<>();

    public RegionFlag() {
        getFlagTypes().stream().forEach(f -> {
            flags.put(f, true);
        });
    }

    public void setFlag(String flagName, Boolean boo) {
        if (getFlagTypes().contains(flagName)) {
            flags.replace(flagName, boo);
        } else {
            flags.put(flagName, boo);
        }
    }

    public Boolean getFlag(String type) {
        return flags.get(type);
    }
}
