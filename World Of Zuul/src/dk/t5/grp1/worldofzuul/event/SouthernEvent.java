package dk.t5.grp1.worldofzuul.event;

import dk.t5.grp1.worldofzuul.interaction.Interaction;
import dk.t5.grp1.worldofzuul.item.Seed;
import dk.t5.grp1.worldofzuul.player.Player;
import dk.t5.grp1.worldofzuul.room.Room;

public class SouthernEvent extends Event {

    public SouthernEvent(Room room) {
        super("Oh no! There is an uncontrollable Forest Fire at the Southern Entrance! Hurry!", room, 1);
    }

    @Override
    public void start(Player player, EventManager eventManager, Interaction interaction) {
        interaction.setInteracting(true);
    }
}
