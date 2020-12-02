package dk.t5.grp1.worldofzuul.room;

import dk.t5.grp1.worldofzuul.Game;
import dk.t5.grp1.worldofzuul.assets.CaveEntity;
import dk.t5.grp1.worldofzuul.item.Seed;
import dk.t5.grp1.worldofzuul.npc.Bat;

import java.util.ArrayList;
import java.util.List;

public class Cave extends Room {

    public Cave(String description, String name) {
        super(description, name, new Seed(), new ArrayList<>(), new Bat(1000, 350), "/textures/rooms/cave.png");

        super.assets.add(new CaveEntity(Game.width-270, 256));
    }
}
