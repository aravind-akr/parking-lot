package com.aravind;

import com.aravind.commands.CommandExecutorFactory;
import com.aravind.mode.FileMode;
import com.aravind.mode.InteractiveMode;
import com.aravind.service.ParkingLotService;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        final OutputPrinter outputPrinter = new OutputPrinter();
        final ParkingLotService parkingLotService = new ParkingLotService();
        final CommandExecutorFactory commandExecutorFactory = new CommandExecutorFactory(parkingLotService);
        if(isInteractiveMode(args)){
            new InteractiveMode(commandExecutorFactory,outputPrinter).process();
        }
        else if(isFileMode(args)){
            new FileMode(commandExecutorFactory,outputPrinter,args[0]).process();
        }
    }

    private static boolean isFileMode(String[] args) {
        return args.length == 1;
    }

    private static boolean isInteractiveMode(String[] args) {
        return args.length == 0;
    }
}
