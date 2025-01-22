package com.dharmaraj.strategies;

import java.util.Map;

import com.dharmaraj.interfaces.WinningStrategy;
import com.dharmaraj.models.Board;
import com.dharmaraj.models.Move;
import com.dharmaraj.models.Player;

public class DiagonalWinningStrategy extends WinningStrategy {

    public DiagonalWinningStrategy(Board board) {
        super(board);
    }

    @Override
    public boolean isWinner(Move move) {

        int boardSize = board.getBoardSize();

        Player player = move.getPlayer();

        Map<Player, Integer> forwardDiagonal = this.board.getForwardDiagonal();
        Map<Player, Integer> backwardDiagonal = this.board.getBackwardDiagonal();

        return forwardDiagonal.getOrDefault(player, 0) == boardSize || backwardDiagonal.getOrDefault(player, 0) == boardSize;
    }

}
