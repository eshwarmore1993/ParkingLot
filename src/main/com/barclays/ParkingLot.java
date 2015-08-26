package com.barclays;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {
    private Map<Integer, Car> parkingMap = new HashMap<Integer, Car>();
    private int parkingLotCapacity;
    int token;

    public ParkingLot(int capacity) {
        this.parkingLotCapacity = capacity;
        this.token = 0;
    }

    public int park(Car car) {
        if (parkingMap.size() >= this.parkingLotCapacity)
            return -1;
        else if (parkingMap.containsValue(car)) {
            return -1;
        }
        parkingMap.put(++token, car);
        return token;

    }

    public Car unpark(int token) {
        if (parkingMap.containsKey(token)) {
            return parkingMap.remove(token);

        }
        return null;
    }
}
