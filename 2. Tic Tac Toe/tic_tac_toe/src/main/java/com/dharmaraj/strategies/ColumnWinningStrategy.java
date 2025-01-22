package com.dharmaraj.strategies;

import java.util.List;
import java.util.Map;

import com.dharmaraj.interfaces.WinningStrategy;
import com.dharmaraj.models.Board;
import com.dharmaraj.models.Move;
import com.dharmaraj.models.Player;

public class ColumnWinningStrategy extends WinningStrategy {

    public ColumnWinningStrategy(Board board) {
        super(board);
    }

    @Override
    public boolean isWinner(Move move) {

        int boardSize = board.getBoardSize();

        Player player = move.getPlayer();

        List<Map<Player, Integer>> columns = this.board.getColumns();

        for (Map<Player, Integer> column : columns) {
            if (column.getOrDefault(player, 0) == boardSize) {
                return true;
            }
        }

        return false;
    }

}
