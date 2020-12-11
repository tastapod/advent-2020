package day9

fun findFirstInvalidXmasNumber(input: List<Long>, windowSize: Int) =
    input.drop(windowSize).zip(input.windowed(windowSize, 1)).find { (value, window) ->
        day1.findAddends(value, window, 2).isEmpty()
    }!!.first

fun findRangeForInvalidValue(input: List<Long>, target: Long): List<Long> {
    tailrec fun mooch(back: Int, front: Int, runningTotal: Long): List<Long> =
        when {
            front == input.size -> {
                emptyList()
            }
            runningTotal == target -> {
                input.slice(back until front)
            }
            runningTotal < target -> {
                mooch(back, front + 1, runningTotal + input[front])
            }
            else -> {
                mooch(back + 1, front, runningTotal - input[back])
            }
        }
    return mooch(0, 0, 0)
}
