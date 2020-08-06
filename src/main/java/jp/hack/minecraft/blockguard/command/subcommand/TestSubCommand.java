package jp.hack.minecraft.blockguard.command.subcommand;

import jp.hack.minecraft.blockguard.core.SubCommand;
import jp.hack.minecraft.blockguard.core.Vectors;
import jp.hack.minecraft.blockguard.core.utils.WorldEditorUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;

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


        Vectors vectors = weUtil.getVectors((Player) sender);
        Vector minVec = vectors.getMin();
        Vector maxVec = vectors.getMax();

        sender.sendMessage("min:("+minVec.getBlockX()+", "+minVec.getBlockY()+", "+minVec.getBlockZ()+")");
        sender.sendMessage("max:("+maxVec.getBlockX()+", "+maxVec.getBlockY()+", "+maxVec.getBlockZ()+")");

        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        return new ArrayList<>();
    }
}
