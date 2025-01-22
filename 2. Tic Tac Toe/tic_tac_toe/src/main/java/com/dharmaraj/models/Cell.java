package com.dharmaraj.models;

import com.dharmaraj.enums.CellStatus;

public class Cell {

    private int row;
    private int column;
    private CellStatus cellStatus;
    private Player player;

    public Cell(int row, int column) {
        this.row = row;
        this.column = column;
        this.cellStatus = CellStatus.EMPTY;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public CellStatus getCellStatus() {
        return cellStatus;
    }

    public void setCellStatus(CellStatus cellStatus) {
        this.cellStatus = cellStatus;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
