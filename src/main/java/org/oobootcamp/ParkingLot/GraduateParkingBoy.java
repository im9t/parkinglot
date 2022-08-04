package org.oobootcamp.ParkingLot;

import java.util.ArrayList;

import org.oobootcamp.ParkingLot.Model.Car;
import org.oobootcamp.ParkingLot.Model.Ticket;
import org.oobootcamp.ParkingLot.ParkingLotExceptions.ParkingLotIsFullException;
import org.oobootcamp.ParkingLot.ParkingLotExceptions.TicketInvalidException;

public class GraduateParkingBoy {

    private final ArrayList<ParkingLot> parkingLots;

    public GraduateParkingBoy(ArrayList<ParkingLot> parkingLots) {
        this.parkingLots = new ArrayList<>(parkingLots);
    }

    public Ticket park(Car car) {
        for (ParkingLot parkingLot : parkingLots) {
            if (parkingLot.canParkMore()) {
                return parkingLot.park(car);
            }
        }
        throw new ParkingLotIsFullException();
    }

    public Car pickUp(Ticket ticket) {
        for (ParkingLot parkingLot : parkingLots) {
            if (parkingLot.hasTicket(ticket)) {
                return parkingLot.pickUp(ticket);
            }
        }
        throw new TicketInvalidException();
    }
}
