package com.epam.rd.autocode.concurrenttictactoe;

import java.util.Arrays;
import java.util.Objects;

public class TicTacToeImpl implements TicTacToe {

    char[][] table = new char[3][3];

    char lastMark;

     boolean isX = false;


    public TicTacToeImpl() {
        for (char[] chars : table) Arrays.fill(chars, ' ');
    }

    @Override
    public void setMark(int x, int y, char mark) {

        if (table[x][y] != ' ')
            throw new IllegalArgumentException();

        table[x][y] = mark;
        lastMark = mark;

    }

    @Override
    public char[][] table() {
        char[][] chars = new char[3][3];
        for (int i = 0; i < table.length; i++)
            chars[i] = Arrays.copyOf(table[i], 3);
        return chars;
    }

    @Override
    public char lastMark() {
        return lastMark;
    }


    @Override
    public synchronized void setRuntimeMark(int x, int y, char mark) {


        if (hasWon())
            Thread.currentThread().stop();

        if (mark == 'X')
            isX = true;

        if (lastMark == mark)
            return;

        if (!isX && Objects.equals(mark, 'O'))
            return;

        if (!Objects.equals(table[x][y], ' ')) {
            return;
        }

        if (table[x][y] == ' ') {
            table[x][y] = mark;
            lastMark = mark;
        }
    }


    public boolean hasWon() {
        return isTrue('X') || isTrue('O');
    }


    private boolean isTrue(char a) {
        return (table[0][0] == a && table[0][1] == a && table[0][2] == a) ||
                (table[1][0] == a && table[1][1] == a && table[1][2] == a) ||
                (table[2][0] == a && table[2][1] == a && table[2][2] == a) ||
                (table[0][0] == a && table[1][0] == a && table[2][0] == a) ||
                (table[0][1] == a && table[1][1] == a && table[2][1] == a) ||
                (table[0][2] == a && table[1][2] == a && table[2][2] == a) ||
                (table[0][0] == a && table[1][1] == a && table[2][2] == a) ||
                (table[2][0] == a && table[1][1] == a && table[0][2] == a);
    }


}
