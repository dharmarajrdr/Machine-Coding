package com.dharmaraj.factory;

import com.dharmaraj.enums.PrinterType;
import com.dharmaraj.exceptions.UnknownPrinterException;
import com.dharmaraj.interfaces.Printer;
import com.dharmaraj.models.Board;
import com.dharmaraj.services.FancyPrinter;
import com.dharmaraj.services.NormalPrinter;

public class PrinterFactory {

    public static Printer getPrinter(PrinterType printerType, Board board) throws UnknownPrinterException {
        switch (printerType) {
            case NORMAL: {
                return new NormalPrinter(board);
            }
            case FANCY: {
                return new FancyPrinter(board);
            }
        }
        throw new UnknownPrinterException(printerType);
    }
}
