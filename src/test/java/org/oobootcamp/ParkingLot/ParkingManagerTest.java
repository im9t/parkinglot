package org.oobootcamp.ParkingLot;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.oobootcamp.ParkingLot.Model.Car;
import org.oobootcamp.ParkingLot.Model.Ticket;
import org.oobootcamp.ParkingLot.ParkingLotExceptions.ParkingLotIsFullException;

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
            //Given
            SmartParkingBoy smartParkingBoy =new SmartParkingBoy(new ArrayList<>(List.of(new ParkingLot(1))));
            GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(new ArrayList<>(List.of(new ParkingLot(1))));
            ParkingLot parkingLot = new ParkingLot(1);
            ArrayList<Parkable> parkables = new ArrayList<>(
                    List.of(
                            smartParkingBoy,
                            graduateParkingBoy,
                            parkingLot
                    )
            );
            ParkingManager parkingManager = new ParkingManager(parkables);
            Car car = new Car();

            //When
            Ticket ticket = parkingManager.park(car);

            //Then
            Car carPickUp = smartParkingBoy.pickUp(ticket);
            assertEquals(car, carPickUp);
        }

        @Test
        void should_parking_by_smartBoy_when_parking_given_parkingManager_has_1_smartBoy_and_1_graduateBoy_and_1_parkingLot_only_managers_parking_lot_not_parked() {
            //Given
            SmartParkingBoy smartParkingBoy =new SmartParkingBoy(new ArrayList<>(List.of(new ParkingLot(1))));
            GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(new ArrayList<>(List.of(new ParkingLot(1))));
            ParkingLot parkingLot = new ParkingLot(1);
            ArrayList<Parkable> parkables = new ArrayList<>(
                    List.of(
                            smartParkingBoy,
                            graduateParkingBoy,
                            parkingLot
                    )
            );
            ParkingManager parkingManager = new ParkingManager(parkables);
            smartParkingBoy.park(new Car());
            graduateParkingBoy.park(new Car());
            Car car = new Car();

            //When
            Ticket ticket = parkingManager.park(car);

            //Then
            Car carPickUp = parkingLot.pickUp(ticket);
            assertEquals(car, carPickUp);
        }

        @Test
        void should_parking_by_smartBoy_when_parking_given_parkingManager_has_1_smartBoy_and_1_graduateBoy_and_1_parkingLot_all_parked() {
            SmartParkingBoy smartParkingBoy =new SmartParkingBoy(new ArrayList<>(List.of(new ParkingLot(1))));
            GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(new ArrayList<>(List.of(new ParkingLot(1))));
            ParkingLot parkingLot = new ParkingLot(1);
            ArrayList<Parkable> parkables = new ArrayList<>(
                    List.of(
                            smartParkingBoy,
                            graduateParkingBoy,
                            parkingLot
                    )
            );
            ParkingManager parkingManager = new ParkingManager(parkables);
            smartParkingBoy.park(new Car());
            graduateParkingBoy.park(new Car());
            parkingLot.park(new Car());
            Car car = new Car();

            ParkingLotIsFullException exception = assertThrows(ParkingLotIsFullException.class,
                    () -> parkingManager.park(car));
            assertThat(exception).hasMessageContaining("停车位已满");
        }
    }

}
