package fr.zeykra.naaraHG.models;

import fr.zeykra.naaraHG.enums.HGScoreboardType;

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

    private static void generateEndgameScoreboard(HGPlayer hgPlayer) {
        HGGame game = hgPlayer.getCurrentGame();

        hgPlayer.getBoard().updateTitle("§6§lNaaraHG " + game.getGameShortName());
        hgPlayer.getBoard().updateLines(
                "§7Joueurs: §a" + game.getPlayers().size() + "/" + game.getMaxPlayers(),
                "§7Temps: dsdaa",
                "§7Status: " + game.getGamestate().getValue()
        );
    }

    private static void generateIngameScoreboard(HGPlayer hgPlayer) {
        HGGame game = hgPlayer.getCurrentGame();

        hgPlayer.getBoard().updateTitle("§6§lNaaraHG " + game.getGameShortName());
        hgPlayer.getBoard().updateLines(
                "§7Joueurs: §a0",
                "§7Kill: 0" ,
                "§7Killstreak: 0"
        );
    }

    private static void generateWaitingScoreboard(HGPlayer hgPlayer) {
        HGGame game = hgPlayer.getCurrentGame();

        hgPlayer.getBoard().updateTitle("§6§lNaaraHG " + game.getGameShortName());
        hgPlayer.getBoard().updateLines(
                "§7Joueurs: §a" + game.getPlayers().size() + "/" + game.getMaxPlayers(),
                "§7Status: " + game.getGamestate().getValue()
        );
    }
}
