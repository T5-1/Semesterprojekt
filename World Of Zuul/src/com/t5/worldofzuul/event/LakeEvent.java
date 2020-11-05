package com.t5.worldofzuul.event;

import com.t5.worldofzuul.player.Player;
import com.t5.worldofzuul.room.Room;

public class LakeEvent extends Event {

    public LakeEvent(Room room) {
        super("You consume the water & sun, but nothing happens? Maybe try going to the lake, to get enough water", room);
    }

    @Override
    public void start(Player player, EventManager eventManager) {
        boolean answered = false;
        String answer;
        System.out.println(getRoom().getNpc().getInfo());
        System.out.println("1: answer 1");
        System.out.println("2: answer 2");
        System.out.println("3: answer 3");
        System.out.println("4: answer 4");

        while (!answered) {
            answer = getScanner().nextLine();
            if (answer.equals("1")) {
                System.out.println("That's right");
                player.levelUp();
                answered = true;
                eventManager.setLakeEventPlayed(true);
            } else if (answer.equals("2")) {
                System.out.println("That's wrong");
            } else if (answer.equals("3")) {
                System.out.println("That's wrong");
            } else if (answer.equals("4")) {
                System.out.println("That's wrong");
            } else {
                System.out.println("Not an option");
            }
        }
    }
}
