package com.t5.worldofzuul.npc;

public abstract class NPC {
    private String name;
    private String info;
    private boolean interacted = false;

    public NPC(String name, String info) {
        this.name = name;
        this.info = info;
    }

    public String getName() {
        return name;
    }

    public String getInfo() {
        return info;
    }

    public boolean isInteracted() {
        return interacted;
    }

    public void setInteracted(boolean interacted) {
        this.interacted = interacted;
    }
}
