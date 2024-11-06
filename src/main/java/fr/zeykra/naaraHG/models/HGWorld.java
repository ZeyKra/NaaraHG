package fr.zeykra.naaraHG.models;

import fr.zeykra.naaraHG.enums.WorldState;
import fr.zeykra.naaraHG.enums.Yaml;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;

import java.util.ArrayList;
import java.util.List;

public class HGWorld {

    private String worldName;
    private WorldState state;
    List<Location> spawningLocations;

    public HGWorld(String worldName, WorldState state) {
        this.worldName = worldName;
        this.state = state;
        this.spawningLocations = new ArrayList<>();
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
        Yaml.CONFIG.setConfigSection("worlds");
        //System.out.println(Yaml.CONFIG.configSection.getConfigurationSection(this.worldName).getConfigurationSection("1").get("x"));
        ConfigurationSection worldKey = Yaml.CONFIG.getConfigSection();
        //System.out.println("DEBUG: " + worldKey.getKeys(false).toString());

        worldKey.getConfigurationSection(this.worldName).getKeys(false).forEach(key -> {
            Location loc = Yaml.CONFIG.getLocWithDirection(this.worldName + "." + key);
            //System.out.println("DEBUG: " + this.worldName + " " +  key + " loc! " + loc.toString());

            spawningLocations.add(loc);
        });


        Yaml.CONFIG.resetConfigSection();
    }

    public void DebugLocations() {
        System.out.println("DEBUG: HGWorld " + this.worldName);
        System.out.println("--------------------");
        for(Location location : spawningLocations) {
            System.out.println(location.toString());
        }
        System.out.println("--------------------");
    }

}
