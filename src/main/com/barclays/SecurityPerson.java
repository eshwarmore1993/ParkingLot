package com.barclays;

public class SecurityPerson implements ParkingLotFullObserver {
    private String name;
    private boolean parkingIsFull = false;

    public SecurityPerson(String name) {
        this.name = name;
    }

    @Override
    public void fullParkingNotification() {
        parkingIsFull = true;
    }



}
