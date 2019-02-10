package brainfuck.Interpreter.commands.brainfuck;

import brainfuck.Interpreter.context.InterpreterContext;
import brainfuck.Interpreter.commands.Command;

public class PrintChar extends Command {

    public PrintChar(CommandType commandType) {
        super(commandType);
    }

    @Override
    public void execute(InterpreterContext interpreterContext) {
        byte[] memory = interpreterContext.getMemory();
        for (int i = 0; i < getCountRepeats(); i++) {
            interpreterContext.print((char) memory[interpreterContext.getDataPointer()]);
        }
    }
}
