package org.oobootcamp.ParkingLot;

import org.oobootcamp.ParkingLot.Model.Car;
import org.oobootcamp.ParkingLot.Model.Ticket;
import org.oobootcamp.ParkingLot.ParkingLotExceptions.ParkingLotIsFullException;

import java.util.ArrayList;

public class ParkingManager {
    private final ArrayList<Parkable> parkables;
    public ParkingManager(ArrayList<Parkable> parkables) {
        this.parkables = parkables;
    }

    public Ticket park(Car car) {
        for (Parkable parkable : parkables) {
            if(parkable.hasAvailableSpace())
            {
                return parkable.park(car);
            }

        }
        throw new ParkingLotIsFullException();
    }
}
