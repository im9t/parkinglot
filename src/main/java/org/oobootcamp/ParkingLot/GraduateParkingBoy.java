package org.oobootcamp.ParkingLot;

import java.util.ArrayList;

import org.oobootcamp.ParkingLot.Model.Car;
import org.oobootcamp.ParkingLot.Model.Ticket;
import org.oobootcamp.ParkingLot.ParkingLotExceptions.ParkingLotIsFullException;
import org.oobootcamp.ParkingLot.ParkingLotExceptions.TicketInvalidException;

public class GraduateParkingBoy extends ParkingBoy{


    public GraduateParkingBoy(ArrayList<ParkingLot> parkingLots) {
        super(parkingLots);
    }

    @Override
    public Ticket park(Car car) {
        for (ParkingLot parkingLot : parkingLots) {
            if (parkingLot.canParkMore()) {
                return parkingLot.park(car);
            }
        }
        throw new ParkingLotIsFullException();
    }
}
