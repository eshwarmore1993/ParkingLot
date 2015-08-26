package com.barclays;

public class Car {
    private String carNumber;
    private String carName;

    public Car(String carNumber, String carName) {
        this.carNumber = carNumber;
        this.carName = carName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Car car = (Car) o;

        return carNumber.equals(car.carNumber);

    }


}
