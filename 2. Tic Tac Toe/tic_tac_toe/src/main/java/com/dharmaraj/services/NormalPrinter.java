package com.dharmaraj.services;

import com.dharmaraj.interfaces.Printer;
import com.dharmaraj.models.Board;
import com.dharmaraj.models.Player;

public class NormalPrinter implements Printer {

    private Board board;

    public NormalPrinter(Board board) {
        this.board = board;
    }

    @Override
    public void print() {

        int boardSize = board.getBoardSize();

        for (int i = 0; i < boardSize; i++) {
            System.out.print(" | ");
            for (int j = 0; j < boardSize; j++) {
                Player player = board.getCell(i, j).getPlayer();
                if (player == null) {
                    System.out.print(" ");
                } else {
                    System.out.print(player.getSymbol());
                }
                System.out.print(" | ");
            }
            System.out.println();
        }

    }

}
