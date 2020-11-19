package dk.t5.grp1.worldofzuul.graphics.room;

import dk.t5.grp1.worldofzuul.item.Water;
import dk.t5.grp1.worldofzuul.npc.Troll;

public class River extends Room{
    public River(String description, String name) {
        super(description, name, new Water(), new Troll(), "/textures/rooms/spawn.png");
    }
}
