package fr.zeykra.naaraHG.commands.hungergame.subcommands;

import fr.zeykra.naaraHG.commands.SubCommand;
import fr.zeykra.naaraHG.enums.Gamestate;
import fr.zeykra.naaraHG.managers.HGManager;
import fr.zeykra.naaraHG.models.HGGame;
import fr.zeykra.naaraHG.utils.MessageUtil;
import org.apache.logging.log4j.message.Message;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import javax.swing.*;
import java.util.UUID;

public class SubCommandJoin extends SubCommand {
    @Override
    public String getName() {
        return "Join";
    }

    @Override
    public String getDescription() {
        return "Command pour rejoindre une partie";
    }

    @Override
    public String getSyntax() {
        return "/hungergame join";
    }

    @Override
    public Boolean isOnlyPlayerCommand() {
        return false;
    }

    @Override
    public void perform(CommandSender sender, String[] args) {
        Player player = (Player) sender;

        if(args.length < 2) {
            player.sendMessage("§Liste des parties en cours :");
            player.sendMessage("§f---------------------------");
            HGManager.getGames().forEach((uuid, hgGame) -> {
                MessageUtil.sendGameListEntryMessage(uuid, player, "§f/hungergame join " + uuid.toString(), "/hungergame join " + uuid.toString());
            });
            return;
        }

        UUID uuid = UUID.fromString(args[1]);

        if (!HGManager.doesGameExist(uuid)) {
            player.sendMessage("§cErreur: La partie n'existe pas");
            return;
        }

        if (HGManager.getHGGameByUUID(uuid).getGamestate() != Gamestate.WAITING) {
            player.sendMessage("§cErreur: La partie a déjà commencé");
            return;
        }

        if (HGManager.getHGGameByUUID(uuid).getPlayers().contains(player.getUniqueId())) {
            player.sendMessage("§cErreur: Vous avez déjà rejoint la partie");
            return;
        }

        HGGame game = HGManager.getHGGameByUUID(uuid);
        game.addPlayer(player);
    }
}
