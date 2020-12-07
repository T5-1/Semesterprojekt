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

    public Interaction(GraphicsContext graphicsContext, NPC npc, Item item) {
        interacting = false;
        this.graphicsContext = graphicsContext;
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
            } else if (!key.interact) {
                pressed = false;
            }
        } else {
            if (key.interact && !pressed) {
                pressed = true;
                interactionLine++;
            } else if (!key.interact) {
                pressed = false;
            } else if (type.equals("npc")) {
                if (player.getNpcsReactedWith() >= player.getNpcsNeededReactionWith() && interactionLine > 0) {
                    interacting = false;
                }
                else {
                    if (npc.getInfo().length < 5 && interactionLine > 0) {
                        interactionLine = 0;
                        player.interact();
                        interacting = false;
                    } else if (interactionLine + 5 > npc.getInfo().length && npc.getInfo().length >= 5) {
                        interactionLine = 0;
                        player.interact();
                        interacting = false;
                    }
                }
            } else if (type.equals("item") && interactionLine > 0) {
                interactionLine = 0;
                player.gather();
                interacting = false;
            } else if (type.equals("consume") && interactionLine > 0) {
                interactionLine = 0;
                player.consume();
                interacting = false;
            } else if (type.equals("level") && interactionLine > 0) {
                interactionLine = 0;
                player.levelUp();
                interacting = false;
            }
        }
    }

    public void render(Screen screen, Player player) {
        if (interacting) {
            screen.renderBox(300, Game.height - 220, Game.width - 300, Game.height - 80);
            graphicsContext.setFill(Color.rgb(198, 198, 198));
            if (type.equals("npc")) {
                if (player.getNpcsReactedWith() >= player.getNpcsNeededReactionWith()) {
                    graphicsContext.fillText("You can't gather more information right now, you should try evolving", 310, Game.height - 200, Game.width - 620);
                } else {
                    graphicsContext.setFill(Color.rgb(87, 255, 81));
                    graphicsContext.fillText(npc.getName(), 310, Game.height - 200);
                    graphicsContext.setFill(Color.rgb(198, 198, 198));
                    if (npc.getInfo().length > 5) {
                        for (int i = 0; i < 5; i++) {
                            if (i + interactionLine < npc.getInfo().length) {
                                graphicsContext.fillText(npc.getInfo()[i + interactionLine], 310, Game.height - 180 + (20 * i), Game.width - 620);
                            }
                        }
                    } else {
                        for (int i = 0; i < npc.getInfo().length; i++) {
                            graphicsContext.fillText(npc.getInfo()[i], 310, Game.height - 180 + (20 * i), Game.width - 620);
                        }
                    }
                }
            } else if (type.equals("item")) {
                graphicsContext.fillText("You picked up: " + item.getName(), 310, Game.height - 200, Game.width - 620);
            } else if (type.equals("consume")) {
                if (player.getNpcsReactedWith() >= player.getNpcsNeededReactionWith()) {
                    if (player.isCanLevelUp() || player.getCurrentLevel() == 3) {
                        if (player.getInventory().getSunCount() >= player.getInventory().getSunCount()) {
                            graphicsContext.fillText("You consumed " + (player.getInventory().getSunCount()) + " Water & Sun", 310, Game.height - 200, Game.width - 620);
                        } else {
                            graphicsContext.fillText("You consumed " + (player.getInventory().getWaterCount()) + " Water & Sun", 310, Game.height - 200, Game.width - 620);
                        }
                    } else {
                        graphicsContext.fillText("Somethings missing but you don't quite know what hmm... try wandering around a bit", 310, Game.height - 200, Game.width - 620);
                    }
                } else {
                    graphicsContext.fillText("You need more information to consume, try talking with someone", 310, Game.height - 200, Game.width - 620);
                }
            } else if (type.equals("level")) {
                graphicsContext.fillText("You are now a " + player.getEvolution()[player.getCurrentLevel() + 1], 310, Game.height - 200, Game.width - 620);
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

    public void setInteracting(boolean interacting) {
        this.interacting = interacting;
    }

    public String getType() {
        return type;
    }

}
