package com.aravind.models;

import com.aravind.exceptions.InvalidCommandException;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Model object to represent the input Command
 */
public class Command {

    private static final String SPACE = " ";
    private String commandName;
    private List<String> params;

    public String getCommandName() {
        return commandName;
    }

    public List<String> getParams() {
        return params;
    }

    /**
     * Constructor. Its takes the input, parses the command name and params, if any.
     * If the command is invalid, then {@link InvalidCommandException} will be thrown
     *
     * @param inputLine to parses the command and params.
     */
    public Command(final String inputLine) {
        final List<String> tokensList = Arrays.stream(inputLine.trim().split(SPACE)).map(String::trim)
                .filter(token -> (token.length() > 0)).collect(Collectors.toList());

        if(tokensList.size() == 0){
            throw new InvalidCommandException();
        }
        commandName = tokensList.get(0).toLowerCase();
        tokensList.remove(0);
        params = tokensList;
    }
}
