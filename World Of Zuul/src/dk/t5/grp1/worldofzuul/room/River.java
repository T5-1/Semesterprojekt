package dk.t5.grp1.worldofzuul.room;

import dk.t5.grp1.worldofzuul.item.Water;
import dk.t5.grp1.worldofzuul.npc.Troll;

public class River extends Room{
    public River(String description, String name) {
        super(description, name, new Water(), new Troll(880, 200), "/textures/rooms/river.png");
    }
}
