package day10

fun adapterList(adapters: List<Long>) = adapters + 0L + (adapters.maxOrNull()!! + 3L)
fun adapterList(vararg adapters: Long) = adapterList(adapters.asList())

fun <T> without(list: List<T>, pos: Int): List<T> =
    list.slice(0 until pos) + list.slice(pos + 1 until list.size)

fun countJoltageDeltas(input: List<Long>): Map<Long, Int> {
    return input.sorted().zipWithNext().fold(emptyMap()) { acc, it ->
        val delta = it.second - it.first
        require(delta < 4) { "Too much difference between ${it.first} and ${it.second}" }
        acc + Pair(delta, acc.getOrDefault(delta, 0) + 1)
    }
}

fun findConnections(input: List<Long>, delta: Long): Map<Long, List<Long>> {
    tailrec fun findConnections(adapters: List<Long>, acc: Map<Long, List<Long>>): Map<Long, List<Long>> =
        if (adapters.size == 1) {
            acc
        } else {
            val head = adapters.first()
            val rest = adapters.drop(1)
            findConnections(rest, acc + Pair(head, rest.takeWhile { head - it <= delta }))
        }
    return findConnections(input.sortedDescending(), emptyMap())
}

fun countValidSequences(
    input: List<Long>,
    delta: Long = 3,
    target: Long = input.minOrNull()!!,
    start: Long = input.maxOrNull()!!
): Long {
    val connections = findConnections(input, delta).also { println("connections = $it") }

    fun countPathsBack(current: Long, acc: Long): Long =
        if (current == target) {
            acc
        } else {
            connections[current]!!.sumOf { countPathsBack(it, 1) }
        }
    return countPathsBack(start, 1)
}
