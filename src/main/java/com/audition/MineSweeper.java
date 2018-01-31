package com.audition;

public class MineSweeper {
    public char[][] board;
    public boolean[][] visited;
    private int rows, cols;

    public MineSweeper(char[][] board) {
        this.board = board;
        rows = board.length;
        cols = board[0].length;
        visited = new boolean[rows][cols];
    }

    public void update(int x, int y) {
        if (x < 0 || x >= rows || y < 0 || y >= cols || visited[x][y]) {
            return;
        }

        if (board[x][y] != '*' && board[x][y] != '.') {
            return;
        }

        visited[x][y] = true;
        if (board[x][y] == '*') {
           return;
        }

        int numberOfMines = computeNumberOfMines(x, y);
        if (numberOfMines == 0) {
            board[x][y] = '0';
            for (int i = Math.max(x - 1, 0); i <= Math.min(x + 1, rows - 1); ++i) {
                for (int j = Math.max(y - 1, 0); j <= Math.min(y + 1, cols - 1); ++j) {
                    update(i, j);
                }
            }
        } else {
            board[x][y] = (char) ('0' + numberOfMines);
        }
    }

    private int computeNumberOfMines(int x, int y) {
        int numberOfMines = 0;
        for (int i = Math.max(x - 1, 0); i <= Math.min(x + 1, rows - 1); ++i) {
            for (int j = Math.max(y - 1, 0); j <= Math.min(y + 1, cols - 1); ++j) {
                if (board[i][j] == '*') {
                    numberOfMines++;
                }
            }
        }
        return numberOfMines;
    }
}