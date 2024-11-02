package fr.zeykra.naaraHG.models;

import java.util.UUID;

public class HGPlayer {

    private int kills;
    private int killstreak;
    private UUID HGGameUUID;

    HGPlayer(UUID HGGameUUID) {
        this.kills = 0;
        this.killstreak = 0;
        this.HGGameUUID = HGGameUUID;
    }

    public void setHGGameUUID(UUID HGGameUUID) {
        this.HGGameUUID = HGGameUUID;
    }

    public UUID getHGGameUUID() {
        return HGGameUUID;
    }


}
