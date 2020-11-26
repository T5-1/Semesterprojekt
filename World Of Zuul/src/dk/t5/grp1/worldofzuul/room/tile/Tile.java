package dk.t5.grp1.worldofzuul.room.tile;

import dk.t5.grp1.worldofzuul.graphics.Screen;
import dk.t5.grp1.worldofzuul.graphics.Sprite;

public class Tile {

    private int x, y;
    public final Sprite sprite;

    public static final Tile grass = new GrassTile(Sprite.grass);
    public static final Tile treestump = new TreestumpTile(Sprite.treestump);
    public static final Tile voidTile = new VoidTile(Sprite.voidSprite);

    public Tile(Sprite sprite) {
        this.sprite = sprite;
    }

    public void render(int x, int y, Screen screen) {

    }

    public boolean solid() {
        return false;
    }
}
