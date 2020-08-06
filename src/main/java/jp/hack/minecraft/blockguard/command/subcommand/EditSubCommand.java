package jp.hack.minecraft.blockguard.command.subcommand;

import jp.hack.minecraft.blockguard.core.RegionPlugin;
import jp.hack.minecraft.blockguard.core.SubCommand;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.util.List;

public class EditSubCommand implements SubCommand {
    RegionPlugin plugin;

    public EditSubCommand(RegionPlugin plugin){
        this.plugin = plugin;
    }

    @Override
    public String getName() {
        return "edit";
    }

    @Override
    public String getPermission() {
        return null ;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        sender.sendMessage("Editコマンドが実行されました。");
        sender.sendMessage("エリア名は"+args[1]+"、設定名は"+args[2]+"、値は"+args[3]+"です");
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        return null;
    }
}
