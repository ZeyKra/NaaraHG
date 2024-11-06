package fr.zeykra.naaraHG.models;

import fr.zeykra.naaraHG.enums.WorldState;
import fr.zeykra.naaraHG.enums.Yaml;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;

import java.util.*;

public class HGWorld {

    private String worldName;
    private WorldState state;
    HashMap<Location, UUID> spawningLocations;

    public HGWorld(String worldName, WorldState state) {
        this.worldName = worldName;
        this.state = state;
        this.spawningLocations = new HashMap<>();
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

            spawningLocations.put(loc, null);
        });


        Yaml.CONFIG.resetConfigSection();
    }

    public HashMap<Location, UUID> getSpawningLocations() {
        return spawningLocations;
    }

    public ArrayList<Location> getSpawningLocationAsList() {
        return new ArrayList<>(spawningLocations.keySet());
    }

    public HashMap<Location, UUID> getAvailableSpawningLocations() {
        HashMap<Location, UUID> availableLocations = new HashMap<>();
        spawningLocations.forEach((location, uuid) -> {
            if(uuid == null) {
                availableLocations.put(location, null);
            }
        });
        return availableLocations;
    }

    public ArrayList<Location> getAvailableSpawningLocationsAsList() {
        return new ArrayList<>(getAvailableSpawningLocations().keySet());
    }

    public Location reserveRandomAvailableLocation(UUID playerUUID) {
        int randomIndex = (int) (Math.random() * getAvailableSpawningLocations().size());
        Location location = getAvailableSpawningLocationsAsList().get(randomIndex);

        //Reservation de la locations
        this.spawningLocations.put(location, playerUUID);

        return location;
    }


    public void debugLocations() {
        System.out.println("DEBUG: HGWorld " + this.worldName);
        System.out.println("--------------------");
        for(Map.Entry<Location, UUID> location : spawningLocations.entrySet()) {
            String valueText = location.getValue() == null ? "null" : location.getValue().toString();
            System.out.println(location.getKey().toString() + " " + valueText);
        }
        System.out.println("--------------------");
    }

}
