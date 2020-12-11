package dk.t5.grp1.worldofzuul.room;

import dk.t5.grp1.worldofzuul.item.Seed;
import dk.t5.grp1.worldofzuul.npc.Tucan;

public class Savanna extends Room {

    public Savanna(String name) {
        super(name, new Seed(900, 430), new Tucan(400, 430), "/textures/rooms/savanna.png");
    }
}
