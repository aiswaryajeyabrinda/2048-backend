package com.exponent.energy.TwentyFortyEight;

import java.util.ArrayList;
import java.util.List;

@org.springframework.stereotype.Service
public class Service {
        public Model move(Model model, String direction) {
            int[][] board = copyBoard(model.getBoard());
            int score = model.getScore();
            boolean moved = false;

            switch (direction.toLowerCase()) {
                case "up":
                    for (int j = 0; j < board.length; j++) {
                        int[] column = getColumn(board, j);
                        MoveResult result = moveLine(column);
                        if (result.moved) moved = true;
                        score += result.score;
                        setColumn(board, j, result.line);
                    }
                    break;
                case "down":
                    for (int j = 0; j < board.length; j++) {
                        int[] column = getColumn(board, j);
                        MoveResult result = moveLine(reverse(column));
                        if (result.moved) moved = true;
                        score += result.score;
                        setColumn(board, j, reverse(result.line));
                    }
                    break;
                case "left":
                    for (int i = 0; i < board.length; i++) {
                        MoveResult result = moveLine(board[i]);
                        if (result.moved) moved = true;
                        score += result.score;
                        board[i] = result.line;
                    }
                    break;
                case "right":
                    for (int i = 0; i < board.length; i++) {
                        MoveResult result = moveLine(reverse(board[i]));
                        if (result.moved) moved = true;
                        score += result.score;
                        board[i] = reverse(result.line);
                    }
                    break;
            }

            if (moved) {
                model.setBoard(board);
                model.setScore(score);
                model.addRandomTile();
                checkGameState(model);
            }

            return model;
        }

        private MoveResult moveLine(int[] line) {
            List<Integer> nonZero = new ArrayList<>();
            for (int value : line) {
                if (value != 0) nonZero.add(value);
            }

            List<Integer> merged = new ArrayList<>();
            boolean moved = false;
            int score = 0;
            int i = 0;

            while (i < nonZero.size()) {
                if (i < nonZero.size() - 1 && nonZero.get(i).equals(nonZero.get(i + 1))) {
                    int newValue = nonZero.get(i) * 2;
                    merged.add(newValue);
                    score += newValue;
                    i += 2;
                    moved = true;
                } else {
                    merged.add(nonZero.get(i));
                    i++;
                }
            }

            while (merged.size() < line.length) {
                merged.add(0);
            }

            int[] result = merged.stream().mapToInt(Integer::intValue).toArray();
            return new MoveResult(result, score, moved || merged.size() != nonZero.size());
        }

        private int[] getColumn(int[][] board, int col) {
            int[] column = new int[board.length];
            for (int i = 0; i < board.length; i++) {
                column[i] = board[i][col];
            }
            return column;
        }

        private void setColumn(int[][] board, int col, int[] column) {
            for (int i = 0; i < board.length; i++) {
                board[i][col] = column[i];
            }
        }

        private int[] reverse(int[] array) {
            int[] reversed = new int[array.length];
            for (int i = 0; i < array.length; i++) {
                reversed[i] = array[array.length - 1 - i];
            }
            return reversed;
        }

        private int[][] copyBoard(int[][] board) {
            int[][] copy = new int[board.length][board.length];
            for (int i = 0; i < board.length; i++) {
                System.arraycopy(board[i], 0, copy[i], 0, board.length);
            }
            return copy;
        }

        private void checkGameState(Model model) {
            int[][] board = model.getBoard();
            int boardSize = board.length;

            // Check for win
            for (int i = 0; i < boardSize; i++) {
                for (int j = 0; j < boardSize; j++) {
                    if (board[i][j] == 2048) {
                        model.setWon(true);
                        return;
                    }
                }
            }

            // Check for possible moves
            for (int i = 0; i < boardSize; i++) {
                for (int j = 0; j < boardSize; j++) {
                    if (board[i][j] == 0) return;
                    if (i < boardSize - 1 && board[i][j] == board[i + 1][j]) return;
                    if (j < boardSize - 1 && board[i][j] == board[i][j + 1]) return;
                }
            }

            model.setGameOver(true);
        }

        private static class MoveResult {
            int[] line;
            int score;
            boolean moved;

            MoveResult(int[] line, int score, boolean moved) {
                this.line = line;
                this.score = score;
                this.moved = moved;
            }
        }
    }