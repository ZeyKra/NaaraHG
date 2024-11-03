package fr.zeykra.naaraHG.models;

import fr.mrmicky.fastboard.FastBoard;
import org.bukkit.entity.Player;

import java.util.UUID;

public class HGPlayer {

    private int kills;
    private int killstreak;
    private UUID CurrentHGGameUUID;
    private FastBoard board;
    private Player player;

    public HGPlayer(Player player) {
        this.kills = 0;
        this.killstreak = 0;
        this.CurrentHGGameUUID = null;
        this.player = player;
        this.board = new FastBoard(player);
    }

    public void setCurrentHGGameUUID(UUID currentHGGameUUID) {
        this.CurrentHGGameUUID = currentHGGameUUID;
    }

    public UUID getCurrentHGGameUUID() {
        return CurrentHGGameUUID;
    }

    public Player getPlayer() {
        return player;
    }


}
