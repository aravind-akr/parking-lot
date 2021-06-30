package com.aravind.service;

import com.aravind.exceptions.ParkingLotException;
import com.aravind.models.ParkingLot;
import com.aravind.models.parking.strategy.ParkingLotStrategy;

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
}
