package org.oobootcamp.ParkingLot;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.oobootcamp.ParkingLot.Model.Car;
import org.oobootcamp.ParkingLot.Model.Result;
import org.oobootcamp.ParkingLot.Model.Ticket;


public class ParkingLotTest {
    @Test
    void should_get_ticket_when_parking_car_given_100_capacity_available() {
        ParkingLot parkingLot = new ParkingLot(100);
        Car car = new Car();
        Result<Ticket> result = parkingLot.park(car);
        assertTrue(result.isSucceed);
        assertThat(result.value).isNotNull();
        assertThat(result.errorMessage).isEmpty();
    }

    @Test
    void should_park_failed_and_get_error_message_when_parking_car_given_fulled_parking_lot() {
        ParkingLot parkingLot = new ParkingLot(0);
        Car car = new Car();
        Result<Ticket> result = parkingLot.park(car);
        assertFalse(result.isSucceed);
        assertNull(result.value);
        assertEquals("停车位已满", result.errorMessage);
    }

    @Test
    void should_get_car_when_pick_up_car_given_valid_ticket() {
        ParkingLot parkingLot = new ParkingLot(2);
        Car car = new Car();
        Result<Ticket> parkingResult = parkingLot.park(car);
        Result<Car> pickUpResult = parkingLot.pickUp(parkingResult.value);
        assertTrue(pickUpResult.isSucceed);
        assertThat(pickUpResult.errorMessage).isEmpty();
        assertThat(pickUpResult.value.number).isEqualTo(car.number);
    }

    @Test
    void should_pick_up_failed_and_get_error_message_when_pick_up_car_given_ticket_from_another_parking_lot() {
        ParkingLot parkingLotA = new ParkingLot(2);
        ParkingLot parkingLotB = new ParkingLot(2);
        Car car = new Car();
        Result<Ticket> parkingTicket = parkingLotA.park(car);
        Result<Car> pickUpResult = parkingLotB.pickUp(parkingTicket.value);
        assertNull(pickUpResult.value);
        assertFalse(pickUpResult.isSucceed);
        assertEquals("Ticket无效", pickUpResult.errorMessage);
    }

    @Test
    void should_pick_up_failed_and_get_error_message_when_pick_up_car_given_ticket_used() {
        ParkingLot parkingLot = new ParkingLot(2);
        Car car = new Car();
        Result<Ticket> parkingTicket = parkingLot.park(car);
        Result<Car> firstPickUpResult = parkingLot.pickUp(parkingTicket.value);
        assertTrue(firstPickUpResult.isSucceed);
        Result<Car> secondPickUpResult = parkingLot.pickUp(parkingTicket.value);
        assertFalse(secondPickUpResult.isSucceed);
        assertEquals("Ticket无效", secondPickUpResult.errorMessage);
    }
}
