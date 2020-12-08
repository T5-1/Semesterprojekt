package dk.t5.grp1.worldofzuul.event;

import dk.t5.grp1.worldofzuul.interaction.Interaction;
import dk.t5.grp1.worldofzuul.item.Seed;
import dk.t5.grp1.worldofzuul.player.Player;
import dk.t5.grp1.worldofzuul.room.Room;

public class NorthernEvent extends Event {

    public NorthernEvent(Room room){
        super("Oh no! a De-forester is cutting down all of the Trees and not planting them again!" +
                " Hurry to the Northern Entrance and set him straight!", room);
    }

    @Override
    public void start(Player player, EventManager eventManager, Interaction interaction) {
        interaction.setInteracting(true);
    }
}
