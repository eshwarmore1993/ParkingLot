package com.barclays;

import org.junit.Assert;
import org.junit.Test;

import static org.mockito.Mockito.mock;

public class ParkingTest {

    Car mockedCar = mock(Car.class);

    @Test
    public void shouldParkCarInParkingLot() {
        ParkingLot parkingLot = new ParkingLot(10);
        Assert.assertTrue(parkingLot.park(mockedCar) > 0);
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
        ParkingLotOwner observer = new ParkingLotOwner("Eshwar");
        ParkingLot parkingLot = new ParkingLot(1);
        parkingLot.subscribe(observer);
        Car car = new Car("DL-7S-BT-2883", "Honda");
        parkingLot.park(car);
        Assert.assertTrue(observer.isParkingFull());
    }

    @Test
    public void shouldNotNotifyOwnerIfSlotIsNotFull() {
        ParkingLotOwner observer = new ParkingLotOwner("Eshwar");
        ParkingLot parkingLot = new ParkingLot(2);
        parkingLot.subscribe(observer);
        Car car = new Car("DL-7S-BT-2883", "Honda");
        parkingLot.park(car);
        Assert.assertFalse(observer.isParkingFull());
    }

    @Test
    public void shouldNotifyAllObserversIfSlotIsNotFull() {
        ParkingLotObserver owner = new ParkingLotOwner("Eshwar");
        ParkingLotObserver security= new SecurityPerson("sambhav");
        ParkingLot parkingLot = new ParkingLot(1);
        parkingLot.subscribe(new ParkingLotObserver() {
            @Override
            public void fullParkingNotification() {

            }

            @Override
            public boolean isParkingFull() {
                return false;
            }
        });
        parkingLot.subscribe(security);
        Car car = new Car("DL-7S-BT-2883", "Honda");
        parkingLot.park(car);
        Assert.assertTrue(owner.isParkingFull());
        Assert.assertTrue(security.isParkingFull());
    }

}
