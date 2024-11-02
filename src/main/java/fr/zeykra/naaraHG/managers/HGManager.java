package fr.zeykra.naaraHG.managers;

import fr.zeykra.naaraHG.models.HGGame;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class HGManager {

    private static Map<UUID, HGGame> games = new HashMap<>();

    public static Map<UUID, HGGame> getGames() {
        return games;
    }

    public static void addGame(UUID uuid, HGGame game) {
        games.put(uuid, game);
    }

    public static HGGame getHGGameByUUID(UUID uuid) {
        return games.get(uuid);
    }

    public static HGGame createGame() {
        HGGame game = new HGGame();
        addGame(game.getGameUUID(), game);

        return game;
    }
}
