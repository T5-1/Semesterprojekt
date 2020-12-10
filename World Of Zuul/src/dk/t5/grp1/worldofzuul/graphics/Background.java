package dk.t5.grp1.worldofzuul.graphics;

import dk.t5.grp1.worldofzuul.Game;
import javafx.scene.image.Image;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Background {

    private String path;
    private BufferedImage image;

    private int[] pixels;

    public static final Background die = new Background("/textures/backgrounds/LoseScreenText.png");
    public static final Background win = new Background("/textures/backgrounds/WinScreenText.png");
    public static final Background start = new Background("/textures/backgrounds/Startmenu.png");

    public Background(String path) {
        this.path = path;
        pixels = new int[Game.width * Game.height];
        load();
    }

    private void load() {
        try {
            image = ImageIO.read(this.getClass().getResource(path));
            int w = image.getWidth();
            int h = image.getHeight();
            image.getRGB(0, 0, w, h, pixels, 0, Game.width);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int[] getPixels() {
        return pixels;
    }
}
