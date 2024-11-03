package fr.zeykra.naaraHG.commands.hungergame.subcommands;

import fr.zeykra.naaraHG.commands.SubCommand;
import fr.zeykra.naaraHG.managers.HGManager;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

public class SubCommandStart extends SubCommand {
    @Override
    public String getName() {
        return "Start";
    }

    @Override
    public String getDescription() {
        return "Command pour démarrer une partie";
    }

    @Override
    public String getSyntax() {
        return "/hungergame start";
    }

    @Override
    public Boolean isOnlyPlayerCommand() {
        return false;
    }

    @Override
    public void perform(CommandSender sender, String[] args) {
        Player player = (Player) sender;
        if(HGManager.getGames().isEmpty()) {
            player.sendMessage("§cErreur: Il n'y a aucune partie en cours");
            return;
        }

        UUID uuid = UUID.fromString(args[1]);

        if (!HGManager.doesGameExist(uuid)) {
            player.sendMessage("§cErreur: La partie n'existe pas");
            return;
        }

        player.sendMessage("§fDémarrage de la partie...");
        HGManager.getHGGameByUUID(uuid).handleGameStart();


    }
}
