package org.oobootcamp.ParkingLot;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.oobootcamp.ParkingLot.Model.Car;
import org.oobootcamp.ParkingLot.Model.Result;
import org.oobootcamp.ParkingLot.Model.Ticket;
import org.oobootcamp.ParkingLot.ParkingLotExceptions.ParkingLotIsFullException;
import org.oobootcamp.ParkingLot.ParkingLotExceptions.TicketInvalidException;


public class ParkingLotTest {
    @Test
    void should_get_ticket_when_parking_car_given_100_capacity_available() throws ParkingLotIsFullException {
        ParkingLot parkingLot = new ParkingLot(100);
        Car car = new Car();

        Ticket ticket = parkingLot.park(car);

        assertThat(ticket).isNotNull();
    }

    @Test
    void should_park_failed_and_get_error_message_when_parking_car_given_fulled_parking_lot() throws ParkingLotIsFullException {
        ParkingLot parkingLot = new ParkingLot(0);
        Car car = new Car();

        ParkingLotIsFullException exception = assertThrows(ParkingLotIsFullException.class,
                () -> parkingLot.park(car));

        assertThat(exception).hasMessageContaining("停车位已满");
    }

    @Test
    void should_get_car_when_pick_up_car_given_valid_ticket() throws ParkingLotIsFullException, TicketInvalidException {
        ParkingLot parkingLot = new ParkingLot(2);
        Car car = new Car();
        Ticket parkingResult = parkingLot.park(car);

        Car pickUpResult = parkingLot.pickUp(parkingResult);

        assertNotNull(pickUpResult);
        assertThat(pickUpResult.number).isEqualTo(car.number);
    }

    @Test
    void should_pick_up_failed_and_get_error_message_when_pick_up_car_given_ticket_from_another_parking_lot() throws ParkingLotIsFullException, TicketInvalidException {
        ParkingLot parkingLotA = new ParkingLot(2);
        ParkingLot parkingLotB = new ParkingLot(2);
        Car car = new Car();
        Ticket parkingTicket = parkingLotA.park(car);

        TicketInvalidException exception = assertThrows(TicketInvalidException.class,
                () -> parkingLotB.pickUp(parkingTicket));

        assertThat(exception).hasMessageContaining("Ticket无效");
    }

    @Test
    void should_pick_up_failed_and_get_error_message_when_pick_up_car_given_ticket_used() throws ParkingLotIsFullException, TicketInvalidException {
        ParkingLot parkingLot = new ParkingLot(2);
        Car car = new Car();
        Ticket parkingTicket = parkingLot.park(car);
        Car firstPickUpResult = parkingLot.pickUp(parkingTicket);
        assertNotNull(firstPickUpResult);

        TicketInvalidException exception = assertThrows(TicketInvalidException.class,
                () -> parkingLot.pickUp(parkingTicket));

        assertThat(exception).hasMessageContaining("Ticket无效");
    }
}
