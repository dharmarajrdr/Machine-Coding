package com.dharmaraj.exceptions;

import com.dharmaraj.enums.Symbol;

public class NoSameSymbolsException extends Exception {

    public NoSameSymbolsException(Symbol duplicatedSymbol) {

        super("Player with same symbols are not allowed. More than one player owning the same symbol '" + duplicatedSymbol + "'.");
    }
}
