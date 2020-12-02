package dk.t5.grp1.worldofzuul.room;

import dk.t5.grp1.worldofzuul.item.Seed;
import dk.t5.grp1.worldofzuul.npc.Flower;

public class FlowerField extends Room{
    public FlowerField(String description, String name) {
        super(description, name, new Seed(), new Flower(740, 450), "/textures/rooms/flowerfield.png");
    }
}
