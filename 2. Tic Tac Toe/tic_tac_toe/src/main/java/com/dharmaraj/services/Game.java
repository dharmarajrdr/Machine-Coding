package com.dharmaraj.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import com.dharmaraj.enums.PrinterType;
import com.dharmaraj.enums.Symbol;
import com.dharmaraj.enums.WinningStrategyEnum;
import com.dharmaraj.exceptions.FieldMissingException;
import com.dharmaraj.exceptions.NoSameSymbolsException;
import com.dharmaraj.exceptions.TooManyPlayersException;
import com.dharmaraj.exceptions.UnknownPrinterException;
import com.dharmaraj.factory.WinningStrategyFactory;
import com.dharmaraj.interfaces.WinningStrategy;
import com.dharmaraj.models.Board;
import com.dharmaraj.models.Move;
import com.dharmaraj.models.Player;

public class Game {

    private Board board;
    private List<Player> players;
    private Integer boardSize;
    private Integer currentTurn;
    private List<Move> movesHistory;
    private Scanner scanner;
    private List<WinningStrategy> winningStrategies;
    private Player winner;

    public Game(Builder builder) throws UnknownPrinterException {
        this.boardSize = builder.getBoardSize();
        this.board = new Board(boardSize, builder.getPrinterType());
        this.currentTurn = 0;
        this.scanner = new Scanner(System.in);
        this.players = builder.getPlayers();
        this.movesHistory = new ArrayList<>();
        this.winningStrategies = new ArrayList<>();
        for (WinningStrategyEnum strategyEnum : builder.getWinningStrategyEnum()) {
            this.winningStrategies.add(WinningStrategyFactory.getStrategy(strategyEnum, this.board));
        }
    }

    private boolean isMadeMaximumMoves() {
        return this.movesHistory.size() == boardSize * boardSize;
    }

    private void announceWinner() {

        if (winner == null || isMadeMaximumMoves()) {
            System.out.println("It is a Draw");
        } else {
            System.out.println("[" + winner.getSymbol() + "] " + winner.getName() + " won the game!");
        }
    }

    private boolean isCurrentPlayerWinner(Move move) {

        for (WinningStrategy winningStrategy : winningStrategies) {

            if (winningStrategy.isWinner(move)) {
                winner = move.getPlayer();
                return true;
            }
        }
        return false;
    }

    private void introducePlayers() {

        for (Player player : players) {
            System.out.println("" + player.getSymbol() + " : " + player.getName());
        }
    }

    private void updateCurrentTurn() {
        this.currentTurn = (this.currentTurn + 1) % this.players.size();
    }

    public void start() {

        introducePlayers();

        board.print();

        while (isMadeMaximumMoves() == false) {

            // Receive input from user
            Player currentPlayer = players.get(currentTurn);
            System.out.println(currentPlayer.getName() + "'s turn.");
            System.out.print("Row : ");
            int row = scanner.nextInt();
            System.out.print("Column : ");
            int column = scanner.nextInt();

            if (!board.isInsideBoundary(row, column)) {
                System.err.println("Entered invalid cell. Please enter number between " + 0 + " and " + (boardSize - 1));
                continue;
            }

            if (board.isCellFilled(row, column)) {
                System.err.println("Entered cell has already been filled. Please retry entering different cell.");
                continue;
            }

            Move move = new Move(board.getCell(row, column), currentPlayer);

            board.makeMove(move);

            movesHistory.add(move);

            board.print();

            if (isCurrentPlayerWinner(move)) {
                break;
            }

            updateCurrentTurn();
        }

        announceWinner();
    }

    // Game.Builder().setBoardSize(3).build();
    public static class Builder {

        private Integer boardSize;
        private List<Player> players;
        private PrinterType printerType;
        private List<WinningStrategyEnum> winningStrategyEnum;

        public int getBoardSize() {
            return boardSize;
        }

        public Builder setBoardSize(int boardSize) {
            this.boardSize = boardSize;
            return this;
        }

        public List<Player> getPlayers() {
            return players;
        }

        public Builder setPlayers(List<Player> players) {
            this.players = players;
            return this;
        }

        public PrinterType getPrinterType() {
            return printerType;
        }

        public Builder setPrinterType(PrinterType printerType) {
            this.printerType = printerType;
            return this;
        }

        public List<WinningStrategyEnum> getWinningStrategyEnum() {
            return winningStrategyEnum;
        }

        public Builder setWinningStrategyEnum(List<WinningStrategyEnum> winningStrategyEnum) {
            this.winningStrategyEnum = winningStrategyEnum;
            return this;
        }

        private void checkDuplicatedSymbols() throws NoSameSymbolsException {

            Set<Symbol> set = new HashSet<>();
            for (Player player : players) {
                Symbol symbol = player.getSymbol();
                if (set.contains(symbol)) {
                    throw new NoSameSymbolsException(symbol);
                }
                set.add(symbol);
            }

        }

        private void userInputValidator() throws TooManyPlayersException, FieldMissingException, NoSameSymbolsException {

            if (players == null) {
                throw new FieldMissingException("players");
            }

            if (boardSize == null) {
                throw new FieldMissingException("boardSize");
            }

            if (printerType == null) {
                throw new FieldMissingException("printerType");
            }

            if (winningStrategyEnum == null) {
                throw new FieldMissingException("winningStrategyEnum");
            }

            if (players.size() >= boardSize) {
                throw new TooManyPlayersException(players.size(), boardSize);
            }

            checkDuplicatedSymbols();

        }

        public Game build() throws UnknownPrinterException, TooManyPlayersException, FieldMissingException, NoSameSymbolsException {
            userInputValidator();
            return new Game(this);
        }

    }
}
