package jp.hack.minecraft.blockguard.command;

import jp.hack.minecraft.blockguard.command.subcommand.CreateSubCommand;
import jp.hack.minecraft.blockguard.core.CommandExecutor;
import jp.hack.minecraft.blockguard.command.subcommand.TestSubCommand;
import jp.hack.minecraft.blockguard.core.RegionPlugin;

public class MainCommand extends CommandExecutor {
    public MainCommand (RegionPlugin plugin){
        super(plugin);

        addSubCommand(new TestSubCommand(plugin));
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
