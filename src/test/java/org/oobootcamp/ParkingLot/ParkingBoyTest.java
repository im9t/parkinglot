package org.oobootcamp.ParkingLot;

import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.assertj.core.api.Assertions.assertThat;


import org.junit.jupiter.api.Test;
import org.oobootcamp.ParkingLot.Model.Car;
import org.oobootcamp.ParkingLot.Model.Result;
import org.oobootcamp.ParkingLot.Model.Ticket;

public class ParkingBoyTest {
    //Given 管理A和B两个停车场的小弟, A停车场已停满，B停车场有50个可用车位，When 停车, Then 停车成功，并获取到 Ticket
    @Test
    void should_parking_successed_and_get_ticked_when_parking_car_by_parking_boy_given_parking_boy_has_avalable_parking_lot()
    {
        ArrayList<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(new ParkingLot(0));
        parkingLots.add(new ParkingLot(2));
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);
        Car car = new Car();

        Result<Ticket> parkingResult = parkingBoy.park(car);

        assertTrue(parkingResult.isSuccessed);
        assertNotNull(parkingResult.value);
    }
}
