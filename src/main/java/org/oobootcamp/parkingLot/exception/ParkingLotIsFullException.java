package org.oobootcamp.parkingLot.exception;

public class ParkingLotIsFullException extends RuntimeException {
    public ParkingLotIsFullException()
    {
        super("停车位已满");
    }
}
