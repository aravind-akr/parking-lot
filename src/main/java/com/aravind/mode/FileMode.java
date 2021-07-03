package com.aravind.mode;

import com.aravind.OutputPrinter;
import com.aravind.commands.CommandExecutorFactory;
import com.aravind.models.Command;

import java.io.*;

/**
 * Mode running in which input commands are given in a file.
 */
public class FileMode extends Mode{
    private String fileName;

    public FileMode(final CommandExecutorFactory commandExecutorFactory, final OutputPrinter outputPrinter,
                    final String fileName) {
        super(commandExecutorFactory, outputPrinter);
        this.fileName = fileName;
    }

    @Override
    public void process() throws IOException {
        final File file = new File(fileName);
        final BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(file));
        }catch (FileNotFoundException e){
            outputPrinter.invalidFile();
            return;
        }

        String input = reader.readLine();
        while(input != null){
            final Command command = new Command(input);
            processCommand(command);
            input = reader.readLine();
        }

    }
}
