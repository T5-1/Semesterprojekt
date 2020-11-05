package com.t5.worldofzuul;

import com.t5.worldofzuul.command.CommandWord;
import com.t5.worldofzuul.command.Parser;
import com.t5.worldofzuul.event.EventManager;
import com.t5.worldofzuul.room.*;
import com.t5.worldofzuul.player.Player;

public class Game
{
    private Parser parser;
    private Player player;
    private EventManager eventManager;
    private Room camp, cave, desert, flowerField, lake, mountain, river, savanna, shore, spawn, northernEntrance, southernEntrance;

    public Game()
    {
        createRooms();
        parser = new Parser();
        player = new Player(spawn);
        eventManager = new EventManager(spawn, lake, northernEntrance, southernEntrance);
    }

    private void createRooms()
    {
        camp = new Camp("at the Camp", "Camp");
        cave = new Cave("at the Cave", "Cave");
        desert = new Desert("at the desert", "Desert");
        flowerField = new FlowerField("at the FlowerField", "Flower Field");
        lake = new Lake("at the lake", "Lake");
        mountain = new Mountain("on the Mountain", "Mountain");
        river = new River("at the River", "River");
        savanna = new Savanna("at the Savanna", "Savanna");
        shore = new Shore("at the Shore", "Shore");
        spawn = new Spawn("at the Spawn", "Spawn");
        northernEntrance = new NorthernEntrance("at the Northern Entrance", "Northern Entrance");
        southernEntrance = new SouthernEntrance("at the Southern Entrance", "Southern Entrance");

        camp.setExit("west", savanna);
        camp.setExit("north",spawn);
        camp.setExit("east",desert);
        camp.setExit("south",southernEntrance);

        cave.setExit("west",river);
        cave.setExit("south", mountain);

        desert.setExit("west", camp);
        desert.setExit("north", mountain);

        flowerField.setExit("east", river);
        flowerField.setExit("south", shore);

        lake.setExit("east", shore);

        mountain.setExit("north", cave);
        mountain.setExit("west",spawn);
        mountain.setExit("south", desert);

        river.setExit("north", northernEntrance);
        river.setExit("west", flowerField);
        river.setExit("east", cave);
        river.setExit("south", spawn);

        savanna.setExit("north", shore);
        savanna.setExit("east", camp);

        shore.setExit("north", flowerField);
        shore.setExit("west", lake);
        shore.setExit("east", spawn);
        shore.setExit("south", savanna);

        spawn.setExit("north", river);
        spawn.setExit("west", shore);
        spawn.setExit("east", mountain);
        spawn.setExit("south",camp);

        northernEntrance.setExit("south", river);

        southernEntrance.setExit("north", camp);
    }

    public void play()
    {
        printWelcome();

        boolean finished = false;
        while (!finished) {
            if (player.isAlive()) {
                player.update();
                finished = player.getCommand().processCommand(player.getCommand(), player);
                eventManager.update(player);
            }
            else {
                if (player.isRestartGame()) {
                    createRooms();
                    player = new Player(spawn);
                    printWelcome();
                }
                else {
                    finished = true;
                }
            }
        }
    }

    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome to **insert game name**!");
        System.out.println("In this game you are going to learn about the forest while saving it!");
        System.out.println("To find out what you need to do, go talk to the Old Tutorial Tree!");
        System.out.println("Type '" + CommandWord.HELP + "' if you need help.");
        System.out.println();
        System.out.println(player.getCurrentRoom().getLongDescription(player));
    }

    public static void main(String[] args) {
            Game game = new Game();
            game.play();
    }
}
