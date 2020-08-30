package jp.hack.minecraft.blockguard.logic;

import jp.hack.minecraft.blockguard.core.Region;
import jp.hack.minecraft.blockguard.core.RegionPlugin;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.*;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;
import java.util.stream.Collectors;

public class BlockGuardLogic extends Region implements Listener {
    private BukkitRunnable checkMonster;

    public BlockGuardLogic(RegionPlugin plugin, String id) {
        super(plugin, id);

        checkMonster = new BukkitRunnable() {
            @Override
            public void run() {
                if (!isWorking()) return;
                if(!isFlag(RegionFlagType.INVADEMOB)) return;
                String worldName = getVectors().getWorldName();
                List<LivingEntity> Entities = plugin.getServer().getWorld(worldName).getLivingEntities().stream().filter(v -> v instanceof Monster).collect(Collectors.toList());
                for (LivingEntity entity : Entities) {
                    if (getRegionArea().contains(entity.getLocation().getX(), entity.getLocation().getY(), entity.getLocation().getZ())) entity.remove();
                }
            }
        };

        checkMonster.runTaskTimer(plugin, 0, 20);
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
            if ((blockName.equals("WATER") || blockName.equals("LAVA")) && e.getBlock().breakNaturally()) {
                if (e.getSourceBlock().isLiquid()) {
                    if (!this.isFlag(RegionFlagType.SPREADLIQUID)) e.setCancelled(true);
                } else if (blockName.equals("FIRE")) {
                    if (!this.isFlag(RegionFlagType.SPREADFIRE)) e.setCancelled(true);
                }
            }
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
    public void onEntitySpawnEvent(EntitySpawnEvent e){
        if(isWorking()){
            if(!this.isFlag(RegionFlagType.INVADEMOB)) e.setCancelled(true);
        }
    }
}
