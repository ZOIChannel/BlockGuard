package jp.hack.minecraft.blockguard.command.subcommand;

import com.sk89q.worldedit.IncompleteRegionException;
import jp.hack.minecraft.blockguard.core.utils.WorldEditorUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public class TestSubCommand implements SubCommand {
    JavaPlugin plugin;

    public TestSubCommand(JavaPlugin plugin){
        this.plugin = plugin;
    }

    @Override
    public String getName() {
        return "test";
    }

    @Override
    public String getPermission() {
        return null;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        plugin.getLogger().info("testコマンドが実行されました");
        for (int i = 0; i < args.length; i++) {
            plugin.getLogger().info("args[" + i + "] = " + args[i]);
        }

        sender.sendMessage("testコマンドが実行されました");
        for (int i = 0; i < args.length; i++) {
            sender.sendMessage("args[" + i + "] = " + args[i]);
        }

        WorldEditorUtil weUtil = new WorldEditorUtil();
        try {
            weUtil.getRegion((Player) sender);
        } catch (IncompleteRegionException e) {
            e.printStackTrace();
        }

        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        return new ArrayList<>();
    }
}
