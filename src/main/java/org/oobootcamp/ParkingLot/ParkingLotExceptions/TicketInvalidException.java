package org.oobootcamp.ParkingLot.ParkingLotExceptions;

public class TicketInvalidException extends Exception {
    public TicketInvalidException()
    {
        super("Ticket无效");
    }
}
