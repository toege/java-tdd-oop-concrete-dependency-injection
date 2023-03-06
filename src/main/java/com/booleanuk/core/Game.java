package com.booleanuk.core;

public class Game {
    public String name;

    public Game(String name) {
        this.name = name;
    }

    public String start() {
        return "Playing " + this.name;
    }
}
