package org.oobootcamp.ParkingLot.Model;
import java.util.UUID;

public class Car {
    public UUID number;
    public Car(){
        number = UUID.randomUUID();
    }
}
