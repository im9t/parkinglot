package org.oobootcamp.ParkingLot;

import java.util.ArrayList;


import org.junit.jupiter.api.Test;
import org.oobootcamp.ParkingLot.Model.Car;
import org.oobootcamp.ParkingLot.Model.Ticket;
import org.oobootcamp.ParkingLot.ParkingLotExceptions.ParkingLotIsFullException;
import org.oobootcamp.ParkingLot.ParkingLotExceptions.TicketInvalidException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class ParkingBoyTest {
    @Test
    void should_parking_succeed_and_get_ticked_when_parking_car_given_parking_boy_has_available_parking_lot() throws ParkingLotIsFullException {
        ArrayList<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(new ParkingLot(0));
        parkingLots.add(new ParkingLot(2));
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);
        Car car = new Car();

        Ticket parkingResult = parkingBoy.park(car);

        assertNotNull(parkingResult);
    }

    @Test
    void should_parking_failed_and_show_error_message_when_parking_car_given_parking_boy_has_no_available_parking_lot() {
        ArrayList<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(new ParkingLot(0));
        parkingLots.add(new ParkingLot(0));
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);
        Car car = new Car();


        ParkingLotIsFullException exception = assertThrows(ParkingLotIsFullException.class,
                () -> parkingBoy.park(car));

        assertThat(exception).hasMessageContaining("停车位已满");
    }

    @Test
    void should_parking_by_order_when_parking_car_given_parking_boy_has_available_parking_lot() throws ParkingLotIsFullException {
        ArrayList<ParkingLot> parkingLots = new ArrayList<>();
        ParkingLot parkingLotA = new ParkingLot(1);
        parkingLots.add(parkingLotA);
        ParkingLot parkingLotB = new ParkingLot(1);
        parkingLots.add(parkingLotB);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);
        Car car = new Car();

        Ticket parkingResult = parkingBoy.park(car);
        assertNotNull(parkingResult);

        // this indicates that the card is parked in A parking lot
        ParkingLotIsFullException exception = assertThrows(ParkingLotIsFullException.class,
                () -> parkingLotA.park(car));
        assertThat(exception).hasMessageContaining("停车位已满");
    }

    @Test
    void should_pick_up_succeed_when_pick_up_given_valid_ticket() throws ParkingLotIsFullException, TicketInvalidException {
        ArrayList<ParkingLot> parkingLots = new ArrayList<>();
        ParkingLot parkingLotA = new ParkingLot(1);
        parkingLots.add(parkingLotA);
        ParkingLot parkingLotB = new ParkingLot(1);
        parkingLots.add(parkingLotB);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);
        Car car = new Car();
        Ticket parkingResult = parkingBoy.park(car);
        assertNotNull(parkingResult);

        Car pickUpResult = parkingBoy.pickUp(parkingResult);

        assertNotNull(pickUpResult);
        assertEquals(car.number, pickUpResult.number);
    }

    @Test
    void should_pick_up_failed_when_pick_up_given_ticket_belongs_to_other_parking_boy() throws ParkingLotIsFullException, TicketInvalidException {
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
        Ticket parkResultFromParkingBoyOne = parkingBoyOne.park(new Car());

        TicketInvalidException exception = assertThrows(TicketInvalidException.class,
                () -> parkingBoyTwo.pickUp(parkResultFromParkingBoyOne));
        assertThat(exception).hasMessageContaining("Ticket无效");
    }

    @Test
    void should_pick_up_failed_when_pick_up_given_ticket_has_already_used() throws ParkingLotIsFullException, TicketInvalidException {
        ParkingBoy parkingBoy = new ParkingBoy(new ArrayList<>() {
            {
                add(new ParkingLot(1));
            }
        });
        Ticket parkingResult = parkingBoy.park(new Car());
        assertNotNull(parkingResult);
        Car pickUpResult = parkingBoy.pickUp(parkingResult);
        assertNotNull(pickUpResult);

        TicketInvalidException exception = assertThrows(TicketInvalidException.class,
                () -> parkingBoy.pickUp(parkingResult));
        assertThat(exception).hasMessageContaining("Ticket无效");
    }
}
