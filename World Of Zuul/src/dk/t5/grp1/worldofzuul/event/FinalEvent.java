package dk.t5.grp1.worldofzuul.event;

import dk.t5.grp1.worldofzuul.interaction.Interaction;
import dk.t5.grp1.worldofzuul.player.Player;
import dk.t5.grp1.worldofzuul.room.Room;

public class FinalEvent extends Event {

    public FinalEvent(Room room) {
        super("Now that you have planted all of the seeds, return to spawn to plant yourself!", room, 0);
    }

    @Override
    public void start(Player player, EventManager eventManager, Interaction interaction) {
        //update old tutorial tree to be an event npc, when this event starts
        getRoom().getNpc().setEventNpcUpdated(true);
        interaction.setInteracting(true);
    }
}
