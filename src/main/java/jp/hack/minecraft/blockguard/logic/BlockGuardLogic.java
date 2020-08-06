package jp.hack.minecraft.blockguard.logic;

import jp.hack.minecraft.blockguard.core.Region;
import jp.hack.minecraft.blockguard.core.RegionPlugin;
import jp.hack.minecraft.blockguard.core.Vectors;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.util.Vector;

public class BlockGuardLogic extends Region implements Listener {
    private Vectors vectors;

    public BlockGuardLogic(RegionPlugin plugin, String id, Vectors vectors) {
        super(plugin, id, vectors);
    }

    public void BlockBreakEvent(BlockBreakEvent e) {
        if(isWorking()) {
            e.setCancelled(true);
        }
    }
}
