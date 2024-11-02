package fr.zeykra.naaraHG.commands;

import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public abstract class SubCommand {

    //Le nom de la commande ex: /cmd <subcommand> <- ceci
    public abstract String getName();

    // Description de la commande
    public abstract String getDescription();

    // Son utilisation
    public abstract String getSyntax();

    public abstract Boolean isOnlyPlayerCommand();

    // le code de la commande
    public abstract void perform(CommandSender sender, String args[]);
    /*
     * ARGS FORMAT
     * /command subcommand args1 args2 -> args...
     * /command   args0    args1 args2 -> args...
     * */

}
