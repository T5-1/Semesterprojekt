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
                if (yp - sprite.SIZE / 2 < 0 || yp + sprite.SIZE / 2 > height || xp - sprite.SIZE / 2 < 0 || xp + sprite.SIZE / 2 > width) break;
                if (sprite.getPixels()[x + y * sprite.SIZE] != 0xffff00ff) {
                    pixels[(x + xp - sprite.SIZE / 2) + (y + yp - sprite.SIZE / 2) * width] = sprite.getPixels()[x + y * sprite.SIZE];
                }
            }
        }
    }

    public void renderBox(int x0, int y0, int x1, int y1) {
        for (int y = y0; y < y1; y++) {
            for (int x = x0; x < x1; x++) {
                int r = (pixels[x + y * width] & 0xFF0000) >> 16;
                int g = (pixels[x + y * width] & 0xFF00) >> 8;
                int b = (pixels[x + y * width] & 0xFF);

                r *= 0.25;
                g *= 0.25;
                b *= 0.25;

                pixels[x + y * width] = 0xff000000 + (r << 16) + (g << 8) + b;
            }
        }
    }

    public void renderHollowBox(int x0, int y0, int x1, int y1) {
        for (int y = y0 - 2; y < y0; y++) {
            for (int x = x0; x < x1; x++) {
                pixels[x + y * width] = 0xffffffff;
            }
        }

        for (int y = y1; y < y1 + 2; y++) {
            for (int x = x0; x < x1; x++) {
                pixels[x + y * width] = 0xffffffff;
            }
        }

        for (int y = y0; y < y1; y++) {
            for (int x = x0 - 2; x < x0; x++) {
                pixels[x + y * width] = 0xffffffff;
            }
        }

        for (int y = y0; y < y1; y++) {
            for (int x = x1; x < x1 + 2; x++) {
                pixels[x + y * width] = 0xffffffff;
            }
        }
    }

    public int[] getPixels() {
        return pixels;
    }
}
