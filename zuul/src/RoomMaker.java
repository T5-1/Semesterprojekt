import java.util.ArrayList;

/**
 * Class RoomMaker contains a single public method, roomMaker(),
 * that builds the maze of rooms and returns the start room.
 *
 * @author (Peter Dordal)
 * @version (Nov 7, 2006)
 */
public class RoomMaker {
    /**
     * Constructor for objects of class RoomMaker
     */
    public RoomMaker() {
    }

    /**
     * createRooms() creates all the rooms, links them, and populates them with Items.
     * It is static
     */
    public static Room createRooms(ArrayList<Item> inventory) {
        Room outside;
        Room pub, lab, office,
                preproom = null,
                theatre,
                steamtunnel;
        /**
         *                      zoolab              preproom
         *                         |                   |     \
         *                         |                   |      \
         *       pub------------outside -------------theatre   \
         *           \             |                            \
         *            \            |                             basement3
         *             \           |                                       \
         *              \         lab--------------office                   \
         *               \       /                                           \
         *            steamtunnel -- basement1--basement2--basementlab        \
         *                                                       \             \
         *                                                        \             \
         *                                                      valveroom        \
         *                                                          \          rocktunnel
         *                                                           \       ___/   |
         *                                                        bottomroom/       |
         *                                                                          |
         *                                                                       darkroom
         *                                                                          |
         *                                                                       gemroom
         */
        // create the rooms
        outside = new Room("outside the main entrance of the university");
        outside.addItem(new Item("key"));       // cheat

        theatre = new Room("in a lecture theatre");
        theatre = new LockedRoom("in a lecture theatre", "north");
        theatre.addItem(new Item("pencil"));

        pub = new Room("in the campus pub");
        pub.addItem(new Item("water", 3));

        lab = new Room("in a computing lab");

        office = new Room("in the computing admin office");
        office.addItem(new Item("memo", 1));
        //       office.addItem(new Item("laptop", 7));
        office.addItem(new Light("flashlight"));
        //    office.addItem(new Item("cookie", 1));

        steamtunnel = new SerpentRoom("in a dark steam tunnel beneath campus"
                + "\nThe door just snapped shut above you"
                + "\nA giant serpent is coiled before you!!!");

        Room basement1 = new Room("in a dark basement somewhere");
        Room basement2 = new Room("in a basement somewhere. Dim light and a strange hum"
                + "\ncomes from the north");

        Room basementlab = null;
        Room valveroom = null;
        Room bottomroom = null;
        Room rocktunnel = null;
        Room basement3 = null;

        link(steamtunnel, "east", basement1, "west");
//        steamtunnel.setExit("east", basement1);
//        basement1.setExit("west", steamtunnel);

        link(basement1, "north", basement2, "south");
//        basement1.setExit("north", basement2);
//        basement2.setExit("south", basement1);


        basementlab = new Room("in a huge underground chamber that contains enormous apparatus"
                + "\nfrom a long-ago experiment. At one end of the room"
                + "\nis a pool of water that, in the dim light, looks very deep."
                + "\nThere is also a damp metal stairway leading down");
//        basementlab.setExit("east", basement3);
        basementlab.addItem(new Item("glowing_donut"));

        link(basement2, "north", basementlab, "south");

        valveroom = new ValveRoom(true, "down",
                "in a deeper part of the basement, below the experiment chamber."
                        + "\nThere are many pipes here, and a huge valve.",
                "You find that the steps are flooded with water"
                        + "\nYou need to find a way to drain the water"
        );

        link(basementlab, "down", valveroom, "up");

        bottomroom = new Room("you are deep in the sub-basement"
                + "\nThe bare-rock walls are dripping with water."
                + "\nA tunnel angles south and slightly upwards");

        link(valveroom, "down", bottomroom, "up");
//      rocktunnel = new Room("in a long sloping rock tunnel, very wet at the north end"
//                            + "\nA ladder leads up from the middle");
        rocktunnel = new ValveRoom(false, "north",
                "in a long sloping rock tunnel, very wet at the north end",
                "Hmm...the north end is MUCH too wet to allow progress"
        );

        Room darkroom = new DarkRoom("north",
                "A cavern room with stalactites and stalagmites",
                inventory      //weird constructor!
        );
        darkroom.addItem(new Item("goldcoin"));              // hmm... will this work?
        Room gemroom = new Room("A small cavern room");
        gemroom.addItem(new Item("bag_of_gems"));

        link(bottomroom, "south", rocktunnel, "north");
        link(rocktunnel, "south", darkroom, "north");
        link(darkroom, "south", gemroom, "north");

        basement3 = new Room("in a crawlspace under the lecture room"
                + "\nAbove you are the undersides of the seats");
        link(rocktunnel, "up", basement3, "down");


//=====================================================================

        preproom = new Room("Lecture preparation room");
        preproom.addItem(new Item("tuition_vouchers"));
        preproom.addItem(new Light("torch"));       // pld new
        //       basement3.setExit("up", preproom);
        link(basement3, "up", preproom, "down");

        Room zoolab = new ZooRoom("You are in the zoological laboratory"
                + "\nThere are cages everywhere.",
                inventory
        );
        // initialise room exits
        outside.setExit("east", theatre);
        outside.setExit("south", lab);
        outside.setExit("west", pub);
        link(outside, "north", zoolab, "south");

        theatre.setExit("west", outside);
        theatre.setExit("north", preproom);

        pub.setExit("east", outside);

        lab.setExit("north", outside);
        lab.setExit("east", office);
        lab.setExit("down", steamtunnel);

        office.setExit("west", lab);

        steamtunnel.setExit("up", pub);
        // steamtunnel.addItem(new Item("key"));    // added dynamically LATER!!!

        preproom.setExit("south", theatre);

        //currentRoom = outside;  // start game outside

//      inventory.add(new Item("cellphone"));

        Room dRoom = new DustyRoom("You are in a very dusty room.\nThe dust is thick and lumpy on the floor.");
        dRoom.addItem(new Item("broom"));
        link(dRoom, "south", preproom, "north");   // DustyRoom

        inventory.add(new Light("flashlight", 4));
        inventory.add(new Item("cookie", 2));

        return outside;
    }

    /**
     * link: creates a link between rooms r1 and r2:
     * From r1, going dir1, we get to r2, and from r2, going dir2, we get to r1
     */
    private static void link(Room r1, String dir1, Room r2, String dir2) {
        r1.setExit(dir1, r2);
        r2.setExit(dir2, r1);
    }


}
