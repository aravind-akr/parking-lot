package com.aravind.models.parking.strategy;

import com.aravind.exceptions.NoFreeSlotAvailableException;

import java.util.TreeSet;

public class NaturalOrderingParkingStrategy implements ParkingLotStrategy{

    TreeSet<Integer> slotTreeSet;

    public NaturalOrderingParkingStrategy() {
        this.slotTreeSet = new TreeSet<>();
    }

    @Override
    public void addSlot(Integer slotNumber) {
        this.slotTreeSet.add(slotNumber);
    }

    @Override
    public void removeSlot(Integer slotNumber) {
        this.slotTreeSet.remove(slotNumber);
    }

    @Override
    public Integer getNextSlot() {
        if(slotTreeSet.isEmpty()){
            throw new NoFreeSlotAvailableException();
        }
        return this.slotTreeSet.first();
    }
}
