package day10

fun countJoltageDeltas(input: List<Long>): Map<Long, Int> {
    val withOutletAndDevice = input + 0L + (input.maxOrNull()!! + 3L)
    return withOutletAndDevice.sorted().zipWithNext().fold(emptyMap()) { acc, it ->
        val delta = it.second - it.first
        require(delta < 4) { "Too much difference between ${it.first} and ${it.second}" }
        acc + Pair(delta, acc.getOrDefault(delta, 0) + 1)
    }
}
