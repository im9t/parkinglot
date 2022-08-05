package org.oobootcamp.ParkingLot;

import java.util.ArrayList;
import java.util.Optional;

public class GraduateParkingBoy extends ParkingBoy{

    public GraduateParkingBoy(ArrayList<ParkingLot> parkingLots) {
        super(parkingLots);
    }

    @Override
    protected Optional<ParkingLot> findAvailableParkingLot() {
        return parkingLots.stream().filter(ParkingLot::hasAvailableSpace).findFirst();
    }
}
