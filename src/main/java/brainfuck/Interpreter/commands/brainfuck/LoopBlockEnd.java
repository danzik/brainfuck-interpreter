package brainfuck.Interpreter.commands.brainfuck;

import brainfuck.Interpreter.context.InterpreterContext;
import brainfuck.Interpreter.commands.Command;

public class LoopBlockEnd extends Command {

    public LoopBlockEnd(CommandType commandType) {
        super(commandType);
    }

    public LoopBlockEnd(int countRepeats, CommandType commandType) {
        super(countRepeats, commandType);
    }

    @Override
    public void execute(InterpreterContext interpreterContext) {
        byte[] memory = interpreterContext.getMemory();
        if (memory[interpreterContext.getDataPointer()] != 0) {
            interpreterContext.moveTo(getCountRepeats());
        }
    }
}
