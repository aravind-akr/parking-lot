package com.aravind.service;

import com.aravind.exceptions.ParkingLotException;
import com.aravind.models.Car;
import com.aravind.models.ParkingLot;
import com.aravind.models.Slot;
import com.aravind.models.parking.strategy.ParkingLotStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Service to enable the functionality of parking Lot. This will have business logic of
 * how the parking service will operate.
 */
public class ParkingLotService {

    private ParkingLotStrategy parkingStrategy;
    private ParkingLot parkingLot;

    /**
     * Allots the parking lot into the parking service.
     * Throws {@link ParkingLotException} if there is already a parking lot allotted to the service.
     * @param parkingLot to be allotted.
     * @param parkingStrategy to be used.
     */
    public void createParkingLot(final ParkingLot parkingLot, final ParkingLotStrategy parkingStrategy){
        if(this.parkingLot != null){
            throw new ParkingLotException("Parking Lot already exists");
        }

        this.parkingLot = parkingLot;
        this.parkingStrategy = parkingStrategy;
        for(int i=0;i<parkingLot.getCapacity();i++){
            parkingStrategy.addSlot(i);
        }
    }

    /**
     * Parks the {@link Car} into the next available slot, decided by {@link ParkingLotStrategy}
     * @param car to be parked into the parking lot.
     * @return SlotNumber , where the car should be parked.
     */
    public Integer park(final Car car){
        validateParkingLotExists();
        final Integer nextFreeSlot = parkingStrategy.getNextSlot();
        parkingLot.park(car,nextFreeSlot);
        parkingStrategy.removeSlot(nextFreeSlot);
        return nextFreeSlot;
    }

    /**
     * Validate whether any parking lot already exists, before doing any other operation.
     * throws {@link ParkingLotException}  if the {@link ParkingLot} is null
     */
    public void validateParkingLotExists(){
        if(parkingLot == null){
            throw new ParkingLotException("Parking lot does not exist to Park!!");
        }
    }

    /**
     * Makes the given slot available to be used, adds to {@link ParkingLotStrategy}
     * @param slotNum to make free
     */
    public void makeSlotFree(final int slotNum) {
        validateParkingLotExists();
        parkingLot.makeSlotFree(slotNum);
        parkingStrategy.addSlot(slotNum);
    }

    /**
     * Provides the list of occupied slots with the {@link Car} details parked into the {@link Slot}
     * @return List of Slots.
     */
    public List<Slot> getOccupiedSlots() {
        validateParkingLotExists();
        final List<Slot> occupiedSlots = new ArrayList<>();
        final Map<Integer, Slot> allSlots = parkingLot.getSlots();

        for(int slotCheck = 1;slotCheck<=parkingLot.getCapacity();slotCheck++){
            if(allSlots.containsKey(slotCheck)){
                final Slot slot = allSlots.get(slotCheck);
                if(!slot.isSlotFree()){
                    occupiedSlots.add(slot);
                }
            }
        }
        return occupiedSlots;
    }

    /**
     * Returns the list of Cars with the required color.
     * @param color to be checked for
     * @return List of slots.
     */
    public List<Slot> getSlotsForColor(final String color) {
        final List<Slot> occupiedSlots = getOccupiedSlots();
        return occupiedSlots.stream().
                filter(slot -> slot.getParkedCar().getColour().equalsIgnoreCase(color)).
                collect(Collectors.toList());
    }
}
