package org.oobootcamp.ParkingLot;

import java.util.HashMap;

import org.oobootcamp.ParkingLot.Model.Car;
import org.oobootcamp.ParkingLot.Model.InvalidTicket;
import org.oobootcamp.ParkingLot.Model.ParkingOutPut;
import org.oobootcamp.ParkingLot.Model.Ticket;

public class ParkingLot {
   private HashMap<Ticket, Car> parkingSpaceAndCar;
   private int parkingSpaceNum = 50;

   public ParkingLot(){
       parkingSpaceAndCar = new HashMap<Ticket, Car>();
   }

   public ParkingLot(int spaceNum) {
       this();
       parkingSpaceNum = spaceNum;
   }

   public ParkingOutPut ParkCar(Car aCar) {
       if (parkingSpaceAndCar.size() < parkingSpaceNum) {
           var ticket = new Ticket();
           parkingSpaceAndCar.put(ticket, aCar);
           return ticket;
       }
       return new InvalidTicket();
   }

   public Car GetCar(ParkingOutPut ticket) {
       if (ticket instanceof Ticket validTicket) {
           return parkingSpaceAndCar.get(validTicket);
       }
       return null;
   }

}
