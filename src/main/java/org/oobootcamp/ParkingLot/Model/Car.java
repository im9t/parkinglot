package org.oobootcamp.ParkingLot.Model;

import java.util.Random;

public class Car {
    public int Number;
    public int Color;
    public Car(){
        Random random = new Random();
        Number = random.nextInt();
        Color = random.nextInt();
    }
}
