package org.oobootcamp.ParkingLot;

import org.junit.jupiter.api.Test;
import org.oobootcamp.ParkingLot.Model.Car;
import org.oobootcamp.ParkingLot.Model.Ticket;
import org.oobootcamp.ParkingLot.ParkingLotExceptions.ParkingLotIsFullException;
import org.oobootcamp.ParkingLot.ParkingLotExceptions.TicketInvalidException;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class SmartParkingBoyTest {
    @Test
    void should_parking_succeed_and_get_ticked_when_parking_car_given_parking_boy_has_available_parking_lot() throws ParkingLotIsFullException {
        ArrayList<ParkingLot> parkingLots = new ArrayList<>();
        ParkingLot parkingLotA = new ParkingLot(4);
        parkingLots.add(parkingLotA);
        ParkingLot parkingLotB = new ParkingLot(3);
        parkingLots.add(parkingLotB);
        SmartParkingBoy smartparkingBoy = new SmartParkingBoy(parkingLots);
        Car car = new Car();

        Ticket parkingResult = smartparkingBoy.park(car);

        assertNotNull(parkingResult);
        assertEquals(1,4 - parkingLotA.getAvailableSpace());
    }

    @Test
    void should_pick_up_succeed_when_pick_up_given_valid_ticket() throws ParkingLotIsFullException, TicketInvalidException {
        ArrayList<ParkingLot> parkingLots = new ArrayList<>();
        ParkingLot parkingLotA = new ParkingLot(1);
        parkingLots.add(parkingLotA);
        ParkingLot parkingLotB = new ParkingLot(1);
        parkingLots.add(parkingLotB);
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLots);
        Car car = new Car();
        Ticket parkingResult = smartParkingBoy.park(car);
        assertNotNull(parkingResult);

        Car pickUpResult = smartParkingBoy.pickUp(parkingResult);

        assertNotNull(pickUpResult);
        assertEquals(car.number, pickUpResult.number);
    }
}
