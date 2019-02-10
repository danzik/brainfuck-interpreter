package brainfuck.Interpreter.context;

import java.io.PrintStream;

public class BrainFuckContext implements InterpreterContext {
    private int dataPointer;
    private final byte[] memory;
    private final PrintStream out;
    private int instructionPointer;

    public BrainFuckContext(PrintStream out, byte[] memory) {
        this.out = out;
        this.memory = memory;
        this.dataPointer = 0;
        this.instructionPointer = 0;
    }

    public byte[] getMemory() {
        return memory;
    }

    public int getDataPointer() {
        return dataPointer;
    }

    public void setDataPointer(int dataPointer) {
        this.dataPointer = dataPointer;
    }

    public void incrementByIndex(int pointer, int value) {
        memory[dataPointer] += value;
    }

    public void print(char c) {
        out.print(c);
    }

    public void decrementByIndex(int pointer, int value) {
        memory[pointer] -= value;
    }

    public int getInstructionPointer() {
        return instructionPointer;
    }

    public void incrementInstructionPointer() {
        this.instructionPointer++;
    }

    public void moveTo(int to) {
        instructionPointer = to;
    }
}
