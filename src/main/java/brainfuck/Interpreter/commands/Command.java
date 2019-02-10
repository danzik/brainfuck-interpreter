package brainfuck.Interpreter.commands;

import brainfuck.Interpreter.commands.brainfuck.CommandType;
import brainfuck.Interpreter.context.InterpreterContext;

public abstract class Command {
    public int countRepeats;
    public CommandType commandType;

    public Command(CommandType commandType) {
        this.countRepeats = 1;
        this.commandType = commandType;
    }

    public Command(int countRepeats, CommandType commandType) {
        this.countRepeats = countRepeats;
        this.commandType = commandType;
    }

    public abstract void execute(InterpreterContext interpreterContext);

    public int getCountRepeats() {
        return countRepeats;
    }

    public void setCountRepeats(int countRepeats) {
        this.countRepeats = countRepeats;
    }

}