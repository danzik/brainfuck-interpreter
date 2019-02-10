package brainfuck.Interpreter;

import brainfuck.Interpreter.commands.Command;
import brainfuck.Interpreter.context.InterpreterContext;

import java.util.List;

public class BrainFuckRunner {

    public static void run(List<Command> commands, InterpreterContext context) {
        while (context.getInstructionPointer() < commands.size()) {
            Command command = commands.get(context.getInstructionPointer());
            command.execute(context);
            context.incrementInstructionPointer();
        }
    }
}