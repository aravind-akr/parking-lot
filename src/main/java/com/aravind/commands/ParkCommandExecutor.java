package com.aravind.commands;

import com.aravind.OutputPrinter;
import com.aravind.exceptions.NoFreeSlotAvailableException;
import com.aravind.models.Car;
import com.aravind.models.Command;
import com.aravind.service.ParkingLotService;

public class ParkCommandExecutor extends CommandExecutor{

    public static String COMMAND_NAME = "park";

    public ParkCommandExecutor(ParkingLotService parkingLotService, OutputPrinter outputPrinter) {
        super(parkingLotService, outputPrinter);
    }

    @Override
    public boolean validate(Command command) {
        return command.getParams().size() == 2;
    }

    @Override
    public void execute(Command command) {
        final Car car = new Car(command.getParams().get(0),command.getParams().get(1));
        try{
            Integer slotNumber = parkingLotService.park(car);
            outputPrinter.printWithNewLine("Allocated Slot Number is "+slotNumber);
        }catch (NoFreeSlotAvailableException e){
            outputPrinter.parkingLotFull();
        }

    }
}
