package jp.hack.minecraft.blockguard.command.subcommand;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public class CreateSubCommand implements SubCommand {
    JavaPlugin plugin;

    public CreateSubCommand(JavaPlugin plugin){
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
<<<<<<< HEAD

=======
        sender.sendMessage("Createコマンドが実行されました。");
        sender.sendMessage("エリア名は"+args[1]+"です");
>>>>>>> 0e911d4e5a8cc97187e7a9a958dd5fe43200e247
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        return null;
    }
}
