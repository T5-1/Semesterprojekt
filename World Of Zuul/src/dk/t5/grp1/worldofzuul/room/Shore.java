package dk.t5.grp1.worldofzuul.room;

import dk.t5.grp1.worldofzuul.item.Water;
import dk.t5.grp1.worldofzuul.npc.Fish;

public class Shore extends Room {
    public Shore(String description, String name) {
        super(description, name, new Water(), new Fish(180, 500), "/textures/rooms/shore.png");

    }

}
