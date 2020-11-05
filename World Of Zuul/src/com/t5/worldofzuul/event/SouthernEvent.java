package com.t5.worldofzuul.event;

import com.t5.worldofzuul.item.Seed;
import com.t5.worldofzuul.player.Player;
import com.t5.worldofzuul.room.Room;

public class SouthernEvent extends Event {

    public SouthernEvent(Room room) {
        super("Oh no! There is an uncontrollable Forest Fire at the Southern Entrance! Hurry!", room);
        super.setActionsLeft(4);
    }

    @Override
    public void start(Player player) {
        boolean answered = false;
        String answer;
        System.out.println(super.getRoom().getNpc().getInfo());
        System.out.println("1: answer 1");
        System.out.println("2: answer 2");
        System.out.println("3: answer 3");
        System.out.println("4: answer 4");

        while (!answered) {
            answer = getScanner().nextLine();
            if (answer.equals("1")) {
                System.out.println("That's right, here take these two seeds");
                for (int i = 0; i < 2; i++) {
                    player.getInventory().add(new Seed());
                }
                answered = true;
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
