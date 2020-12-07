package dk.t5.grp1.worldofzuul.room.tile;

import dk.t5.grp1.worldofzuul.graphics.Screen;
import dk.t5.grp1.worldofzuul.graphics.Sprite;

public class FlowerTile2 extends Tile {
    public FlowerTile2(Sprite sprite) {
        super(sprite);
    }
    @Override
    public void render(int x, int y, Screen screen) {
        screen.renderTile(x << 6, y << 6, this);
    }
}
