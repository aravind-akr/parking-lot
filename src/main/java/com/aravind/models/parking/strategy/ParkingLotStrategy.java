package com.aravind.models.parking.strategy;

/**
 * Strategy which will be used to decide how slots will be used to park the car.
 */
public interface ParkingLotStrategy {

    /**
     * Add a new slot to parking strategy. After adding, this slot will be available for parking vehicles
     *
     * @param slotNumber to be added/
     */
    public void addSlot(Integer slotNumber);

    /**
     * Remove the slot from parking strategy. Once removed, this slot becomes unavailable for future parking.
     *
     * @param slotNumber to be removed.
     */
    public void removeSlot(Integer slotNumber);

    /**
     * Returns the next available slot as per parking strategy.
     *
     * @return next free slot number
     */
    public Integer getNextSlot();
}
