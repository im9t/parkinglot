package org.oobootcamp.parkingLot.model;
import java.util.UUID;

public class Car {
    public UUID number;
    public Car(){
        number = UUID.randomUUID();
    }
}
