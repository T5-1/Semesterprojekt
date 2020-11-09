package dk.t5.grp1.worldofzuul.event;

import dk.t5.grp1.worldofzuul.player.Player;
import dk.t5.grp1.worldofzuul.room.Room;

public class FinalEvent extends Event {

    public FinalEvent(Room room) {
        super("You have planted all of the seeds! Now return to spawn!", room);
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
                answered = true;
                eventManager.setFinalEventPlayed(true);
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
