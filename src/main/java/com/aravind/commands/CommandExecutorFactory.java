package com.aravind.commands;

import com.aravind.OutputPrinter;
import com.aravind.exceptions.InvalidCommandException;
import com.aravind.models.Command;
import com.aravind.service.ParkingLotService;

import java.util.HashMap;
import java.util.Map;

public class CommandExecutorFactory {
    private Map<String, CommandExecutor> commands = new HashMap<>();

    public CommandExecutorFactory(final ParkingLotService parkingLotService){
        final OutputPrinter outputPrinter = new OutputPrinter();

        commands.put(CreateParkingLotCommandExecutor.COMMAND_NAME,
                new CreateParkingLotCommandExecutor(parkingLotService,outputPrinter));

        commands.put(ParkCommandExecutor.COMMAND_NAME,
                new ParkCommandExecutor(parkingLotService,outputPrinter));

        commands.put(LeaveCommandExecutor.COMMAND_NAME,
                new LeaveCommandExecutor(parkingLotService,outputPrinter));

        commands.put(StatusCommandExecutor.COMMAND_NAME,
                new StatusCommandExecutor(parkingLotService,outputPrinter));

        commands.put(ColorToRegNumCommandExecutor.COMMAND_NAME,
                new ColorToRegNumCommandExecutor(parkingLotService,outputPrinter));

        commands.put(ColotToSlotNumCommandExecutor.COMMAND_NAME,
                new ColotToSlotNumCommandExecutor(parkingLotService,outputPrinter));

        commands.put(SlotForRegNumCommandExecutor.COMMAND_NAME,
                new SlotForRegNumCommandExecutor(parkingLotService,outputPrinter));

        commands.put(ExitCommandExecutor.COMMAND_NAME,
                new ExitCommandExecutor(parkingLotService,outputPrinter));

    }

    public CommandExecutor getCommandExecutor(Command command) {
        final CommandExecutor commandExecutor = commands.get(command.getCommandName());
        if(commandExecutor == null){
            throw new InvalidCommandException();
        }
        return commandExecutor;
    }
}
