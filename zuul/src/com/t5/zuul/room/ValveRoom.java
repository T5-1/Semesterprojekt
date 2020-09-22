package com.t5.zuul.room;

import com.t5.zuul.command.Command;
import com.t5.zuul.item.Item;

import java.util.ArrayList;

/**
 * The name is something of a misnomer.
 * Only one room, of a set, has an actual valve.
 * The others allow the direction "wetdirection" only if the valve is open.
 * "wetproblem" is a description of the wetness
 * <p>
 * Note that valve_open MUST BE STATIC!!!
 *
 * @author Peter Dordal
 * @version April 1
 */
public class ValveRoom extends Room {
    // instance variables - replace the example below with your own
    private static boolean valve_open = false;
    private boolean hasvalve;
    private String wetdirection, wetproblem;

    /**
     * Constructor for objects of class DragonRoom
     */
    public ValveRoom(boolean hasvalve, String wetdirection,
                     String description, String wetproblem) {
        super(description);
        this.hasvalve = hasvalve;
        this.wetdirection = wetdirection;
        this.wetproblem = wetproblem;
        valve_open = false;
    }

    /**
     * ValveRoom.respond: special commands are GO wetdirection, TURN VALVE
     */
    public Room respond(Command c, ArrayList<Item> inv) {
        String commandWord = c.getCommandWord();
        String object = c.getSecondWord();
        if (commandWord.equals("go")
                && object.equals(wetdirection)
                && !valve_open) {
            System.out.println(wetproblem);
            return null;
        }
        if (hasvalve && commandWord.equals("turn")) {
            if (object.equals("valve")) {
                valve_open = true;
                System.out.println("There is an enormous sound of water in the pipes below");
                return null;
            } else {
                System.out.println("You can't turn that!");
                return null;
            }
        }
        return super.respond(c, inv);
    }

    private boolean valveopen() {
        return valve_open;
    }

}
