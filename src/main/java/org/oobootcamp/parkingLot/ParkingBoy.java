package org.oobootcamp.parkingLot;

import org.oobootcamp.parkingLot.model.Car;
import org.oobootcamp.parkingLot.model.Ticket;
import org.oobootcamp.parkingLot.exception.ParkingLotIsFullException;
import org.oobootcamp.parkingLot.exception.InvalidTicketException;

import java.util.List;
import java.util.Optional;

public abstract class ParkingBoy implements Parkable {

    protected final List<ParkingLot> parkingLots;

    protected ParkingBoy(List<ParkingLot> parkingLots) {
        this.parkingLots = parkingLots;
    }

    @Override
    public boolean hasAvailableSpace() {
        return parkingLots.stream().anyMatch(ParkingLot::hasAvailableSpace);
    }

    @Override
    public boolean hasCarWith(Ticket ticket) {
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

    public Car pickUp(Ticket ticket) throws InvalidTicketException {
        for (ParkingLot parkingLot : parkingLots) {
            if (parkingLot.hasCarWith(ticket)) {
                return parkingLot.pickUp(ticket);
            }
        }
        throw new InvalidTicketException();
    }

}
