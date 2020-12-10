package dk.t5.grp1.worldofzuul;

import dk.t5.grp1.worldofzuul.event.EventManager;
import dk.t5.grp1.worldofzuul.graphics.Background;
import dk.t5.grp1.worldofzuul.graphics.HUD;
import dk.t5.grp1.worldofzuul.graphics.Screen;
import dk.t5.grp1.worldofzuul.player.Player;
import dk.t5.grp1.worldofzuul.question.QuestionManager;
import dk.t5.grp1.worldofzuul.room.*;
import dk.t5.grp1.worldofzuul.input.Keyboard;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Game extends Application {
    public static final int width = 1616;
    public static final int height = 935;

    public static boolean exit = false;
    public static boolean restart = false;

    private String title = "Need For Tree";

    private WritableImage writableImage = new WritableImage(width, height);
    private PixelWriter pixelWriter = writableImage.getPixelWriter();
    private Canvas canvas = new Canvas(width, height);
    private GraphicsContext graphicsContext;

    private Screen screen = new Screen(width, height);
    private Keyboard key;
    public static final QuestionManager questionManager = new QuestionManager();

    private Player player;
    private HUD hud;
    private EventManager eventManager;
    private Room camp, cave, desert, flowerField, lake, mountain, river, savanna, shore, spawn, northernEntrance, southernEntrance;

    private void createRooms() {
        camp = new Camp("at the Camp", "Camp");
        cave = new Cave("at the Cave", "Cave");
        desert = new Desert("at the desert", "Desert");
        flowerField = new FlowerField("at the FlowerField", "Flower Field");
        lake = new Lake("at the lake", "Lake");
        mountain = new Mountain("on the Mountain", "Mountain");
        river = new River("at the River", "River");
        savanna = new Savanna("at the Savanna", "Savanna");
        shore = new Shore("at the Shore", "Shore");
        spawn = new Spawn("at the Spawn", "Spawn");
        northernEntrance = new NorthernEntrance("at the Northern Entrance", "Northern Entrance");
        southernEntrance = new SouthernEntrance("at the Southern Entrance", "Southern Entrance");

        camp.setExit(0, spawn);
        camp.setExit(1, desert);
        camp.setExit(2, southernEntrance);
        camp.setExit(3, savanna);

        cave.setExit(2, mountain);
        cave.setExit(3, river);

        desert.setExit(0, mountain);
        desert.setExit(3, camp);

        flowerField.setExit(1, river);
        flowerField.setExit(2, shore);

        lake.setExit(1, shore);

        mountain.setExit(0, cave);
        mountain.setExit(2, desert);
        mountain.setExit(3, spawn);

        river.setExit(0, northernEntrance);
        river.setExit(1, cave);
        river.setExit(2, spawn);
        river.setExit(3, flowerField);

        savanna.setExit(0, shore);
        savanna.setExit(1, camp);

        shore.setExit(0, flowerField);
        shore.setExit(1, spawn);
        shore.setExit(2, savanna);
        shore.setExit(3, lake);

        spawn.setExit(0, river);
        spawn.setExit(1, mountain);
        spawn.setExit(2, camp);
        spawn.setExit(3, shore);

        northernEntrance.setExit(2, river);

        southernEntrance.setExit(0, camp);
    }

    @Override
    public void start(Stage stage) {
        ImageView game = new ImageView(writableImage);
        Group root = new Group(game);
        Scene scene = new Scene(root, width, height);
        key = new Keyboard(scene);

        createRooms();
        eventManager = new EventManager(spawn, lake, northernEntrance, southernEntrance);
        graphicsContext = canvas.getGraphicsContext2D();
        player = new Player(spawn, eventManager, width / 2, height / 2 + 150, graphicsContext);
        hud = new HUD(player, graphicsContext);

        graphicsContext.setFont(Font.font("Verdana", 16));

        root.getChildren().add(canvas);

        stage.setTitle(title);
        stage.setResizable(false);
        stage.setWidth(width);
        stage.setHeight(height);
        stage.setScene(scene);

        stage.show();
        stage.requestFocus();

        AnimationTimer animation = new AnimationTimer() {

            @Override
            public void handle(long now) {
                canvas.getGraphicsContext2D().clearRect(0, 0, width, height);
                update();
                render();
                if (exit) {
                    stage.close();
                }
                else if (restart) {
                    createRooms();
                    eventManager = new EventManager(spawn, lake, northernEntrance, southernEntrance);
                    player = new Player(spawn, eventManager, width / 2, height / 2 + 150, graphicsContext);
                    hud = new HUD(player, graphicsContext);
                    restart = false;
                }

                stage.setTitle(title + "  |  " + player.getCurrentRoom().getName());
            }
        };

        animation.start();
    }

    public void update() {
        key.update();
        player.update(key);
        player.getCurrentRoom().getNpc().update();
        eventManager.update(player, player.getInteraction());
        player.getInteraction().update(key, player.getInteraction().getType(), player);
    }

    public void render() {
        screen.clear();
        player.getCurrentRoom().render(screen);

        if ( player.getY() > player.getCurrentRoom().getNpc().getY() + player.getCurrentRoom().getNpc().startInteractionY) {
            player.getCurrentRoom().getNpc().render(screen);
            player.render(screen);
        }
        else {
            player.render(screen);
            player.getCurrentRoom().getNpc().render(screen);
        }

        hud.render(screen);

        if (player.getInteraction().getType().equals("start")) {
            for (int i = 0; i < screen.getPixels().length; i++) {
                screen.getPixels()[i] = Background.start.getPixels()[i];
            }
        }
        else if (eventManager.isFinalEventPlayed()) {
            for (int i = 0; i < screen.getPixels().length; i++) {
                screen.getPixels()[i] = Background.win.getPixels()[i];
            }
        }
        else if (!player.isAlive()) {
            for (int i = 0; i < screen.getPixels().length; i++) {
                screen.getPixels()[i] = Background.die.getPixels()[i];
            }
        }

        player.getInteraction().render(screen, player);

        pixelWriter.setPixels(0, 0, width, height, PixelFormat.getIntArgbInstance(), screen.getPixels(), 0, width);
    }

    public static void main(String[] args) {
        launch(args);
    }
}