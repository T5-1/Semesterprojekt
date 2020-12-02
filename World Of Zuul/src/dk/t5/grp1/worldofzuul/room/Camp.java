package dk.t5.grp1.worldofzuul.room;

import dk.t5.grp1.worldofzuul.item.Seed;
import dk.t5.grp1.worldofzuul.npc.Treehugger;

public class Camp extends Room{

    public Camp(String description, String name) {
        super(description, name, new Seed(), new Treehugger(1340, 580), "/textures/rooms/camp.png");

    }
}
