package jp.hack.minecraft.blockguard.command.subcommand;

import jp.hack.minecraft.blockguard.core.Region;
import jp.hack.minecraft.blockguard.core.RegionManager;
import jp.hack.minecraft.blockguard.core.RegionPlugin;
import jp.hack.minecraft.blockguard.core.SubCommand;
import jp.hack.minecraft.blockguard.core.utils.I18n;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.List;

public class ListOperatorSubCommand implements SubCommand {
    RegionPlugin plugin;

    public ListOperatorSubCommand(RegionPlugin plugin){
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
        sender.sendMessage("ListOperatorコマンドが実行されました。");
        if (args.length < 1) {
            sender.sendMessage(I18n.tl("error.command.invalid.arguments"));
            return false;
        }

        String id = args[0];
        Region region = RegionManager.getInstance().findRegion(id);

        StringBuilder stringBuilder = new StringBuilder();

        region.getOperators().stream().forEach(ope -> { stringBuilder.append(ope).append(" "); });
        sender.sendMessage(I18n.tl("message.command.list.operators", stringBuilder.toString()));
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        return new ArrayList<>();
    }
}
