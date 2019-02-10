package brainfuck.Interpreter.exception;

public class CommandNotSupport extends RuntimeException {

    public CommandNotSupport(String message) {
        super(message);
    }
}
