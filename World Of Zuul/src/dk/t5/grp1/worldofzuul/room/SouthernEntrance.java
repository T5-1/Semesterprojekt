package dk.t5.grp1.worldofzuul.room;

import dk.t5.grp1.worldofzuul.npc.Orangutan;

public class SouthernEntrance extends Room {

    public SouthernEntrance(String description, String name) {
        super(description, name, new Orangutan(800, 500), "/textures/rooms/southern.png");
        setAccessible(false);
    }
}
