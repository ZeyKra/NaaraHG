package fr.zeykra.naaraHG;

import fr.zeykra.naaraHG.commands.hungergame.CommandHungerGame;
import fr.zeykra.naaraHG.enums.Yaml;
import fr.zeykra.naaraHG.listeners.PlayerJoinListener;
import fr.zeykra.naaraHG.managers.HGBorderManager;
import fr.zeykra.naaraHG.managers.HGChestManager;
import fr.zeykra.naaraHG.managers.HGPlayerManger;
import fr.zeykra.naaraHG.managers.HGWorldManager;
import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;

public final class NaaraHG extends JavaPlugin {

    public static NaaraHG INSTANCE;

    @Override
    public void onEnable() {
        INSTANCE = this;
        Yaml.CONFIG.create(getLogger());

        getServer().broadcastMessage("§aLe plugin NaaraHG a bien été activé !");

        //Chargement des listeners
        getServer().getPluginManager().registerEvents(new PlayerJoinListener(), this);

        //Chargement des commandes
        getCommand("hungergame").setExecutor(new CommandHungerGame());

        HGPlayerManger.regenerateData();
        HGWorldManager.generateWorldList();
        HGChestManager.regenerateChestContentList();
        HGBorderManager.generateBorderSetting();

        System.out.println("DEBUG: " + Bukkit.getWorlds().toString());

    }


    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
