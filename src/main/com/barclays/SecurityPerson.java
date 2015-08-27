package com.barclays;

public class SecurityPerson implements ParkingLotObserver {
    private String name;
    private boolean parkingIsFull = false;

    public SecurityPerson(String name) {
        this.name = name;
    }

    @Override
    public void sendNotification(NotificationType notification) {

    }
}
