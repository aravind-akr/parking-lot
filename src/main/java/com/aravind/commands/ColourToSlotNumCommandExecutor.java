package com.aravind.commands;

import com.aravind.OutputPrinter;
import com.aravind.models.Command;
import com.aravind.models.Slot;
import com.aravind.service.ParkingLotService;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Executor to get the slot numbers for given colour
 */
public class ColourToSlotNumCommandExecutor extends CommandExecutor{

    public static String COMMAND_NAME = "slot_numbers_for_cars_with_colour";

    public ColourToSlotNumCommandExecutor(ParkingLotService parkingLotService, OutputPrinter outputPrinter) {
        super(parkingLotService, outputPrinter);
    }

    @Override
    public boolean validate(Command command) {
        return command.getParams().size() == 1;
    }

    @Override
    public void execute(Command command) {
        final List<Slot> slotsForColor = parkingLotService.getSlotsForColor(command.getParams().get(0));
        if(slotsForColor.isEmpty()){
            outputPrinter.notFound();
        }
        else{
            final String result = slotsForColor.stream()
                    .map(slot -> slot.getSlotNumber().toString())
                    .collect(Collectors.joining(", "));
            outputPrinter.printWithNewLine(result);
        }
    }
}
