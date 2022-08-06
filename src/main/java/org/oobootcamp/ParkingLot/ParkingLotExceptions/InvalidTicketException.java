package org.oobootcamp.ParkingLot.ParkingLotExceptions;

public class InvalidTicketException extends RuntimeException {
    public InvalidTicketException()
    {
        super("Ticket无效");
    }
}
