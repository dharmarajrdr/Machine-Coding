package com.dharmaraj;

import java.util.List;

import com.dharmaraj.enums.PrinterType;
import com.dharmaraj.enums.Symbol;
import com.dharmaraj.enums.WinningStrategyEnum;
import com.dharmaraj.exceptions.FieldMissingException;
import com.dharmaraj.exceptions.NoSameSymbolsException;
import com.dharmaraj.exceptions.TooManyPlayersException;
import com.dharmaraj.exceptions.UnknownPrinterException;
import com.dharmaraj.models.Player;
import com.dharmaraj.services.Game;

public class Main {

    public static void main(String[] args) {

        int boardSize = 3;
        List<Player> players = List.of(
                new Player("Dharmaraj", Symbol.X),
                new Player("Mohankumar", Symbol.O)
        );
        PrinterType printerType = PrinterType.FANCY;
        List<WinningStrategyEnum> winningStrategies = List.of(
                WinningStrategyEnum.ROW_WINNING_STRATEGY,
                WinningStrategyEnum.COLUMN_WINNING_STRATEGY,
                WinningStrategyEnum.DIAGONAL_WINNING_STRATEGY
        );
        try {
            Game game = new Game.Builder()
                    .setBoardSize(boardSize)
                    .setPlayers(players)
                    .setPrinterType(printerType)
                    .setWinningStrategyEnum(winningStrategies)
                    .build();
            game.start();
        } catch (UnknownPrinterException | TooManyPlayersException | FieldMissingException | NoSameSymbolsException e) {
            System.err.println(e);
        }
    }
}
