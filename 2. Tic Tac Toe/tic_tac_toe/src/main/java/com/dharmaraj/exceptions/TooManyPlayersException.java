package com.dharmaraj.exceptions;

public class TooManyPlayersException extends Exception {

    public TooManyPlayersException(int playersCount, int boardSize) {
        super("Received " + playersCount + " player instead of " + (boardSize - 1) + ".");
    }
}
