package com.barclays;

import org.junit.Assert;
import org.junit.Test;

import static org.mockito.Mockito.*;

public class ParkingTest {

    ParkingLotOwner mockedOwner = mock(ParkingLotOwner.class);
    SecurityPerson mockedSecurity = mock(SecurityPerson.class);

    @Test
    public void shouldParkCarInParkingLot() {
        Car car = new Car("DL-7S-BT-2883", "Honda");
        ParkingLot parkingLot = new ParkingLot(10);
        Assert.assertTrue(parkingLot.park(car) > 0);
    }


    @Test
    public void shouldNotParkTheSameCarIfAlreadyInParkingLot() {
        ParkingLot parkingLot = new ParkingLot(10);
        Car car = new Car("DL-7S-BT-2883", "Honda");
        parkingLot.park(car);
        Assert.assertTrue(parkingLot.park(car) < 0);
    }

    @Test
    public void shouldUnparkTheParkedCar() {
        ParkingLot parkingLot = new ParkingLot(10);
        Car car = new Car("DL-7S-BT-2883", "Honda");
        int token = parkingLot.park(car);
        Assert.assertEquals(car, parkingLot.unpark(token));
    }

    @Test
    public void shouldNotUnparkANonExistingCar() {
        ParkingLot parkingLot = new ParkingLot(10);
        Car car = new Car("DL-7S-BT-28893", "Honda");
        int token = parkingLot.park(car);

        Assert.assertEquals(null, parkingLot.unpark(token + 1));
    }

    @Test
    public void shouldNotifyOwnerIfSlotIsFull() {
        ParkingLot parkingLot = new ParkingLot(1);
        parkingLot.subscribe(mockedOwner);
        Car car = new Car("DL-7S-BT-2883", "Honda");
        parkingLot.park(car);
        verify(mockedOwner, atLeastOnce()).fullParkingNotification();
    }

    @Test
    public void shouldNotNotifyOwnerIfSlotIsNotFull() {
        ParkingLot parkingLot = new ParkingLot(2);
        parkingLot.subscribe(mockedOwner);
        Car car = new Car("DL-7S-BT-2883", "Honda");
        parkingLot.park(car);
        verify(mockedOwner, never()).fullParkingNotification();
    }

    @Test
    public void shouldNotifyAllObserversIfSlotIsNotFull() {
        ParkingLot parkingLot = new ParkingLot(1);
        parkingLot.subscribe(mockedSecurity);
        parkingLot.subscribe(mockedOwner);
        Car car = new Car("DL-7S-BT-2883", "Honda");
        parkingLot.park(car);
        verify(mockedOwner, atLeastOnce()).fullParkingNotification();
        verify(mockedSecurity, atLeastOnce()).fullParkingNotification();
    }

}
