package jp.hack.minecraft.blockguard.command.subcommand;

import jp.hack.minecraft.blockguard.core.*;
import jp.hack.minecraft.blockguard.core.utils.I18n;
import jp.hack.minecraft.blockguard.core.utils.WorldEditorUtil;
import jp.hack.minecraft.blockguard.utils.RegionConfiguration;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
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
        if (!(args.length > 0)) {
            sender.sendMessage(I18n.tl("error.command.invalid.arguments"));
            return false;
        }
        sender.sendMessage("Createコマンドが実行されました。");

        Player player = (Player) sender;
        String id = args[0];

        Vectors vectors = getVectors(player);

        if(vectors == null) {
            sender.sendMessage(I18n.tl("error.command.noposition"));
            return false;
        }

        Region region = RegionManager.getInstance().createRegion(plugin, id);
        region.setVectors(vectors);
        region.addOperator(player.getUniqueId());

        RegionConfiguration configuration = region.getConfiguration();

        configuration.setRegion(region);
        configuration.save();

        sender.sendMessage("設定の作成に成功しました: "+id);
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        return new ArrayList<>();
    }
}
