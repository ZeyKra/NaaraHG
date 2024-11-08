package fr.zeykra.naaraHG.listeners;

import fr.zeykra.naaraHG.managers.HGManager;
import fr.zeykra.naaraHG.managers.HGPlayerManger;
import fr.zeykra.naaraHG.models.HGGame;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerJoinListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        event.setJoinMessage("§a" + event.getPlayer().getName() + " a rejoint le serveur !");
        HGPlayerManger.createHGPLayer(event.getPlayer());
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        event.setQuitMessage("§c" + event.getPlayer().getName() + " a quitté le serveur !");

        if(HGPlayerManger.getHGPlayer(event.getPlayer()).getCurrentHGGameUUID() == null) return;
        HGGame game = HGManager.getHGGameByUUID(HGPlayerManger.getHGPlayer(event.getPlayer()).getCurrentHGGameUUID());
        game.removePlayer(event.getPlayer());

        HGPlayerManger.deleteHGPLayer(event.getPlayer());
    }

}
