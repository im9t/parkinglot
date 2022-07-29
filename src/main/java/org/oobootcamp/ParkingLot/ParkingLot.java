package org.oobootcamp.ParkingLot;

import java.util.HashMap;

import org.oobootcamp.ParkingLot.Model.Car;
import org.oobootcamp.ParkingLot.Model.Result;
import org.oobootcamp.ParkingLot.Model.Ticket;

public class ParkingLot {
   private HashMap<Ticket, Car> ticketAndCar;
   private int capacity ;

   public ParkingLot(int capacity) {
       this.capacity = capacity;
       ticketAndCar = new HashMap<Ticket, Car>();
   }

   public Result<Ticket> parkCar(Car car) {
       var parkCarSuccessed = false;
       var info = "停车失败";
       Ticket ticket = null;
       boolean canParkMore = ticketAndCar.size() < capacity;
       if (canParkMore) {
           parkCarSuccessed = true;
           info = "";
           ticket = new Ticket();
           ticketAndCar.put(ticket, car);
       }
       return new Result<Ticket>(parkCarSuccessed, info, ticket);
   }

   public Result<Car> getCar(Ticket ticket) {
       var getCarSuccessed = false;
       var info = "Ticket无效";
       if (ticketAndCar.containsKey(ticket)) {
           getCarSuccessed = true;
           info = "";
       }
       return new Result<Car>(getCarSuccessed, info, ticketAndCar.remove(ticket));
   }
}
