package com.barclays;

import org.junit.Assert;
import org.junit.Test;

public class ParkingTest {

    @Test
    public void shouldParkCarInParkingLot(){
        ParkingLot parkingLot = new ParkingLot();
        Car car = new Car();
        Assert.assertTrue(parkingLot.park(car));
    }

}
