package jp.hack.minecraft.blockguard.command.subcommand;

import jp.hack.minecraft.blockguard.core.RegionManager;
import jp.hack.minecraft.blockguard.core.RegionPlugin;
import jp.hack.minecraft.blockguard.core.SubCommand;
import jp.hack.minecraft.blockguard.core.utils.I18n;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
        if (!(args.length > 2)) {
            I18n.tl("error.command.invalid.arguments");
            return false;
        }
        sender.sendMessage("Editコマンドが実行されました。");
        sender.sendMessage("エリア名は"+args[0]+"、設定名は"+args[1]+"、値は"+args[2]+"です");
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<String> ids = RegionManager.getInstance().getIds();
        if (args.length < 1) {
            return ids;
        } else if (args.length < 2){
            return ids.stream().filter(s -> s.startsWith(args[0])).collect(Collectors.toList());
        } else if (args.length < 3) {
            return ids.stream().filter(s -> s.startsWith(args[1])).collect(Collectors.toList());
        } else if (args.length < 4) {
            return Stream.of("on","off").filter(s -> s.startsWith(args[2])).collect(Collectors.toList());
        }
        return new ArrayList<>();
    }
}
