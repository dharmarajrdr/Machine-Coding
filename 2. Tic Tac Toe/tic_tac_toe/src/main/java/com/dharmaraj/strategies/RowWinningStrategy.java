package com.dharmaraj.strategies;

import java.util.List;
import java.util.Map;

import com.dharmaraj.interfaces.WinningStrategy;
import com.dharmaraj.models.Board;
import com.dharmaraj.models.Move;
import com.dharmaraj.models.Player;

public class RowWinningStrategy extends WinningStrategy {

    public RowWinningStrategy(Board board) {
        super(board);
    }

    @Override
    public boolean isWinner(Move move) {

        int boardSize = board.getBoardSize();

        Player player = move.getPlayer();

        List<Map<Player, Integer>> rows = this.board.getRows();

        for (Map<Player, Integer> row : rows) {
            if (row.getOrDefault(player, 0) == boardSize) {
                return true;
            }
        }

        return false;
    }

}
