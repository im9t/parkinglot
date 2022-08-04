package org.oobootcamp.ParkingLot;

import org.oobootcamp.ParkingLot.Model.Car;
import org.oobootcamp.ParkingLot.Model.Ticket;
import org.oobootcamp.ParkingLot.ParkingLotExceptions.TicketInvalidException;

import java.util.ArrayList;

public abstract class ParkingBoy {

    protected final ArrayList<ParkingLot> parkingLots;

    protected ParkingBoy(ArrayList<ParkingLot> parkingLots) {
        this.parkingLots = parkingLots;
    }

    abstract Ticket park(Car car);

    public Car pickUp(Ticket ticket) throws TicketInvalidException {
        for (ParkingLot parkingLot : parkingLots) {
            try {
                return parkingLot.pickUp(ticket);
            } catch (TicketInvalidException ignored) {
            }
        }
        throw new TicketInvalidException();
    }

}
