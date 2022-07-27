package org.oobootcamp.ParkingLot;

import org.junit.jupiter.api.Test;
import org.oobootcamp.ParkingLot.Model.Car;
import org.oobootcamp.ParkingLot.Model.ParkingOutPut;
import org.oobootcamp.ParkingLot.Model.Ticket;

import static org.assertj.core.api.Assertions.assertThat;

public class ParkingLotTest {
    @Test
    void should_get_ticket_when_parking_car_given_avalible_park() {
        ParkingLot parkingLot = new ParkingLot(50);
        Car aCar = new Car();
        ParkingOutPut ticket = parkingLot.ParkCar(aCar);
        assertThat(ticket.getTicketNum()).isNotEqualTo("");
    }

    @Test
    void should_get_invalidTicket_when_parking_car_given_Notavalible_park() {
        ParkingLot parkingLot = new ParkingLot(0);
        Car aCar = new Car();
        ParkingOutPut ticket = parkingLot.ParkCar(aCar);
        assertThat(ticket.getTicketNum()).isEqualTo("");
    }

    @Test
    void should_get_car_when_get_car_given_valid_ticket() {
        ParkingLot parkingLot = new ParkingLot(2);
        Car aCar = new Car();
        ParkingOutPut ticket = parkingLot.ParkCar(aCar);
        Car outCar = parkingLot.GetCar(ticket);
        assertThat(outCar.Color).isEqualTo(aCar.Color);
        assertThat(outCar.Number).isEqualTo(aCar.Number);
        // assertThat(outCar).isEqualTo(aCar);
    }

    @Test
    void should_get_car_when_get_car_given_invalid_ticket() {
        ParkingLot parkingLot = new ParkingLot(2);
        Car aCar = new Car();
        parkingLot.ParkCar(aCar);
        Ticket invalidTicket = new Ticket();
        Car outCar = parkingLot.GetCar(invalidTicket);
        assertThat(outCar).isNull();
        // assertThat(outCar).isEqualTo(aCar);
    }
}
