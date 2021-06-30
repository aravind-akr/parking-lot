package com.aravind.models.parking.strategy;

public interface ParkingLotStrategy {
    public void addSlot(Integer slotNumber);
    public void removeSlot(Integer slotNumber);
    public Integer getNextSlot();
}
