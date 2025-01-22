package com.dharmaraj.exceptions;

import com.dharmaraj.enums.PrinterType;

public class UnknownPrinterException extends Exception {

    public UnknownPrinterException(PrinterType printerType) {
        super("Received unknown printerType : " + printerType);
    }
}
