package jp.hack.minecraft.blockguard.utils;

import jp.hack.minecraft.blockguard.core.Region;
import jp.hack.minecraft.blockguard.core.RegionPlugin;
import jp.hack.minecraft.blockguard.core.Vectors;
import jp.hack.minecraft.blockguard.core.utils.Configuration;

import java.io.File;
import java.util.*;
import java.util.stream.Collectors;

public class RegionConfiguration extends Configuration {

    public RegionConfiguration(File configFile) {
        super(configFile);
    }

    public static RegionConfiguration create(RegionPlugin plugin, String id) {

        File regionFolder = new File(plugin.getDataFolder(), id);
        if (!regionFolder.exists()) {
            regionFolder.mkdirs();
        }

        RegionConfiguration configuration = new RegionConfiguration(new File(regionFolder, "config.yml"));
        configuration.load();

        plugin.getConfiguration().addRegion(id);

        return configuration;
    }

    public void setId(String id) {
        set("id", id);
    }

    public String getId() {
        return (String) get("id");
    }

    public void setVectors(Vectors vectors) {
        set("vectors", vectors);
    }

    public Vectors getVectors(){
        return (Vectors) get("vectors");
    }

    public void setWorking(Boolean isWorking) {
        set("isWorking", isWorking);
    }

    public Boolean isWorking() {
        return (Boolean) get("isWorking");
    }

    public void setMembers(List<String> members) {
        set("members", members);
    }

    public List<UUID> getMembers() {
        return new ArrayList<>((List<String>) get("members")).stream().map(v -> UUID.fromString(v)).collect(Collectors.toList());
    }

    public void setOperators(List<String> operators) {
        set("operators", operators);
    }

    public List<UUID> getOperators() {
        return new ArrayList<>((List<String>) get("operators")).stream().map(v -> UUID.fromString(v)).collect(Collectors.toList());
    }
}