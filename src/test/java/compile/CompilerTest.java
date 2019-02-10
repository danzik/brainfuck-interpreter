package compile;

import brainfuck.Interpreter.commands.Command;
import brainfuck.Interpreter.commands.brainfuck.CommandType;
import brainfuck.Interpreter.commands.brainfuck.CursorDecrement;
import brainfuck.Interpreter.commands.brainfuck.CursorIncrement;
import brainfuck.Interpreter.commands.brainfuck.Decrement;
import brainfuck.Interpreter.commands.brainfuck.Increment;
import brainfuck.Interpreter.commands.brainfuck.LoopBlockBegin;
import brainfuck.Interpreter.commands.brainfuck.LoopBlockEnd;
import brainfuck.Interpreter.commands.brainfuck.PrintChar;
import brainfuck.Interpreter.compiler.BrainFuckCompiler;
import brainfuck.Interpreter.compiler.Compiler;
import brainfuck.Interpreter.exception.CommandNotSupport;
import brainfuck.Interpreter.factory.BrainfuckCommandFactory;
import brainfuck.Interpreter.factory.CommandFactory;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.collection.IsIterableContainingInOrder.contains;
import static org.junit.Assert.assertThat;

public class CompilerTest {
    private Compiler compiler;

    @Before
    public void setUp() {
        CommandFactory factory = new BrainfuckCommandFactory();
        compiler = new BrainFuckCompiler(factory);
    }

    @Test
    public void shouldCountConsecutiveProgram() {
        String program = "++++++++";

        List<Command> compiled = compiler.compile(program);
        assertThat(compiled, contains(
                commandMatcher(new Decrement(8, CommandType.INC))
        ));
    }

    @Test
    public void shouldCompileOneInstruction() {
        String program = "-";

        List<Command> compiled = compiler.compile(program);
        assertThat(compiled, contains(commandMatcher(new Decrement(CommandType.DEC))));
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionOnEmptyProgram() {
        compiler.compile("");
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionOnNullObject() {
        compiler.compile(null);
    }

    @Test(expected = CommandNotSupport.class)
    public void shouldThrowExceptionOnIncorrectCommand() {
        compiler.compile("\t");
    }

    @Test
    public void shouldCompile() {
        String brainfuckProgram = "<>+-.[]";
        List<Command> actual = compiler.compile(brainfuckProgram);
        assertThat(actual, contains(
                commandMatcher(new CursorDecrement(CommandType.CURSOR_DECREMENT)),
                commandMatcher(new CursorIncrement(CommandType.CURSOR_INCREMENT)),
                commandMatcher(new Increment(CommandType.INC)),
                commandMatcher(new Decrement(CommandType.DEC)),
                commandMatcher(new PrintChar(CommandType.PRINT)),
                commandMatcher(new LoopBlockBegin(6, CommandType.BEGIN_CYCLE)),
                commandMatcher(new LoopBlockEnd(5, CommandType.END_CYCLE))
        ));
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionOnNotClosedLoop() {
        compiler.compile("[");
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionOnNotOpenLoop() {
        compiler.compile("]");
    }

    private Matcher<Command> commandMatcher(final Command expected) {
        return new TypeSafeMatcher<Command>() {
            @Override
            protected boolean matchesSafely(Command actual) {
                return actual.countRepeats == expected.countRepeats && expected.commandType == actual.commandType;
            }

            @Override
            public void describeTo(Description description) {
            }
        };
    }

}
