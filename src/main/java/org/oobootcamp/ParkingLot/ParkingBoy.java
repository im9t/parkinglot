package org.oobootcamp.ParkingLot;

import java.util.ArrayList;

import org.oobootcamp.ParkingLot.Model.Car;
import org.oobootcamp.ParkingLot.Model.Result;
import org.oobootcamp.ParkingLot.Model.Ticket;

public class ParkingBoy {

    private ArrayList<ParkingLot> parkingLots;
    public ParkingBoy(ArrayList<ParkingLot> parkingLots) {
        this.parkingLots = new ArrayList<ParkingLot>();
        this.parkingLots.addAll(parkingLots);
    }

    public Result<Ticket> park(Car car) {
        
    }

}
