/**
 * This class is part of the "World of Zuul" application.
 * "World of Zuul" is a very simple, text based adventure game.
 * <p>
 * This class holds information about a command that was issued by the user.
 * A command currently consists of two parts: a CommandWord and a string
 * (for example, if the command was "take map", then the two parts
 * are TAKE and "map").
 * <p>
 * The way this is used is: Commands are already checked for being valid
 * command words. If the user entered an invalid command (a word that is not
 * known) then the CommandWord is UNKNOWN.
 * <p>
 * If the command had only one word, then the second word is <null>.
 *
 * @author Michael Kolling and David J. Barnes
 * @version 2006.03.30
 */

package com.t5.worldofzuul;

import com.t5.worldofzuul.player.Player;
import com.t5.worldofzuul.room.Room;

public class Command {
    private CommandWord commandWord;
    private String secondWord;
    private Parser parser;

    public Command(CommandWord commandWord, String secondWord) {
        this.commandWord = commandWord;
        this.secondWord = secondWord;
    }

    public CommandWord getCommandWord() {
        return commandWord;
    }

    public String getSecondWord() {
        return secondWord;
    }

    public boolean isUnknown() {
        return (commandWord == CommandWord.UNKNOWN);
    }

    public boolean hasSecondWord() {
        return (secondWord != null);
    }


    public boolean processCommand(Command command, Player player) {
        boolean wantToQuit = false;

        CommandWord commandWord = command.getCommandWord();
        if (!player.isAlive()) {
            parser = new Parser();
            boolean validAnswer = false;
            while (!validAnswer) {
                if (commandWord == CommandWord.YES) {
                    player.setRestartGame(true);
                    validAnswer = true;
                } else if (commandWord == CommandWord.NO) {
                    endGame();
                    validAnswer = true;
                } else if (commandWord == CommandWord.UNKNOWN) {
                    System.out.println("I don't know what you mean...");
                    System.out.println("Do you want to restart the game? (yes/no)");
                    command = parser.getCommand();
                    commandWord = command.getCommandWord();
                }
            }
        }

        else if (commandWord == CommandWord.UNKNOWN || commandWord == CommandWord.YES || commandWord == CommandWord.NO) {
            System.out.println("I don't know what you mean...");
            return false;
        }

        if (commandWord == CommandWord.HELP) {
            printHelp(player);
        } else if (commandWord == CommandWord.GO) {
            goRoom(command, player);
        } else if (commandWord == CommandWord.GATHER) {
            player.gather();
        } else if (commandWord == CommandWord.INVENTORY) {
            player.getInventory().printInventory();
        } else if (commandWord == CommandWord.CONSUME) {
            player.consume();
        } else if (commandWord == CommandWord.QUIT) {
            wantToQuit = quit(command);
        } else if (commandWord == CommandWord.MAP) {
            getMap();
        }

        return wantToQuit;
    }

    public void restartGame(Player player) {
        System.out.println("You are dead, do you want to restart the game? (yes/no)");
    }

    public void endGame() {
        System.out.println("Thank you for playing.  Good bye.");
    }

    private void printHelp(Player player) {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around at the university.");
        System.out.println();
        System.out.println("Your command words are:");
        player.getParser().showCommands();
    }

    private void getMap(){
        System.out.println(" ------------------------------------------------------------------- ");
        System.out.println("|                                 |NorthernEntrance|                |");
        System.out.println("|-------------------------------------------------------------------|");
        System.out.println("|                |  FlowerField   |     River      |      Cave      |");
        System.out.println("|-------------------------------------------------------------------|");
        System.out.println("|      Lake      |     Shore      |     Spawn      |    Mountain    |");
        System.out.println("| ------------------------------------------------------------------|");
        System.out.println("|                |    Savanna     |      Camp      |     Desert     |");
        System.out.println("|-------------------------------------------------------------------|");
        System.out.println("|                                 |SouthernEntrance|                |");
        System.out.println(" ------------------------------------------------------------------- ");
    }

    private void goRoom(Command command, Player player) {
        if (!command.hasSecondWord()) {
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        Room nextRoom = player.getCurrentRoom().getExit(direction);

        if (nextRoom == null) {
            System.out.println("There is no door!");
        } else {
            player.setCurrentRoom(nextRoom);
            System.out.println(player.getCurrentRoom().getLongDescription());
        }
    }

    private boolean quit(Command command) {
        if (command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        } else {
            return true;
        }
    }
}

