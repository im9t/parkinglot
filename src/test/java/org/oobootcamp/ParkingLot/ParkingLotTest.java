package org.oobootcamp.ParkingLot;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.oobootcamp.ParkingLot.Model.Car;
import org.oobootcamp.ParkingLot.Model.Result;
import org.oobootcamp.ParkingLot.Model.Ticket;

import static org.assertj.core.api.Assertions.assertThat;

public class ParkingLotTest {
    @Test
    void should_get_ticket_when_parking_car_given_100_capacity_avalable() {
        ParkingLot parkingLot = new ParkingLot(100);
        Car car = new Car();
        Result<Ticket> result = parkingLot.parkCar(car);
        assertThat(result.isSuccess).isTrue();
        assertThat(result.value).isNotNull();
        assertThat(result.errorInfo).isEmpty();
    }

    @Test
    void should_get_invalidTicket_when_parking_car_given_Notavalible_park() {
        ParkingLot parkingLot = new ParkingLot(0);
        Car car = new Car();
        Result<Ticket> result = parkingLot.parkCar(car);
        assertThat(result.isSuccess).isFalse();
        assertThat(result.value).isNull();;
        assertEquals("停车失败", result.errorInfo);
    }

    @Test
    void should_get_car_when_get_car_given_valid_ticket() {
        ParkingLot parkingLot = new ParkingLot(2);
        Car car = new Car();
        Result<Ticket> result = parkingLot.parkCar(car);
        Result<Car> carResult = parkingLot.getCar(result.value);
        assertTrue(carResult.isSuccess);
        assertThat(carResult.errorInfo).isEmpty();
        assertThat(carResult.value.Number).isEqualTo(car.Number);
        // assertThat(outCar).isEqualTo(aCar);
    }

    @Test
    void should_get_car_when_get_car_given_invalid_ticket() {
        ParkingLot parkingLot = new ParkingLot(2);
        Car aCar = new Car();
        parkingLot.parkCar(aCar);
        Ticket invalidTicket = new Ticket();
        Result<Car> result = parkingLot.getCar(invalidTicket);
        assertThat(result.value).isNull();
        assertFalse(result.isSuccess);
        assertEquals("Ticket无效", result.errorInfo);
    }
}
