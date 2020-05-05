package com.example.test.game;

public class WinnerCheckerHorizontal implements WinnerInterface {
    private Game game;

    WinnerCheckerHorizontal(Game game) {
        this.game = game;
    }

    public Player checkWinner() {
        Board[][] field = game.getField();
        Player currPlayer;
        Player lastPlayer;
        for (Board[] boards : field) {
            lastPlayer = null;
            int successCounter = 1;
            for (Board board : boards) {
                currPlayer = board.getPlayer();
                if (currPlayer != null && currPlayer == lastPlayer) {
                    successCounter++;
                    if (successCounter == boards.length) {
                        return currPlayer;
                    }
                }
                lastPlayer = currPlayer;
            }
        }
        return null;
    }
}
