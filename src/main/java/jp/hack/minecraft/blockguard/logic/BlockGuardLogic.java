package jp.hack.minecraft.blockguard.logic;

import jp.hack.minecraft.blockguard.core.Region;
import jp.hack.minecraft.blockguard.core.RegionPlugin;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.*;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.EntitySpawnEvent;

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
            // 着火ナシ
            if (e.getBlock().getType() == Material.FIRE) {
                if (!this.isFlag(RegionFlagType.SPREADFIRE)) e.setCancelled(true);
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
            // if (e.getSourceBlock().isLiquid() && e.getBlock().breakNaturally()) {
            // if (!this.isFlag(RegionFlagType.SPREADLIQUID));
            //  e.setCancelled(true);

            // } else
            if (blockName.equals("FIRE")) {
                if (!this.isFlag(RegionFlagType.SPREADFIRE)) e.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onBlockFromToEvent(BlockFromToEvent e) {
        if (isWorking()) {
            if (!this.isFlag(RegionFlagType.SPREADLIQUID)) e.setCancelled(true);
            System.out.println("onBlockFromToEvent:" + e.getBlock());
        }
    }

    @EventHandler
    public void onEntityExplodeEvent(EntityExplodeEvent e) {
        if (isWorking()) {
            if (!this.isFlag(RegionFlagType.EXPLODETNT)) e.setCancelled(true);
        }
    }

    // モンスターのスポーン抑制
    @EventHandler
    public void onEntitySpawnEvent(EntitySpawnEvent e) {
        if (isWorking()) {
            if (!this.isFlag(RegionFlagType.INVADEMOB)) e.setCancelled(true);
        }
    }
}
