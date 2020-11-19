package dk.t5.grp1.worldofzuul.graphics.room;

import dk.t5.grp1.worldofzuul.npc.Deforester;

public class NorthernEntrance extends Room{
    public NorthernEntrance(String description, String name) {
        super(description, name, new Deforester(), "/textures/rooms/spawn.png");
        setAccessible(false);
    }
}
