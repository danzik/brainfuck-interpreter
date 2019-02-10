package brainfuck.Interpreter.context;

public interface InterpreterContext {

    byte[] getMemory();

    void moveTo(int to);

    int getDataPointer();

    int getInstructionPointer();

    void incrementInstructionPointer();

    void setDataPointer(int dataPointer);

    void decrementByIndex(int pointer, int value);

    void incrementByIndex(int pointer, int value);

    void print(char c);
}