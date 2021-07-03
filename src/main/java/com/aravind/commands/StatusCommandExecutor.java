package com.aravind.commands;

import com.aravind.OutputPrinter;
import com.aravind.models.Car;
import com.aravind.models.Command;
import com.aravind.models.Slot;
import com.aravind.service.ParkingLotService;

import java.util.List;

/**
 * Executor to provide the status of parking lot, with the slot numbers, registration number and color of the Car
 */
public class StatusCommandExecutor extends CommandExecutor{

    public static String COMMAND_NAME = "status";

    public StatusCommandExecutor(ParkingLotService parkingLotService, OutputPrinter outputPrinter) {
        super(parkingLotService, outputPrinter);
    }

    @Override
    public boolean validate(Command command) {
        return command.getParams().isEmpty();
    }

    @Override
    public void execute(Command command) {
        final List<Slot> occupiedSlots = parkingLotService.getOccupiedSlots();
        if(occupiedSlots.isEmpty()){
            outputPrinter.parkingLotEmpty();
        }

        outputPrinter.statusHeader();
        for(Slot slot:occupiedSlots){
           final Car parkedCar = slot.getParkedCar();
           final String slotNumber = slot.getSlotNumber().toString();
           outputPrinter.printWithNewLine(padString(slotNumber,12)+
                   padString(parkedCar.getRegistrationNumber(),19)+
                   parkedCar.getColour());
        }

    }

    public static String padString(final String word, final int length){
        String newWord = word;
        for(int count = word.length();count<length;count++){
            newWord = newWord + " ";
        }
        return newWord;
    }
}
