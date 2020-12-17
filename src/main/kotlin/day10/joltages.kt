package day10

fun adapterList(adapters: List<Int>) = adapters + 0 + (adapters.maxOrNull()!! + 3)
fun adapterList(vararg adapters: Int) = adapterList(adapters.asList())

fun <T> without(list: List<T>, pos: Int): List<T> =
    list.slice(0 until pos) + list.slice(pos + 1 until list.size)

fun countJoltageDeltas(input: List<Int>): Map<Int, Int> {
    return input.sorted().zipWithNext().fold(emptyMap()) { acc, it ->
        val delta = it.second - it.first
        require(delta < 4) { "Too much difference between ${it.first} and ${it.second}" }
        acc + Pair(delta, acc.getOrDefault(delta, 0) + 1)
    }
}

fun findBackLinks(input: List<Int>, maxDelta: Int): Map<Int, List<Int>> {
    tailrec fun findBackLinks(adapters: List<Int>, acc: Map<Int, List<Int>>): Map<Int, List<Int>> =
        if (adapters.size == 1) {
            acc
        } else {
            val head = adapters.first()
            val rest = adapters.drop(1)
            findBackLinks(rest, acc + Pair(head, rest.takeWhile { head - it <= maxDelta }))
        }
    return findBackLinks(input.sortedDescending(), emptyMap())
}

/**
 * Example:
 *  6-7-8-9-10
 * X-1-1-1-1-X
 *
 * Define paths-to-6 = 1, start at 7.
 *
 *     paths-to-7  =  6-7: paths-to-6 (1) [1]
 *
 *     paths-to-8  =  7-8: paths-to-7 (1) [2]
 *                 +  6-8: paths-to-6 (1)
 *
 *     paths-to-9  =  8-9: paths-to-8 (2) [4]
 *                 +  7-9: paths-to-7 (1)
 *                 +  6-9: paths-to-6 (1)
 *
 *     paths-to-10 = 9-10: paths-to-9 (4) [7]
 *                 + 8-10: paths-to-8 (2)
 *                 + 7-10: paths-to-7 (1)
 */
fun countValidSequences(
    input: List<Int>,
    maxDelta: Int = 3,
): Long {
    val ordered = input.sorted()
    val backLinks = findBackLinks(input, maxDelta)

    return ordered.drop(1).fold(mapOf(0 to 1L)) { acc, value ->
        acc + Pair(value, backLinks.getValue(value).sumOf { acc.getValue(it) })
    }.getValue(ordered.last())
}
