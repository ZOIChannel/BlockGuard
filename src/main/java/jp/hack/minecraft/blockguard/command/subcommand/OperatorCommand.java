package jp.hack.minecraft.blockguard.command.subcommand;

import jp.hack.minecraft.blockguard.core.CommandExecutor;
import jp.hack.minecraft.blockguard.core.RegionPlugin;
import jp.hack.minecraft.blockguard.core.SubCommand;

public class OperatorCommand extends CommandExecutor {

    public OperatorCommand (RegionPlugin plugin){
        super(plugin);

        addSubCommand(new AddOperatorSubCommand(plugin));
        addSubCommand(new DeleteOperatorSubCommand(plugin));
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
