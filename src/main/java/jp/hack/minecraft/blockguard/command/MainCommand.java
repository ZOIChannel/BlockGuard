package jp.hack.minecraft.blockguard.command;

import jp.hack.minecraft.blockguard.command.subcommand.CreateSubCommand;
import jp.hack.minecraft.blockguard.core.utils.GameCommandExecutor;
import org.bukkit.plugin.java.JavaPlugin;

public class MainCommand extends GameCommandExecutor {
    public MainCommand (JavaPlugin plugin){
        super(plugin);

        addSubCommand(new CreateSubCommand(plugin));
    }

    @Override
    public String getName() {
        return "blockguard";
    }

    @Override
    public String getPermission() {
        return "blockguard.admin";
    }
}
