package fr.zeykra.naaraHG.models;

import fr.zeykra.naaraHG.NaaraHG;
import fr.zeykra.naaraHG.enums.Gamestate;
import org.bukkit.entity.Player;

import java.util.*;

public class HGGame {

    public Map<UUID, HGPlayer> players;
    public Gamestate gamestate;

    private UUID gameUUID;

    /**
     * Initializing HGGame Object
     *
     * @Author : ssgadryan
     */
    public HGGame() {
        this.gamestate = Gamestate.Initializing;
        this.gameUUID = UUID.randomUUID();
    }

    public void start() {
        this.gamestate = Gamestate.STARTING;
    }

    private HashSet<HGPlayer> getRemainingPlayers() {
        return new HashSet<>(this.players.values());
    }


    public UUID getGameUUID() {
        return gameUUID;
    }

    /**
     * Function to handle player loss
     *
     * @param uuid uuid
     *
     * @Author : ssgadryan
     */
    public void handePlayerLoss(UUID uuid) {
        Player player = NaaraHG.INSTANCE.getServer().getPlayer(uuid);

        if (getRemainingPlayers().size() <= 1) {
            this.gamestate = Gamestate.FINISHED;
            handleGameEnd();
        }

    }

    public void handleGameEnd() {
        // TODO : Handle game end
    }


}
