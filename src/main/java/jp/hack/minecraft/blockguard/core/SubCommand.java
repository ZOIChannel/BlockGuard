package jp.hack.minecraft.blockguard.core;

import org.bukkit.command.TabExecutor;

public interface SubCommand extends TabExecutor {
    String getName();
    String getPermission();
}
