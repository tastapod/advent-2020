package day10

fun withOutletAndDevice(input: List<Long>) = input + 0L + (input.maxOrNull()!! + 3L)

fun <T> without(list: List<T>, pos: Int): List<T> =
    list.slice(0 until pos) + list.slice(pos + 1 until list.size)

fun countJoltageDeltas(input: List<Long>): Map<Long, Int> {
    return withOutletAndDevice(input).sorted().zipWithNext().fold(emptyMap()) { acc, it ->
        val delta = it.second - it.first
        require(delta < 4) { "Too much difference between ${it.first} and ${it.second}" }
        acc + Pair(delta, acc.getOrDefault(delta, 0) + 1)
    }
}

fun findValidPredecessors(input: List<Long>, delta: Long): Map<Long, List<Long>> {
    tailrec fun findPredecessors(adapters: List<Long>, acc: Map<Long, List<Long>>): Map<Long, List<Long>> {
        val head = adapters.first()
        val rest = adapters.drop(1)
        return if (adapters.size == 1) {
            acc
        } else {
            findPredecessors(rest, acc + Pair(head, rest.takeWhile { head - it <= delta }))
        }
    }

    val adapters = withOutletAndDevice(input).sortedDescending()
    return findPredecessors(adapters, emptyMap())
}