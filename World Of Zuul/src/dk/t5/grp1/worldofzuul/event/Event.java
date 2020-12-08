package dk.t5.grp1.worldofzuul.event;

import dk.t5.grp1.worldofzuul.interaction.Interaction;
import dk.t5.grp1.worldofzuul.player.Player;
import dk.t5.grp1.worldofzuul.room.Room;

import java.util.Scanner;

public abstract class Event {

    private String description;

    private boolean answered = false;
    private int answer = -1;
    private int correctAnswer = -1;

    private Room room;

    public Event(String description, Room room){
        this.description = description;
        this.room = room;
    }

    public void update() {
        if (answer == correctAnswer) {
            answered = true;
        }
    }

    public String getDescription() {
        return description;
    }

    public Room getRoom() {
        return room;
    }

    public boolean isAnswered() {
        return answered;
    }

    public void setAnswered(boolean answered) {
        this.answered = answered;
    }

    public int getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(int correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public int getAnswer() {
        return answer;
    }

    public void setAnswer(int answer) {
        this.answer = answer;
    }

    public abstract void start(Player player, EventManager eventManager, Interaction interaction);
}
