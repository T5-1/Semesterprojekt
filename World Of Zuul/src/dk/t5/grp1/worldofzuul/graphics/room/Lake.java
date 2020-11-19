package dk.t5.grp1.worldofzuul.graphics.room;

import dk.t5.grp1.worldofzuul.item.Water;
import dk.t5.grp1.worldofzuul.npc.Mermaid;

public class Lake extends Room{
    public Lake(String description, String name) {
        super(description, name, new Water(), new Mermaid(), "/textures/rooms/spawn.png");
        setDeadly(true);
    }
}
