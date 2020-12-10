package dk.t5.grp1.worldofzuul.room;

import dk.t5.grp1.worldofzuul.npc.Deforester;

public class NorthernEntrance extends Room{

    public NorthernEntrance(String description, String name) {
        super(description, name, new Deforester(800, 475), "/textures/rooms/northern.png");
        setAccessible(false);
    }
}
