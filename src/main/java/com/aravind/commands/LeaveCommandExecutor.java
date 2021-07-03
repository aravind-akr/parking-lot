package com.aravind.commands;

import com.aravind.OutputPrinter;
import com.aravind.models.Command;
import com.aravind.service.ParkingLotService;
import com.aravind.validator.IntegerValidator;

import java.util.List;

/**
 * Executor to run the leave command to make the car leave from the slot and returns the slot number.
 */
public class LeaveCommandExecutor extends CommandExecutor{

    public static String COMMAND_NAME = "leave";

    public LeaveCommandExecutor(ParkingLotService parkingLotService, OutputPrinter outputPrinter) {
        super(parkingLotService, outputPrinter);
    }

    @Override
    public boolean validate(Command command) {
        final List<String> params = command.getParams();
        if(params.size()!=1){
            return false;
        }
        return IntegerValidator.isInteger(params.get(0));
    }

    @Override
    public void execute(Command command) {
        final int slotNum = Integer.parseInt(command.getParams().get(0));
        parkingLotService.makeSlotFree(slotNum);
        outputPrinter.printWithNewLine("Slot Number " + slotNum +" is free..");

    }
}
