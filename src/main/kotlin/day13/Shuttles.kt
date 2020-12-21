package day13

fun findNextShuttle(earliest: Int, shuttles: List<Int>) =
    shuttles.fold(emptyMap<Int, Int>()) { acc, shuttle ->
        val delay = shuttle - earliest % shuttle
        acc + Pair(delay, shuttle)
    }.minByOrNull { (delay, _) -> delay }!!.toPair()

fun parseInput(input: String): Pair<Int, List<Int>> {
    val lines = input.lines()
    return Pair(
        Integer.parseInt(lines[0]),
        lines[1].split(",").filterNot { it == "x" }.map(Integer::parseInt)
    )
}
