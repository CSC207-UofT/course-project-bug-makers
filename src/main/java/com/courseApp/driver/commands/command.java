package com.courseApp.driver.commands;

public abstract class command {
    protected final int maxArguments;
    protected final int minArguments;

    public command(int maxArguments, int minArguments) {
        this.maxArguments = maxArguments;
        this.minArguments = minArguments;
    }

    public int getMaxArg() {
        return maxArguments;
    }

    public int getMinArg() {
        return minArguments;
    }

    abstract public String executeCommand
            (IShellState shellState, List<String> arguments) throws Exception;

    protected void checkArgumentsNum(List<String> arguments) throws Exception {
        if (arguments.size() > maxArguments) {
            throw new Exception("too many arguments provided");
        }
        if (arguments.size() < minArguments) {
            throw new Exception("too little arguments provided");
        }
    }
}
