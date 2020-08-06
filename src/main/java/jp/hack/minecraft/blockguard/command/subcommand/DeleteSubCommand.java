package jp.hack.minecraft.blockguard.command.subcommand;

import jp.hack.minecraft.blockguard.core.Region;
import jp.hack.minecraft.blockguard.core.RegionManager;
import jp.hack.minecraft.blockguard.core.RegionPlugin;
import jp.hack.minecraft.blockguard.core.SubCommand;
import jp.hack.minecraft.blockguard.utils.RegionConfiguration;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

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
        sender.sendMessage("Deleteコマンドが実行されました。");
        sender.sendMessage("エリア名は"+args[0]+"です");

        String areaId = args[0];
        RegionManager regionManager = RegionManager.getInstance();
        Region region = regionManager.findRegion(areaId);
        RegionConfiguration configuration = region.getConfiguration();

        regionManager.deleteRegion(areaId);
        configuration.removeProperty(areaId);
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        return null;
    }
}
