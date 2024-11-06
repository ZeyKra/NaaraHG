package fr.zeykra.naaraHG.models;

import fr.zeykra.naaraHG.enums.WorldState;
import fr.zeykra.naaraHG.enums.Yaml;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;

import java.util.List;

public class HGWorld {

    private String worldName;
    private WorldState state;
    List<Location> spawningLocations;

    public HGWorld(String worldName, WorldState state) {
        this.worldName = worldName;
        this.state = state;
    }

    public String getWorldName() {
        return worldName;
    }

    public WorldState getState() {
        return this.state;
    }

    public void setState(WorldState state) {
        this.state = state;
    }

    public void setWorldName(String worldName) {
        this.worldName = worldName;
    }

    public void generateSpawningLocationsList() {
        Yaml.CONFIG.setConfigSection("worlds." + this.worldName);
        ConfigurationSection worldKey = Yaml.CONFIG.getConfigSection();
        System.out.println("DEBUG: " + worldKey.getKeys(false).toString());
        for(int index = 0; index < worldKey.getKeys(false).size(); index++) {
            Location loc = Yaml.CONFIG.getLocWithDirection("worlds." + this.worldName + "." + String.valueOf(index));
            System.out.println("DEBUG: " + loc.toString());


            //spawningLocations.add();
        }
        Yaml.CONFIG.resetConfigSection();
    }

    public void DebugLocations() {
        Bukkit.getServer().broadcastMessage("DEBUG: HGWorld");
        Bukkit.getServer().broadcastMessage("--------------------");
        for(Location location : spawningLocations) {
            Bukkit.getServer().broadcastMessage(location.toString());
        }
        Bukkit.getServer().broadcastMessage("--------------------");
    }

}
