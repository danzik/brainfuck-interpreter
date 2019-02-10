package brainfuck.Interpreter.factory;

import brainfuck.Interpreter.commands.brainfuck.CommandType;
import brainfuck.Interpreter.commands.Command;
import brainfuck.Interpreter.commands.brainfuck.CursorDecrement;
import brainfuck.Interpreter.commands.brainfuck.CursorIncrement;
import brainfuck.Interpreter.commands.brainfuck.Decrement;
import brainfuck.Interpreter.commands.brainfuck.Increment;
import brainfuck.Interpreter.commands.brainfuck.LoopBlockBegin;
import brainfuck.Interpreter.commands.brainfuck.LoopBlockEnd;
import brainfuck.Interpreter.commands.brainfuck.PrintChar;
import brainfuck.Interpreter.exception.CommandNotSupport;

public class BrainfuckCommandFactory extends CommandFactory {
    @Override
    public Command getCommand(char commandCandidate) {
        switch (commandCandidate) {
            case '-' : return new Decrement(CommandType.DEC);
            case '+' : return new Increment(CommandType.INC);
            case '.' : return new PrintChar(CommandType.PRINT);
            case ']' : return new LoopBlockEnd(CommandType.END_CYCLE);
            case '[' : return new LoopBlockBegin(CommandType.BEGIN_CYCLE);
            case '>' : return new CursorIncrement(CommandType.CURSOR_INCREMENT);
            case '<' : return new CursorDecrement(CommandType.CURSOR_DECREMENT);
        }
        throw new CommandNotSupport("Symbol " + commandCandidate + " is not support this factory");
    }
}
