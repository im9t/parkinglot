package org.oobootcamp.ParkingLot;

import org.oobootcamp.ParkingLot.Model.Car;
import org.oobootcamp.ParkingLot.Model.Ticket;
import org.oobootcamp.ParkingLot.ParkingLotExceptions.ParkingLotIsFullException;
import org.oobootcamp.ParkingLot.ParkingLotExceptions.TicketInvalidException;

import java.util.ArrayList;

public class SmartParkingBoy {
    private final ArrayList<ParkingLot> parkingLots;
    private ParkingLot theParkingLotHavingMostSpace;
    public SmartParkingBoy(ArrayList<ParkingLot> parkingLots) {
        this.parkingLots = parkingLots;
    }

    public Ticket park(Car car) throws ParkingLotIsFullException {
        setUpTheMostAvailableParkingLot();
        if(theParkingLotHavingMostSpace.canParkMore())
        {
            return theParkingLotHavingMostSpace.park(car);
        }
        throw new ParkingLotIsFullException();
    }

    private void setUpTheMostAvailableParkingLot()
    {
        theParkingLotHavingMostSpace = parkingLots.get(0);
        for (ParkingLot parkingLot : parkingLots)
        {
            if(parkingLot.getAvailableSpace() > theParkingLotHavingMostSpace.getAvailableSpace())
                theParkingLotHavingMostSpace = parkingLot;
        }
    }

    public Car pickUp(Ticket ticket) throws TicketInvalidException {
        for (ParkingLot parkingLot : parkingLots) {
            try
            {
                return parkingLot.pickUp(ticket);
            }
            catch (TicketInvalidException ignored) {
            }
        }
        throw new TicketInvalidException();
    }
}
