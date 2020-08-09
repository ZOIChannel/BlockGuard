package jp.hack.minecraft.blockguard.logic;

import jp.hack.minecraft.blockguard.core.Region;
import jp.hack.minecraft.blockguard.core.RegionManager;
import jp.hack.minecraft.blockguard.core.RegionPlugin;
import jp.hack.minecraft.blockguard.core.Vectors;
import org.bukkit.GameRule;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.util.Vector;

public class BlockGuardLogic extends Region implements Listener {

    public BlockGuardLogic(RegionPlugin plugin, String id) {
        super(plugin, id);
    }

    @EventHandler
    public void BlockBreakEvent(BlockBreakEvent e) {
        if(isWorking()) {
            e.setCancelled(true);
        }
    }

    public void ControlSpreadFire(String id, boolean value){
        String worldName = RegionManager.getInstance().findRegion(id).getVectors().getWorldName();
        World world = getPlugin().getServer().getWorld(worldName);

        world.setGameRule(GameRule.DO_FIRE_TICK, value);
    }
}
