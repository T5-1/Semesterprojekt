package dk.t5.grp1.worldofzuul.room;

import dk.t5.grp1.worldofzuul.npc.Tree;

public class Spawn extends Room {

    public Spawn(String description, String name) {

        super(description, name, new Tree(40, 300), "/textures/rooms/spawn.png");
    }
}
