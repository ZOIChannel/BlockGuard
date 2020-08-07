package jp.hack.minecraft.blockguard.core;

import jp.hack.minecraft.blockguard.core.utils.I18n;
import jp.hack.minecraft.blockguard.utils.MainConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public abstract class RegionPlugin extends JavaPlugin {
    private transient MainConfiguration configuration;
    private transient I18n i18n;

    @Override
    public void onEnable() {

        getServer().getPluginManager().registerEvents(RegionManager.getInstance(), this);

        if (getDataFolder().exists() != true) {
            getDataFolder().mkdirs();
        }

        this.i18n = new I18n(this);
        this.i18n.onEnable();
        this.i18n.updateLocale("ja");

        this.configuration = new MainConfiguration(new File(getDataFolder(), "config.yml"));
        this.configuration.load();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        if (this.configuration != null) {
            this.configuration.save();
        }

        if (this.i18n != null) {
            this.i18n.onDisable();
        }
    }

    public MainConfiguration getConfiguration() {
        return configuration;
    }
}