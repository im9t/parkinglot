package org.oobootcamp.ParkingLot;

import java.util.HashMap;

import org.oobootcamp.ParkingLot.Model.Car;
import org.oobootcamp.ParkingLot.Model.Ticket;
import org.oobootcamp.ParkingLot.ParkingLotExceptions.ParkingLotIsFullException;
import org.oobootcamp.ParkingLot.ParkingLotExceptions.TicketInvalidException;

public class ParkingLot {
    private final HashMap<Ticket, Car> ticketsAndCars;
   private final int capacity ;

   public ParkingLot(int capacity) {
       this.capacity = capacity;
       ticketsAndCars = new HashMap<>();
   }

   public Ticket park(Car car) {
       if (hasAvailableSpace()) {
           Ticket ticket = new Ticket();
           ticketsAndCars.put(ticket, car);
           return ticket;
       }
       throw new ParkingLotIsFullException();
   }

   public boolean hasAvailableSpace()
   {
       return ticketsAndCars.size() < capacity;
   }


   public Car pickUp(Ticket ticket) {
       if (ticketsAndCars.containsKey(ticket)) {
           return ticketsAndCars.remove(ticket);
       }
       throw new TicketInvalidException();
   }

   public int getAvailableSpace()
   {
       return capacity - ticketsAndCars.size();
   }

    public boolean hasCarWith(Ticket ticket) {
        return ticketsAndCars.containsKey(ticket);
    }
}
