package com.booleanuk.core;

import java.util.ArrayList;

public class Computer {
    public ArrayList<Game> installedGames = new ArrayList<>();

    public void turnOn() {
        PowerSupply psu = new PowerSupply();
        psu.turnOn();
    }

    public void installGame() {
        Game game = new Game("Morrowind");
        this.installedGames.add(game);
    }

    public String playGame() {
        for (Game g : this.installedGames) {
            if (g.name.equals("Morrowind")) {
                return g.start();
            }
        }

        return "Game not installed";
    }
}
