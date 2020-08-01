package jp.hack.minecraft.blockguard.command.subcommand;

import org.bukkit.command.TabExecutor;

public interface SubCommand extends TabExecutor {
    String getName();
    String getPermission();
}
