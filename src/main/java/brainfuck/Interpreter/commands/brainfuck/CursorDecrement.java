package brainfuck.Interpreter.commands.brainfuck;

import brainfuck.Interpreter.context.InterpreterContext;
import brainfuck.Interpreter.commands.Command;

public class CursorDecrement extends Command {

    public CursorDecrement(CommandType commandType) {
        super(commandType);
    }

    @Override
    public void execute(InterpreterContext interpreterContext) {
        interpreterContext.setDataPointer(interpreterContext.getDataPointer() - getCountRepeats());
    }
}
