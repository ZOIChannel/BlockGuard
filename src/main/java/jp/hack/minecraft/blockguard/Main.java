package jp.hack.minecraft.blockguard;

import jp.hack.minecraft.blockguard.command.MainCommand;
import jp.hack.minecraft.blockguard.core.RegionPlugin;

public final class Main extends RegionPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getCommand("blockguard").setExecutor(new MainCommand(this));
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
