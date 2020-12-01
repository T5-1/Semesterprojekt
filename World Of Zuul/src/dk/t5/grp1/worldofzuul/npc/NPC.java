package dk.t5.grp1.worldofzuul.npc;

import dk.t5.grp1.worldofzuul.graphics.Screen;
import dk.t5.grp1.worldofzuul.graphics.Sprite;

public abstract class NPC {
    private int x, y;

    public final int startInteractionX, startInteractionY, endInteractionX, endInteractionY;

    private String name;
    private String info;
    private boolean interacted = false;

    private Sprite sprite;

    public NPC(String name, String info, int x, int y, Sprite sprite, int startInteractionX, int startInteractionY, int endInteractionX, int endInteractionY) {
        this.name = name;
        this.info = info;
        this.x = x;
        this.y = y;
        this.sprite = sprite;
        this.startInteractionX = startInteractionX;
        this.startInteractionY = startInteractionY;
        this.endInteractionX = endInteractionX;
        this.endInteractionY = endInteractionY;
    }

    public void update() {

    }

    public void render(Screen screen) {
        screen.renderMob(x, y, sprite);
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

    public Sprite getSprite() {
        return sprite;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
