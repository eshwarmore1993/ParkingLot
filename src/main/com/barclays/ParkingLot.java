package com.barclays;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParkingLot {
    private Map<Integer, Car> parkingMap = new HashMap<Integer, Car>();
    private int parkingLotCapacity;
    int token;
    List<ParkingLotObserver> observers = new ArrayList<ParkingLotObserver>();

    public ParkingLot(int capacity) {
        this.parkingLotCapacity = capacity;
        this.token = 0;
    }
    public void subscribe(ParkingLotObserver newObserver){
        observers.add(newObserver);
    }

    public int park(Car car) {
        if (parkingMap.size() >= this.parkingLotCapacity)
            return -1;
        else if (parkingMap.containsValue(car)) {
            return -1;
        }
        parkingMap.put(++token, car);
        if (parkingMap.size() == parkingLotCapacity) {
            for(ParkingLotObserver observer : observers){
                observer.fullParkingNotification();
            }
        }
        return token;

    }

    public Car unpark(int token) {
        if (parkingMap.containsKey(token)) {

            return parkingMap.remove(token);

        }
        return null;
    }
}
