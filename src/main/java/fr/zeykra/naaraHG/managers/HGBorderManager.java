package fr.zeykra.naaraHG.managers;

import fr.zeykra.naaraHG.models.HGGame;
import org.bukkit.Bukkit;

public class HGBorderManager {
    // This is the border size
    private static int borderSize = 1000;

    public static void generateBorderSetting() {
        Bukkit.getServer().getWorlds().forEach(world -> {
            world.getWorldBorder().setCenter(world.getSpawnLocation());
        });
    }

    public static double getBorderSize(String worldName) {
        return Bukkit.getServer().getWorld(worldName).getWorldBorder().getSize();
    }

    public static void setBorderSize(String worldName, int size) {
        Bukkit.getServer().getWorld(worldName).getWorldBorder().setSize(size);
    }

    public static void updateBordersizeAnimated(HGGame game) {
        //System.out.println(game.getWorldName());
        switch ((int) game.getTimeElapsed().toSeconds()) {
            case 10:
                animateBorderSize(game.getWorldName(), 800);
                break;
            case 20:
                animateBorderSize(game.getWorldName(), 600);
                break;
            case 30:
                animateBorderSize(game.getWorldName(), 400);
                break;
        }
    }

    private static void animateBorderSize(String worldName, int size) {
        Bukkit.getServer().getWorld(worldName).getWorldBorder().setSize(size, 60);
    }

}
