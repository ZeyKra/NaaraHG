package fr.zeykra.naaraHG.managers;

import fr.zeykra.naaraHG.NaaraHG;
import fr.zeykra.naaraHG.enums.Yaml;
import fr.zeykra.naaraHG.utils.BukkitSerialization;
import fr.zeykra.naaraHG.utils.Cuboid;
import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.BlockState;
import org.bukkit.block.Chest;
import org.bukkit.inventory.ItemStack;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class HGChestManager {

    public static HashMap<String, String> ChestContentList = new HashMap<>();

    /**
     * Fonction pour ajouter un contenu de coffre a la liste dans la configuration a partir d'une string en base64
     *
     * @param chestContentInBase64 String
     */
    public static void addChestContent(String chestContentInBase64) {
        ChestContentList.put(Integer.toString(ChestContentList.size()), chestContentInBase64);
        Yaml.CONFIG.setHashmap("chest", ChestContentList);
    }

    /**
     * Fonction pour ajouter un contenu de coffre a la liste dans la configuration a partir d'une arraylist d'item
     *
     * @param chestContent
     */
    public static void addChestContent(ItemStack[] chestContent) {
        String chestContentInBase64 = BukkitSerialization.itemStackArrayToBase64(chestContent);
        addChestContent(chestContentInBase64);
    }

    public static void regenerateChestContentList() {
        ChestContentList = Yaml.CONFIG.getHashmap("chest");
    }

    public static String getRandomChestContent() {
        int randomIndex = (int) (Math.random() * ChestContentList.size());
        return ChestContentList.get(Integer.toString(randomIndex));
    }

    public static void randomFillChest(String world) {
        getChunksAroundCenter(world).forEach(chunk -> {
            for(BlockState blockState : chunk.getTileEntities()) {
                if (!(blockState instanceof Chest)) continue;
                Chest chest = (Chest) blockState;

                // for debug
                //Bukkit.getServer().getWorld(world).spawnEntity(chest.getLocation().clone().add(0, 1, 0), EntityType.ARMOR_STAND);
                //Bukkit.getServer().broadcastMessage("DEBUG: " + chest.getLocation().toString() + " " + chest.getBlock().getType().toString());

                try {
                    chest.getInventory().setContents(BukkitSerialization.itemStackArrayFromBase64(getRandomChestContent()));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }


    private static List<Chunk> getChunksAroundCenter(String world) {
        Location worldCenter = Bukkit.getServer().getWorld(world).getSpawnLocation();
        Cuboid cuboid = new Cuboid(worldCenter.clone().add(-500, -100, -500), worldCenter.clone().add(500, 100, 500));
        cuboid.getCenter().getBlock().setType(Material.DIAMOND_BLOCK);

        return cuboid.getChunks();
    }


}
