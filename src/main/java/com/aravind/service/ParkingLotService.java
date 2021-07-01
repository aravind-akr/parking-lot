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

public class ParkingLotService {

    private ParkingLotStrategy parkingStrategy;
    private ParkingLot parkingLot;

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

    public Integer park(final Car car){
        validateParkingLotExists();
        final Integer nextFreeSlot = parkingStrategy.getNextSlot();
        parkingLot.park(car,nextFreeSlot);
        parkingStrategy.removeSlot(nextFreeSlot);
        return nextFreeSlot;
    }

    public void validateParkingLotExists(){
        if(parkingLot == null){
            throw new ParkingLotException("Parking lot does not exist to Park!!");
        }
    }

    public void makeSlotFree(final int slotNum) {
        validateParkingLotExists();
        parkingLot.makeSlotFree(slotNum);
        parkingStrategy.addSlot(slotNum);
    }

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

    public List<Slot> getSlotsForColor(final String color) {
        final List<Slot> occupiedSlots = getOccupiedSlots();
        return occupiedSlots.stream().
                filter(slot -> slot.getParkedCar().getColour().equalsIgnoreCase(color)).
                collect(Collectors.toList());
    }
}
