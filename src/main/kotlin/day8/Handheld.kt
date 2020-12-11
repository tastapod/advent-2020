package day8

import java.lang.Integer.parseInt


data class ProgramState(val acc: Int, val pc: Int)

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

    private val program: List<Instruction> = listing.map{
        val (command, param) = it.split(" ")
        Instruction(Operation.valueOf(command.toUpperCase()), parseInt(param))
    }

    fun execute(): ProgramState {
        tailrec fun executeNext(state: ProgramState, visited: Set<Int>): ProgramState =
            if (state.pc in visited || state.pc >= program.size) {
                state
            } else {
                executeNext(program[state.pc].execute(state), visited + state.pc)
            }
        return executeNext(ProgramState(0, 0), emptySet())
    }
}
