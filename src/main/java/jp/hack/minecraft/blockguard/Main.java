package jp.hack.minecraft.blockguard;

import jp.hack.minecraft.blockguard.command.MainCommand;
import jp.hack.minecraft.blockguard.core.RegionManager;
import jp.hack.minecraft.blockguard.core.RegionPlugin;
import jp.hack.minecraft.blockguard.logic.BlockGuardLogic;

public final class Main extends RegionPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        super.onEnable();

        getCommand("blockguard").setExecutor(new MainCommand(this));

        RegionManager.getInstance().setGenerator((plugin, id) -> new BlockGuardLogic(plugin, id));

        RegionManager.getInstance().loadRegion(this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        super.onDisable();

    }
}
