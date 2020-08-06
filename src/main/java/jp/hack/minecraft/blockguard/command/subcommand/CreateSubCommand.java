package jp.hack.minecraft.blockguard.command.subcommand;

import jp.hack.minecraft.blockguard.core.Region;
import jp.hack.minecraft.blockguard.core.RegionPlugin;
import jp.hack.minecraft.blockguard.core.SubCommand;
import jp.hack.minecraft.blockguard.core.Vectors;
import jp.hack.minecraft.blockguard.core.utils.WorldEditorUtil;
import jp.hack.minecraft.blockguard.utils.RegionConfiguration;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;

import java.util.List;

public class CreateSubCommand extends WorldEditorUtil implements SubCommand {
    RegionPlugin plugin;

    public CreateSubCommand(RegionPlugin plugin){
        this.plugin = plugin;
    }

    @Override
    public String getName() {
        return "create";
    }

    @Override
    public String getPermission() {
        return null ;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        sender.sendMessage("Createコマンドが実行されました。");
        sender.sendMessage("エリア名は"+args[0]+"です");
        String areaId = args[0];

        if (args.length < 1) {
            return false;
        }

        Vectors vectors = getVectors((Player) sender);

        if(vectors == null) {
            return false;
        }

        Region region = new Region(plugin, areaId, vectors);

        RegionConfiguration configuration = region.getConfiguration();

        configuration.setRegion(region);
        configuration.save();

        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        return null;
    }
}
