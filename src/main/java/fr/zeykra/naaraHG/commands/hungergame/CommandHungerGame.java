package fr.zeykra.naaraHG.commands.hungergame;

import fr.zeykra.naaraHG.commands.SubCommand;
import fr.zeykra.naaraHG.commands.hungergame.subcommands.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class CommandHungerGame implements CommandExecutor  {

    //Class permettant de gerer les sous-commands/ arguments de celle si

    private ArrayList<SubCommand> subCommands = new ArrayList<>();

    public CommandHungerGame() {
        subCommands.add(new SubCommandDebug());
        subCommands.add(new SubCommandCreate());
        subCommands.add(new SubCommandList());
        subCommands.add(new SubCommandManage());
        subCommands.add(new SubCommandStart());
        subCommands.add(new SubCommandJoin());
        subCommands.add(new SubCommandAddChest());
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player){
            Player p = (Player) sender;

            if (args.length > 0){
                for (int i = 0; i < getSubcommands().size(); i++){
                    if (args[0].equalsIgnoreCase(getSubcommands().get(i).getName())){
                        getSubcommands().get(i).perform(p, args);
                    }
                }
            }else if(args.length == 0){
                p.sendMessage("--------------------------------");
                for (int i = 0; i < getSubcommands().size(); i++){
                    p.sendMessage(getSubcommands().get(i).getSyntax() + " - " + getSubcommands().get(i).getDescription());
                }
                p.sendMessage("--------------------------------");
            }

        }


        return true;
    }

    public ArrayList<SubCommand> getSubcommands(){
        return subCommands;
    }


    public ArrayList<SubCommand> getSubCommands() { return subCommands; }

}