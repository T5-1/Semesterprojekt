package dk.t5.grp1.worldofzuul.graphics;

import dk.t5.grp1.worldofzuul.room.tile.Tile;

public class Screen {
    private int width;
    private int height;

    private int[] pixels;

    public Screen(int width, int height) {
        this.width = width;
        this.height = height;
        pixels = new int[width * height];
    }

    public void clear() {
        for (int i = 0; i < pixels.length; i++) {
            pixels[i] = 0xff000000;
        }
    }

    public void renderTile(int xp, int yp, Tile tile) {
        for (int y = 0; y < tile.sprite.SIZE; y++) {
            int ya = y + yp;
            for (int x = 0; x < tile.sprite.SIZE; x++) {
                int xa = x + xp;
                pixels[xa + ya * width] = tile.sprite.getPixels()[x + y * tile.sprite.SIZE];
            }
        }
    }

    public void renderMob(int xp, int yp, Sprite sprite) {
        for (int y = 0; y < sprite.SIZE; y++) {
            for (int x = 0; x < sprite.SIZE; x++) {
                if (yp - 16 < 0 || yp + 16 > height || xp - 16 < 0 || xp + 16 > width) break;
                if (sprite.getPixels()[x + y * sprite.SIZE] != 0xffff00ff) {
                    pixels[(x + xp - sprite.SIZE / 2) + (y + yp - sprite.SIZE / 2) * width] = sprite.getPixels()[x + y * sprite.SIZE];
                }
            }
        }
    }

    public int[] getPixels() {
        return pixels;
    }
}
