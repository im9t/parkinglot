package org.oobootcamp.parkingLot.exception;

public class InvalidTicketException extends RuntimeException {
    public InvalidTicketException()
    {
        super("无效Ticket");
    }
}
