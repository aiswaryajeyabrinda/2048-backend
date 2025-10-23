package com.exponent.energy.TwentyFortyEight;

import java.util.ArrayList;
import java.util.List;

public class Model {
    private int[][] board;
    private int score;
    private boolean gameOver;
    private boolean won;
    private int boardSize;

    public Model(int boardSize) {
        this.boardSize = boardSize;
        this.board = new int[boardSize][boardSize];
        this.score = 0;
        this.gameOver = false;
        this.won = false;
        initializeBoard();
    }

    public int[][] getBoard() {
        return board;
    }

    public void setBoard(int[][] board) {
        this.board = board;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    public boolean isWon() {
        return won;
    }

    public void setWon(boolean won) {
        this.won = won;
    }

    public int getBoardSize() {
        return boardSize;
    }

    public void setBoardSize(int boardSize) {
        this.boardSize = boardSize;
    }

    private void initializeBoard() {
        addRandomTile();
        addRandomTile();
    }

    public void addRandomTile() {
        List<int[]> emptyCells = new ArrayList<>();
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                if (board[i][j] == 0) {
                    emptyCells.add(new int[]{i, j});
                }
            }
        }
        if (!emptyCells.isEmpty()) {
            int[] cell = emptyCells.get((int) (Math.random() * emptyCells.size()));
            board[cell[0]][cell[1]] = Math.random() < 0.9 ? 2 : 4;
        }
    }
}
