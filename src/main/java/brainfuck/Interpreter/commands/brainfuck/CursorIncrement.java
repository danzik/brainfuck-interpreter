package brainfuck.Interpreter.commands.brainfuck;

import brainfuck.Interpreter.context.InterpreterContext;
import brainfuck.Interpreter.commands.Command;

public class CursorIncrement extends Command {

    public CursorIncrement(CommandType commandType) {
        super(commandType);
    }

    @Override
    public void execute(InterpreterContext interpreterContext) {
        interpreterContext.setDataPointer(interpreterContext.getDataPointer() + getCountRepeats());
    }
}
