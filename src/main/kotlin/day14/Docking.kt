package day14

import java.lang.Long.parseLong

typealias MaskingFunction = (Long) -> Long

data class Machine(val applyMask: MaskingFunction, val memory: Map<Long, Long>) {
    fun totalValues() = memory.values.sum()
}

fun runProgram(program: String) =
    program.lines().foldIndexed(Machine({ x -> x }, emptyMap())) { i, acc, line ->
        when {
            line.startsWith("mask") -> {
                val (mask) = Regex("""mask = ([X01]{36})""").matchEntire(line)!!.destructured
                acc.copy(applyMask = maskingFunction(mask))
            }
            line.startsWith("mem") -> {
                val (address, value) = Regex("""mem\[(\d+)] = (\d+)""").matchEntire(line)!!.destructured
                acc.copy(
                    memory = acc.memory + Pair(parseLong(address), acc.applyMask(parseLong(value)))
                )
            }
            else ->
                throw IllegalArgumentException("Unknown line $i: $line")
        }
    }

fun maskingFunction(mask: String): MaskingFunction {
    val andMask = parseLong(mask.replace('X', '1'), 2)
    val orMask = parseLong(mask.replace('X', '0'), 2)
    return { x -> x and andMask or orMask }
}
