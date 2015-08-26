package com.barclays;

import java.util.List;

public class ParkingLotOwner implements ParkingLotObserver {

    private String name;
    private List<ParkingLot> parkingLotList;
    private ParkingLotAssistant assistant;
    private static int count=0;

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

    public void parkingSlotAvailableAgainNotification() {
        parkingIsFull = false;
    }
    public int callAssistantToParkCar(){
        
    }
}
