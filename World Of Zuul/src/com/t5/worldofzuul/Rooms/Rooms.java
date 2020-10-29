package com.t5.worldofzuul.Rooms;

import java.util.HashMap;

public abstract class Rooms {
    private String description;

    //man skal kunne vide hvor exits er
    //HashMap er som en ArrayListe med to values
    private HashMap<String, Rooms> exits;

    public Rooms(String description)
    {
        this.description = description;
        exits = new HashMap<String, Rooms>();
    }
}
