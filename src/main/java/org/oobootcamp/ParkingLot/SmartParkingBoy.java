package org.oobootcamp.ParkingLot;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Optional;

public class SmartParkingBoy extends ParkingBoy {

    protected SmartParkingBoy(ArrayList<ParkingLot> parkingLots) {
        super(parkingLots);
    }

    @Override
    protected Optional<ParkingLot> findAvailableParkingLot() {
        return parkingLots.stream().max(Comparator.comparing(ParkingLot::getAvailableSpace));
    }

}
