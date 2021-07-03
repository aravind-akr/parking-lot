package com.aravind.commands;

import com.aravind.OutputPrinter;
import com.aravind.models.Command;
import com.aravind.models.Slot;
import com.aravind.service.ParkingLotService;

import java.util.List;
import java.util.Optional;

/**
 * Executor to get the slot number for the given registration number.
 */
public class SlotForRegNumCommandExecutor extends CommandExecutor{

    public static String COMMAND_NAME = "slot_number_for_registration_number";

    public SlotForRegNumCommandExecutor(ParkingLotService parkingLotService, OutputPrinter outputPrinter) {
        super(parkingLotService, outputPrinter);
    }

    @Override
    public boolean validate(Command command) {
        return command.getParams().size() == 1;
    }

    @Override
    public void execute(Command command) {
        final List<Slot> occupiedSlots = parkingLotService.getOccupiedSlots();
        final String regNumberToFind = command.getParams().get(0);
        final Optional<Slot> foundSlot = occupiedSlots.stream().
                filter(slot -> slot.getParkedCar().
                        getRegistrationNumber().equalsIgnoreCase(regNumberToFind))
                .findFirst();

        if(foundSlot.isPresent()){
            outputPrinter.printWithNewLine(foundSlot.get().getSlotNumber().toString());
        }
        else {
            outputPrinter.notFound();
        }


    }
}
