package com.dharmaraj.factory;

import com.dharmaraj.enums.WinningStrategyEnum;
import com.dharmaraj.interfaces.WinningStrategy;
import com.dharmaraj.models.Board;
import com.dharmaraj.strategies.ColumnWinningStrategy;
import com.dharmaraj.strategies.DiagonalWinningStrategy;
import com.dharmaraj.strategies.RowWinningStrategy;

public class WinningStrategyFactory {

    public static WinningStrategy getStrategy(WinningStrategyEnum winningStrategyEnum, Board board) {
        switch (winningStrategyEnum) {
            case ROW_WINNING_STRATEGY: {
                return new RowWinningStrategy(board);
            }
            case COLUMN_WINNING_STRATEGY: {
                return new ColumnWinningStrategy(board);
            }
            case DIAGONAL_WINNING_STRATEGY: {
                return new DiagonalWinningStrategy(board);
            }
            default:
                throw new AssertionError();
        }
    }
}
