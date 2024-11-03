package fr.zeykra.naaraHG.commands.hungergame.subcommands;

import fr.zeykra.naaraHG.commands.SubCommand;
import fr.zeykra.naaraHG.managers.HGManager;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import net.md_5.bungee.api.chat.TextComponent;

import java.util.UUID;

public class SubCommandManage extends SubCommand {
    @Override
    public String getName() {
        return "Manage";
    }

    @Override
    public String getDescription() {
        return "Command pour gérer une partie";
    }

    @Override
    public String getSyntax() {
        return "/hungergame manage";
    }

    @Override
    public Boolean isOnlyPlayerCommand() {
        return false;
    }

    @Override
    public void perform(CommandSender sender, String[] args) {
        Player player = (Player) sender;
        UUID gameUUID = UUID.fromString(args[1]);
        player.sendMessage("§fOuais manage la partie : §e" + gameUUID);
    }

}
