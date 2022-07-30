package org.oobootcamp.ParkingLot;

import java.util.HashMap;

import org.oobootcamp.ParkingLot.Model.Car;
import org.oobootcamp.ParkingLot.Model.Result;
import org.oobootcamp.ParkingLot.Model.Ticket;

public class ParkingLot {
   private final String parkingFailErrorMessage = "停车位已满";
   private final String pickUpFailErrorMessage = "Ticket无效";
   private HashMap<Ticket, Car> ticketsAndCars;
   private int capacity ;

   public ParkingLot(int capacity) {
       this.capacity = capacity;
       ticketsAndCars = new HashMap<Ticket, Car>();
   }

   public Result<Ticket> park(Car car) {
       boolean canParkMore = ticketsAndCars.size() < capacity;
       if (canParkMore) {
           Ticket ticket = new Ticket();
           ticketsAndCars.put(ticket, car);
           return new Result<Ticket>(true, "", ticket);
       }
       return new Result<Ticket>(false, parkingFailErrorMessage, null);
   }

   public Result<Car> pickUp(Ticket ticket) {
       if (ticketsAndCars.containsKey(ticket)) {
           return new Result<Car>(true, "", ticketsAndCars.remove(ticket));
       }
       return new Result<Car>(false, pickUpFailErrorMessage, null);
   }
}