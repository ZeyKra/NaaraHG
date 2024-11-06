package fr.zeykra.naaraHG.managers;

import fr.zeykra.naaraHG.enums.WorldState;
import fr.zeykra.naaraHG.enums.Yaml;
import fr.zeykra.naaraHG.models.HGWorld;
import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class HGWorldManager {

    public static Map<String, HGWorld> HGWorldList = new HashMap<>();

    public static void generateWorldList() {
        /*  Va a la section des mondes dans le fichier de configuration yml
            world:
        */
        Yaml.CONFIG.setConfigSection("worlds");
        ConfigurationSection worldKey = Yaml.CONFIG.configSection;

        worldKey.getKeys(false).forEach(worldName -> {
            HGWorld hgWorld = new HGWorld(worldName, WorldState.FREE);
            hgWorld.generateSpawningLocationsList();
            HGBorderManager.resetBorderSize(worldName);
            hgWorld.debugLocations(); // INFO: Fonction de debug
            HGWorldList.put(worldName, hgWorld);
        });
        Yaml.CONFIG.resetConfigSection();
    }

    public static void debugLog() {
        Bukkit.getServer().broadcastMessage("DEBUG: HGWorldManager");
        Bukkit.getServer().broadcastMessage("--------------------");
        HGWorldList.forEach((key, value) -> Bukkit.getServer().broadcastMessage(key + " " + value));
        Bukkit.getServer().broadcastMessage("--------------------");
    }


    /**
     * Fonction pour recuperer un monde aléatoire par rapport aux mondes disponibles
     *
     *
     * @return string Worldname the world of the current HG game
     */
    public static String getRandomAvailableWorld() {
        ArrayList<String> availableWorlds = getAvaibleWorlds();
        int randomIndex = (int) (Math.random() * availableWorlds.size());
        return availableWorlds.get(randomIndex);
    }

    private static ArrayList<String> getAvaibleWorlds() {
        ArrayList<String> availableWorlds = new ArrayList<>();

        HGWorldList.forEach((worldName, hgWorld) -> {
            if (hgWorld.getState() == WorldState.FREE) availableWorlds.add(worldName);
        });

        return availableWorlds;
    }

    public static HGWorld getHGWorldByName(String worldName) {
        return HGWorldList.get(worldName);
    }

}
