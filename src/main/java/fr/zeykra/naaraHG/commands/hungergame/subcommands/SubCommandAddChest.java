package fr.zeykra.naaraHG.commands.hungergame.subcommands;

import fr.zeykra.naaraHG.commands.SubCommand;
import fr.zeykra.naaraHG.managers.HGChestManager;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.block.Chest;

import java.util.HashSet;

public class SubCommandAddChest extends SubCommand {

    @Override
    public String getName() {
        return "AddChest";
    }

    @Override
    public String getDescription() {
        return "Commande pour ajouter un coffre";
    }

    @Override
    public String getSyntax() {
        return "/hungergame addchest";
    }

    @Override
    public Boolean isOnlyPlayerCommand() {
        return true;
    }

    @Override
    public void perform(CommandSender sender, String[] args) {
        Player player = (Player) sender;

        Block targetBlock = player.getTargetBlock((HashSet<Byte>) null, 10);

        if(targetBlock.getType() != Material.CHEST) {;
            player.sendMessage("§cVous devez regarder un coffre pour l'ajouter !");
            return;
        }

        if(targetBlock.getDrops().isEmpty()) {
            player.sendMessage("§cLe coffre est vide !");
            return;
        }

        Chest chest = (Chest) targetBlock.getState();
        ItemStack[] chestContent = chest.getInventory().getContents();
        HGChestManager.addChestContent(chestContent);
        player.sendMessage("§aCoffre ajouté avec succès !");
    }
}
