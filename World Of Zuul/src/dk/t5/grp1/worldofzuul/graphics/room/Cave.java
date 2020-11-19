package dk.t5.grp1.worldofzuul.graphics.room;

import dk.t5.grp1.worldofzuul.item.Seed;
import dk.t5.grp1.worldofzuul.npc.Bat;

public class Cave extends Room {

    public Cave(String description, String name) {
        super(description, name, new Seed(), new Bat(), "/textures/rooms/spawn.png");

    }
}
