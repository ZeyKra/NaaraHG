package fr.zeykra.naaraHG.models;

import fr.zeykra.naaraHG.NaaraHG;
import fr.zeykra.naaraHG.enums.Gamestate;
import fr.zeykra.naaraHG.enums.HGScoreboardType;
import fr.zeykra.naaraHG.managers.HGPlayerManger;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.time.LocalDateTime;
import java.util.*;

public class HGGame {

    /**
     * Une map pour identifier les joueurs HGPlayer par leur UUID.
     */
    public Map<UUID, HGPlayer> players;
    public Gamestate gamestate;

    private LocalDateTime startedAt;
    private BukkitTask gameRunnable;
    private UUID gameUUID;
    private int maxPlayers = 12;

    /**
     * Initializing HGGame Object
     *
     * @Author : ssgadryan
     */
    public HGGame() {
        this.gamestate = Gamestate.Initializing;
        this.players = new HashMap<UUID, HGPlayer>();
        this.gameUUID = UUID.randomUUID();
    }

    public void startWait() {
        this.gamestate = Gamestate.WAITING;
        this.gameRunnable = new BukkitRunnable() {
            @Override
            public void run() {
                if (getRemainingPlayers().size() >= 2) {
                    handleGameStart();
                    this.cancel();
                }
            }
        }.runTaskAsynchronously(NaaraHG.INSTANCE);
    }

    public void handleGameStart() {
        this.gamestate = Gamestate.STARTING;
        this.startedAt = LocalDateTime.now();

        start();
    }

    /**
     * Function to start the game
     *
     * @Author : ssgadryan
     */
    private void start() {
        this.gameRunnable = new BukkitRunnable() {
            @Override
            public void run() {
                if (getRemainingPlayers().size() <= 1) {
                    gamestate = Gamestate.FINISHED;
                    handleGameEnd();

                }
            }
        }.runTaskAsynchronously(NaaraHG.INSTANCE);
    }

    /**
     * Function to add player to the game
     *
     * @param player player
     *
     * @Author : ssgadryan
     */
    public void addPlayer(Player player) {
        HGPlayer hgPlayer = HGPlayerManger.getHGPlayer(player);
        hgPlayer.setCurrentHGGameUUID(this.gameUUID);
        this.players.put(player.getUniqueId(), hgPlayer);
        // Va automatiquement mettre à jour le tableau de bord pour le joueur qui rejoin
        // en meme temps que celui des autres
        updateScoreboard(HGScoreboardType.WAITING_SCOREBOARD);
    }

    /**
     * Fonction pour retirer un joueur du la partie a partir de Player
     *
     * @param player player
     *
     * @Author : ssgadryan
     */
    public void removePlayer(Player player) {
        this.players.remove(player.getUniqueId());
        updateScoreboard(HGScoreboardType.WAITING_SCOREBOARD);
    }

    public HashSet<HGPlayer> getPlayers() {
        return new HashSet<>(this.players.values());
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

    /**
     * Function to broadcast message to all players in the game
     *
     * @param message
     *
     * @Author : ssgadryan
     */
    public void broadcastMessage(String message) {
        if (getPlayers().isEmpty()) return;
        getPlayers().forEach(player -> player.getPlayer().sendMessage(message));
    }

    public int getMaxPlayers() {
        return maxPlayers;
    }

    /**
     * Function to handle game end
     *
     * @Author : ssgadryan
     */
    public void handleGameEnd() {
        // TODO : Handle game end
    }

    public Gamestate getGamestate() {
        return gamestate;
    }

    /**
     * fonction afin d'avoir un nom plus court de l'uuid de la partie
     *
     * @return String
     *
     * @Author : ssgadryan
     */
    public String getGameShortName() {
        return this.getGameUUID().toString().substring(0, 5);
    }

    public void updateScoreboard(HGScoreboardType type) {
        if (getPlayers().isEmpty()) return;
        getPlayers().forEach(player -> HGScoreboard.setProperScoreboard(player, type));
    }

}
