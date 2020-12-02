package dk.t5.grp1.worldofzuul.room.tile;

import dk.t5.grp1.worldofzuul.graphics.Screen;
import dk.t5.grp1.worldofzuul.graphics.Sprite;

public class Tile {

    private int x, y;
    public final Sprite sprite;

    public static final Tile grass = new GrassTile(Sprite.grass);
    public static final Tile treestump = new TreestumpTile(Sprite.treestump);
    public static final Tile voidTile = new VoidTile(Sprite.voidSprite);
    public static final Tile flower1 = new FlowerTile1(Sprite.flower1);
    public static final Tile flower2 = new FlowerTile2(Sprite.flower2);
    public static final Tile water = new WaterTile(Sprite.water);
    public static final Tile sand = new SandTile(Sprite.sand);
    public static final Tile burnt = new BurntTile(Sprite.burnt);
    public static final Tile stone = new StoneTile(Sprite.stone);
    public static final Tile water2 = new WaterTile2(Sprite.water2);
    public static final Tile savanna = new SavannaTile(Sprite.savanna);
    public static final Tile burnt2 = new BurntTreeTile(Sprite.burnt2);
    public static final Tile savanna2 = new SavannaTreeTile(Sprite.savanna2);
    public static final Tile cactus = new CactusTile(Sprite.cactus);


    public Tile(Sprite sprite) {
        this.sprite = sprite;
    }

    public void render(int x, int y, Screen screen) {

    }

    public boolean solid() {
        return false;
    }
}
