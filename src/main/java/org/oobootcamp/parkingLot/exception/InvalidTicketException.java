package org.oobootcamp.parkingLot.exception;

public class InvalidTicketException extends RuntimeException {
    public InvalidTicketException()
    {
        super("Ticket无效");
    }
}
