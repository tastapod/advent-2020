package day8

import java.lang.Integer.parseInt


data class ProgramState(val acc: Int, val pc: Int)

enum class ExitCondition { LOOP_DETECTED, END_OF_PROGRAM }
data class ExitState(val acc: Int, val exitCondition: ExitCondition)

enum class Operation {
    ACC {
        override fun execute(argument: Int, state: ProgramState) =
            state.copy(acc = state.acc + argument, pc = state.pc + 1)
    },

    JMP {
        override fun execute(argument: Int, state: ProgramState) = state.copy(pc = state.pc + argument)
    },

    NOP {
        override fun execute(argument: Int, state: ProgramState) = state.copy(pc = state.pc + 1)
    };

    abstract fun execute(argument: Int, state: ProgramState): ProgramState
}

data class Instruction(val operation: Operation, val argument: Int) {
    fun execute(state: ProgramState) = operation.execute(argument, state)
}

class Handheld(listing: List<String>) {

    private val program: List<Instruction> = listing.map {
        val (command, param) = it.split(" ")
        Instruction(Operation.valueOf(command.toUpperCase()), parseInt(param))
    }

    fun execute(): ExitState {
        tailrec fun executeNext(state: ProgramState, visited: Set<Int>): ExitState =
            when {
                state.pc >= program.size -> {
                    ExitState(state.acc, ExitCondition.END_OF_PROGRAM)
                }
                state.pc in visited -> {
                    ExitState(state.acc, ExitCondition.LOOP_DETECTED)
                }
                else -> {
                    executeNext(program[state.pc].execute(state), visited + state.pc)
                }
            }
        return executeNext(ProgramState(0, 0), emptySet())
    }

    fun mutateUntilExit(): ExitState {

        tailrec fun executeNext(
            program: List<Instruction>,
            state: ProgramState,
            visited: Set<Int>,
            nextMutation: Int
        ): ExitState =
            when {
                state.pc >= program.size -> {
                    ExitState(state.acc, ExitCondition.END_OF_PROGRAM)
                }
                state.pc in visited -> {
                    val original = program[nextMutation]
                    val mutated =
                        when (original.operation) {
                            Operation.JMP -> original.copy(operation = Operation.NOP)
                            Operation.NOP -> original.copy(operation = Operation.JMP)
                            else -> original
                        }
                    executeNext(
                        // try with next mutation
                        this.program.toMutableList().apply { set(nextMutation, mutated) }.toList(),
                        ProgramState(0, 0),
                        emptySet(),
                        nextMutation + 1
                    )
                }
                else -> {
                    executeNext(
                        program,
                        program[state.pc].execute(state),
                        visited + state.pc,
                        nextMutation
                    )
                }
            }
        return executeNext(program, ProgramState(0, 0), emptySet(), 0)
    }
}
