package org.oobootcamp.ParkingLot;

import java.util.HashMap;

import org.oobootcamp.ParkingLot.Model.Car;
import org.oobootcamp.ParkingLot.Model.Ticket;
import org.oobootcamp.ParkingLot.ParkingLotExceptions.ParkingLotIsFullException;
import org.oobootcamp.ParkingLot.ParkingLotExceptions.InvalidTicketException;

public class ParkingLot implements Parkable {
    private final HashMap<Ticket, Car> ticketsAndCars;
   private final int capacity ;

   public ParkingLot(int capacity) {
       this.capacity = capacity;
       ticketsAndCars = new HashMap<>();
   }

   @Override
   public Ticket park(Car car) {
       if (hasAvailableSpace()) {
           Ticket ticket = new Ticket();
           ticketsAndCars.put(ticket, car);
           return ticket;
       }
       throw new ParkingLotIsFullException();
   }

   @Override
   public boolean hasAvailableSpace()
   {
       return ticketsAndCars.size() < capacity;
   }


   @Override
   public Car pickUp(Ticket ticket) {
       if (ticketsAndCars.containsKey(ticket)) {
           return ticketsAndCars.remove(ticket);
       }
       throw new InvalidTicketException();
   }

   public int getAvailableSpace()
   {
       return capacity - ticketsAndCars.size();
   }

    @Override
    public boolean hasCarWith(Ticket ticket) {
        return ticketsAndCars.containsKey(ticket);
    }
}
