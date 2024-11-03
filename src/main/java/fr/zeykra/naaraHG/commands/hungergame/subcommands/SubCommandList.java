package fr.zeykra.naaraHG.commands.hungergame.subcommands;

import fr.zeykra.naaraHG.commands.SubCommand;
import fr.zeykra.naaraHG.managers.HGManager;
import fr.zeykra.naaraHG.utils.JsonMessage;
import fr.zeykra.naaraHG.utils.MessageUtil;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

public class SubCommandList extends SubCommand {
    @Override
    public String getName() {
        return "List";
    }

    @Override
    public String getDescription() {
        return "Command pour lister les parties";
    }

    @Override
    public String getSyntax() {
        return "/hungergame list";
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

        player.sendMessage("§fListe des parties en cours :");
        player.sendMessage("§f---------------------------");
        HGManager.getGames().forEach((uuid, hgGame) -> {
            MessageUtil.sendGameListEntryMessage(uuid, player, "§f/hungergame manage " + uuid.toString(), "/hungergame manage " + uuid.toString());
        });
        player.sendMessage("§f---------------------------");
    }




}
