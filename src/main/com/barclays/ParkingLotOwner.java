package com.barclays;

public class ParkingLotOwner implements ParkingLotAvailableObserver,ParkingLotFullObserver {

    private String name;

    public ParkingLotOwner(String name) {
        this.name = name;
    }

    private boolean parkingIsFull = false;

    @Override
    public void fullParkingNotification() {
        parkingIsFull = true;
    }

    @Override
    public void parkingEmptyAgainNotification() {

    }


}
