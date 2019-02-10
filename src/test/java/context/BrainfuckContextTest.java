package context;

import brainfuck.Interpreter.BrainFuckRunner;
import brainfuck.Interpreter.commands.Command;
import brainfuck.Interpreter.commands.brainfuck.CommandType;
import brainfuck.Interpreter.commands.brainfuck.CursorDecrement;
import brainfuck.Interpreter.commands.brainfuck.CursorIncrement;
import brainfuck.Interpreter.commands.brainfuck.Decrement;
import brainfuck.Interpreter.commands.brainfuck.Increment;
import brainfuck.Interpreter.context.BrainFuckContext;
import brainfuck.Interpreter.context.InterpreterContext;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.io.PrintStream;
import java.util.Collections;

public class BrainfuckContextTest {
    private InterpreterContext context;

    @Mock
    private PrintStream printStream;

    @Before
    public void setUp() {
        byte[] memory = new byte[30000];
        context = new BrainFuckContext(printStream, memory);
    }

    @Test
    public void shouldIncrementAndDecrementValueInMemory() {
        Command incrementCommand = new Increment(CommandType.INC);
        Command decrementCommand = new Decrement(CommandType.DEC);
        BrainFuckRunner.run(Collections.singletonList(incrementCommand), context);
        byte[] memory = context.getMemory();

        Assert.assertEquals(memory[context.getDataPointer()], 1);

        BrainFuckRunner.run(Collections.singletonList(decrementCommand), context);

        Assert.assertEquals(memory[context.getDataPointer()], 0);
    }

    @Test
    public void shouldIncrementAndDecrementDataPointer() {
        Command incrementDataPointer = new CursorIncrement(CommandType.CURSOR_INCREMENT);
        Command decrementDataPointer = new CursorDecrement(CommandType.CURSOR_DECREMENT);

        BrainFuckRunner.run(Collections.singletonList(incrementDataPointer), context);
        Assert.assertEquals(context.getDataPointer(), 1);

        context.moveTo(0);

        BrainFuckRunner.run(Collections.singletonList(decrementDataPointer), context);
        Assert.assertEquals(context.getDataPointer(), 0);
    }
}
