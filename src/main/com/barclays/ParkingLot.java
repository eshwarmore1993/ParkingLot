package com.barclays;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {
    private Map<Integer, Car> parkingMap;
    private int parkingLotCapacity;
    int token;

    public ParkingLot(int capacity) {
        this.parkingLotCapacity = capacity;
        this.parkingMap = new HashMap<Integer, Car>(capacity);
        token=1;
    }

    public boolean park(Car car){
        if(parkingMap.size() >= this.parkingLotCapacity)
            return false;
        else if(parkingMap.containsValue(car)){
            return false;
        }
        parkingMap.put(token++, car);
        return true;
    }
}
