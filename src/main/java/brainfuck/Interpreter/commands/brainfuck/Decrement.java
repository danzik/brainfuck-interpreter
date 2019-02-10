package brainfuck.Interpreter.commands.brainfuck;

import brainfuck.Interpreter.context.InterpreterContext;
import brainfuck.Interpreter.commands.Command;

public class Decrement extends Command {

    public Decrement(CommandType commandType) {
        super(commandType);
    }

    public Decrement(int countRepeats, CommandType commandType) {
        super(countRepeats, commandType);
    }

    @Override
    public void execute(InterpreterContext interpreterContext) {
        interpreterContext.decrementByIndex(interpreterContext.getDataPointer(), getCountRepeats());
    }
}
