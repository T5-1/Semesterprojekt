package dk.t5.grp1.worldofzuul.room;

import dk.t5.grp1.worldofzuul.item.Seed;
import dk.t5.grp1.worldofzuul.npc.Bat;

public class Cave extends Room {

    public Cave(String description, String name) {
        super(description, name, new Seed(), new Bat(1000, 350), "/textures/rooms/cave.png");

    }
}
