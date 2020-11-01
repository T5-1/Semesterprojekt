package com.t5.worldofzuul.npc;

public abstract class NPC {
    private String name;
    private String question;
    private String room;

    public NPC(String name, String question, String room) {
        this.name = name;
        this.question = question;
        this.room = room;
    }


}
