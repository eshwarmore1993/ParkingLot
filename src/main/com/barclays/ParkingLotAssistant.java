package com.barclays;

public class ParkingLotAssistant {
    String name;
    public int park(Car car,ParkingLot parkingLot){
        int token=parkingLot.park(car);
        return token;
    }
}
