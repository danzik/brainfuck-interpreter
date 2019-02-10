package brainfuck.Interpreter.compiler;

import brainfuck.Interpreter.commands.Command;

import java.util.List;

public interface Compiler {
    List<Command> compile(String program);
}
