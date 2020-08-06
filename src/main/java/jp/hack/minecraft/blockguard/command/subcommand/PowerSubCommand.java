package jp.hack.minecraft.blockguard.command.subcommand;

import jp.hack.minecraft.blockguard.core.Region;
import jp.hack.minecraft.blockguard.core.RegionManager;
import jp.hack.minecraft.blockguard.core.SubCommand;
import jp.hack.minecraft.blockguard.core.utils.I18n;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public class PowerSubCommand implements SubCommand {
    JavaPlugin plugin;

    public PowerSubCommand(JavaPlugin plugin){
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
        sender.sendMessage("Powerコマンドが実行されました。");
        sender.sendMessage("エリア名は"+args[0]+"、値は"+args[1]+"です");
        String id = args[0];
        Boolean isWorking = Boolean.valueOf(args[1]);

        RegionManager regionManager = RegionManager.getInstance();
        Region region = regionManager.findRegion(id);

        if(args[1].equals("on")) {
            if(!region.isWorking()) region.setWorking(true);
        } else if(args[1].equals("off")) {
            if(region.isWorking()) region.setWorking(false);
        } else {
            sender.sendMessage(I18n.tl("error.command.invalid.arguments"));
            return false;
        }

        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        return null;
    }
}
