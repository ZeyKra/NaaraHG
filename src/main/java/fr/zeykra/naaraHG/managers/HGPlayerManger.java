package fr.zeykra.naaraHG.managers;

import fr.zeykra.naaraHG.models.HGPlayer;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class HGPlayerManger {

    /**
     * A map of HGPlayers identified by their UUIDs.
     */
    public static Map<UUID, HGPlayer> HGPLayers = new HashMap<>();


    /**
     * Gets the HGPlayer instance for the player.
     *
     * @param player the player whose HGPlayer instance is to be retrieved
     * @return the HGPlayer instance for the given player
     */
    public static HGPlayer getHGPlayer(Player player) {
        return HGPLayers.get(player.getUniqueId());
    }

    /**
     * Gets the UUID of the current HG game for the given player.
     *
     * @param player the player whose game UUID is to be retrieved
     * @return the UUID of the current HG game
     */
    public static UUID getGameUUIDByPlayer(Player player) {
        return HGPLayers.get(player.getUniqueId()).getCurrentHGGameUUID();
    }

    /**
     * Creates a new HGPlayer instance for the given player and adds it to the map.
     *
     * @param player the player for whom the HGPlayer instance is to be created
     */
    public static void createHGPLayer(Player player) {
        HGPlayer hgPlayer = new HGPlayer(player);
        HGPLayers.put(player.getUniqueId(), hgPlayer);
    }

    /**
     * Deletes the HGPlayer instance for the given player from the map.
     *
     * @param player the player whose HGPlayer instance is to be deleted
     */
    public static void deleteHGPLayer(Player player) {
        HGPLayers.remove(player.getUniqueId());
    }
}
