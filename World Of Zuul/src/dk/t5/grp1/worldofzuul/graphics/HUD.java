package dk.t5.grp1.worldofzuul.graphics;

import dk.t5.grp1.worldofzuul.player.Player;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class HUD {

    private Player player;
    private GraphicsContext graphics;

    public HUD(Player player, GraphicsContext graphics) {
        this.player = player;
        this.graphics = graphics;
    }

    public void update() {

    }

    public void render(Screen screen) {
        screen.renderBox(1270, 10, 1590, 74);
        screen.renderBox(10, 10, 150, 155);
        graphics.setFill(Color.rgb(198, 198, 198));
        graphics.fillText("Sun:    " + player.getInventory().getSunCount(), 15, 35);
        graphics.fillText("Water: " + player.getInventory().getWaterCount(), 15, 65);
        graphics.fillText("Seed:  " + player.getInventory().getSeedCount(), 15, 95);
        if (player.getInventory().getWaterCount() > 0 && player.getInventory().getSunCount() > 0) {
            graphics.setFill(Color.rgb(87, 255, 81));
            graphics.fillText("Press Interact \nto consume", 15, 125);
        }
    }
}