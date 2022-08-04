package org.oobootcamp.ParkingLot;

import org.oobootcamp.ParkingLot.Model.Car;
import org.oobootcamp.ParkingLot.Model.Ticket;
import org.oobootcamp.ParkingLot.ParkingLotExceptions.ParkingLotIsFullException;

import java.util.ArrayList;

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
        if (parkingLots.size() == 0) throw new ParkingLotIsFullException();

        ParkingLot parkingLotHasMostAvailableSpace = parkingLots.get(0);
        for (ParkingLot parkingLot : parkingLots) {
            if (parkingLot.getAvailableSpace() > parkingLotHasMostAvailableSpace.getAvailableSpace())
                parkingLotHasMostAvailableSpace = parkingLot;
        }
        return parkingLotHasMostAvailableSpace;
    }
}
