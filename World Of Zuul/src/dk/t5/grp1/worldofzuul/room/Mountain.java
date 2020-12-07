package dk.t5.grp1.worldofzuul.room;

import dk.t5.grp1.worldofzuul.item.Sun;
import dk.t5.grp1.worldofzuul.npc.Goat;

public class Mountain extends Room{
    public Mountain(String description, String name) {
        super(description, name, new Sun(1300, 650), new Goat(1300, 450), "/textures/rooms/mountain.png");
    }
}
