package com.aravind.commands;

import com.aravind.OutputPrinter;
import com.aravind.models.Command;
import com.aravind.models.ParkingLot;
import com.aravind.models.parking.strategy.NaturalOrderingParkingStrategy;
import com.aravind.models.parking.strategy.ParkingLotStrategy;
import com.aravind.service.ParkingLotService;
import com.aravind.validator.IntegerValidator;

import java.util.List;

/**
 * Executor to create a parking lot with the given number, which is passes in the command line
 */
public class CreateParkingLotCommandExecutor extends CommandExecutor{

    public static String COMMAND_NAME = "create_parking_lot";

    public CreateParkingLotCommandExecutor(ParkingLotService parkingLotService, OutputPrinter outputPrinter) {
        super(parkingLotService, outputPrinter);
    }


    @Override
    public boolean validate(final Command command) {
        final List<String> params = command.getParams();
        if(params.size()!=1){
            return false;
        }
        return IntegerValidator.isInteger(params.get(0));
    }

    @Override
    public void execute(final Command command) {
        final int parkingLotCapacity = Integer.parseInt(command.getParams().get(0));
        final ParkingLot parkingLot = new ParkingLot(parkingLotCapacity);
        parkingLotService.createParkingLot(parkingLot,new NaturalOrderingParkingStrategy());
        outputPrinter.printWithNewLine("Created a parking lot with "+parkingLotCapacity);
    }
}
