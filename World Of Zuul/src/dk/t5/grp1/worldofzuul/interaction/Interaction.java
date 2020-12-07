package dk.t5.grp1.worldofzuul.interaction;

import dk.t5.grp1.worldofzuul.Game;
import dk.t5.grp1.worldofzuul.graphics.Screen;
import dk.t5.grp1.worldofzuul.input.Keyboard;
import dk.t5.grp1.worldofzuul.item.Item;
import dk.t5.grp1.worldofzuul.npc.NPC;
import dk.t5.grp1.worldofzuul.player.Player;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class Interaction {

    private boolean interacting;
    private GraphicsContext graphicsContext;
    private NPC npc;
    private Item item;

    private String type;

    public Interaction(Canvas canvas, NPC npc, Item item) {
        interacting = false;
        graphicsContext = canvas.getGraphicsContext2D();
        this.npc = npc;
        this.item = item;
    }

    int interactionLine = 0;

    boolean pressed = false;
    public void update(Keyboard key, String type, Player player) {
        this.type = type;

        if (!interacting) {
            if (key.interact && !pressed) {
                interacting = true;
                pressed = true;
            }
            else if (!key.interact) {
                pressed = false;
            }
        }
        else {
            if (key.interact && !pressed) {
                pressed = true;
                interactionLine++;
            }
            else if (!key.interact) {
                pressed = false;
            }
            else if (type.equals("npc") && npc.getInfo().length < 5 && interactionLine > 0) {
                interactionLine = 0;
                interacting = false;
            }
            else if (type.equals("npc") && interactionLine + 5 > npc.getInfo().length && npc.getInfo().length >= 5) {
                interactionLine = 0;
                interacting = false;
            }
            else if (type.equals("item") && interactionLine > 0) {
                player.gather();
                interacting = false;
            }
        }
    }

    public void render(Screen screen) {
        graphicsContext.setFont(Font.font("Verdana", 16));

        if (interacting) {
            screen.renderDialogueBox();
            graphicsContext.setFill(Color.rgb(198, 198, 198));
            if (type.equals("npc")) {
                graphicsContext.setFill(Color.rgb(87, 255, 81));
                graphicsContext.fillText(npc.getName(), 310, Game.height - 200);
                graphicsContext.setFill(Color.rgb(198, 198, 198));
                if (npc.getInfo().length > 5) {
                    for (int i = 0; i < 5; i++) {
                        if (i + interactionLine < npc.getInfo().length) {
                            graphicsContext.fillText(npc.getInfo()[i + interactionLine], 310, Game.height - 180 + (20 * i), Game.width - 620);
                        }
                    }
                }
                else {
                    for (int i = 0; i < npc.getInfo().length; i++) {
                        graphicsContext.fillText(npc.getInfo()[i], 310, Game.height - 180 + (20 * i), Game.width - 620);
                    }
                }
            }
            else if (type.equals("item")) {
                graphicsContext.fillText("You picked up: " + item.getName(), 310, Game.height - 200, Game.width - 620);
            }
        }
    }

    public boolean isInteracting() {
        return interacting;
    }

    public void setNpc(NPC npc) {
        this.npc = npc;
    }

    public void setItem(Item item) {
        this.item = item;
    }

}
