package jp.hack.minecraft.blockguard.command;

import jp.hack.minecraft.blockguard.command.subcommand.CreateSubCommand;
<<<<<<< HEAD
import jp.hack.minecraft.blockguard.core.CommandExecutor;
=======
import jp.hack.minecraft.blockguard.command.subcommand.TestSubCommand;
import jp.hack.minecraft.blockguard.core.utils.GameCommandExecutor;
>>>>>>> 7c339e29a07897f54146c8472f390c856bdf79dc
import org.bukkit.plugin.java.JavaPlugin;

public class MainCommand extends CommandExecutor {
    public MainCommand (JavaPlugin plugin){
        super(plugin);

        addSubCommand(new TestSubCommand(plugin));
        addSubCommand(new CreateSubCommand(plugin));
    }

    @Override
    public String getName() {
        return "blockguard";
    }

    @Override
    public String getPermission() {
        return "blockguard.admin";
    }
}
