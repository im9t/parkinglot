package org.oobootcamp.ParkingLot.ParkingLotExceptions;

public class TicketInvalidException extends RuntimeException {
    public TicketInvalidException()
    {
        super("Ticket无效");
    }
}
