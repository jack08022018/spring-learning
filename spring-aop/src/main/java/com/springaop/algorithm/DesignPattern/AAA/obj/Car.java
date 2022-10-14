package com.springaop.algorithm.DesignPattern.AAA.obj;

import com.springaop.algorithm.DesignPattern.AAA.CarType;

public class Car {
    private final CarType carType;
    private final int seats;
    private final Engine engine;

    public Car(CarType carType, int seats, Engine engine) {
        this.carType = carType;
        this.seats = seats;
        this.engine = engine;
    }

    public CarType getCarType() {
        return carType;
    }

    public int getSeats() {
        return seats;
    }

    public Engine getEngine() {
        return engine;
    }

    public static class Builder {
        private final CarType carType;
        private final int seats;
        private final Engine engine;

        public Builder(CarType carType, int seats, Engine engine) {
            this.carType = carType;
            this.seats = seats;
            this.engine = engine;
        }

    }

}
