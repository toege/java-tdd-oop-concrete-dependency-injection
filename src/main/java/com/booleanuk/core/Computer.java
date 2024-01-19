package com.booleanuk.core;

import java.util.ArrayList;

public class Computer {
    public ArrayList<Game> installedGames = new ArrayList<>();
    PowerSupply psu;

    public Computer(PowerSupply psu) {
        this.psu = psu;
    }

    public Computer(PowerSupply psu, ArrayList<Game> installedGames) {
        this.psu = psu;
        this.installedGames = installedGames;
    }

    public void turnOn() {
        psu.turnOn();
    }

    public void installGame(Game game) {
        this.installedGames.add(game);
    }

    public String playGame(String title) {
        for (Game g : this.installedGames) {
            if (g.name.equals(title)) {
                return g.start();
            }
        }

        return "Game not installed";
    }
}
