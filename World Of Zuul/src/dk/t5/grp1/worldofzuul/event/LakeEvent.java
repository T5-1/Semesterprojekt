package dk.t5.grp1.worldofzuul.event;

import dk.t5.grp1.worldofzuul.interaction.Interaction;
import dk.t5.grp1.worldofzuul.player.Player;
import dk.t5.grp1.worldofzuul.room.Room;

public class LakeEvent extends Event {

    public LakeEvent(Room room) {
        super("You consume the water & sun, but nothing happens? Maybe try going to the lake, to get enough water", room, 2);
    }

    @Override
    public void start(Player player, EventManager eventManager, Interaction interaction) {
        interaction.setInteracting(true);
    }
}
