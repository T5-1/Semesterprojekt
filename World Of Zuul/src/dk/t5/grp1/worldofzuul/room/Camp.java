package dk.t5.grp1.worldofzuul.room;

import dk.t5.grp1.worldofzuul.assets.Assets;
import dk.t5.grp1.worldofzuul.assets.Tent1;
import dk.t5.grp1.worldofzuul.assets.Tent2;
import dk.t5.grp1.worldofzuul.item.Seed;
import dk.t5.grp1.worldofzuul.npc.Treehugger;

import java.util.ArrayList;
import java.util.List;

public class Camp extends Room{

    public Camp(String description, String name) {
        super(description, name, new Seed(), new ArrayList<>(), new Treehugger(1340, 580),"/textures/rooms/camp.png");

        super.assets.add(new Tent1(600, 780));
        super.assets.add(new Tent2(400, 780));
    }
}
