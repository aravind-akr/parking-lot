package com.aravind.models;

import java.util.HashMap;
import java.util.Map;

/**
 * Model to represent Parking slot for cars with slots
 */
public class ParkingLot {

    private static int MAX_CAPACITY = 10000;
    private int capacity;
    private Map<Integer,Slot> slots;

    public int getCapacity() {
        return capacity;
    }

    public ParkingLot(final int capacity) {
        if(capacity>MAX_CAPACITY || capacity<=0){

        }
        this.capacity = capacity;
        this.slots = new HashMap<>();
    }

    public Map<Integer, Slot> getSlots() {
        return slots;
    }

    /**
     * getSlot()
     * park()
     * makeSlotFree()
     */

}
