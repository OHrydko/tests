package com.example.test.game;

public class Game {

    // players
    private Player[] players;

    // board
    private Board[][] field;

    // did a game start?
    private boolean started;

    // current player
    private Player activePlayer;

    // all fill fields
    private int filled;

    // all field
    private int boardCount;
    private WinnerInterface[] winnerCheckers;


    public Game() {
        field = new Board[3][3];
        boardCount = 0;
        winnerCheckers = new WinnerInterface[4];
        winnerCheckers[0] = new WinnerCheckerHorizontal(this);
        winnerCheckers[1] = new WinnerCheckerVertical(this);
        winnerCheckers[2] = new WinnerCheckerDiagonalLeft(this);
        winnerCheckers[3] = new WinnerCheckerDiagonalRight(this);
        //fill a board
        for (int i = 0, l = field.length; i < l; i++) {
            for (int j = 0, l2 = field[i].length; j < l2; j++) {
                field[i][j] = new Board();
                boardCount++;
            }
        }
        players = new Player[2];
        started = false;
        activePlayer = null;
        filled = 0;
    }

    public void start() {
        resetPlayers();
        started = true;
    }

    private void resetPlayers() {
        players[0] = new Player("X");
        players[1] = new Player("O");
        setCurrentActivePlayer(players[0]);
    }

    private void setCurrentActivePlayer(Player player) {
        activePlayer = player;
    }

    public Board[][] getField() {
        return field;
    }

    public boolean makeTurn(int x, int y) {
        if (field[x][y].isFilled()) {
            return false;
        }
        field[x][y].fill(getCurrentActivePlayer());
        filled++;
        switchPlayers();
        return true;
    }

    private void switchPlayers() {
        activePlayer = (activePlayer == players[0]) ? players[1] : players[0];
    }

    public Player getCurrentActivePlayer() {
        return activePlayer;
    }

    public boolean isFieldFilled() {
        return boardCount == filled;
    }

    public void reset() {
        resetField();
        resetPlayers();
    }

    private void resetField() {
        for (Board[] boards : field) {
            for (Board board : boards) {
                board.fill(null);
            }
        }
        filled = 0;
    }

    public Player checkWinner() {
        for (WinnerInterface winChecker : winnerCheckers) {
            Player winner = winChecker.checkWinner();
            if (winner != null) {
                return winner;
            }
        }
        return null;
    }
}

