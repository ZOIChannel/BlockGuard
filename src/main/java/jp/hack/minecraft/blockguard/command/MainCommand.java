package jp.hack.minecraft.blockguard.command;

import jp.hack.minecraft.blockguard.command.subcommand.*;
import jp.hack.minecraft.blockguard.core.CommandExecutor;
import jp.hack.minecraft.blockguard.core.RegionPlugin;

public class MainCommand extends CommandExecutor {
    public MainCommand (RegionPlugin plugin){
        super(plugin);

        addSubCommand(new CreateSubCommand(plugin));
        addSubCommand(new DeleteSubCommand(plugin));
        addSubCommand(new EditSubCommand(plugin));
        addSubCommand(new ListSubCommand(plugin));
        addSubCommand(new PowerSubCommand(plugin));
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
