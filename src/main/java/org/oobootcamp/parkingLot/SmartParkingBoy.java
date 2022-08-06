package org.oobootcamp.parkingLot;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class SmartParkingBoy extends ParkingBoy {

    protected SmartParkingBoy(List<ParkingLot> parkingLots) {
        super(parkingLots);
    }

    @Override
    protected Optional<ParkingLot> findAvailableParkingLot() {
        return parkingLots.stream().max(Comparator.comparing(ParkingLot::getAvailableSpace));
    }

}
