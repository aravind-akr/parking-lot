package com.aravind.commands;

import com.aravind.OutputPrinter;
import com.aravind.models.Command;
import com.aravind.service.ParkingLotService;

/**
 * Command Executor abstract class
 */
public abstract class CommandExecutor {
    protected ParkingLotService parkingLotService;
    protected OutputPrinter outputPrinter;

    public CommandExecutor(ParkingLotService parkingLotService, OutputPrinter outputPrinter) {
        this.parkingLotService = parkingLotService;
        this.outputPrinter = outputPrinter;
    }

    /**
     * validates a given command if it is valid or not
     * @param command to be validated
     * @return boolean indicating whether the command is valid and can proceed
     */
    public abstract boolean validate(Command command);

    /**
     * executes the command
     * @param command to be executed
     */
    public abstract void execute(Command command);
}
