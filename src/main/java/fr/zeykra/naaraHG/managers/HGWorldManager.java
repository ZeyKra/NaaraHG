package fr.zeykra.naaraHG.managers;

import fr.zeykra.naaraHG.enums.WorldState;
import fr.zeykra.naaraHG.enums.Yaml;
import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class HGWorldManager {

    public static Map<String, WorldState> HGWorldList = new HashMap<>();

    public static WorldState getWorldState(String worldName) {
        return HGWorldList.get(worldName);
    }

    public static void setWordState(String worldName, WorldState state) {
        HGWorldList.put(worldName, state);
    }

    public static void generateWorldList() {
        /*  Va a la section des mondes dans le fichier de configuration yml
            world:
        */
        Yaml.CONFIG.setConfigSection("worlds");
        ConfigurationSection worldKey = Yaml.CONFIG.configSection;

        worldKey.getKeys(false).forEach(worldName -> {
            HGWorldList.put(worldName, WorldState.FREE);
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

        HGWorldList.forEach((worldName, state) -> {
            if (state == WorldState.FREE) availableWorlds.add(worldName);
        });

        return availableWorlds;
    }


}
