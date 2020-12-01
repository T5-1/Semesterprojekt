package dk.t5.grp1.worldofzuul.interaction;

import dk.t5.grp1.worldofzuul.graphics.Screen;
import dk.t5.grp1.worldofzuul.input.Keyboard;
import javafx.scene.text.Text;

public class Interaction {

    private boolean interacting;

    public Interaction() {
        interacting = false;
    }

    public void update(Keyboard key) {
        if (key.interact) {
            interacting = true;
        }


    }

    public void render(Screen screen) {
        Text text = new Text(10, 50, "test");
        if (interacting) {
            screen.renderDialogueBox();
        }
    }

    public boolean isInteracting() {
        return interacting;
    }

}
