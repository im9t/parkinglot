package org.oobootcamp.ParkingLot;

import java.util.ArrayList;

import org.oobootcamp.ParkingLot.Model.Car;
import org.oobootcamp.ParkingLot.Model.Result;
import org.oobootcamp.ParkingLot.Model.Ticket;
import org.oobootcamp.ParkingLot.ParkingLotExceptions.ParkingLotIsFullException;
import org.oobootcamp.ParkingLot.ParkingLotExceptions.TicketInvalidException;

public class ParkingBoy {

    private final ArrayList<ParkingLot> parkingLots;

    public ParkingBoy(ArrayList<ParkingLot> parkingLots) {
        this.parkingLots = new ArrayList<>(parkingLots);
    }

    public Ticket park(Car car) throws ParkingLotIsFullException {
        for (ParkingLot parkingLot : parkingLots) {
            if (parkingLot.canParkMore()) {
                return parkingLot.park(car);
            }
        }
        throw  new ParkingLotIsFullException();
    }

    public Car pickUp(Ticket ticket) throws TicketInvalidException {
        for (ParkingLot parkingLot : parkingLots) {
            try
            {
                var pickUpResult = parkingLot.pickUp(ticket);
                return  pickUpResult;
            }
            catch (TicketInvalidException e) {
            }
        }
        throw new TicketInvalidException();
    }
}
