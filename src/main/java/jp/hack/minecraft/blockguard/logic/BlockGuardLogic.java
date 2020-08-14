package jp.hack.minecraft.blockguard.logic;

import jp.hack.minecraft.blockguard.core.Region;
import jp.hack.minecraft.blockguard.core.RegionManager;
import jp.hack.minecraft.blockguard.core.RegionPlugin;
import org.bukkit.GameRule;
import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.*;

public class BlockGuardLogic extends Region implements Listener {

    public BlockGuardLogic(RegionPlugin plugin, String id) {
        super(plugin, id);
    }

    @EventHandler
    public void onBlockPlaceEvent(BlockPlaceEvent e) {
        if(isWorking()) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onBlockBreakEvent(BlockBreakEvent e) {
        if(isWorking()) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onBlockPhysicsEvent(BlockPhysicsEvent e) {
        if(isWorking()) {
            String blockName = e.getSourceBlock().getType().name();
            System.out.println(blockName);
            if (blockName.equals("WATER") || blockName.equals("LAVA")) {
                if (!this.isFlag(RegionFlagType.SPREADLIQUID)) e.setCancelled(true);
            } else if (blockName.equals("FIRE")) {
                if (!this.isFlag(RegionFlagType.SPREADFIRE)) e.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onEntityExplodeEvent(BlockExplodeEvent e) {
        if(isWorking()) {
            System.out.println(this.isFlag(RegionFlagType.EXPLODETNT));
            if (!this.isFlag(RegionFlagType.EXPLODETNT)) e.setCancelled(true);
        }
    }
}
