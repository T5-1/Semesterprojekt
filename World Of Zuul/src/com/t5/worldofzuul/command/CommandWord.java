package com.t5.worldofzuul.command;

public enum CommandWord
{
    GO("go"), QUIT("quit"), HELP("help"), GATHER("gather"),
    INVENTORY("inventory"), CONSUME("consume"), UNKNOWN("?"),
    YES("yes"), NO("no"), MAP("map");

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
