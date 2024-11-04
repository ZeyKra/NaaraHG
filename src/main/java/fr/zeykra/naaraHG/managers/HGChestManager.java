package fr.zeykra.naaraHG.managers;

import fr.zeykra.naaraHG.enums.WorldState;
import fr.zeykra.naaraHG.enums.Yaml;
import fr.zeykra.naaraHG.utils.BukkitSerialization;
import fr.zeykra.naaraHG.utils.Cuboid;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.inventory.ItemStack;

import java.io.IOException;
import java.util.HashMap;

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
        Location worldCenter = Bukkit.getServer().getWorld(world).getSpawnLocation();
        Cuboid cuboid = new Cuboid(worldCenter.clone().add(-500, 0, -500), worldCenter.clone().add(500, 0, 500));

        Bukkit.getServer().broadcastMessage("DEBUG: worldCenter" + worldCenter.toString());
        Bukkit.getServer().broadcastMessage("DEBUG: Cuboid Size" + cuboid.getBlocks().size());
        //Bukkit.getServer().broadcastMessage("DEBUG: Cuboid" + cuboid.getBlocks().size());

        for (Block block : cuboid) {
            if (block.getType() != Material.AIR) {
                Bukkit.getServer().broadcastMessage("DEBUG: " + block.getLocation().toString() + " " + block.getType().toString());
            }
        }

        /*
        cuboid.getBlocks().forEach(block -> {
            if (block.getType() != Material.AIR) {
                //Chest chest = (Chest) block.getState();
                Bukkit.getServer().broadcastMessage("DEBUG: " + block.getLocation().toString() + " " + block.getType().toString());
                /*
                try {
                    Bukkit.getServer().broadcastMessage("DEBUG: Cuboid Size" + cuboid.getBlocks().size());
                    Bukkit.getServer().broadcastMessage("DEBUG: " + chest.getLocation().toString());
                    chest.getInventory().setContents(BukkitSerialization.itemStackArrayFromBase64(getRandomChestContent()));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        })
        */
    }


}
