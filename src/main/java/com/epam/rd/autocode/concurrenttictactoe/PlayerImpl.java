package com.epam.rd.autocode.concurrenttictactoe;

import java.util.Objects;

public class PlayerImpl implements Player {

    TicTacToe ticTacToe;
    final char mark;
    PlayerStrategy strategy;


    public PlayerImpl(TicTacToe ticTacToe, char mark, PlayerStrategy strategy) {
        this.ticTacToe = ticTacToe;
        this.mark = mark;
        this.strategy = strategy;
    }

    @Override
    public void run() {

        while (true) {
            Move move;
            try {
                move = strategy.computeMove(mark, ticTacToe);

                if (Objects.isNull(move))
                    break;

                ticTacToe.setRuntimeMark(move.row, move.column, mark);

            } catch (IllegalArgumentException e) {
                break;
            }

        }

    }

}
