package com.barclays;

public class ParkingLotOwner implements ParkingLotObserver {

    private String name;

    public ParkingLotOwner(String name) {
        this.name = name;
    }

    private boolean parkingIsFull = false;

    @Override
    public void fullParkingNotification() {
        parkingIsFull = true;
    }

    public boolean isParkingFull() {
        return parkingIsFull;
    }
}
