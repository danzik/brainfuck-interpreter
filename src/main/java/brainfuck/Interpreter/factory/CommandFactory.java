package brainfuck.Interpreter.factory;


import brainfuck.Interpreter.commands.Command;

public abstract class CommandFactory {
    public abstract Command getCommand(char symbol);
}
