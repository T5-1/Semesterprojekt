package dk.t5.grp1.worldofzuul.interaction;

import dk.t5.grp1.worldofzuul.Game;
import dk.t5.grp1.worldofzuul.event.EventManager;
import dk.t5.grp1.worldofzuul.graphics.Screen;
import dk.t5.grp1.worldofzuul.input.Keyboard;
import dk.t5.grp1.worldofzuul.item.Item;
import dk.t5.grp1.worldofzuul.item.Seed;
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

    private String type = "";

    private EventManager eventManager;

    public Interaction(GraphicsContext graphicsContext, NPC npc, Item item, EventManager eventManager) {
        interacting = false;
        this.graphicsContext = graphicsContext;
        this.npc = npc;
        this.item = item;
        this.eventManager = eventManager;
    }

    int interactionLine = 0;
    int selectedOption = 0;
    int questionIndex = 0;

    boolean directionalKeysPressed = false;
    boolean pressed = false;
    boolean eventStart = true;

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
            else if (type.equals("npc")) {
                if (player.getNpcsReactedWith() >= player.getNpcsNeededReactionWith() && !player.getCurrentRoom().getNpc().getName().equals("Old Tutorial Tree") && interactionLine > 0) {
                    interacting = false;
                    interactionLine = 0;
                    setType("null");
                }
                else {
                    if (npc.getInfo().length < 5 && interactionLine > 0) {
                        interactionLine = 0;
                        setType("null");
                        player.interact();
                        interacting = false;
                    }
                    else if (interactionLine + 5 > npc.getInfo().length && npc.getInfo().length >= 5) {
                        interactionLine = 0;
                        setType("null");
                        player.interact();
                        interacting = false;
                    }
                }
            }
            else if (type.equals("item") && interactionLine > 0) {
                interactionLine = 0;
                setType("null");
                player.gather();
                interacting = false;
            }
            else if (type.equals("consume") && interactionLine > 0) {
                interactionLine = 0;
                setType("null");
                player.consume();
                interacting = false;
            }
            else if (type.equals("level") && interactionLine > 0) {
                interactionLine = 0;
                setType("null");
                player.levelUp();
                interacting = false;
            }
            else if (type.equals("event")) {
                player.setCanLevelUp(true);
                npc = eventManager.getCurrentEvent().getRoom().getNpc();
                eventManager.getCurrentEvent().update();

                if (eventManager.getCurrentEvent().isAnswered() && interactionLine > 0) {
                    eventStart = true;
                    interacting = false;
                    interactionLine = 0;
                }

                if (!eventManager.getCurrentEvent().isAnswered()) {
                    if (selectedOption > 0 && key.up && !directionalKeysPressed) {
                        selectedOption--;
                        directionalKeysPressed = true;
                    } else if (selectedOption < 2 && key.down && !directionalKeysPressed) {
                        selectedOption++;
                        directionalKeysPressed = true;
                    } else if (!key.down && !key.up) {
                        directionalKeysPressed = false;
                    }

                    if (key.interact && interactionLine > 0) {
                        eventManager.getCurrentEvent().setAnswer(selectedOption);
                        if (eventManager.getCurrentEvent().getCorrectAnswer() != selectedOption) {
                            setType("wrongAnswer");
                        }
                        if (eventManager.getCurrentEvent().getCorrectAnswer() == selectedOption) {
                            setType("rightAnswer");
                        }
                        interactionLine = 0;
                    }

                }
                else if (interactionLine > 0) {
                    interactionLine = 0;
                    setType("null");
                    interacting = false;
                }

            }
            else if (eventManager.isEventRunning() && eventStart && interactionLine > 0 && type.equals("eventStart")) {
                for (int i = 0; i < Game.questionManager.getQuestions().length; i++) {
                    if (Game.questionManager.getQuestions()[i].isAvailable()) {
                        Game.questionManager.getQuestions()[i].setAvailable(false);
                        questionIndex = i;
                        break;
                    }
                }
                eventManager.getCurrentEvent().setCorrectAnswer(Game.questionManager.getQuestions()[questionIndex].getCorrectAnswer());
                interactionLine = 0;
                setType("null");
                interacting = false;
                eventStart = false;
            }
            else if (type.equals("wrongAnswer") && interactionLine > 0) {
                interactionLine = 0;
                setType("event");
            }
            else if (type.equals("rightAnswer") && interactionLine > 0) {
                eventManager.getCurrentEvent().setAnswered(true);
                setType("null");
                eventManager.endEvent(player);
                if (eventManager.getCurrentEvent() == eventManager.getLakeEvent()) {
                    for (int i = 0; i < eventManager.getCurrentEvent().getSeedReward(); i++) {
                        player.getInventory().add(new Seed());
                    }
                    interacting = false;
                    eventStart = true;
                    interactionLine = 0;
                    setType("null");
                }
                else if (eventManager.getCurrentEvent() != eventManager.getFinalEvent()) {
                    for (int i = 0; i < eventManager.getCurrentEvent().getSeedReward(); i++) {
                        player.getInventory().add(new Seed());
                    }
                    interacting = false;
                    eventStart = true;
                    interactionLine = 0;
                    setType("null");
                }
                else {
                    eventStart = true;
                    interactionLine = 0;
                    player.die("You won the game!");
                }
            }
            else if (type.equals("plant") && interactionLine > 0) {
                interactionLine = 0;
                interacting = false;
                player.plant();
                setType("null");
            }
            else if (!player.isAlive()) {
                if (selectedOption > 0 && key.up && !directionalKeysPressed) {
                    selectedOption--;
                    directionalKeysPressed = true;
                } else if (selectedOption < 1 && key.down && !directionalKeysPressed) {
                    selectedOption++;
                    directionalKeysPressed = true;
                } else if (!key.down && !key.up) {
                    directionalKeysPressed = false;
                }

                if (key.interact && interactionLine > 0) {
                    if (selectedOption == 0) {
                        Game.restart = true;
                    }
                    if (selectedOption == 1) {
                        Game.exit = true;
                    }
                }
            }
            else if (type.equals("start") && interactionLine > 0) {
                interactionLine = 0;
                setType("npc");
            }
            else if (type.equals("") || type.equals("null")) {
                interacting = false;
            }

            if (!key.interact) {
                pressed = false;
            }
        }
    }

    public void render(Screen screen, Player player) {
        if (interacting) {
            screen.renderBox(300, Game.height - 220, Game.width - 300, Game.height - 80);
            graphicsContext.setFill(Color.rgb(198, 198, 198));
            if (type.equals("npc")) {
                if (player.getNpcsReactedWith() >= player.getNpcsNeededReactionWith() && player.getCurrentRoom().getNpc().getName() != "Old Tutorial Tree") {
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
                            graphicsContext.fillText("You consumed " + (player.getInventory().getWaterCount()) + " Water & " + (player.getInventory().getSunCount()) + " Sun", 310, Game.height - 200, Game.width - 620);
                        }
                        else {
                            graphicsContext.fillText("You consumed " + (player.getInventory().getWaterCount()) + " Water & " + (player.getInventory().getSunCount()) + " Sun", 310, Game.height - 200, Game.width - 620);
                        }
                    }
                    else {
                        graphicsContext.fillText("Somethings missing but you don't quite know what hmm... try wandering around a bit", 310, Game.height - 200, Game.width - 620);
                    }
                }
                else {
                    graphicsContext.fillText("You need more information to consume, try talking with someone", 310, Game.height - 200, Game.width - 620);
                }
            }
            else if (type.equals("level")) {
                graphicsContext.fillText("You are now a " + player.getEvolution()[player.getCurrentLevel() + 1], 310, Game.height - 200, Game.width - 620);
            }
            else if (type.equals("event")) {
                graphicsContext.setFill(Color.rgb(87, 255, 81));
                graphicsContext.fillText(npc.getName(), 310, Game.height - 200);
                graphicsContext.setFill(Color.rgb(198, 198, 198));

                if (!eventManager.getCurrentEvent().isAnswered()) {
                    graphicsContext.fillText(Game.questionManager.getQuestions()[questionIndex].getQuestion(), 310, Game.height - 180);
                    graphicsContext.fillText(Game.questionManager.getQuestions()[questionIndex].getAnswers()[0], 310, Game.height - 145);
                    graphicsContext.fillText(Game.questionManager.getQuestions()[questionIndex].getAnswers()[1], 310, Game.height - 125);
                    graphicsContext.fillText(Game.questionManager.getQuestions()[questionIndex].getAnswers()[2], 310, Game.height - 105);
                    screen.renderHollowBox(310, Game.height - 160 + (20 * selectedOption), Game.width - 310, Game.height - 140 + (20 * selectedOption));
                }
                else {
                    graphicsContext.fillText("You have already answered my question!", 310, Game.height - 180);
                }
            }
            else if (eventManager.isEventRunning() && eventStart && type.equals("eventStart")) {
                graphicsContext.fillText(eventManager.getCurrentEvent().getDescription(), 310, Game.height - 200);
            }
            else if (type.equals("wrongAnswer")) {
                graphicsContext.fillText("That is wrong!", 310, Game.height - 200);
            }
            else if (type.equals("rightAnswer")) {
                if (eventManager.getCurrentEvent() != eventManager.getFinalEvent()) {
                    graphicsContext.fillText("That is right! Take " + eventManager.getCurrentEvent().getSeedReward() + " seed(s)!", 310, Game.height - 200);
                }
                else {
                    graphicsContext.fillText("Dang it grandson, you saw through my cunning ruse, and defeated me!", 310, Game.height - 200);
                    graphicsContext.fillText("Well you got it as you wanted it, you saved the forest.", 310, Game.height - 180);
                    graphicsContext.fillText("But beware, next time it won't be this easy...", 310, Game.height - 160);
                }
            }
            else if (type.equals("die")) {
                graphicsContext.fillText(player.getDeathMessage(), 310, Game.height - 180);
                graphicsContext.fillText("Yes!", 310, Game.height - 145);
                graphicsContext.fillText("No!", 310, Game.height - 125);
                screen.renderHollowBox(310, Game.height - 160 + (20 * selectedOption), Game.width - 310, Game.height - 140 + (20 * selectedOption));
            }
            else if (type.equals("plant")) {
                graphicsContext.fillText("You have planted all the seeds!", 310, Game.height - 200);
            }
            else if (type.equals("start")) {
                graphicsContext.fillText("Welcome to Need For Tree", 310, Game.height - 200);
                graphicsContext.fillText("Press 'Enter' to start", 310, Game.height - 180);
            }
            else {
                graphicsContext.fillText("You found an error, press enter to try and continue!", 310, Game.height - 200);
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

    public void setType(String type) {
        this.type = type;
    }
}
