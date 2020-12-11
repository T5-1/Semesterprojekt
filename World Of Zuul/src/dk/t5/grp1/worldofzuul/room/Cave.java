package dk.t5.grp1.worldofzuul.room;

import dk.t5.grp1.worldofzuul.Game;
import dk.t5.grp1.worldofzuul.assets.CaveEntity;
import dk.t5.grp1.worldofzuul.item.Seed;
import dk.t5.grp1.worldofzuul.npc.Bat;

import java.util.ArrayList;
import java.util.List;

public class Cave extends Room {

    public Cave(String name) {
        super(name, new Seed(500, 500), new ArrayList<>(), new Bat(1010, 300), "/textures/rooms/cave.png");

        assets.add(new CaveEntity(Game.width-270-270, 256));
    }
}
