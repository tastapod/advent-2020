package day14

import java.lang.Long.parseLong

typealias MaskingFunction = (Long) -> Long

interface Emulator {
    val totalValues: Long
    fun updateMask(mask: String): Emulator
    fun setValue(address: Long, value: Long): Emulator
}

class MaskingEmulator(
    private val applyMask: MaskingFunction = { it },
    private val memory: Map<Long, Long> = emptyMap()
) : Emulator {
    override val totalValues = memory.values.sum()

    override fun updateMask(mask: String) =
        MaskingEmulator(maskingFunction(mask), memory)

    override fun setValue(address: Long, value: Long) =
        MaskingEmulator(applyMask, memory + Pair(address, applyMask(value)))
}

class FloatingEmulator(
    private val masks: Set<MaskingFunction> = emptySet(),
    private val memory: Map<Long, Long> = emptyMap()
) : Emulator {
    override val totalValues = memory.values.sum()

    override fun updateMask(mask: String) =
        FloatingEmulator(
            unrollFloatingMask(mask).map(::maskingFunction).toSet().also { println("${it.size} masks") },
            memory
        )

    override fun setValue(address: Long, value: Long) =
        FloatingEmulator(
            masks,
            masks.fold(memory) { acc, applyMask -> acc + Pair(applyMask(address), value) }
        )
}

fun maskingFunction(mask: String): MaskingFunction {
    val andMask = parseLong(mask.replace('X', '1'), 2)
    val orMask = parseLong(mask.replace('X', '0'), 2)
    return { x -> x and andMask or orMask }
}

fun runProgram(program: String, emulator: Emulator) =
    program.lines().foldIndexed(emulator) { i, acc, line ->
        when {
            line.startsWith("mask") -> {
                val (mask) = Regex("""mask = ([X01]{36})""").matchEntire(line)!!.destructured
                acc.updateMask(mask)
            }
            line.startsWith("mem") -> {
                val (address, value) = Regex("""mem\[(\d+)] = (\d+)""").matchEntire(line)!!.destructured
                acc.setValue(parseLong(address), parseLong(value))
            }
            else ->
                throw IllegalArgumentException("Unknown line $i: $line")
        }
    }

fun String.replaceCharAt(i: Int, ch: Char) =
    substring(0, i) + ch + substring(i + 1)

fun unrollFloatingMask(mask: String): Set<String> {

    tailrec fun unrollFloats(acc: Set<String>, remaining: List<String>): Set<String> =
        if (remaining.isEmpty()) acc
        else {
            val nextMask = remaining.first()
            val nextFloat = nextMask.indexOf('F')
            if (nextFloat == -1)
                unrollFloats(acc + nextMask, remaining.drop(1))
            else
                unrollFloats(
                    acc,
                    remaining.drop(1) + nextMask.replaceCharAt(nextFloat, '0') + nextMask.replaceCharAt(nextFloat, '1')
                )
        }
    return unrollFloats(emptySet(), listOf(mask.replace('X', 'F').replace('0', 'X')))
}
