package jp.hack.minecraft.blockguard.core.utils;

import com.sk89q.worldedit.IncompleteRegionException;
import com.sk89q.worldedit.LocalSession;
import com.sk89q.worldedit.WorldEdit;
import com.sk89q.worldedit.WorldEditException;
import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.regions.Region;
import org.bukkit.entity.Player;

public class WorldEditorUtil {
    public Region getRegion(Player player) throws IncompleteRegionException {
        com.sk89q.worldedit.bukkit.BukkitPlayer wePlayer = BukkitAdapter.adapt(player);
        LocalSession session = WorldEdit.getInstance().getSessionManager().get(wePlayer);

        try {
            Region region = session.getRegionSelector(wePlayer.getWorld()).getRegion();
            return region;
        } catch (WorldEditException e) {
            I18n.tl("error.worldedit.save", e);
            e.printStackTrace();
        }

        return null;
    }
}
