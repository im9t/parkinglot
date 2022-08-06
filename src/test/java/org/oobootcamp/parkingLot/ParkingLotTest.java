package org.oobootcamp.parkingLot;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.oobootcamp.parkingLot.model.Car;
import org.oobootcamp.parkingLot.model.Ticket;
import org.oobootcamp.parkingLot.exception.ParkingLotIsFullException;
import org.oobootcamp.parkingLot.exception.InvalidTicketException;


public class ParkingLotTest {
    @Test
    void should_get_ticket_when_parking_car_given_100_capacity_available() {
        ParkingLot parkingLot = new ParkingLot(100);
        Car car = new Car();

        Ticket ticket = parkingLot.park(car);

        assertThat(ticket).isNotNull();
    }

    @Test
    void should_park_failed_and_get_error_message_when_parking_car_given_fulled_parking_lot() {
        ParkingLot parkingLot = new ParkingLot(0);
        Car car = new Car();

        ParkingLotIsFullException exception = assertThrows(ParkingLotIsFullException.class,
                () -> parkingLot.park(car));

        assertThat(exception).hasMessageContaining("停车位已满");
    }

    @Test
    void should_get_car_when_pick_up_car_given_valid_ticket() {
        ParkingLot parkingLot = new ParkingLot(2);
        Car car = new Car();
        Ticket parkingResult = parkingLot.park(car);

        Car pickUpResult = parkingLot.pickUp(parkingResult);

        assertNotNull(pickUpResult);
        assertThat(pickUpResult.number).isEqualTo(car.number);
    }

    @Test
    void should_pick_up_failed_and_get_error_message_when_pick_up_car_given_ticket_from_another_parking_lot() {
        ParkingLot parkingLotA = new ParkingLot(2);
        ParkingLot parkingLotB = new ParkingLot(2);
        Car car = new Car();
        Ticket parkingTicket = parkingLotA.park(car);

        InvalidTicketException exception = assertThrows(InvalidTicketException.class,
                () -> parkingLotB.pickUp(parkingTicket));

        assertThat(exception).hasMessageContaining("Ticket无效");
    }

    @Test
    void should_pick_up_failed_and_get_error_message_when_pick_up_car_given_ticket_used(){
        ParkingLot parkingLot = new ParkingLot(2);
        Car car = new Car();
        Ticket parkingTicket = parkingLot.park(car);
        Car firstPickUpResult = parkingLot.pickUp(parkingTicket);
        assertNotNull(firstPickUpResult);

        InvalidTicketException exception = assertThrows(InvalidTicketException.class,
                () -> parkingLot.pickUp(parkingTicket));

        assertThat(exception).hasMessageContaining("Ticket无效");
    }

    @Test
    void should_return_false_when_get_can_park_more_given_a_parking_lot_capacity_is_one_and_parked_one() {
        ParkingLot parkingLot = new ParkingLot(1);
        Car car = new Car();
        parkingLot.park(car);

        assertFalse(parkingLot.hasAvailableSpace());
    }
    @Test
    void should_return_true_when_get_can_park_more_given_a_parking_lot_capacity_is_one_and_parked_zero() {
        ParkingLot parkingLot = new ParkingLot(1);

        assertTrue(parkingLot.hasAvailableSpace());
    }

    @Test
    void should_return_2_when_get_available_space_given_a_parking_lot_capacity_is_two_and_parked_zero() {
        ParkingLot parkingLot = new ParkingLot(2);

        assertEquals(2, parkingLot.getAvailableSpace());
    }

    @Test
    void should_return_2_when_get_available_space_given_a_parking_lot_capacity_is_two_and_parked_one() {
        ParkingLot parkingLot = new ParkingLot(2);
        parkingLot.park(new Car());

        assertEquals(1, parkingLot.getAvailableSpace());
    }

    @Test
    void should_return_false_when_get_hasTicket_given_a_parking_lot_capacity_is_1_and_parked_0() {
        ParkingLot parkingLot = new ParkingLot(1);
        Ticket ticket = new Ticket();
        assertFalse(parkingLot.hasCarWith(ticket));
    }
    @Test
    void should_return_true_when_get_hasTicket_given_a_car_is_parked_in_this_parking_lot() {
        ParkingLot parkingLot = new ParkingLot(1);
        Car car = new Car();
        Ticket ticket = parkingLot.park(car);
        assertTrue(parkingLot.hasCarWith(ticket));
    }
}
