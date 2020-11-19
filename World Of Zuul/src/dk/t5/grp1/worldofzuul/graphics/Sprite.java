package dk.t5.grp1.worldofzuul.graphics;

public class Sprite {

    public final int SIZE;
    private int x, y;
    private int[] pixels;

    public static final Sprite grass = new Sprite(64, 0, 0, SpriteSheet.tiles);
    public static final Sprite treestump = new Sprite(64, 1, 0, SpriteSheet.tiles);
    public static final Sprite voidSprite = new Sprite(64, 0xFF00FF);

    private SpriteSheet sheet;

    private Sprite(int size, int x, int y, SpriteSheet sheet) {
        this.SIZE = size;
        pixels = new int[SIZE * SIZE];
        this.x = x * size;
        this.y = y * size;
        this.sheet = sheet;
        load();
    }

    private Sprite(int size, int colour) {
        this.SIZE = size;
        pixels = new int[SIZE * SIZE];
        setColour(colour);
    }

    private void setColour(int colour) {
        for (int i = 0; i < pixels.length; i++) {
            pixels[i] = colour;
        }
    }

    private void load() {
        for (int y = 0; y < SIZE; y++) {
            for (int x = 0; x < SIZE; x++) {
                pixels[x + y * SIZE] = sheet.getPixels()[(x + this.x) + (y + this.y) * sheet.getSIZE()];
            }
        }
    }

    public int[] getPixels() {
        return pixels;
    }
}
