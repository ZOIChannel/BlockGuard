package jp.hack.minecraft.blockguard.command.subcommand;

import jp.hack.minecraft.blockguard.core.RegionPlugin;
import jp.hack.minecraft.blockguard.core.SubCommand;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.util.List;

public class ListSubCommand implements SubCommand {
    RegionPlugin plugin;

    public ListSubCommand(RegionPlugin plugin){
        this.plugin = plugin;
    }

    @Override
    public String getName() {
        return "list";
    }

    @Override
    public String getPermission() {
        return null ;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        sender.sendMessage("Listコマンドが実行されました。");
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        return null;
    }
}
