package runner;

import brainfuck.Interpreter.BrainFuckRunner;
import brainfuck.Interpreter.commands.Command;
import brainfuck.Interpreter.compiler.BrainFuckCompiler;
import brainfuck.Interpreter.compiler.Compiler;
import brainfuck.Interpreter.context.BrainFuckContext;
import brainfuck.Interpreter.context.InterpreterContext;
import brainfuck.Interpreter.factory.BrainfuckCommandFactory;
import brainfuck.Interpreter.factory.CommandFactory;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.Mock;

import java.io.PrintStream;
import java.util.List;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.MockitoAnnotations.initMocks;

public class BrainfuckRunnerTest {

    @Mock
    private PrintStream printStream;
    private Compiler compiler;
    private InterpreterContext context;

    @Before
    public void startUp() {
        initMocks(this);
        CommandFactory factory = new BrainfuckCommandFactory();
        byte[] memory = new byte[30000];

        context = new BrainFuckContext(printStream, memory);
        compiler = new BrainFuckCompiler(factory);
    }

    @Test
    public void shouldPrintHelloWorld() {
        runProgram("++++++++[>++++[>++>+++>+++>+<<<<-]>+>+>->>+[<]<-]>>.>---.+++++++..+++.>>.<-.<.+++.------.--------.>>+.>++.");
        InOrder inOrder = inOrder(printStream);

        inOrder.verify(printStream, times(1)).print(eq('H'));
        inOrder.verify(printStream, times(1)).print(eq('e'));
        inOrder.verify(printStream, times(2)).print(eq('l'));
        inOrder.verify(printStream, times(1)).print(eq('o'));
        inOrder.verify(printStream, times(1)).print(eq(' '));
        inOrder.verify(printStream, times(1)).print(eq('W'));
        inOrder.verify(printStream, times(1)).print(eq('o'));
        inOrder.verify(printStream, times(1)).print(eq('r'));
        inOrder.verify(printStream, times(1)).print(eq('l'));
        inOrder.verify(printStream, times(1)).print(eq('d'));
        inOrder.verify(printStream, times(1)).print(eq('!'));
        inOrder.verify(printStream, times(1)).print(eq('\n'));

        verifyNoMoreInteractions(printStream);
    }

    private void runProgram(String input) {
        List<Command> commands = compiler.compile(input);
        BrainFuckRunner.run(commands, context);
    }
}
