package org.oobootcamp.ParkingLot;

import org.junit.jupiter.api.Test;
import org.oobootcamp.ParkingLot.Model.Car;
import org.oobootcamp.ParkingLot.Model.Ticket;
import org.oobootcamp.ParkingLot.ParkingLotExceptions.ParkingLotIsFullException;
import org.oobootcamp.ParkingLot.ParkingLotExceptions.TicketInvalidException;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SmartParkingBoyTest {
    @Test
    void should_parking_to_the_second_parking_lot_when_parking_car_given_parking_boy_has_a_capacity_is_4_parked_3_and_b_capacity_is_3_parked_0(){
        ArrayList<ParkingLot> parkingLots = new ArrayList<>();
        ParkingLot parkingLotA = new ParkingLot(4);
        parkingLotA.park(new Car());
        parkingLotA.park(new Car());
        parkingLotA.park(new Car());
        parkingLots.add(parkingLotA);
        ParkingLot parkingLotB = new ParkingLot(3);
        parkingLots.add(parkingLotB);
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLots);
        Car car = new Car();

        Ticket ticket = smartParkingBoy.park(car);
        assertNotNull(ticket);
        assertEquals(car,parkingLotB.pickUp(ticket));
    }

    @Test
    void should_parking_to_the_first_parking_lot_when_parking_car_given_parking_boy_has_a_capacity_is_4_parked_1_and_b_capacity_is_3_parked_0() {
        ArrayList<ParkingLot> parkingLots = new ArrayList<>();
        ParkingLot parkingLotA = new ParkingLot(4);
        parkingLotA.park(new Car());
        parkingLots.add(parkingLotA);
        ParkingLot parkingLotB = new ParkingLot(1);
        parkingLots.add(parkingLotB);
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLots);
        Car car = new Car();

        Ticket ticket = smartParkingBoy.park(car);

        assertNotNull(ticket);
        assertEquals(car, parkingLotA.pickUp(ticket));
    }

    @Test
    void should_parking_failed_and_show_error_message_when_parking_car_given_parking_boy_has_a_capacity_is_1_parked_1_and_b_capacity_is_2_parked_2() {
        ArrayList<ParkingLot> parkingLots = new ArrayList<>();
        ParkingLot parkingLot = new ParkingLot(1);
        parkingLot.park(new Car());
        parkingLots.add(parkingLot);
        ParkingLot parkingLotB = new ParkingLot(2);
        parkingLotB.park(new Car());
        parkingLotB.park(new Car());
        parkingLots.add(parkingLotB);
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLots);
        Car car = new Car();


        ParkingLotIsFullException exception = assertThrows(ParkingLotIsFullException.class,
                () -> smartParkingBoy.park(car));

        assertThat(exception).hasMessageContaining("停车位已满");
    }

    @Test
    void should_pick_up_succeed_when_pick_up_given_parking_boy_has_a_capacity_is_1_and_b_capacity_is_1_and_my_car_is_in_a() {
        ArrayList<ParkingLot> parkingLots = new ArrayList<>();
        ParkingLot parkingLot = new ParkingLot(1);
        Car car = new Car();
        Ticket ticket = parkingLot.park(car);
        parkingLots.add(parkingLot);
        ParkingLot parkingLotB = new ParkingLot(1);
        parkingLots.add(parkingLotB);
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLots);

        assertEquals(car, smartParkingBoy.pickUp(ticket));
    }

    @Test
    void should_pick_up_failed_when_pick_up_given_ticket_belongs_to_other_parking_lot() {
        SmartParkingBoy smartParkingBoyOne = new SmartParkingBoy(new ArrayList<>() {
            {
                add(new ParkingLot(1));
            }
        });
        ParkingLot parkingLot = new ParkingLot(1);
        Ticket ticket = parkingLot.park(new Car());


        TicketInvalidException exception = assertThrows(TicketInvalidException.class,
                () -> smartParkingBoyOne.pickUp(ticket));
        assertThat(exception).hasMessageContaining("Ticket无效");
    }

    @Test
    void should_pick_up_failed_when_pick_up_given_ticket_has_already_used() {
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(new ArrayList<>() {
            {
                add(new ParkingLot(1));
            }
        });
        Ticket parkingResult = smartParkingBoy.park(new Car());
        assertNotNull(parkingResult);
        Car pickUpResult = smartParkingBoy.pickUp(parkingResult);
        assertNotNull(pickUpResult);

        TicketInvalidException exception = assertThrows(TicketInvalidException.class,
                () -> smartParkingBoy.pickUp(parkingResult));
        assertThat(exception).hasMessageContaining("Ticket无效");
    }

}
