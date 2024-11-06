package fr.zeykra.naaraHG.commands.hungergame.subcommands;

import fr.zeykra.naaraHG.commands.SubCommand;
import fr.zeykra.naaraHG.managers.HGChestManager;
import fr.zeykra.naaraHG.managers.HGManager;
import fr.zeykra.naaraHG.managers.HGPlayerManger;
import fr.zeykra.naaraHG.managers.HGWorldManager;
import fr.zeykra.naaraHG.models.HGGame;
import fr.zeykra.naaraHG.models.HGPlayer;
import fr.zeykra.naaraHG.models.HGWorld;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SubCommandDebug extends SubCommand {

    @Override
    public String getName() {
        return "Debug";
    }

    @Override
    public String getDescription() {
        return "Command pour debug";
    }

    @Override
    public String getSyntax() {
        return "/hungergame debug";
    }

    @Override
    public Boolean isOnlyPlayerCommand() {
        return false;
    }

    @Override
    public void perform(CommandSender sender, String[] args) {
        Player player = (Player) sender;
        player.sendMessage("§aDebug hehe");

        //System.out.println(args[1]);
        //HGChestManager.randomFillChest(args[1]);
        HGGame game = HGManager.getHGGameByUUID(HGPlayerManger.getGameUUIDByPlayer(player));
        HGWorld gameWorld = HGWorldManager.getHGWorldByName(game.getWorldName());
        gameWorld.debugLocations();
        Location loc = gameWorld.reserveRandomAvailableLocation(player.getUniqueId());
        player.teleport(loc);

        gameWorld.debugLocations();
    }
}
