package com.aravind.mode;

import com.aravind.OutputPrinter;
import com.aravind.commands.CommandExecutorFactory;
import com.aravind.commands.ExitCommandExecutor;
import com.aravind.models.Command;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Mode running in which input commands are given in a interactive command prompt.
 */
public class InteractiveMode extends Mode{

    public InteractiveMode(final CommandExecutorFactory commandExecutorFactory, final OutputPrinter outputPrinter) {
        super(commandExecutorFactory, outputPrinter);
    }

    @Override
    public void process() throws IOException {
        outputPrinter.welcome();
        final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while(true){
            final String input = reader.readLine();
            final Command command = new Command(input);
            processCommand(command);
            if(command.getCommandName().equalsIgnoreCase(ExitCommandExecutor.COMMAND_NAME));
        }

    }
}
