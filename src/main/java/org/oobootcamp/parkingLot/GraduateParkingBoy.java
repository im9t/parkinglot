package org.oobootcamp.parkingLot;

import java.util.List;
import java.util.Optional;

public class GraduateParkingBoy extends ParkingBoy {

    public GraduateParkingBoy(List<ParkingLot> parkingLots) {
        super(parkingLots);
    }

    @Override
    protected Optional<ParkingLot> findAvailableParkingLot() {
        return parkingLots.stream().filter(ParkingLot::hasAvailableSpace).findFirst();
    }
}
