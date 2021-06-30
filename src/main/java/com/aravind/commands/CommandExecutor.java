package com.aravind.commands;

import com.aravind.OutputPrinter;
import com.aravind.models.Command;
import com.aravind.models.ParkingLot;
import com.aravind.models.parking.strategy.ParkingLotStrategy;
import com.aravind.service.ParkingLotService;

public abstract class CommandExecutor {
    protected ParkingLotService parkingLotService;
    protected OutputPrinter outputPrinter;

    public CommandExecutor(ParkingLotService parkingLotService, OutputPrinter outputPrinter) {
        this.parkingLotService = parkingLotService;
        this.outputPrinter = outputPrinter;
    }

    public abstract boolean validate(Command command);

    public abstract void execute(Command command);
}
