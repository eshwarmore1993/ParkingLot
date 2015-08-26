package com.barclays;

import org.junit.Assert;
import org.junit.Test;

public class ParkingTest {

    @Test
    public void shouldParkCarInParkingLot() {
        ParkingLot parkingLot = new ParkingLot(10);
        Car car = new Car("DL-7S-BT-2883", "Honda");
        Assert.assertTrue(parkingLot.park(car)>0);
    }


    @Test
    public void shouldNotParkTheSameCarIfAlreadyInParkingLot() {
        ParkingLot parkingLot = new ParkingLot(10);
        Car car = new Car("DL-7S-BT-2883", "Honda");
        parkingLot.park(car);
        Assert.assertTrue(parkingLot.park(car)<0);
    }
    @Test
    public void shouldUnparkTheParkedCar(){
        ParkingLot parkingLot = new ParkingLot(10);
        Car car = new Car("DL-7S-BT-2883", "Honda");
        int token = parkingLot.park(car);
        Assert.assertEquals(car,parkingLot.unpark(token));
    }
    @Test
    public void shouldNotUnparkANonExistingCar(){
        ParkingLot parkingLot = new ParkingLot(10);
        Car car = new Car("DL-7S-BT-28893", "Honda");
        int token = parkingLot.park(car);

        Assert.assertEquals(null,parkingLot.unpark(token+1));
    }

}
