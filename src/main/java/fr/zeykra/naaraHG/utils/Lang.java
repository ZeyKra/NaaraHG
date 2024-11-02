package fr.zeykra.naaraHG.utils;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class Lang {

    public static String format(String txt) {
        return ChatColor.translateAlternateColorCodes('&', txt);
    }

    public static String format(String txt, Player player) {
        return ChatColor.translateAlternateColorCodes('&', txt).replace("{PLAYER}", player.getName());
    }

    public static String format(String txt, Double time) {
        time = (double) Math.round(time);
        return ChatColor.translateAlternateColorCodes('&', txt).replace("{TIME}", time.toString());
    }

    public static String format(String txt, int number) {
        return ChatColor.translateAlternateColorCodes('&', txt).replace("{NUMBER}", Integer.toString(number));
    }

    public static String format(String txt, Location location) {
        String res = "x:" + location.getX() +
                " y:" + location.getY() +
                " z:" + location.getZ();
        return ChatColor.translateAlternateColorCodes('&', txt).replace("{LOCATION}", res);
    }

    public static String customFormat(String txt, String var, String replace) {
        return txt.replace(var, replace);
    }

}