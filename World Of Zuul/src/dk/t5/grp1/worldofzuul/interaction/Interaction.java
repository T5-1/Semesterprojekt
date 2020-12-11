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

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Interaction {
    //Attributes
    private boolean interacting;
    private GraphicsContext graphicsContext;
    private NPC npc;
    private Item item;

    private String type = "";

    private EventManager eventManager;

    //Constructor
    public Interaction(GraphicsContext graphicsContext, NPC npc, Item item, EventManager eventManager) {
        interacting = false;
        this.graphicsContext = graphicsContext;
        this.npc = npc;
        this.item = item;
        this.eventManager = eventManager;
    }

    //Initialization of more attributes
    int interactionLine = 0;
    int selectedOption = 0;
    int questionIndex = 0;

    boolean directionalKeysPressed = false;
    boolean pressed = false;
    boolean eventStart = true;

    public void update(Keyboard key, String type, Player player) {
        this.type = type;

        //If player presses interact
        if (!interacting) {

            //Checks that player has already interacted
            if (key.interact && !pressed) {
                interacting = true;
                pressed = true;
            } else if (!key.interact) {
                pressed = false;
            }
        } else {
            //Checks if the player hasn't interacted before and with who/what
            if (key.interact && !pressed) {
                pressed = true;
                interactionLine++;

            //If you interact with npc
            } else if (type.equals("npc")) {
                //Checks if the player has interacted with max number of npc's per evolution
                //If the player has interacted with the maximum
                if (player.getNpcsReactedWith() >= player.getNpcsNeededReactionWith() && !player.getCurrentRoom().getNpc().getName().equals("Old Tutorial Tree") && interactionLine > 0) {
                    interacting = false;
                    interactionLine = 0;
                    setType("null");
                } else {
                    //If player has not interacted with the maximum
                    //Checks the length of the npc text
                    if (npc.getInfo().length < 5 && interactionLine > 0) {
                        interactionLine = 0;
                        setType("null");
                        player.interact();
                        interacting = false;
                    } else if (interactionLine + 5 > npc.getInfo().length && npc.getInfo().length >= 5) {
                        interactionLine = 0;
                        setType("null");
                        player.interact();
                        interacting = false;
                    }
                }

            //If the player interacts with item
            } else if (type.equals("item") && interactionLine > 0) {
                interactionLine = 0;
                setType("null");
                player.gather();
                interacting = false;

            //If the player has collected enough to consume it triggers this interaction
            } else if (type.equals("consume") && interactionLine > 0) {
                interactionLine = 0;
                setType("null");
                player.consume();
                interacting = false;

            //If the player has consumed enough to evolve it triggers this interaction
            } else if (type.equals("level") && interactionLine > 0) {
                interactionLine = 0;
                setType("null");
                player.levelUp();
                interacting = false;

            //If you evolved enough it triggers events
            } else if (type.equals("event")) {
                //Sets true for player to level up,
                //finds the current event to which stage the player is,
                //and updates the eventManager
                player.setCanLevelUp(true);
                npc = eventManager.getCurrentEvent().getRoom().getNpc();
                eventManager.getCurrentEvent().update();

                //Event trigger
                //If player has answered
                if (eventManager.getCurrentEvent().isAnswered() && interactionLine > 0) {
                    eventStart = true;
                    interacting = false;
                    interactionLine = 0;
                }

                //If player hasn't answered
                if (!eventManager.getCurrentEvent().isAnswered()) {

                    //Makes player able to move their keys,
                    //when the player selects the answer-options during event
                    if (selectedOption > 0 && key.up && !directionalKeysPressed) {
                        selectedOption--;
                        directionalKeysPressed = true;
                    } else if (selectedOption < 2 && key.down && !directionalKeysPressed) {
                        selectedOption++;
                        directionalKeysPressed = true;
                    } else if (!key.down && !key.up) {
                        directionalKeysPressed = false;
                    }

                    //Checks if answer is right or wrong
                    if (key.interact && interactionLine > 0) {
                        if (eventManager.getCurrentEvent().getCorrectAnswer() != selectedOption) {
                            setType("wrongAnswer");
                        }
                        if (eventManager.getCurrentEvent().getCorrectAnswer() == selectedOption) {
                            setType("rightAnswer");
                        }
                        interactionLine = 0;
                    }

                //Ends event
                } else if (interactionLine > 0) {
                    interactionLine = 0;
                    setType("null");
                    interacting = false;
                }

            //When event starts and showcases questions in randomized order
            } else if (eventManager.isEventRunning() && eventStart && interactionLine > 0 && type.equals("eventStart")) {
                List<Integer> availableQuestions = new ArrayList<>();
                for (int i = 0; i < Game.questionManager.getQuestions().length; i++) {
                    if (Game.questionManager.getQuestions()[i].isAvailable()) {
                        availableQuestions.add(i);
                    }
                }
                Random random = new Random();
                int randomQuestion = random.nextInt(availableQuestions.size() - 1);
                questionIndex = availableQuestions.get(randomQuestion);
                Game.questionManager.getQuestions()[availableQuestions.get(randomQuestion)].setAvailable(false);

                eventManager.getCurrentEvent().setCorrectAnswer(Game.questionManager.getQuestions()[questionIndex].getCorrectAnswer());
                interactionLine = 0;
                setType("null");
                interacting = false;
                eventStart = false;

            //If player answers incorrectly it restarts the event
            } else if (type.equals("wrongAnswer") && interactionLine > 0) {
                interactionLine = 0;
                setType("event");

            //If player answers correctly it ends event and gifts you seed-items
            } else if (type.equals("rightAnswer") && interactionLine > 0) {
                eventManager.getCurrentEvent().setAnswered(true);
                setType("null");
                eventManager.endEvent(player);

                //Lake event and seed reward
                if (eventManager.getCurrentEvent() == eventManager.getLakeEvent()) {
                    for (int i = 0; i < eventManager.getCurrentEvent().getSeedReward(); i++) {
                        player.getInventory().add(new Seed());
                    }
                    interacting = false;
                    eventStart = true;
                    interactionLine = 0;
                    setType("null");

                //Final event and seed reward
                } else if (eventManager.getCurrentEvent() != eventManager.getFinalEvent()) {
                    for (int i = 0; i < eventManager.getCurrentEvent().getSeedReward(); i++) {
                        player.getInventory().add(new Seed());
                    }
                    interacting = false;
                    eventStart = true;
                    interactionLine = 0;
                    setType("null");

                //Win-game event
                } else {
                    eventStart = true;
                    interactionLine = 0;
                    player.die("You won the game!");
                }

            //Plant event
            } else if (type.equals("plant") && interactionLine > 0) {
                interactionLine = 0;
                interacting = false;
                player.plant();
                setType("null");

            //Player died event
            //Checks which keys the player presses
            } else if (!player.isAlive()) {
                if (selectedOption > 0 && key.up && !directionalKeysPressed) {
                    selectedOption--;
                    directionalKeysPressed = true;
                } else if (selectedOption < 1 && key.down && !directionalKeysPressed) {
                    selectedOption++;
                    directionalKeysPressed = true;
                } else if (!key.down && !key.up) {
                    directionalKeysPressed = false;
                }

                //If player selects restart, it restarts
                //If not, it quits the game
                if (key.interact && interactionLine > 0) {
                    if (selectedOption == 0) {
                        Game.restart = true;
                    }
                    if (selectedOption == 1) {
                        Game.exit = true;
                    }
                }

            //Start game event
            } else if (type.equals("start") && interactionLine > 0) {
                interactionLine = 0;
                setType("npc");

            //Checks if there is nothing to interact with
            } else if (type.equals("") || type.equals("null")) {
                interacting = false;
            }

            if (!key.interact) {
                pressed = false;
            }
        }
    }

    public void render(Screen screen, Player player) {
        //Checks what/who player interacts with
        if (interacting) {

            //Render Textbox UI element
            screen.renderBox(300, Game.height - 220, Game.width - 300, Game.height - 80);

            //Color of text
            graphicsContext.setFill(Color.rgb(198, 198, 198));

            if (type.equals("npc")) {

                //Checks if player gathered the maximum amount of info per evolution
                //If max reached
                if (player.getNpcsReactedWith() >= player.getNpcsNeededReactionWith() && player.getCurrentRoom().getNpc().getName() != "Old Tutorial Tree") {
                    graphicsContext.fillText("You can't gather more information right now, you should try evolving", 310, Game.height - 200, Game.width - 620);

                //If max not reached
                //Renders textbox, text and textcolor
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

            //Checks which item player picks up
            } else if (type.equals("item")) {
                graphicsContext.fillText("You picked up: " + item.getName(), 310, Game.height - 200, Game.width - 620);

            //Checks when player can consume items
            } else if (type.equals("consume")) {
                if (player.getNpcsReactedWith() >= player.getNpcsNeededReactionWith()) {
                    if (player.isCanLevelUp() || player.getCurrentLevel() == 3) {

                        //The amount player consumes
                        if (player.getInventory().getSunCount() >= player.getInventory().getSunCount()) {
                            graphicsContext.fillText("You consumed " + (player.getInventory().getWaterCount()) + " Water & " + (player.getInventory().getSunCount()) + " Sun", 310, Game.height - 200, Game.width - 620);
                        } else {
                            graphicsContext.fillText("You consumed " + (player.getInventory().getWaterCount()) + " Water & " + (player.getInventory().getSunCount()) + " Sun", 310, Game.height - 200, Game.width - 620);
                        }
                    } else {
                        //If player hasn't filled the requirements
                        graphicsContext.fillText("Something is missing but you don't quite know what hmm... try wandering around a bit", 310, Game.height - 200, Game.width - 620);
                    }
                } else {
                    //If player needs more information
                    graphicsContext.fillText("You need more information to consume, try talking with someone", 310, Game.height - 200, Game.width - 620);
                }
            } else if (type.equals("level")) {
                //Showcases player's new evolution
                graphicsContext.fillText("You are now a " + player.getEvolution()[player.getCurrentLevel() + 1], 310, Game.height - 200, Game.width - 620);

            } else if (type.equals("event")) {
                //Textbox, text and textcolor during event-trigger
                graphicsContext.setFill(Color.rgb(87, 255, 81));
                graphicsContext.fillText(npc.getName(), 310, Game.height - 200);
                graphicsContext.setFill(Color.rgb(198, 198, 198));

                //Showcases questions and answers during events
                if (!eventManager.getCurrentEvent().isAnswered()) {
                    graphicsContext.fillText(Game.questionManager.getQuestions()[questionIndex].getQuestion(), 310, Game.height - 180);
                    graphicsContext.fillText(Game.questionManager.getQuestions()[questionIndex].getAnswers()[0], 310, Game.height - 145);
                    graphicsContext.fillText(Game.questionManager.getQuestions()[questionIndex].getAnswers()[1], 310, Game.height - 125);
                    graphicsContext.fillText(Game.questionManager.getQuestions()[questionIndex].getAnswers()[2], 310, Game.height - 105);
                    screen.renderHollowBox(310, Game.height - 160 + (20 * selectedOption), Game.width - 310, Game.height - 140 + (20 * selectedOption));
                } else {
                    //If player interacts with event-npc again after answering
                    graphicsContext.fillText("You have already answered my question!", 310, Game.height - 180);
                }

            //Showcases description for event
            } else if (eventManager.isEventRunning() && eventStart && type.equals("eventStart")) {
                graphicsContext.fillText(eventManager.getCurrentEvent().getDescription(), 310, Game.height - 200);

            //Showcases if player answers incorrectly during events
            } else if (type.equals("wrongAnswer")) {
                graphicsContext.fillText("That is wrong!", 310, Game.height - 200);

            //Showcases if player answers correctly during events
            } else if (type.equals("rightAnswer")) {
                if (eventManager.getCurrentEvent() != eventManager.getFinalEvent()) {
                    graphicsContext.fillText("That is right! Take " + eventManager.getCurrentEvent().getSeedReward() + " seed(s)!", 310, Game.height - 200);
                } else {
                    //Showcase during final event
                    graphicsContext.fillText("Dang it grandson, you saw through my cunning ruse, and defeated me!", 310, Game.height - 200);
                    graphicsContext.fillText("Well you got it as you wanted it, you saved the forest.", 310, Game.height - 180);
                    graphicsContext.fillText("But beware, next time it won't be this easy...", 310, Game.height - 160);
                }

            //Showcases death-event
            } else if (type.equals("die")) {
                graphicsContext.fillText(player.getDeathMessage(), 310, Game.height - 180);
                graphicsContext.fillText("Yes!", 310, Game.height - 145);
                graphicsContext.fillText("No!", 310, Game.height - 125);
                screen.renderHollowBox(310, Game.height - 160 + (20 * selectedOption), Game.width - 310, Game.height - 140 + (20 * selectedOption));

            //Showcases plant-event
            } else if (type.equals("plant")) {
                graphicsContext.fillText("You have planted all the seeds!", 310, Game.height - 200);

            //Showcases start-event
            } else if (type.equals("start")) {
                graphicsContext.fillText("Welcome to Need For Tree", 310, Game.height - 200);
                graphicsContext.fillText("Press 'Enter' to start", 310, Game.height - 180);

            //If player interacts with nothing
            } else {
                graphicsContext.fillText("You found an error, press enter to try and continue!", 310, Game.height - 200);
            }
        }
    }

    //Accessor and mutator methods
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
