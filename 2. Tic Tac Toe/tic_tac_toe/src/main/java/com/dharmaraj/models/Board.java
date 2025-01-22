package com.dharmaraj.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.dharmaraj.enums.CellStatus;
import com.dharmaraj.enums.PrinterType;
import com.dharmaraj.exceptions.UnknownPrinterException;
import com.dharmaraj.factory.PrinterFactory;
import com.dharmaraj.interfaces.Printer;

public class Board {

    private List<List<Cell>> grid;
    private Integer boardSize;
    private Printer printer;

    private List<Map<Player, Integer>> rows = new ArrayList<Map<Player, Integer>>();
    private List<Map<Player, Integer>> columns = new ArrayList<Map<Player, Integer>>();
    private Map<Player, Integer> forwardDiagonal = new HashMap<Player, Integer>();
    private Map<Player, Integer> backwardDiagonal = new HashMap<Player, Integer>();

    public Board(Integer boardSize, PrinterType printerType) throws UnknownPrinterException {
        this.boardSize = boardSize;
        this.grid = new ArrayList<>();
        this.printer = PrinterFactory.getPrinter(printerType, this);
        initializeBoard();
        initializeCaches();
    }

    private void initializeCaches() {
        for (int i = 0; i < boardSize; i++) {
            this.rows.add(new HashMap<>());
            this.columns.add(new HashMap<>());
        }
    }

    private void initializeBoard() {

        for (int i = 0; i < boardSize; i++) {
            List<Cell> row = new ArrayList<>();
            for (int j = 0; j < boardSize; j++) {
                Cell cell = new Cell(i, j);
                row.add(cell);
            }
            this.grid.add(row);
        }
    }

    public Integer getBoardSize() {
        return this.boardSize;
    }

    public void makeMove(Move move) {

        Player player = move.getPlayer();
        Cell cell = move.getCell();

        cell.setCellStatus(CellStatus.FILLED);
        cell.setPlayer(player);

        int row = cell.getRow();
        int column = cell.getColumn();

        rows.get(row).put(player, rows.get(row).getOrDefault(player, 0) + 1);
        columns.get(column).put(player, columns.get(column).getOrDefault(player, 0) + 1);

        if (row == column) {
            forwardDiagonal.put(player, forwardDiagonal.getOrDefault(player, 0) + 1);
        }
        if (row + column == boardSize) {
            backwardDiagonal.put(player, backwardDiagonal.getOrDefault(player, 0) + 1);
        }
    }

    public boolean isInsideBoundary(int row, int column) {
        return row > -1 && row < boardSize && column > -1 && column < boardSize;
    }

    public boolean isCellFilled(int row, int column) {
        Cell cell = getCell(row, column);
        return cell.getCellStatus().equals(CellStatus.FILLED);
    }

    public Cell getCell(int row, int column) {
        return this.grid.get(row).get(column);
    }

    public void print() {
        this.printer.print();
    }

    public List<List<Cell>> getGrid() {
        return grid;
    }

    public void setGrid(List<List<Cell>> grid) {
        this.grid = grid;
    }

    public Printer getPrinter() {
        return printer;
    }

    public void setPrinter(Printer printer) {
        this.printer = printer;
    }

    public List<Map<Player, Integer>> getRows() {
        return rows;
    }

    public void setRows(List<Map<Player, Integer>> rows) {
        this.rows = rows;
    }

    public List<Map<Player, Integer>> getColumns() {
        return columns;
    }

    public void setColumns(List<Map<Player, Integer>> columns) {
        this.columns = columns;
    }

    public Map<Player, Integer> getForwardDiagonal() {
        return forwardDiagonal;
    }

    public void setForwardDiagonal(Map<Player, Integer> forwardDiagonal) {
        this.forwardDiagonal = forwardDiagonal;
    }

    public Map<Player, Integer> getBackwardDiagonal() {
        return backwardDiagonal;
    }

    public void setBackwardDiagonal(Map<Player, Integer> backwardDiagonal) {
        this.backwardDiagonal = backwardDiagonal;
    }
}
