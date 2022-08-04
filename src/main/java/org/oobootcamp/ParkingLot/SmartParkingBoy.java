package org.oobootcamp.ParkingLot;

import org.oobootcamp.ParkingLot.Model.Car;
import org.oobootcamp.ParkingLot.Model.Ticket;
import org.oobootcamp.ParkingLot.ParkingLotExceptions.ParkingLotIsFullException;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Optional;

public class SmartParkingBoy extends ParkingBoy {

    protected SmartParkingBoy(ArrayList<ParkingLot> parkingLots) {
        super(parkingLots);
    }

    @Override
    public Ticket park(Car car) throws ParkingLotIsFullException {
        ParkingLot parkingLot = getParkingLotHasMostAvailableSpace();
        if (parkingLot.canParkMore()) {
            return parkingLot.park(car);
        }
        throw new ParkingLotIsFullException();
    }

    private ParkingLot getParkingLotHasMostAvailableSpace() {
        Optional<ParkingLot> parkingLotHasMostAvailableSpace = parkingLots.stream().max(Comparator.comparing(i -> i.getAvailableSpace()));
        if (parkingLotHasMostAvailableSpace.get() == null) throw new ParkingLotIsFullException();

        return parkingLotHasMostAvailableSpace.get();
    }
}
