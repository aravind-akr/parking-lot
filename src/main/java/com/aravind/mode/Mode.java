package com.aravind.mode;

import com.aravind.OutputPrinter;
import com.aravind.commands.CommandExecutor;
import com.aravind.commands.CommandExecutorFactory;
import com.aravind.exceptions.InvalidCommandException;
import com.aravind.models.Command;

import java.io.IOException;

/**
 * Interface for mode of the program in which it can be ran.
 */
public abstract class Mode {

    private CommandExecutorFactory commandExecutorFactory;
    protected OutputPrinter outputPrinter;

    public Mode(CommandExecutorFactory commandExecutorFactory, OutputPrinter outputPrinter) {
        this.commandExecutorFactory = commandExecutorFactory;
        this.outputPrinter = outputPrinter;
    }

    /**
     * Helper command to process a command. It basically uses {@link CommandExecutor} to run the
     * given command
     * @param command to be processed
     */
    protected void processCommand(final Command command){
        final CommandExecutor commandExecutor = commandExecutorFactory.getCommandExecutor(command);
        if(commandExecutor.validate(command)){
            commandExecutor.execute(command);
        }
        else {
            throw new InvalidCommandException();
        }
    }

    /**
     * Abstract method to process the mode. Each mode has its own process.
     * @throws IOException
     */
    public abstract void process() throws IOException;
}
