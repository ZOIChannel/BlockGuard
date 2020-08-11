package jp.hack.minecraft.blockguard.command;

import jp.hack.minecraft.blockguard.command.subcommand.AddOperatorSubCommand;
import jp.hack.minecraft.blockguard.command.subcommand.DeleteOperatorSubCommand;
import jp.hack.minecraft.blockguard.command.subcommand.ListOperatorSubCommand;
import jp.hack.minecraft.blockguard.core.CommandExecutor;
import jp.hack.minecraft.blockguard.core.RegionPlugin;

public class OperatorCommand extends CommandExecutor {

    public OperatorCommand (RegionPlugin plugin){
        super(plugin);

        addSubCommand(new AddOperatorSubCommand(plugin));
        addSubCommand(new DeleteOperatorSubCommand(plugin));
        addSubCommand(new ListOperatorSubCommand(plugin));
    }

    @Override
    public String getName() {
        return "operator";
    }

    @Override
    public String getPermission() {
        return "blockguard.admin";
    }


}
