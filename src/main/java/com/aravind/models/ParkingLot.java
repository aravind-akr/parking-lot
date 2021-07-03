package com.aravind.models;

import com.aravind.exceptions.InvalidSlotException;
import com.aravind.exceptions.SlotAlreadyOccupiedException;

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
     * Helper method to get a {@link Slot} object for a given slot number. If slot does not exist,
     * new slot will be created and returns.
     * throws {@link InvalidSlotException}
     * @param slotNumber to be get the details for
     * @return {@link Slot} details
     */
    public Slot getSlot(final int slotNumber){
        if(slotNumber > getCapacity() || slotNumber<0){
            throw new InvalidSlotException();
        }

        final Map<Integer,Slot> allSlots = getSlots();
        if(!allSlots.containsKey(slotNumber)){
            allSlots.put(slotNumber,new Slot(slotNumber));
        }
        return allSlots.get(slotNumber);
    }

    /**
     * Helper method to park the Car in the given slot
     * @param car to park on the given {@param} slotNumber
     * throws {@link SlotAlreadyOccupiedException} if there is no available slot in the given slot Number.
     * @return {@link Slot}
     */
    public Slot park(final Car car, final Integer slotNumber){
        final Slot slot = getSlot(slotNumber);
        if(!slot.isSlotFree()){
            throw new SlotAlreadyOccupiedException();
        }
        slot.assignCar(car);
        return slot;
    }

    /**
     * Helper method to make the slot available.
     * @param slotNumber to be freed
     * @return slot
     */
    public Slot makeSlotFree(final Integer slotNumber){
        final Slot slot = getSlot(slotNumber);
        slot.unassignCar();
        return slot;
    }
}
