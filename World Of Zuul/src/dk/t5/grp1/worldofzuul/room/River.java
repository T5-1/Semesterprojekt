package dk.t5.grp1.worldofzuul.room;

import dk.t5.grp1.worldofzuul.assets.Assets;
import dk.t5.grp1.worldofzuul.assets.Bridge;
import dk.t5.grp1.worldofzuul.graphics.Sprite;
import dk.t5.grp1.worldofzuul.item.Water;
import dk.t5.grp1.worldofzuul.npc.Troll;

import java.util.ArrayList;

public class River extends Room{
    public River(String description, String name) {
        super(description, name, new Water(880, 400), new ArrayList<>(),new Troll(880, 200), "/textures/rooms/river.png");

        super.assets.add(new Bridge(800, 480));
    }
}
