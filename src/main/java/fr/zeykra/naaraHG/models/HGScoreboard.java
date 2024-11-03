package fr.zeykra.naaraHG.models;

import fr.zeykra.naaraHG.enums.HGScoreboardType;

import java.time.Duration;
import java.time.LocalDateTime;

public class HGScoreboard {

    public static void setProperScoreboard(HGPlayer player, HGScoreboardType type) {
        switch (type) {
            case WAITING_SCOREBOARD:
                generateWaitingScoreboard(player);
                break;
            case INGAME_SCOREBOARD:
                generateIngameScoreboard(player);
                break;
            case ENDGAME_SCOREBOARD:
                generateEndgameScoreboard(player);
                break;
        }
    }

    /**
     * Generate endgame scoreboard
     *
     * @param hgPlayer
     */
    private static void generateEndgameScoreboard(HGPlayer hgPlayer) {
        HGGame game = hgPlayer.getCurrentGame();

        hgPlayer.getBoard().updateTitle("§6§lNaaraHG " + game.getGameShortName());
        hgPlayer.getBoard().updateLines(
                "§7Joueurs: §a" + game.getPlayers().size() + "/" + game.getMaxPlayers(),
                "§7Temps: dsdaa",
                "§7Status: " + game.getGamestate().getValue()
        );
    }

    /**
     * Generate ingame scoreboard
     *
     * @param hgPlayer
     */
    private static void generateIngameScoreboard(HGPlayer hgPlayer) {
        HGGame game = hgPlayer.getCurrentGame();
        Duration time = Duration.between(game.getStartedAt(), LocalDateTime.now());

        hgPlayer.getBoard().updateTitle("§6§lNaaraHG " + game.getGameShortName());
        hgPlayer.getBoard().updateLines(
                "§7Joueurs: §a" + game.getPlayers().size() + "/" + game.getMaxPlayers(),
                "§7Temps: " + time.toMinutes() + "m " + time.toSecondsPart() + "s",
                "§7Kill: 0" ,
                "§7Killstreak: 0"
        );
    }

    /**
     * Generate waiting scoreboard
     *
     * @param hgPlayer
     */
    private static void generateWaitingScoreboard(HGPlayer hgPlayer) {
        HGGame game = hgPlayer.getCurrentGame();

        hgPlayer.getBoard().updateTitle("§6§lNaaraHG " + game.getGameShortName());
        hgPlayer.getBoard().updateLines(
                "§7Joueurs: §a" + game.getPlayers().size() + "/" + game.getMaxPlayers(),
                "§7Status: " + game.getGamestate().getValue()
        );
    }
}
