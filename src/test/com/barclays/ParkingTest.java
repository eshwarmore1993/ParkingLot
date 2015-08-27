package com.barclays;

import com.barclays.exception.CarAlreadyParkException;
import com.barclays.exception.CarNotPresentException;
import com.barclays.exception.ParkingFullException;
import org.junit.Assert;
import org.junit.Test;

import static org.mockito.Mockito.*;

public class ParkingTest {

    ParkingLotOwner mockedOwner = mock(ParkingLotOwner.class);
    SecurityPerson mockedSecurity = mock(SecurityPerson.class);

    @Test
    public void shouldParkCarInParkingLot() {
        Car car = new Car("DL-7S-BT-2883", "Honda");
        Token token = new Token(1, 10);
        ParkingLot parkingLot = new ParkingLot(10);
        Assert.assertEquals(token, parkingLot.park(car));
    }


    @Test(expected = CarAlreadyParkException.class)
    public void shouldNotParkTheSameCarIfAlreadyInParkingLot() {
        ParkingLot parkingLot = new ParkingLot(10);
        Car car = new Car("DL-7S-BT-2883", "Honda");
        parkingLot.park(car);
        parkingLot.park(car);
    }

    @Test
    public void shouldUnparkTheParkedCar() {
        ParkingLot parkingLot = new ParkingLot(10);
        Car car = new Car("DL-7S-BT-2883", "Honda");
        Token token = parkingLot.park(car);
        Assert.assertEquals(car, parkingLot.unpark(token));
    }

    @Test(expected = CarNotPresentException.class)
    public void shouldNotUnparkANonExistingCar() {
        ParkingLot parkingLot = new ParkingLot(10);
        Token token = new Token(1, 10);
        parkingLot.unpark(token);

    }

    @Test(expected = ParkingFullException.class)
    public void shouldNotParkIfParkingLotIsFull() {
        ParkingLot parkingLot = new ParkingLot(1);
        Car car = new Car("DL-7S-BT-2883", "Honda");
        Car car2 = new Car("DL-7S-ST-2991", "TVS");
        parkingLot.park(car);
        parkingLot.park(car2);
    }

    @Test
    public void shouldNotifyOwnerIfSlotIsFull() {
        ParkingLot parkingLot = new ParkingLot(1);
        parkingLot.subscribe(mockedOwner);
        Car car = new Car("DL-7S-BT-2883", "Honda");
        parkingLot.park(car);
        verify(mockedOwner, times(1)).fullParkingNotification();
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
        verify(mockedOwner, times(1)).fullParkingNotification();
        verify(mockedSecurity, times(1)).fullParkingNotification();
    }

    @Test
    public void shouldNotifyOwnerIfParkingLotIsEmptyAgain() {
        ParkingLot parkingLot = new ParkingLot(1);
        parkingLot.subscribe(mockedOwner);
        Car car = new Car("DL-7S-BT-2883", "Honda");
        Token token = parkingLot.park(car);
        parkingLot.unpark(token);
        verify(mockedOwner, times(1)).parkingEmptyAgainNotification();
    }

}
