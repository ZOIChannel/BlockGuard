package jp.hack.minecraft.blockguard.command.subcommand;

import jp.hack.minecraft.blockguard.core.RegionManager;
import jp.hack.minecraft.blockguard.core.RegionPlugin;
import jp.hack.minecraft.blockguard.core.SubCommand;
import jp.hack.minecraft.blockguard.core.utils.I18n;
import jp.hack.minecraft.blockguard.utils.MainConfiguration;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DeleteSubCommand implements SubCommand {
    RegionPlugin plugin;

    public DeleteSubCommand(RegionPlugin plugin){
        this.plugin = plugin;
    }

    @Override
    public String getName() {
        return "delete";
    }

    @Override
    public String getPermission() {
        return null ;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(args.length > 0)) {
            I18n.tl("error.command.invalid.arguments");
            return false;
        }
        sender.sendMessage("Deleteコマンドが実行されました。");
        sender.sendMessage("エリア名は"+args[0]+"です");

        String areaId = args[0];

        RegionManager regionManager = RegionManager.getInstance();
        regionManager.deleteRegion(areaId);

        MainConfiguration configuration = plugin.getConfiguration();
        configuration.removeProperty(areaId);
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<String> ids = RegionManager.getInstance().getIds();
        if (args.length < 1) {
            return ids;
        } else if (args.length < 2){
            return ids.stream().filter(s -> s.startsWith(args[0])).collect(Collectors.toList());
        }
        return new ArrayList<>();
    }
}
