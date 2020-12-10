package dk.t5.grp1.worldofzuul.graphics;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class SpriteSheet {

    public final int SPRITE_SIZE;
    public final int SIZE;

    private String path;
    private int[] pixels;

    public static final SpriteSheet tiles = new SpriteSheet("/textures/sheets/tiles.png", 1024, 64);
    public static final SpriteSheet npc = new SpriteSheet("/textures/sheets/npc.png", 1280, 256);
    public static final SpriteSheet player = new SpriteSheet("/textures/sheets/player.png", 384, 64);
    public static final SpriteSheet evolution = new SpriteSheet("/textures/sheets/evolution.png", 320, 64);

    public SpriteSheet(String path, int size, int spriteSize) {
        this.path = path;
        this.SIZE = size;
        this.SPRITE_SIZE = spriteSize;
        pixels = new int[SIZE * SIZE];
        load();
    }

    public void load() {
        try {
            BufferedImage image = ImageIO.read(this.getClass().getResource(path));
            int w = image.getWidth();
            int h = image.getHeight();
            image.getRGB(0, 0, w, h, pixels, 0, w);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int[] getPixels() {
        return pixels;
    }
}
