package com.example.vovas.vvscrossnull;

/**
 * Created by Vovas on 27.06.2016.
 */

public class Game {

    private boolean isPlayerXGoes, isGameEnd;
    private Tile field[][] = new Tile[3][3];
    private int numOfNotEmpty = 0;

    public Game() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                field[i][j] = Tile.EMPTY;
            }
        }
    }

    public Tile getTile(int i, int j) {
        if (Math.abs(i) < 3 && Math.abs(j) < 3) {
            return field[i][j];
        } else {
            return Tile.EMPTY;
        }
    }

    public String step(int i, int j) {
        if (!isGameEnd && field[i][j] == Tile.EMPTY) {
            if (isPlayerXGoes) {
                field[i][j] = Tile.CROSS;
                isPlayerXGoes = false;
                numOfNotEmpty++;
                return "X";
            } else {
                field[i][j] = Tile.NULIK;
                isPlayerXGoes = !isPlayerXGoes;
                numOfNotEmpty++;
                return "O";
            }
        }
        return " ";
    }

    public String currentPlayer() {
        if (isPlayerXGoes) {
            return "Player X goes";
        } else {
            return "Player O goes";
        }
    }

    private boolean threeSameTiles(int x1, int y1, int x2, int y2, int x3, int y3) {
        return (field[x1][y1] == field[x2][y2] && field[x1][y1] == field[x3][y3]);
    }

    public GameState currentGameState() {

        for (int i = 0; i < 3; i++) {
            if (threeSameTiles(i, 0, i, 1, i, 2)) {
                if (field[i][0] == Tile.CROSS) {
                    isGameEnd = true;
                    return GameState.WON_X;
                }
                if (field[i][0] == Tile.NULIK) {
                    isGameEnd = true;
                    return GameState.WON_O;
                }
            }
            if (threeSameTiles(0, i, 1, i, 2, i)) {
                if (field[0][i] == Tile.CROSS) {
                    isGameEnd = true;
                    return GameState.WON_X;
                }
                if (field[0][i] == Tile.NULIK) {
                    isGameEnd = true;
                    return GameState.WON_O;
                }
            }
        }
        if (threeSameTiles(0, 0, 1, 1, 2, 2)) {
            if (field[1][1] == Tile.CROSS) {
                isGameEnd = true;
                return GameState.WON_X;
            }
            if (field[1][1] == Tile.NULIK) {
                isGameEnd = true;
                return GameState.WON_O;
            }
        }
        if (threeSameTiles(0, 2, 1, 1, 2, 0)) {
            if (field[1][1] == Tile.CROSS) {
                isGameEnd = true;
                return GameState.WON_X;
            }
            if (field[1][1] == Tile.NULIK) {
                isGameEnd = true;
                return GameState.WON_O;
            }
        }
        if (numOfNotEmpty == 9) {
            isGameEnd = true;
            return GameState.DRAW;
        }
        return GameState.CONTINUE;
    }

}
