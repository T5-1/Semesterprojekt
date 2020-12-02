package dk.t5.grp1.worldofzuul.room;

import dk.t5.grp1.worldofzuul.item.Sun;
import dk.t5.grp1.worldofzuul.npc.Cactus;

public class Desert extends Room{

    public Desert(String description, String name) {
        super(description, name, new Sun(), new Cactus(1150, 670), "/textures/rooms/desert.png");
    }
}
