package dk.t5.grp1.worldofzuul.graphics;

import dk.t5.grp1.worldofzuul.Game;
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
        screen.renderBox(10, 10, 150, 185);
        screen.renderBox(Game.width - 285, Game.height - 235, Game.width, Game.height);
        screen.renderMob(Game.width - 150, Game.height - 175, Sprite.map);
        screen.renderMob(1270 + Sprite.evolution1.SIZE / 2, 10 + Sprite.evolution1.SIZE / 2, Sprite.evolution1);
        screen.renderMob(1270 + Sprite.evolution2.SIZE / 2 + 64, 10 + Sprite.evolution2.SIZE / 2, Sprite.evolution2);
        screen.renderMob(1270 + Sprite.evolution3.SIZE / 2+ 64+ 64, 10 + Sprite.evolution3.SIZE / 2, Sprite.evolution3);
        screen.renderMob(1270 + Sprite.evolution4.SIZE / 2+ 64+ 64+ 64, 10 + Sprite.evolution4.SIZE / 2, Sprite.evolution4);
        screen.renderMob(1270 + Sprite.evolution5.SIZE / 2+ 64+ 64+ 64+ 64, 10 + Sprite.evolution5.SIZE / 2, Sprite.evolution5);

        graphics.setFill(Color.rgb(198, 198, 198));
        graphics.fillText("Sun:         " + player.getInventory().getSunCount() + "/" + player.getItemsNeededForNextLvl(), 15, 35);
        graphics.fillText("Water:      " + player.getInventory().getWaterCount() + "/" + player.getItemsNeededForNextLvl(), 15, 65);
        graphics.fillText("Seed:       " + player.getInventory().getSeedCount() + "/8", 15, 95);
        graphics.fillText("Evolution: " + player.getCurrentLevel() + "/4", 15, 125);
        if (player.getCurrentLevel() >= 4 && player.getInventory().getSeedCount() >= 8) {
            graphics.setFill(Color.rgb(87, 255, 81));
            graphics.fillText("Press Interact \nto plant", 15, 155);
        }
        else if (player.getInventory().getWaterCount() > 0 && player.getInventory().getSunCount() > 0) {
            graphics.setFill(Color.rgb(87, 255, 81));
            graphics.fillText("Press Interact \nto consume", 15, 155);
        }
    }
}
