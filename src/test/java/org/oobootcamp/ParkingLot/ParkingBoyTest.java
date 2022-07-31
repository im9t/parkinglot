package org.oobootcamp.ParkingLot;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;


import org.junit.jupiter.api.Test;
import org.oobootcamp.ParkingLot.Model.Car;
import org.oobootcamp.ParkingLot.Model.Result;
import org.oobootcamp.ParkingLot.Model.Ticket;

public class ParkingBoyTest {
    @Test
    void should_parking_succeed_and_get_ticked_when_parking_car_given_parking_boy_has_available_parking_lot() {
        ArrayList<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(new ParkingLot(0));
        parkingLots.add(new ParkingLot(2));
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);
        Car car = new Car();

        Result<Ticket> parkingResult = parkingBoy.park(car);

        assertTrue(parkingResult.isSucceed());
        assertNotNull(parkingResult.value);
    }

    @Test
    void should_parking_failed_and_show_error_message_when_parking_car_given_parking_boy_has_no_available_parking_lot() {
        ArrayList<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(new ParkingLot(0));
        parkingLots.add(new ParkingLot(0));
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);
        Car car = new Car();

        Result<Ticket> parkingResult = parkingBoy.park(car);

        assertFalse(parkingResult.isSucceed());
        assertNull(parkingResult.value);
        assertEquals("停车位已满", parkingResult.errorMessage);
    }

    @Test
    void should_parking_by_order_when_parking_car_given_parking_boy_has_available_parking_lot() {
        ArrayList<ParkingLot> parkingLots = new ArrayList<>();
        ParkingLot parkingLotA = new ParkingLot(1);
        parkingLots.add(parkingLotA);
        ParkingLot parkingLotB = new ParkingLot(1);
        parkingLots.add(parkingLotB);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);
        Car car = new Car();

        Result<Ticket> parkingResult = parkingBoy.park(car);
        assertTrue(parkingResult.isSucceed());

        // this indicates that the card is parked in A parking lot
        Result<Ticket> parkingResultForA = parkingLotA.park(new Car());
        assertFalse(parkingResultForA.isSucceed());
    }

    @Test
    void should_pick_up_succeed_when_pick_up_given_valid_ticket() {
        ArrayList<ParkingLot> parkingLots = new ArrayList<>();
        ParkingLot parkingLotA = new ParkingLot(1);
        parkingLots.add(parkingLotA);
        ParkingLot parkingLotB = new ParkingLot(1);
        parkingLots.add(parkingLotB);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);
        Car car = new Car();
        Result<Ticket> parkingResult = parkingBoy.park(car);
        assertTrue(parkingResult.isSucceed());

        Result<Car> pickUpResult = parkingBoy.pickUp(parkingResult.value);

        assertTrue(pickUpResult.isSucceed());
        assertNotNull(pickUpResult.value);
        assertEquals(car.number, pickUpResult.value.number);
    }

    @Test
    void should_pick_up_failed_when_pick_up_given_ticket_belongs_to_other_parking_boy() {
        ParkingBoy parkingBoyOne = new ParkingBoy(new ArrayList<>() {
            {
                add(new ParkingLot(1));
            }
        });
        ParkingBoy parkingBoyTwo = new ParkingBoy(new ArrayList<>() {
            {
                add(new ParkingLot(1));
            }
        });
        Result<Ticket> parkResultFromParkingBoyOne = parkingBoyOne.park(new Car());

        Result<Car> pickUpResult = parkingBoyTwo.pickUp(parkResultFromParkingBoyOne.value);

        assertFalse(pickUpResult.isSucceed());
        assertEquals("Ticket 无效", pickUpResult.errorMessage);
    }

    @Test
    void should_pick_up_failed_when_pick_up_given_ticket_has_already_used() {
        ParkingBoy parkingBoy = new ParkingBoy(new ArrayList<>() {
            {
                add(new ParkingLot(1));
            }
        });
        Result<Ticket> parkingResult = parkingBoy.park(new Car());
        assertTrue(parkingResult.isSucceed());
        Result<Car> pickUpResult = parkingBoy.pickUp(parkingResult.value);
        assertTrue(pickUpResult.isSucceed());

        Result<Car> secondPickUpResult = parkingBoy.pickUp(parkingResult.value);

        assertFalse(secondPickUpResult.isSucceed());
        assertEquals("Ticket 无效", secondPickUpResult.errorMessage);
    }
}
