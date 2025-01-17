package dk.t5.grp1.worldofzuul.room;

import dk.t5.grp1.worldofzuul.item.Water;
import dk.t5.grp1.worldofzuul.npc.Mermaid;

public class Lake extends Room{
    public Lake(String description, String name) {
        super(description, name, new Water(500, 450), new Mermaid(700, 450), "/textures/rooms/lake.png");
        setDeadly(true);
    }
}
