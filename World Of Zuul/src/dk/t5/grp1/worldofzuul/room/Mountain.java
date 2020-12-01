package dk.t5.grp1.worldofzuul.room;

import dk.t5.grp1.worldofzuul.item.Sun;
import dk.t5.grp1.worldofzuul.npc.Goat;

public class Mountain extends Room{
    public Mountain(String description, String name) {
        super(description, name, new Sun(), new Goat(40, 300), "/textures/rooms/mountain.png");
    }
}
