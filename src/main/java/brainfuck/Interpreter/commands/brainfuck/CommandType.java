package brainfuck.Interpreter.commands.brainfuck;

public enum CommandType {
    INC('+'),
    DEC('-'),
    PRINT('.'),
    END_CYCLE(']'),
    BEGIN_CYCLE('['),
    CURSOR_INCREMENT('>'),
    CURSOR_DECREMENT('<');

    public final char token;

    CommandType(char token) {
        this.token = token;
    }
}
