package fr.zeykra.naaraHG.utils;

import org.bukkit.entity.Player;

import java.util.UUID;

public class MessageUtil {

    /**
     * Create a JsonMessage to display the list of games
     *
     * @param uuid uuid
     *
     * @return JsonMessage
     *
     * @Author : ssgadryan
     **/
    public static void sendGameListEntryMessage(UUID uuid, Player player, String hoverMessage, String clickCommand) {
        JsonMessage message = new JsonMessage();
        message.append("§a" + uuid.toString())
                .setHoverAsTooltip(hoverMessage)
                .setClickAsSuggestCmd(clickCommand)
                .save();
        message.send(player);
    }

}
