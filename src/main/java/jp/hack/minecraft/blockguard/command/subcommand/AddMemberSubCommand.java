package jp.hack.minecraft.blockguard.command.subcommand;

import jp.hack.minecraft.blockguard.core.Region;
import jp.hack.minecraft.blockguard.core.RegionManager;
import jp.hack.minecraft.blockguard.core.RegionPlugin;
import jp.hack.minecraft.blockguard.core.SubCommand;
import jp.hack.minecraft.blockguard.core.utils.I18n;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class AddMemberSubCommand implements SubCommand {
    RegionPlugin plugin;

    public AddMemberSubCommand(RegionPlugin plugin){
        this.plugin = plugin;
    }

    @Override
    public String getName() {
        return "add";
    }

    @Override
    public String getPermission() {
        return null ;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        sender.sendMessage("AddMemberコマンドが実行されました。");
        if (args.length < 2) {
            sender.sendMessage(I18n.tl("error.command.invalid.arguments"));
            return false;
        }

        String id = args[0];
        String playerName = args[1];

        RegionManager regionManager = RegionManager.getInstance();
        Region region = regionManager.findRegion(id);
        region.addMember(Bukkit.getPlayer(playerName).getUniqueId());
        region.getConfiguration().setMembers(region.getMembers().stream().map(v -> v.toString()).collect(Collectors.toList()));

        sender.sendMessage("メンバーの追加に成功しました: "+id+" "+playerName);
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<String> sArgs = Arrays.stream(args).filter(s1 -> !s1.equals(" ")).collect(Collectors.toList());
        List<String> ids = RegionManager.getInstance().getIds();
        List<String> playerNames = Bukkit.getOnlinePlayers().stream().map(p -> p.getName()).collect(Collectors.toList());

        if (sArgs.size() < 1) {
            return ids;
        } else if (sArgs.size() < 2) {
            return ids.stream().filter(s -> s.startsWith(sArgs.get(0))).collect(Collectors.toList());
        } else if (sArgs.size() < 3){
            return playerNames.stream().filter(s -> s.startsWith(sArgs.get(0))).collect(Collectors.toList());
        }
        return new ArrayList<>();
    }
}
