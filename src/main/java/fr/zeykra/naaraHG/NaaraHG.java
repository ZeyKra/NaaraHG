package fr.zeykra.naaraHG;

import fr.zeykra.naaraHG.listeners.PlayerJoinListener;
import org.bukkit.plugin.java.JavaPlugin;

public final class NaaraHG extends JavaPlugin {

    public static NaaraHG INSTANCE;

    @Override
    public void onEnable() {
        INSTANCE = this;
        getServer().broadcastMessage("§aLe plugin NaaraHG a bien été activé !");

        getServer().getPluginManager().registerEvents(new PlayerJoinListener(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
