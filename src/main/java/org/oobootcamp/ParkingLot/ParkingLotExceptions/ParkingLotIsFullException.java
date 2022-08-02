package org.oobootcamp.ParkingLot.ParkingLotExceptions;

public class ParkingLotIsFullException extends Exception {
    public ParkingLotIsFullException()
    {
        super("停车位已满");
    }
}
