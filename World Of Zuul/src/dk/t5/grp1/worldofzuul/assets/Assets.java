package dk.t5.grp1.worldofzuul.assets;

import dk.t5.grp1.worldofzuul.graphics.Screen;
import dk.t5.grp1.worldofzuul.graphics.Sprite;

public abstract class Assets {
    private int x, y;
    private Sprite sprite;

    public Assets(int x, int y, Sprite sprite) {
        this.x = x;
        this.y = y;
        this.sprite = sprite;
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

    public void render(Screen screen) {
        screen.renderMob(x, y, sprite);
    }
}
