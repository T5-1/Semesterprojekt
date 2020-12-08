package dk.t5.grp1.worldofzuul.event;

import dk.t5.grp1.worldofzuul.interaction.Interaction;
import dk.t5.grp1.worldofzuul.player.Player;
import dk.t5.grp1.worldofzuul.room.Room;

public class FinalEvent extends Event {

    public FinalEvent(Room room) {
        super("You have planted all of the seeds! Now return to spawn!", room);
    }

    @Override
    public void start(Player player, EventManager eventManager, Interaction interaction) {
        getRoom().getNpc().setEventNpcUpdated(true);
        interaction.setInteracting(true);
    }
}
