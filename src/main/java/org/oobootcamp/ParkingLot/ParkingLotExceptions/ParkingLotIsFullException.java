package org.oobootcamp.ParkingLot.ParkingLotExceptions;

public class ParkingLotIsFullException extends RuntimeException {
    public ParkingLotIsFullException()
    {
        super("停车位已满");
    }
}
