package fr.zeykra.naaraHG.commands.hungergame.subcommands;

import fr.zeykra.naaraHG.commands.SubCommand;
import fr.zeykra.naaraHG.managers.HGManager;
import fr.zeykra.naaraHG.utils.JsonMessage;
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
            sendGameListEntryMessage(uuid, player);
        });
        player.sendMessage("§f---------------------------");
    }


    /**
     * Create a JsonMessage to display the list of games
     *
     * @param uuid uuid
     *
     * @return JsonMessage
     *
     * @Author : ssgadryan
     *
    private JsonMessage createGameListMessage(UUID uuid) {

        TextComponent message = new TextComponent("text");
        message.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://www.spigotmc.org"));
        message.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("hi im owner").create()));

        return message;

    } */

    private void sendGameListEntryMessage(UUID uuid, Player player) {
        JsonMessage message = new JsonMessage();
        message.append("§a" + uuid.toString())
                .setClickAsSuggestCmd("§f/hungergame manage " + uuid.toString())
                .setClickAsSuggestCmd("/hungergame manage " + uuid.toString())
                .save();
        message.send(player);
    }


}
