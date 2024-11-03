package fr.zeykra.naaraHG;

import fr.zeykra.naaraHG.commands.hungergame.CommandHungerGame;
import fr.zeykra.naaraHG.enums.Yaml;
import fr.zeykra.naaraHG.listeners.PlayerJoinListener;
import org.bukkit.plugin.java.JavaPlugin;

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
    }


    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
