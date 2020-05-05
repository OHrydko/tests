package com.example.test.game;

public class Board {
    private Player player;

    void fill(Player player) {
        this.player = player;
    }

    boolean isFilled() {
        return player != null;
    }

    public Player getPlayer() {
        return player;
    }
}
