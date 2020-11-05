package com.t5.worldofzuul.event;

import com.t5.worldofzuul.item.Seed;
import com.t5.worldofzuul.player.Player;
import com.t5.worldofzuul.room.Room;

public class NorthernEvent extends Event {

    public NorthernEvent(Room room){
        super("Oh no! a De-forester is cutting down all of the Trees and not planting them again!" +
                " Hurry to the Northern Entrance and set him straight!", room);
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
