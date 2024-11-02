package fr.zeykra.naaraHG.enums;

import fr.zeykra.naaraHG.NaaraHG;
import fr.zeykra.naaraHG.utils.YamlInventoryObject;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.*;
import java.util.HashMap;
import java.util.logging.Logger;

public enum Yaml {
    CONFIG("config.yml");

    private final String fileName;
    private final File dataFolder;
    private final NaaraHG instance = NaaraHG.INSTANCE;
    private final File file;

    public ConfigurationSection configSection;

    /**
     * Initializing Yaml Object
     *
     * @param subFolder subfolder to put file  in
     * @param fileName  file name
     * @Author : ssgadryan
     */
    Yaml(String subFolder, String fileName) {
        this.fileName = fileName;
        //System.out.println(instance.getDataFolder().toPath() + "\\" + subFolder + "\\");
        this.dataFolder = new File(instance.getDataFolder().toPath().toFile(), subFolder);
        this.file = new File(dataFolder, fileName);
        this.ymlConfig = YamlConfiguration.loadConfiguration(file);
    }

    /**
     * Initializing Yaml Object directly into Spigot DataFolder
     *
     * @param fileName file name
     * @Author : ssgadryan
     */
    Yaml(String fileName) {
        this.fileName = fileName;
        this.dataFolder = instance.getDataFolder();
        this.file = new File(dataFolder, fileName);
        this.ymlConfig = YamlConfiguration.loadConfiguration(file);
    }

    /**
     * Creating the Yaml file into folder
     *
     * @param logger Logger
     * @Author : sggadryan
     */
    public void create(Logger logger) {
        if (fileName == null || fileName.isEmpty()) {
            throw new IllegalArgumentException("ResourcePath cannot be found");
        }
        InputStream in = instance.getResource(fileName);
        if (in == null) {
            throw new IllegalArgumentException(fileName + "n'a pas était trouvé dans les sources du .jar");
        }
        if (!dataFolder.exists() && !dataFolder.mkdir()) {
            logger.severe("Le dossier n'a pas pu être crée");
        }
        File outFile = getFile();

        try {
            if (!outFile.exists()) {
                logger.info(fileName + "n'a pas été trouvé, création du fichier en cours");

                OutputStream out = new FileOutputStream(outFile);
                byte[] buff = new byte[1024];
                int n;

                while ((n = in.read(buff)) >= 0) {
                    out.write(buff, 0, n);
                }
                out.close();
                in.close();
                if (!outFile.exists()) {
                    logger.severe("Duplication du fichier impossible");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public File getFile() {
        return new File(dataFolder, fileName);
    }

    /* -------------------------------------
     * @Author : ZeyKra_
     * Class permettant de gerer les fichier yml
     * -------------------------------------
     */

    //made by ZeyKra_

    YamlConfiguration ymlConfig;

    public void setConfigSection(String key) {
        this.configSection = ymlConfig.getConfigurationSection(key);
    }
    public void setConfigSection(ConfigurationSection configurationSection) {
        this.configSection = configurationSection;
    }

    public void resetConfigSection() { this.configSection = null; }

    public ConfigurationSection getConfigSection() {
        return configSection;
    }

    //basique
    // getter
    public String getString(String key) {
        if (file.exists()) {
            if (configSection == null) {
                return ymlConfig.getString(key);
            }
            return configSection.getString(key);
        }
        return null;
    }


    public double getDouble(String key) {
        if (file.exists()) {
            if (configSection == null) {
                return ymlConfig.getDouble(key);
            }
            return configSection.getDouble(key);
        }
        return 0.0;
    }

    public Boolean getBoolean(String key) {
        if (file.exists()) {
            if (configSection == null) {
                return ymlConfig.getBoolean(key);
            }
            return configSection.getBoolean(key);
        }
        return null;
    }

    public int getInt(String key) {
        if (file.exists()) {
            if (configSection == null) {
                return ymlConfig.getInt(key);
            }
            return configSection.getInt(key);
        }
        return 0;
    }

    public float getFloat(String key) {
        return (float) getDouble(key);
    }

    //setter

    public void set(String key, Object value) {
        if (file.exists()) {
            if (configSection == null) {
                ymlConfig.set(key, value);
                try {
                    ymlConfig.save(file);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return;
            }
            configSection.set(key, value);
            try {
                ymlConfig.save(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //Plus spécifique

    //setter
    public void setLoc(ConfigurationSection configurationSection, Location loc) {
        configurationSection.set("world", loc.getWorld().getName());
        configurationSection.set("x", loc.getX());
        configurationSection.set("y", loc.getY());
        configurationSection.set("z", loc.getZ());
        try {
            ymlConfig.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setLoc(String configSectionkey, Location loc) {
        if (configSection == null) {
            setLoc(ymlConfig.getConfigurationSection(configSectionkey), loc);
            return;
        }
        // si ya deja un config section bah ça cherche la key dedans example pos1/player2
        setLoc(configSection.getConfigurationSection(configSectionkey), loc);
    }

    public void setLocDirection(ConfigurationSection configurationSection, Location loc) {
        configurationSection.set("world", loc.getWorld().getName());
        configurationSection.set("x", loc.getX());
        configurationSection.set("y", loc.getY());
        configurationSection.set("z", loc.getZ());
        configurationSection.set("yaw", loc.getYaw());
        configurationSection.set("pitch", loc.getPitch());
        try {
            ymlConfig.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void setLocDirection(String configSectionkey, Location loc) {
        if (configSection == null) {
            setLocDirection(ymlConfig.getConfigurationSection(configSectionkey), loc);
            return;
        }
        // si ya deja un config section bah ça cherche la key dedans example pos1/player2
        setLocDirection(configSection.getConfigurationSection(configSectionkey), loc);
    }

    public void setLoc(Location loc) {
        setLoc(configSection, loc);
    }

    //getter
    public Location getLoc() {
        return getLoc(configSection);
    }

    public Location getLoc(ConfigurationSection configurationSection) {
        World world = Bukkit.getWorld(configurationSection.getString("world"));
        double x = configurationSection.getDouble("x");
        double y = configurationSection.getDouble("y");
        double z = configurationSection.getDouble("z");
        Location loc = new Location(world, x, y, z);
        return loc;
    }

    public Location getLoc(String configSectionkey) {
        if (configSection == null) {
            return getLoc(ymlConfig.getConfigurationSection(configSectionkey));
        }
        // si ya deja un config section bah ça cherche la key dedans example pos1/player2
        return getLoc(configSection.getConfigurationSection(configSectionkey));
    }

    //Avec YAW
    public Location getLocWithDirection() {
        return getLocWithDirection(configSection);
    }

    public Location getLocWithDirection(ConfigurationSection configurationSection) {
        World world = Bukkit.getWorld(configurationSection.getString("world"));
        double x = configurationSection.getDouble("x");
        double y = configurationSection.getDouble("y");
        double z = configurationSection.getDouble("z");
        float yaw = (float) configurationSection.getDouble("yaw");
        float pitch = (float) configurationSection.getDouble("pitch");
        Location loc = new Location(world, x, y, z, yaw, pitch);
        return loc;
    }

    public Location getLocWithDirection(String configSectionkey) {
        if (configSection == null) {
            return getLocWithDirection(ymlConfig.getConfigurationSection(configSectionkey));
        }
        // si ya deja un config section bah ça cherche la key dedans example pos1/player2
        return getLocWithDirection(configSection.getConfigurationSection(configSectionkey));
    }

    //Working With Gui

    /**
     * Getting hashmap of the gui generated from yaml
     *
     * @param page page name
     * @return Hashmap with slot and guiObjects
     * @Author : ZeyKra_
     */
    public HashMap<Integer, YamlInventoryObject> readGui(String page) {
        HashMap<Integer, YamlInventoryObject> objectsHashmap = new HashMap<>();
        if (!ymlConfig.isConfigurationSection(page)) {
            //System.out.println(ymlConfig + " " + page);
            return objectsHashmap;
        }
        ConfigurationSection pageYmlConfig = ymlConfig.getConfigurationSection(page);
        for (int i = 0; i < 53; i++) {
            if (pageYmlConfig.isSet("" + i)) {
                objectsHashmap.put(i, readSlot(page, "" + i));
                //System.out.println(readSlot(page, ""  + i).toString());
            }
        }
        return objectsHashmap;
    }

    public HashMap<Integer, YamlInventoryObject> readInventory() {
        HashMap<Integer, YamlInventoryObject> objectsHashmap = new HashMap<>();
        for (int i = 0; i < 53; i++) {
            if (ymlConfig.isSet("" + i)) {
                objectsHashmap.put(i, readSlot("" + i));
                //System.out.println(readSlot(""  + i).toString());
            }
        }
        return objectsHashmap;
    }

    /**
     * Initializing Yaml Object
     *
     * @param page page name
     * @param slot number of the slot
     * @Author : ZeyKra_
     */
    public YamlInventoryObject readSlot(String page, String slot) {;
        ConfigurationSection objectSection = ymlConfig.getConfigurationSection(page).getConfigurationSection(slot);
        YamlInventoryObject ymlGuiObject = new YamlInventoryObject(
                Integer.parseInt(slot),
                objectSection.getString("item"),
                objectSection.getString("name"),
                objectSection.getString("lore"),
                objectSection.getString("action")
        );
        if (objectSection.isSet("byte")) {
            ymlGuiObject.setItemByte(objectSection.getInt("byte"));
        }
        if (objectSection.isSet("texture")) {
            ymlGuiObject.setTexture(objectSection.getString("texture"));
        }
        if (objectSection.isSet("unbreakable")) {
            ymlGuiObject.setUnbreakable(objectSection.getBoolean("unbreakable"));
        }
        return ymlGuiObject;
    }

    public YamlInventoryObject readSlot(String slot) {
        ConfigurationSection objectSection = ymlConfig.getConfigurationSection(slot);
        YamlInventoryObject ymlGuiObject = new YamlInventoryObject(
                Integer.parseInt(slot),
                objectSection.getString("item"),
                objectSection.getString("name"),
                objectSection.getString("lore"),
                objectSection.getString("action")
        );
        if (objectSection.isSet("byte")) {
            ymlGuiObject.setItemByte(objectSection.getInt("byte"));
        }
        if (objectSection.isSet("texture")) {
            ymlGuiObject.setTexture(objectSection.getString("texture"));
        }
        if (objectSection.isSet("unbreakable")) {
            ymlGuiObject.setUnbreakable(objectSection.getBoolean("unbreakable"));
        }
        return ymlGuiObject;
    }
}

