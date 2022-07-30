package org.oobootcamp.ParkingLot;

import java.util.ArrayList;

import org.oobootcamp.ParkingLot.Model.Car;
import org.oobootcamp.ParkingLot.Model.Result;
import org.oobootcamp.ParkingLot.Model.Ticket;

public class ParkingBoy {

    private final ArrayList<ParkingLot> parkingLots;

    public ParkingBoy(ArrayList<ParkingLot> parkingLots) {
        this.parkingLots = new ArrayList<>(parkingLots);
    }

    public Result<Ticket> park(Car car) {
        for (ParkingLot parkingLot : parkingLots) {
            var parkingResult = parkingLot.park(car);
            if (parkingResult.isSucceed) {
                return parkingResult;
            }
        }
        return new Result<>(false, "停车位已满", null);
    }

    public Result<Car> pickUp(Ticket ticket) {
        for (ParkingLot parkingLot : parkingLots) {
            var pickUpResult = parkingLot.pickUp(ticket);
            if (pickUpResult.isSucceed) {
                return  pickUpResult;
            }
        }
        return new Result<>(false, "Ticket 无效", null);
    }
}
