package jp.hack.minecraft.blockguard.logic;

import jp.hack.minecraft.blockguard.core.Region;
import jp.hack.minecraft.blockguard.core.RegionPlugin;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.*;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class BlockGuardLogic extends Region implements Listener {

    public BlockGuardLogic(RegionPlugin plugin, String id) {
        super(plugin, id);
    }

    @EventHandler
    public void onBlockPlaceEvent(BlockPlaceEvent e) {
        Player p = e.getPlayer();
        if (isWorking()) {
            if (!(getMembers().contains(p.getUniqueId()) || getOperators().contains(p.getUniqueId()))) {
                e.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onBlockBreakEvent(BlockBreakEvent e) {
        Player p = e.getPlayer();
        if (isWorking()) {
            if (!(getMembers().contains(p.getUniqueId()) || getOperators().contains(p.getUniqueId()))) {
                e.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onBlockPhysicsEvent(BlockPhysicsEvent e) {
        if (isWorking()) {
            String blockName = e.getSourceBlock().getType().name();
            if ((blockName.equals("WATER") || blockName.equals("LAVA")) && e.getBlock().breakNaturally()) {
                if (!this.isFlag(RegionFlagType.SPREADLIQUID)) e.setCancelled(true);
            } else if (blockName.equals("FIRE")) {
                if (!this.isFlag(RegionFlagType.SPREADFIRE)) e.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onEntityExplodeEvent(EntityExplodeEvent e) {
        if (isWorking()) {
            if (!this.isFlag(RegionFlagType.EXPLODETNT)) e.setCancelled(true);
        }
    }
}
