package com.barclays;

import com.barclays.exception.CarAlreadyParkException;
import com.barclays.exception.CarNotPresentException;
import com.barclays.exception.ParkingFullException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParkingLot {
    private Map<Token, Car> parkingMap = new HashMap<Token, Car>();
    private int parkingLotCapacity;
    private final int parkingCost = 10;
    int tokenCount;
    List<ParkingLotObserver> observers = new ArrayList<ParkingLotObserver>();

    public ParkingLot(int capacity) {
        this.parkingLotCapacity = capacity;
        this.tokenCount = 0;
    }

    public void subscribe(ParkingLotObserver newObserver) {
        observers.add(newObserver);
    }

    public Token park(Car car) {
        if (parkingMap.size() >= this.parkingLotCapacity)
            throw new ParkingFullException();
        else if (parkingMap.containsValue(car)) {
            throw new CarAlreadyParkException();
        }
        Token token = new Token(++tokenCount, parkingCost);
        parkingMap.put(token, car);
        if (parkingMap.size() == parkingLotCapacity) {
            for (ParkingLotObserver observer : observers) {
                observer.fullParkingNotification();
            }
        }
        return token;

    }

    public Car unpark(Token token) {
        if (parkingMap.containsKey(token)) {
            sendParkingEmptyNotification();
            return parkingMap.remove(token);

        }
        throw new CarNotPresentException();
    }

    private void sendParkingEmptyNotification() {
        for (ParkingLotObserver observer : observers) {
            observer.parkingEmptyAgainNotification();
        }
    }
}
