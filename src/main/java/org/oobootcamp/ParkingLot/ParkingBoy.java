package org.oobootcamp.ParkingLot;

import org.oobootcamp.ParkingLot.Model.Car;
import org.oobootcamp.ParkingLot.Model.Ticket;
import org.oobootcamp.ParkingLot.ParkingLotExceptions.ParkingLotIsFullException;
import org.oobootcamp.ParkingLot.ParkingLotExceptions.TicketInvalidException;

import java.util.ArrayList;
import java.util.Optional;

public abstract class ParkingBoy implements Parkable {

    protected final ArrayList<ParkingLot> parkingLots;

    protected ParkingBoy(ArrayList<ParkingLot> parkingLots) {
        this.parkingLots = parkingLots;
    }

    @Override
    public boolean hasAvailableSpace()
    {
        return parkingLots.stream().anyMatch(ParkingLot::hasAvailableSpace);
    }

    @Override
    public boolean hasCarWith(Ticket ticket)
    {
        return parkingLots.stream().anyMatch(parkingLot -> parkingLot.hasCarWith(ticket));
    }

    public Ticket park(Car car) throws ParkingLotIsFullException {
        Optional<ParkingLot> parkingLot = findAvailableParkingLot();
        if (parkingLot.isPresent() && parkingLot.get().hasAvailableSpace()) {
            return parkingLot.get().park(car);
        }
        throw new ParkingLotIsFullException();
    }

    protected abstract Optional<ParkingLot> findAvailableParkingLot();

    public Car pickUp(Ticket ticket) throws TicketInvalidException {
        for (ParkingLot parkingLot : parkingLots) {
            if (parkingLot.hasCarWith(ticket)) {
                return parkingLot.pickUp(ticket);
            }
        }
        throw new TicketInvalidException();
    }

}
