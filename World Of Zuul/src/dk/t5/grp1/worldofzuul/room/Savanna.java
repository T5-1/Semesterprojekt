package dk.t5.grp1.worldofzuul.room;

import dk.t5.grp1.worldofzuul.item.Seed;
import dk.t5.grp1.worldofzuul.npc.Tucan;

public class Savanna extends Room {
    public Savanna(String description, String name) {
        super(description, name, new Seed(), new Tucan(40, 300), "/textures/rooms/spawn.png");
    }

}
