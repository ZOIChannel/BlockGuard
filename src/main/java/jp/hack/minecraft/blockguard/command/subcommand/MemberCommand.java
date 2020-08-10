package jp.hack.minecraft.blockguard.command.subcommand;

import jp.hack.minecraft.blockguard.core.CommandExecutor;
import jp.hack.minecraft.blockguard.core.RegionPlugin;

public class MemberCommand extends CommandExecutor {

    public MemberCommand (RegionPlugin plugin){
        super(plugin);

        addSubCommand(new AddMemberSubCommand(plugin));
        addSubCommand(new DeleteMemberSubCommand(plugin));
    }

    @Override
    public String getName() {
        return "member";
    }

    @Override
    public String getPermission() {
        return "blockguard.admin";
    }
}
