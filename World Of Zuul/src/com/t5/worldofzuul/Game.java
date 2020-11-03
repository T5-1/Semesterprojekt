package com.t5.worldofzuul;

import com.t5.worldofzuul.command.CommandWord;
import com.t5.worldofzuul.command.Parser;
import com.t5.worldofzuul.room.*;
import com.t5.worldofzuul.player.Player;

public class Game
{
    private Parser parser;

    private Player player;

    public Game()
    {
        createRooms();
        parser = new Parser();
    }

    private void createRooms()
    {
        Room camp, cave, desert, flowerField, lake, mountain, river, savanna, shore, spawn, northernEntrance, southernEntrance;

        camp = new Camp("This is the Camp");
        cave = new Cave("This is the Cave");
        desert = new Desert("This is the desert");
        flowerField = new FlowerField("This is the FlowerField");
        lake = new Lake("This is the lake");
        mountain = new Mountain("This is the Mountain");
        river = new River("This is the River");
        savanna = new Savanna("This is the Savanna");
        shore = new Shore("This is the Shore");
        spawn = new Spawn("This is the Spawn");
        northernEntrance = new NorthernEntrance("This is the Northern Entrance");
        southernEntrance = new SouthernEntrance("This is the Southern Entrance");

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

        player = new Player(spawn);
    }

    public void play()
    {
        printWelcome();

        boolean finished = false;
        while (!finished) {
            if (player.isAlive()) {
                player.update();
                finished = player.getCommand().processCommand(player.getCommand(), player);
            }
            else {
                if (player.isRestartGame()) {
                    createRooms();
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
        System.out.println("Welcome to the World of Zuul!");
        System.out.println("World of Zuul is a new, incredibly boring adventure game.");
        System.out.println("Type '" + CommandWord.HELP + "' if you need help.");
        System.out.println();
        System.out.println(player.getCurrentRoom().getLongDescription());
    }

    public static void main(String[] args) {
            Game game = new Game();
            game.play();
    }
}
