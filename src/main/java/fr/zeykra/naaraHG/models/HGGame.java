package fr.zeykra.naaraHG.models;

import fr.zeykra.naaraHG.enums.Gamestate;

import java.util.Map;
import java.util.UUID;

public class HGGame {

    public Map<UUID, HGPlayer> players;
    public Gamestate gamestate;

    public HGGame() {
        this.gamestate = Gamestate.Initializing;
    }

}
