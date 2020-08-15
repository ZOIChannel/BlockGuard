package jp.hack.minecraft.blockguard.command.subcommand;

import jp.hack.minecraft.blockguard.core.RegionManager;
import jp.hack.minecraft.blockguard.core.RegionPlugin;
import jp.hack.minecraft.blockguard.core.SubCommand;
import jp.hack.minecraft.blockguard.core.utils.I18n;
import jp.hack.minecraft.blockguard.utils.MainConfiguration;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
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
            sender.sendMessage(I18n.tl("error.command.invalid.arguments"));
            return false;
        }
        sender.sendMessage("Deleteコマンドが実行されました。");

        String id = args[0];

        RegionManager regionManager = RegionManager.getInstance();
        if (!regionManager.deleteRegion(plugin, id)) {
            return false;
        }

        sender.sendMessage("設定の消去に成功しました: "+id);
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<String> sArgs = Arrays.stream(args).filter(s1 -> !s1.equals(" ")).collect(Collectors.toList());
        List<String> ids = RegionManager.getInstance().getIds();
        if (sArgs.size() < 1) {
            return ids;
        } else if (sArgs.size() < 2){
            return ids.stream().filter(s -> s.startsWith(sArgs.get(0))).collect(Collectors.toList());
        }
        return new ArrayList<>();
    }
}
