package jp.hack.minecraft.blockguard.core.utils;

import org.bukkit.command.TabExecutor;

public interface SubCommand extends TabExecutor {
    String getName();
    String getPermission();
}
