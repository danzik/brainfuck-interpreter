package brainfuck.Interpreter.compiler;

import brainfuck.Interpreter.commands.Command;
import brainfuck.Interpreter.commands.brainfuck.CommandType;
import brainfuck.Interpreter.factory.CommandFactory;

import java.util.ArrayList;
import java.util.List;

public class BrainFuckCompiler implements Compiler {
    private CommandFactory brainfuckFactory;

    public BrainFuckCompiler(CommandFactory factory) {
        this.brainfuckFactory = factory;
    }

    public List<Command> compile(String program) {
        if (program == null || program.isEmpty()) throw new IllegalArgumentException("IAE");
        char[] commands = program.toCharArray();
        List<Command> result = compileAndGetCommands(commands);
        validateBrainfuckCycleCommands(result);
        return result;
    }

    private List<Command> compileAndGetCommands(char[] program) {
        List<Command> listCommands = new ArrayList<>();
        int instructionPointer = 0;

        while (instructionPointer < program.length) {
            int countCommands;
            char command = program[instructionPointer];

            countCommands = getCountRepeatedCommands(command, program, instructionPointer);

            Command commandCandidate = brainfuckFactory.getCommand(command);

            if (commandCandidate.commandType == CommandType.BEGIN_CYCLE || commandCandidate.commandType == CommandType.END_CYCLE) {
                commandCandidate.setCountRepeats(Integer.MIN_VALUE);
            } else {
                if (countCommands > 1) {
                    commandCandidate.setCountRepeats(countCommands);
                }
            }

            listCommands.add(commandCandidate);
            instructionPointer += countCommands;
        }

        return listCommands;
    }

    private void validateBrainfuckCycleCommands(List<Command> commands) {
        int counter = 0;
        while (counter < commands.size()) {

            Command command = commands.get(counter);

            if (command.commandType == CommandType.BEGIN_CYCLE && command.getCountRepeats() == Integer.MIN_VALUE) {
                int endPosition = findEndOfLoopPosition(counter, commands);
                command.setCountRepeats(endPosition);
                commands.set(counter, command);
            } else if (command.commandType == CommandType.END_CYCLE && command.getCountRepeats() == Integer.MIN_VALUE) {
                int startPosition = findStartOfLoopPosition(counter, commands);
                command.setCountRepeats(startPosition);
                commands.set(counter, command);
            }
            counter++;
        }
    }

    private int findEndOfLoopPosition(int currentPos, List<Command> operations) {
        int depth = 1;

        while (depth != 0) {
            currentPos++;

            if (currentPos == operations.size())
                throw new IllegalArgumentException("Invalid syntax, no ] found for start-loop");

            Command command = operations.get(currentPos);

            switch (command.commandType) {
                case BEGIN_CYCLE:
                    depth++;
                    break;
                case END_CYCLE:
                    depth--;
                    break;
            }
        }

        return currentPos;
    }

    private int findStartOfLoopPosition(int currentPos, List<Command> operations) {
        int depth = 1;

        while (depth != 0) {
            currentPos--;

            if (currentPos == -1)
                throw new IllegalArgumentException("Invalid syntax, no [ found for end-loop");

            switch (operations.get(currentPos).commandType) {
                case END_CYCLE:
                    depth++;
                    break;
                case BEGIN_CYCLE:
                    depth--;
                    break;
            }
        }

        return currentPos;
    }

    private int getCountRepeatedCommands(char symbol, char[] program, int currentPos) {
        int count = 1;

        if (currentPos == program.length - 1) return count;

        for (int i = currentPos + 1; i < program.length; i++) {
            if (program[i] == symbol)
                count++;
            else
                break;
        }

        return count;
    }
}
