package dk.t5.grp1.worldofzuul.room;

import dk.t5.grp1.worldofzuul.Game;
import dk.t5.grp1.worldofzuul.npc.Tree;

public class Spawn extends Room {

    public Spawn(String description, String name) {

        super(description, name, new Tree(Game.width / 2, Game.height / 2 - 150), "/textures/rooms/spawn.png");
    }
}
