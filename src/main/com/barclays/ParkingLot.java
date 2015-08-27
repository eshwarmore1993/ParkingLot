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
    Map<SubscriptionStrategy, List<ParkingLotObserver>> observers = new HashMap<SubscriptionStrategy, List<ParkingLotObserver>>();

    public ParkingLot(int capacity) {
        this.parkingLotCapacity = capacity;
        this.tokenCount = 0;
        List<ParkingLotObserver> fullSubscriberList = new ArrayList<ParkingLotObserver>();

    }

    public void subscribe(SubscriptionStrategy strategy, ParkingLotObserver newObserver) {
        if (observers.containsKey(strategy)) {
            observers.get(strategy).add(newObserver);
        } else {
            List<ParkingLotObserver> newStrategySubscriberList = new ArrayList<ParkingLotObserver>();
            newStrategySubscriberList.add(newObserver);
            observers.put(strategy, newStrategySubscriberList);
        }
    }

    public void unsubscribe(ParkingLotAvailableObserver observer) {
        observers.get(SubscriptionStrategy.ALL).remove(observer);
        observers.get(SubscriptionStrategy.PARTIAL).remove(observer);
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
            sendParkingFullNotification();

        }
        return token;

    }

    private void sendParkingFullNotification() {
        for (SubscriptionStrategy strategy : observers.keySet()) {
            List<ParkingLotObserver> allObservers = observers.get(strategy);
            for (ParkingLotObserver observer : allObservers) {
                observer.sendNotification(NotificationType.PARKINGFULL);
            }
        }
    }

    public Car unpark(Token token) {
        if (parkingMap.containsKey(token)) {
            sendParkingEmptyNotification();
            return parkingMap.remove(token);

        }
        throw new CarNotPresentException();
    }

    private void sendParkingEmptyNotification() {
        List<ParkingLotObserver> fullObservers = observers.get(SubscriptionStrategy.ALL);
        if (fullObservers != null) {
            for (ParkingLotObserver observer : fullObservers) {
                observer.sendNotification(NotificationType.PARKINGAVAILABLEAGAIN);
            }
        }
    }
}
