package org.oobootcamp.ParkingLot;

import org.oobootcamp.ParkingLot.Model.Car;
import org.oobootcamp.ParkingLot.Model.Ticket;

public interface Parkable {
    Ticket park(Car car);
    Car pickUp(Ticket ticket);
    boolean hasAvailableSpace();
    boolean hasCarWith(Ticket ticket);
}
