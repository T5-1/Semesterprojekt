package dk.t5.grp1.worldofzuul.input;

import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class Keyboard {

    public boolean up, down, left, right, interact;
    private Scene scene;

    public Keyboard(Scene scene) {
        this.scene = scene;
    }

    public void update() // Checks whether or not specific keys are being used
    {
        scene.addEventHandler(KeyEvent.KEY_PRESSED, (keyEvent -> {
            if (keyEvent.getCode() == KeyCode.W || keyEvent.getCode() == KeyCode.UP) up = true;
            if (keyEvent.getCode() == KeyCode.S || keyEvent.getCode() == KeyCode.DOWN) down = true;
            if (keyEvent.getCode() == KeyCode.A || keyEvent.getCode() == KeyCode.LEFT) left = true;
            if (keyEvent.getCode() == KeyCode.D || keyEvent.getCode() == KeyCode.RIGHT) right = true;
            if (keyEvent.getCode() == KeyCode.SPACE || keyEvent.getCode() == KeyCode.ENTER) interact = true;
        }));

        scene.addEventHandler(KeyEvent.KEY_RELEASED, (keyEvent -> {
            if (keyEvent.getCode() == KeyCode.W || keyEvent.getCode() == KeyCode.UP) up = false;
            if (keyEvent.getCode() == KeyCode.S || keyEvent.getCode() == KeyCode.DOWN) down = false;
            if (keyEvent.getCode() == KeyCode.A || keyEvent.getCode() == KeyCode.LEFT) left = false;
            if (keyEvent.getCode() == KeyCode.D || keyEvent.getCode() == KeyCode.RIGHT) right = false;
            if (keyEvent.getCode() == KeyCode.SPACE || keyEvent.getCode() == KeyCode.ENTER) interact = false;
        }));
    }
}
