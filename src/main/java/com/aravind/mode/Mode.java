package com.aravind.mode;

import com.aravind.OutputPrinter;
import com.aravind.commands.CommandExecutor;
import com.aravind.commands.CommandExecutorFactory;
import com.aravind.exceptions.InvalidCommandException;
import com.aravind.models.Command;

import java.io.IOException;

public abstract class Mode {

    private CommandExecutorFactory commandExecutorFactory;
    protected OutputPrinter outputPrinter;

    public Mode(CommandExecutorFactory commandExecutorFactory, OutputPrinter outputPrinter) {
        this.commandExecutorFactory = commandExecutorFactory;
        this.outputPrinter = outputPrinter;
    }

    protected void processCommand(final Command command){
        final CommandExecutor commandExecutor = commandExecutorFactory.getCommandExecutor(command);
        if(commandExecutor.validate(command)){
            commandExecutor.execute(command);
        }
        else {
            throw new InvalidCommandException();
        }
    }

    public abstract void process() throws IOException;
}
