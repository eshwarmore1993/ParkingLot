package com.barclays;

import org.junit.Assert;
import org.junit.Test;

public class ParkingTest {

    @Test
    public void shouldParkCarInParkingLot() {
        ParkingLot parkingLot = new ParkingLot(10);
        Car car = new Car("DL-7S-BT-2883", "Honda");
        Assert.assertTrue(parkingLot.park(car));
    }

    @Test
    public void shouldNotParkTheSameCarIfAlreadyInParkingLot(){
        ParkingLot parkingLot = new ParkingLot(10);
        Car car = new Car("DL-7S-BT-2883", "Honda");
        parkingLot.park(car);
        Assert.assertFalse(parkingLot.park(car));
    }

}
