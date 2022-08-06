package org.oobootcamp.parkingLot;

import org.oobootcamp.parkingLot.model.Car;
import org.oobootcamp.parkingLot.model.Ticket;
import org.oobootcamp.parkingLot.exception.InvalidTicketException;
import org.oobootcamp.parkingLot.exception.ParkingLotIsFullException;

import java.util.List;

public class ParkingManager {
    private final List<Parkable> parkables;

    public ParkingManager(List<Parkable> parkables) {
        this.parkables = parkables;
    }

    public Ticket park(Car car) {
        for (Parkable parkable : parkables) {
            if (parkable.hasAvailableSpace()) {
                return parkable.park(car);
            }
        }
        throw new ParkingLotIsFullException();
    }

    public Car pickUp(Ticket ticket) {
        for (Parkable parkable : parkables) {
            if (parkable.hasCarWith(ticket)) {
                return parkable.pickUp(ticket);
            }
        }
        throw new InvalidTicketException();
    }
}
