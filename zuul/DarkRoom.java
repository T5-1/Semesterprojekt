import java.util.ArrayList;

/**
 * DarkRoom: if you don't have the flashlight,
 * you can't do anything except exit via the exitdir.
 * Note that the flashlight can be lit *only* in the DarkRoom.
 * 
 * @author (Peter Dordal) 
 * @version (Spring 2006)
 */
public class DarkRoom extends Room
{
    // instance variables - replace the example below with your own
    private String exitdir;
    private ArrayList<Item> inventory;

    /**
     * If you don't have a light, the only visible way out is exitdir
     */
    public DarkRoom(String exitdir, String desc, ArrayList<Item> inv)
    {
        super(desc);
        this.exitdir = exitdir;
        this.inventory = inv;
    }

    /**
     * getShortDescription prints the dark description if there is no light.
     */
    public String getShortDescription() {
        if (Light.haslight(inventory)) 
            return super.getShortDescription();
        String desc = "in a pitch-dark place! You need a light!";
        return desc;
    }
    
    /**
     * getLongDescription doesn't print all the exits if it's dark, or the contents.
     */
    public String getLongDescription() {
        if (Light.haslight(inventory))
            return super.getLongDescription();
        String desc1 = getShortDescription();
        String desc2 = "The only way out you can see is a dimly lit exit to the " + exitdir;
        return desc1 + "\n" + desc2;
    }

    /**
     * respond: LIGHT FLASHLIGHT and TAKE <anything> (TAKE is disabled in the dark!)
     * Arguably LIGHT FLASHLIGHT should be moved to elsewhere.
     * Class demo: Respond to LIGHT <anything>, where we check if
     * <anything> is on the inventory and, if so, if it's a Light.
     * GO only allows going out the exitdir if we don't have the light.
     * Note that this is NOT necessarily the same as the way we came in!
     * What would it take to implement the latter?
     */
    public Room respond(Command c, ArrayList<Item> inventory) {
        String commandWord = c.getCommandWord();
        String object = c.getSecondWord();
        if (commandWord.equals("go") 
            && !object.equals(exitdir)
            && !Light.haslight(inventory) )
        {
            System.out.println("It's too dark to see any exit that way!");
            return null;
        }
        
        if (commandWord.equals("light")) {
            Item f = Item.findByName(object, inventory);
            if (f == null) {
                System.out.println("you don't have the " + object);
                return null;
            }
            if (f instanceof Light) {
                Light fl = (Light) f;
                fl.light();
                System.out.println("The " + object + " is now lit");
                System.out.println(getLongDescription());
                return null;
            } else {
                System.out.println("You can't light the " + object + "!");
                return null;
            }
            
        } 
        if (commandWord.equals("take")) {
            if (Light.haslight(inventory)) { 
                return super.respond(c, inventory);
            } else {
                System.out.println("It's too dark to see anything to take!");
                return null;
            }
        } 
        
        return super.respond(c, inventory);
        
    }
    
    /* */
}
