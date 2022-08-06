package org.oobootcamp.parkingLot;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.oobootcamp.parkingLot.model.Car;
import org.oobootcamp.parkingLot.model.Ticket;
import org.oobootcamp.parkingLot.exception.ParkingLotIsFullException;
import org.oobootcamp.parkingLot.exception.InvalidTicketException;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ParkingManagerTest {

    @Nested
    public class Parking
    {
        @Test
        void should_parking_by_smartBoy_when_parking_given_parkingManager_has_1_smartBoy_and_1_graduateBoy_and_1_parkingLot_all_of_them_not_parked() {
            SmartParkingBoy smartParkingBoy =new SmartParkingBoy(new ArrayList<>(List.of(new ParkingLot(1))));
            GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(new ArrayList<>(List.of(new ParkingLot(1))));
            ParkingLot parkingLot = new ParkingLot(1);
            ParkingManager parkingManager = new ParkingManager(List.of(
                    smartParkingBoy,
                    graduateParkingBoy,
                    parkingLot
            ));
            Car car = new Car();

            Ticket ticket = parkingManager.park(car);

            Car carPickUp = smartParkingBoy.pickUp(ticket);
            assertEquals(car, carPickUp);
        }

        @Test
        void should_parking_by_managers_parking_lot_when_parking_given_parkingManager_has_1_graduateBoy_and_1_smartBoy_and_1_parkingLot_only_managers_parking_lot_not_parked() {
            SmartParkingBoy smartParkingBoy =new SmartParkingBoy(new ArrayList<>(List.of(new ParkingLot(1))));
            GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(new ArrayList<>(List.of(new ParkingLot(1))));
            ParkingLot parkingLot = new ParkingLot(1);
            ParkingManager parkingManager = new ParkingManager(List.of(
                    graduateParkingBoy,
                    smartParkingBoy,
                    parkingLot
            ));
            smartParkingBoy.park(new Car());
            graduateParkingBoy.park(new Car());
            Car car = new Car();

            Ticket ticket = parkingManager.park(car);

            Car carPickUp = parkingLot.pickUp(ticket);
            assertEquals(car, carPickUp);
        }

        @Test
        void should_parking_by_smartBoy_when_parking_given_parkingManager_has_1_graduateBoy_and_1_smartBoy_and_1_parkingLot_all_parked() {
            SmartParkingBoy smartParkingBoy =new SmartParkingBoy(new ArrayList<>(List.of(new ParkingLot(1))));
            GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(new ArrayList<>(List.of(new ParkingLot(1))));
            ParkingLot parkingLot = new ParkingLot(1);

            ParkingManager parkingManager = new ParkingManager(
                    List.of(
                            graduateParkingBoy,
                            smartParkingBoy,
                            parkingLot
                    )
            );
            smartParkingBoy.park(new Car());
            graduateParkingBoy.park(new Car());
            parkingLot.park(new Car());
            Car car = new Car();

            ParkingLotIsFullException exception = assertThrows(ParkingLotIsFullException.class,
                    () -> parkingManager.park(car));
            assertThat(exception).hasMessageContaining("停车位已满");
        }
    }

    @Nested
    class PickUp {
        @Test
        void should_pick_up_success_when_pick_up_given_parkingManager_has_1_graduateBoy_and_1_smartBoy_and_1_parkingLot_and_car_is_in_his_parking_lot() {
            //Given
            SmartParkingBoy smartParkingBoy =new SmartParkingBoy(new ArrayList<>(List.of(new ParkingLot(1))));
            GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(new ArrayList<>(List.of(new ParkingLot(1))));
            ParkingLot parkingLot = new ParkingLot(1);

            smartParkingBoy.park(new Car());
            graduateParkingBoy.park(new Car());
            ParkingManager parkingManager = new ParkingManager(
                    List.of(
                            graduateParkingBoy,
                            smartParkingBoy,
                            parkingLot
                    )
            );
            Car car = new Car();
            Ticket ticket = parkingManager.park(car);

            Car pickedCar = parkingManager.pickUp(ticket);

            assertEquals(car, pickedCar);
        }

        @Test
        void should_pick_up_success_when_pick_up_given_parkingManager_has_1_graduateBoy_and_1_smartBoy_and_1_parkingLot_and_car_is_in_graduate_boy() {
            //Given
            SmartParkingBoy smartParkingBoy =new SmartParkingBoy(new ArrayList<>(List.of(new ParkingLot(1))));
            GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(new ArrayList<>(List.of(new ParkingLot(1))));
            ParkingLot parkingLot = new ParkingLot(1);

            ParkingManager parkingManager = new ParkingManager(
                    List.of(
                            graduateParkingBoy,
                            smartParkingBoy,
                            parkingLot
                    )
            );
            Car car = new Car();
            Ticket ticket = parkingManager.park(car);

            Car pickedCar = graduateParkingBoy.pickUp(ticket);

            assertEquals(car, pickedCar);
        }

        @Test
        void should_pick_up_failed_when_pick_up_given_parkingManager_has_1_graduateBoy_and_1_smartBoy_and_1_parkingLot_and_ticket_which_is_already_used_to_pick_up_car_from_parking_lot() {
            SmartParkingBoy smartParkingBoy =new SmartParkingBoy(new ArrayList<>(List.of(new ParkingLot(1))));
            GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(new ArrayList<>(List.of(new ParkingLot(1))));
            ParkingLot parkingLot = new ParkingLot(1);
            smartParkingBoy.park(new Car());
            graduateParkingBoy.park(new Car());
            ParkingManager parkingManager = new ParkingManager(
                    List.of(
                            graduateParkingBoy,
                            smartParkingBoy,
                            parkingLot
                    )
            );
            Car car = new Car();
            Ticket ticket = parkingManager.park(car);
            parkingManager.pickUp(ticket);

            InvalidTicketException exception = assertThrows(InvalidTicketException.class,
                    () -> parkingManager.pickUp(ticket));
            assertThat(exception).hasMessageContaining("Ticket无效");
        }
        @Test
        void should_pick_up_failed_when_pick_up_card_from_parkingManagerB_given_parkingManagerA_has_1_graduateBoy_and_parkingManagerB_has_1_parking_lot_and_ticket_which_is_returned_by_parkingManagerA() {
            ParkingManager parkingManagerA = new ParkingManager(
                    List.of(new GraduateParkingBoy(List.of(new ParkingLot(1))))
            );
            ParkingManager parkingManagerB = new ParkingManager(List.of(new ParkingLot(1)));
            Ticket ticket = parkingManagerA.park(new Car());

            InvalidTicketException exception = assertThrows(InvalidTicketException.class,
                    () -> parkingManagerB.pickUp(ticket));
            assertThat(exception).hasMessageContaining("Ticket无效");
        }
    }
}
