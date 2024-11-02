package fr.zeykra.naaraHG.commands.hungergame.subcommands;

import fr.zeykra.naaraHG.commands.SubCommand;
import fr.zeykra.naaraHG.managers.HGManager;
import fr.zeykra.naaraHG.models.HGGame;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SubCommandCreate extends SubCommand {

    @Override
    public String getName() {
        return "Create";
    }

    @Override
    public String getDescription() {
        return "Command pour créer une partie";
    }

    @Override
    public String getSyntax() {
        return "/hungergame create";
    }

    @Override
    public Boolean isOnlyPlayerCommand() {
        return false;
    }

    @Override
    public void perform(CommandSender sender, String[] args) {
        Player player = (Player) sender;
        player.sendMessage("§aCréation de la partie en cours...");

        HGGame game = HGManager.createGame();
        player.sendMessage("§aPartie créée avec succès ! : " + game.getGameUUID());
    }
}
