package com.dharmaraj.interfaces;

import com.dharmaraj.models.Board;
import com.dharmaraj.models.Move;

public abstract class WinningStrategy {

    protected Board board;

    public WinningStrategy(Board board) {
        this.board = board;
    }

    public abstract boolean isWinner(Move move);
}
