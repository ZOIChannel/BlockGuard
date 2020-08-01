package jp.hack.minecraft.blockguard.utils;

import jp.hack.minecraft.blockguard.core.Region;
import jp.hack.minecraft.blockguard.core.RegionPlugin;
import jp.hack.minecraft.blockguard.core.utils.Configuration;

import java.io.File;

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

    public void setRegion(Region region) {
        set("region", region);
    }
}