package fr.zeykra.naaraHG.enums;

public enum Gamestate {

    Initializing("Initializing"),
    WAITING("Waiting"),
    STARTING("Starting"),
    INGAME("Ingame"),
    FINISHING("Finishing"),
    FINISHED("Finished");

    private final String value;

    Gamestate(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
