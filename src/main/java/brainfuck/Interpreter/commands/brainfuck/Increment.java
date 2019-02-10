package brainfuck.Interpreter.commands.brainfuck;

import brainfuck.Interpreter.context.InterpreterContext;
import brainfuck.Interpreter.commands.Command;

public class Increment extends Command {

    public Increment(CommandType commandType) {
        super(commandType);
    }

    @Override
    public void execute(InterpreterContext context) {
        context.incrementByIndex(context.getDataPointer(), getCountRepeats());
    }
}