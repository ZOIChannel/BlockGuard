package jp.hack.minecraft.blockguard.logic;

import jp.hack.minecraft.blockguard.core.Region;
import jp.hack.minecraft.blockguard.core.RegionManager;
import jp.hack.minecraft.blockguard.core.RegionPlugin;
import org.bukkit.GameRule;
import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockExplodeEvent;
import org.bukkit.event.block.BlockSpreadEvent;

public class BlockGuardLogic extends Region implements Listener {

    public BlockGuardLogic(RegionPlugin plugin, String id) {
        super(plugin, id);
    }

    @EventHandler
    public void onBlockBreakEvent(BlockBreakEvent e) {
        if(isWorking()) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onBlockSpreadEvent(BlockSpreadEvent e) {
        if(isWorking()) {
            String blockName = e.getSource().getType().data.getName();
            if (blockName.equals("Water") || blockName.equals("Lava")) {
                if (this.isFlag(RegionFlagType.SPREADLIQUID)) e.setCancelled(true);
            } else if (blockName.equals("Fire")) {
                if (this.isFlag(RegionFlagType.SPREADFIRE)) e.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onBlockExplodeEvent(BlockExplodeEvent e) {
        if(isWorking()) {
            if (this.isFlag(RegionFlagType.EXPLODETNT)) e.setCancelled(true);
        }
    }
}
