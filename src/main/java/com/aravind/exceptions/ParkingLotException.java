package com.aravind.exceptions;

/**
 * Generic Exception for general parking lot exceptions
 */
public class ParkingLotException extends RuntimeException{

    public ParkingLotException(){
    }

    public ParkingLotException(String message){
        super(message);
    }
}
