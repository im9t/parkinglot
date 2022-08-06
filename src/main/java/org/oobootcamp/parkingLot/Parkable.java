package org.oobootcamp.parkingLot;

import org.oobootcamp.parkingLot.model.Car;
import org.oobootcamp.parkingLot.model.Ticket;

public interface Parkable {
    Ticket park(Car car);
    Car pickUp(Ticket ticket);
    boolean hasAvailableSpace();
    boolean hasCarWith(Ticket ticket);
}
