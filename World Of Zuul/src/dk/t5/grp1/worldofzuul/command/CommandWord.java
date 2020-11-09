package dk.t5.grp1.worldofzuul.command;

public enum CommandWord
{
    GO("go"), QUIT("quit"), HELP("help"), GATHER("gather"),
    INVENTORY("inventory"), CONSUME("consume"), UNKNOWN("?"),
    YES("yes"), NO("no"), MAP("map"), INTERACT("interact"),
    PLANT("plant");

    private String commandString;

    CommandWord(String commandString)
    {
        this.commandString = commandString;
    }

    public String toString()
    {
        return commandString;
    }
}
