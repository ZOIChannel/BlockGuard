package jp.hack.minecraft.blockguard.core.utils;

import com.sk89q.worldedit.LocalSession;
import com.sk89q.worldedit.WorldEdit;
import com.sk89q.worldedit.WorldEditException;
import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.regions.Region;
import jp.hack.minecraft.blockguard.core.Vectors;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public class WorldEditorUtil {
    public Vectors getVectors(Player player) {
        com.sk89q.worldedit.bukkit.BukkitPlayer wePlayer = BukkitAdapter.adapt(player);
        LocalSession session = WorldEdit.getInstance().getSessionManager().get(wePlayer);

        try {
            Region region = session.getRegionSelector(wePlayer.getWorld()).getRegion();
            Vector min = new Vector(region.getMinimumPoint().getX(), region.getMinimumPoint().getY(), region.getMinimumPoint().getZ());
            Vector max = new Vector(region.getMaximumPoint().getX(), region.getMaximumPoint().getY(), region.getMaximumPoint().getZ());
            String worldName = player.getWorld().getName();

            Vectors vectors = new Vectors(min, max, worldName);
            return vectors;
        } catch (WorldEditException e) {
            I18n.tl("error.worldedit.save", e);
            e.printStackTrace();
        }

        return null;
    }
}
